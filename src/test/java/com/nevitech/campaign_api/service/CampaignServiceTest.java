package com.nevitech.campaign_api.service;

import com.nevitech.campaign_api.model.Campaign;
import com.nevitech.campaign_api.repository.CampaignRepository;
import com.nevitech.campaign_api.repository.CampaignStatusHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
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

    @Test
    void testCreateCampaign_WhenNotDuplicate_ShouldBeOnayBekliyor() {
        Campaign campaign = new Campaign();
        campaign.setTitle("Test Başlık");
        campaign.setDescription("Bu açıklama yeterli uzunlukta.");
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
    void testCreateCampaign_WhenDuplicate_ShouldBeMarkedAsMükerrer() {

        Campaign campaign = new Campaign();
        campaign.setTitle("Başlık");
        campaign.setDescription("Detaylı açıklama metni burada.");
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
    void testCreateCampaign_WhenCategoryIsHayatSigortası_ShouldBeAktif() {

        Campaign campaign = new Campaign();
        campaign.setTitle("Hayat Sigortası Kampanyası");
        campaign.setDescription("Hayat sigortası ile geleceğinizi güvence altına alın.");
        campaign.setCategory("Hayat Sigortası");

        when(campaignRepository.existsByTitleAndDescriptionAndCategory(
                campaign.getTitle(), campaign.getDescription(), campaign.getCategory()
        )).thenReturn(false);

        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign result = campaignService.createCampaign(campaign);

        assertEquals("Aktif", result.getStatus());
        verify(campaignRepository).save(campaign);
    }

    @Test
    void testUpdateCampaignStatus_WhenValidTransitionFromOnayBekliyorToAktif() {

        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setStatus("Onay Bekliyor");

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign updated = campaignService.updateCampaignStatus(1L, "Aktif");

        assertEquals("Aktif", updated.getStatus());
        verify(campaignRepository).save(campaign);
    }

}
