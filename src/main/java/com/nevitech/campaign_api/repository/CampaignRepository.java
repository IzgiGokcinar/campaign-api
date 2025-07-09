package com.nevitech.campaign_api.repository;

import com.nevitech.campaign_api.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    boolean existsByTitleAndDescriptionAndCategory(String title, String description, String category);
}
