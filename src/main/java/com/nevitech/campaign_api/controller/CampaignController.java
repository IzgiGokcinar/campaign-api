package com.nevitech.campaign_api.controller;

import com.nevitech.campaign_api.model.Campaign;
import com.nevitech.campaign_api.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nevitech.campaign_api.model.CampaignStatusHistory;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) {
        return campaignService.getCampaignById(id);
    }

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody @Valid Campaign campaign) {
        Campaign saved = campaignService.createCampaign(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        Campaign updatedCampaign = campaignService.updateCampaignStatus(id, status);
        return ResponseEntity.ok(updatedCampaign);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
    }

    @GetMapping("/statistics")
    public Map<String, Long> getCampaignStatistics() {
        return campaignService.getCampaignStatusStatistics();
    }

    @GetMapping("/dashboard/classifieds/statistics")
    public Map<String, Long> getStatistics() {
        return campaignService.getCampaignStatistics();
    }

    @GetMapping("/{id}/status-history")
    public List<CampaignStatusHistory> getCampaignStatusHistory(@PathVariable Long id) {
        return campaignService.getStatusHistoryByCampaignId(id);
    }

}
