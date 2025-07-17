package com.nevitech.campaign_api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CampaignStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignId;

    private String oldStatus;

    private String newStatus;

    private LocalDateTime changedAt;

    public CampaignStatusHistory() {}

    public CampaignStatusHistory(Long campaignId, String oldStatus, String newStatus, LocalDateTime changedAt) {
        this.campaignId = campaignId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedAt = changedAt;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}
