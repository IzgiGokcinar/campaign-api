package com.nevitech.campaign_api.repository;

import com.nevitech.campaign_api.model.CampaignStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignStatusHistoryRepository extends JpaRepository<CampaignStatusHistory, Long> {

    List<CampaignStatusHistory> findByCampaignIdOrderByChangedAtAsc(Long campaignId);
}
