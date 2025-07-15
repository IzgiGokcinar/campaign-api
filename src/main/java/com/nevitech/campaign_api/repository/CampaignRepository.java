package com.nevitech.campaign_api.repository;

import com.nevitech.campaign_api.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    boolean existsByTitleAndDescriptionAndCategory(String title, String description, String category);

    @Query("SELECT c.status, COUNT(c) FROM Campaign c GROUP BY c.status")
    List<Object[]> countCampaignsByStatus();

}
