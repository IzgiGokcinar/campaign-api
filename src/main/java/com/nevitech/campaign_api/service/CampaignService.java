package com.nevitech.campaign_api.service;

import com.nevitech.campaign_api.model.Campaign;
import com.nevitech.campaign_api.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
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

    public Campaign updateStatus(Long id, String status) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Kampanya bulunamadı: " + id)
        );

        if (!"Mükerrer".equalsIgnoreCase(campaign.getStatus())) {
            campaign.setStatus(status);
            return campaignRepository.save(campaign);
        }

        return campaign;
    }
}
