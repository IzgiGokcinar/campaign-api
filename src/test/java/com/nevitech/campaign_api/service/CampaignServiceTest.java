package com.nevitech.campaign_api.service;

import com.nevitech.campaign_api.model.Campaign;
import com.nevitech.campaign_api.repository.CampaignRepository;
import com.nevitech.campaign_api.repository.CampaignStatusHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private CampaignStatusHistoryRepository historyRepository;

    @InjectMocks
    private CampaignService campaignService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreateCampaignTests {

        @Test
        void shouldSetStatusOnayBekliyor_WhenNotDuplicate() {
            Campaign campaign = new Campaign();
            campaign.setTitle("Başlık 1");
            campaign.setDescription("Uzun açıklama burada.");
            campaign.setCategory("TSS");

            when(campaignRepository.existsByTitleAndDescriptionAndCategory(
                    campaign.getTitle(), campaign.getDescription(), campaign.getCategory()
            )).thenReturn(false);

            when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

            Campaign result = campaignService.createCampaign(campaign);

            assertEquals("Onay Bekliyor", result.getStatus());
            verify(campaignRepository).save(campaign);
        }

        @Test
        void shouldSetStatusMükerrer_WhenDuplicate() {
            Campaign campaign = new Campaign();
            campaign.setTitle("Başlık");
            campaign.setDescription("Açıklama metni.");
            campaign.setCategory("ÖSS");

            when(campaignRepository.existsByTitleAndDescriptionAndCategory(
                    campaign.getTitle(), campaign.getDescription(), campaign.getCategory()
            )).thenReturn(true);

            when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

            Campaign result = campaignService.createCampaign(campaign);

            assertEquals("Mükerrer", result.getStatus());
            verify(campaignRepository).save(campaign);
        }

        @Test
        void shouldSetStatusAktif_WhenHayatSigortasiCategory() {
            Campaign campaign = new Campaign();
            campaign.setTitle("Hayat Kampanyası");
            campaign.setDescription("Hayat sigortası açıklaması.");
            campaign.setCategory("Hayat Sigortası");

            when(campaignRepository.existsByTitleAndDescriptionAndCategory(
                    campaign.getTitle(), campaign.getDescription(), campaign.getCategory()
            )).thenReturn(false);

            when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

            Campaign result = campaignService.createCampaign(campaign);

            assertEquals("Aktif", result.getStatus());
            verify(campaignRepository).save(campaign);
        }
    }

    @Nested
    class UpdateCampaignStatusTests {

        @Test
        void shouldUpdateStatus_WhenValidTransition() {
            Campaign campaign = new Campaign();
            campaign.setId(1L);
            campaign.setStatus("Onay Bekliyor");

            when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
            when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

            Campaign updated = campaignService.updateCampaignStatus(1L, "Aktif");

            assertEquals("Aktif", updated.getStatus());
            verify(campaignRepository).save(campaign);
        }

        @Test
        void shouldThrowRuntimeException_WhenCampaignNotFound() {
            when(campaignRepository.findById(999L)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class, () ->
                    campaignService.updateCampaignStatus(999L, "Aktif"));

            assertEquals("Kampanya bulunamadı", exception.getMessage());
        }

        @Test
        void shouldThrowIllegalStateException_WhenTransitionIsInvalid() {
            Campaign campaign = new Campaign();
            campaign.setId(2L);
            campaign.setStatus("Mükerrer");

            when(campaignRepository.findById(2L)).thenReturn(Optional.of(campaign));

            IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                    campaignService.updateCampaignStatus(2L, "Aktif"));

            assertEquals("Mükerrer kampanyanın durumu güncellenemez", exception.getMessage());
        }
    }
}
