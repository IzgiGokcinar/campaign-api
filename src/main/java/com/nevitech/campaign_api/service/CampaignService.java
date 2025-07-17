package com.nevitech.campaign_api.service;

import com.nevitech.campaign_api.model.Campaign;
import com.nevitech.campaign_api.model.CampaignStatusHistory;
import com.nevitech.campaign_api.repository.CampaignRepository;
import com.nevitech.campaign_api.repository.CampaignStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignStatusHistoryRepository historyRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository,
                           CampaignStatusHistoryRepository historyRepository) {
        this.campaignRepository = campaignRepository;
        this.historyRepository = historyRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id: " + id));
    }

    public Campaign createCampaign(Campaign campaign) {
        boolean exists = campaignRepository.existsByTitleAndDescriptionAndCategory(
                campaign.getTitle(),
                campaign.getDescription(),
                campaign.getCategory()
        );

        if (exists) {
            campaign.setStatus("Mükerrer");
        } else {
            if ("Hayat Sigortası".equalsIgnoreCase(campaign.getCategory())) {
                campaign.setStatus("Aktif");
            } else {
                campaign.setStatus("Onay Bekliyor");
            }
        }

        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaignStatus(Long id, String newStatus) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kampanya bulunamadı"));

        if (campaign.getStatus().equalsIgnoreCase("Mükerrer")) {
            throw new IllegalStateException("Mükerrer kampanyanın durumu güncellenemez");
        }

        if (!List.of("Aktif", "Pasif", "Deaktif", "Onay Bekliyor", "Mükerrer").contains(newStatus)) {
            throw new IllegalArgumentException("Geçersiz durum: " + newStatus);
        }

        String oldStatus = campaign.getStatus();
        campaign.setStatus(newStatus);
        Campaign updated = campaignRepository.save(campaign);

        // Durum geçmişine kayıt
        CampaignStatusHistory history = new CampaignStatusHistory();
        history.setCampaignId(campaign.getId());
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedAt(LocalDateTime.now());
        historyRepository.save(history);

        return updated;
    }

    public List<CampaignStatusHistory> getStatusHistoryByCampaignId(Long campaignId) {
        return historyRepository.findByCampaignIdOrderByChangedAtAsc(campaignId);
    }

    public void deleteCampaign(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id: " + id));
        campaignRepository.delete(campaign);
    }

    public Map<String, Long> getCampaignStatusStatistics() {
        return campaignRepository.findAll().stream()
                .collect(Collectors.groupingBy(Campaign::getStatus, Collectors.counting()));
    }

    public Map<String, Long> getCampaignStatistics() {
        List<Object[]> results = campaignRepository.countCampaignsByStatus();
        Map<String, Long> stats = new HashMap<>();
        for (Object[] result : results) {
            String status = (String) result[0];
            Long count = (Long) result[1];
            stats.put(status, count);
        }
        return stats;
    }
}
