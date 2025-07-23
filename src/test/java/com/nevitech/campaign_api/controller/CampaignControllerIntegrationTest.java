package com.nevitech.campaign_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevitech.campaign_api.model.Campaign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class CampaignControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCampaignSuccessfully() throws Exception {
        Campaign campaign = new Campaign();
        campaign.setTitle("Test Kampanya 123");
        campaign.setDescription("Bu kampanya test amaçlıdır. En az 20 karakter");
        campaign.setCategory("TSS");

        mockMvc.perform(post("/api/campaigns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campaign)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateCampaignStatus() throws Exception {

        Campaign campaign = new Campaign();
        campaign.setTitle("Durum Güncelleme Testi");
        campaign.setDescription("Bu kampanya test amaçlıdır. En az 20 karakter");
        campaign.setCategory("ÖSS");

        String response = mockMvc.perform(post("/api/campaigns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campaign)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Campaign created = objectMapper.readValue(response, Campaign.class);


        mockMvc.perform(put("/api/campaigns/" + created.getId() + "/status")
                        .param("status", "Deaktif"))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnStatistics() throws Exception {
        mockMvc.perform(get("/api/campaigns/dashboard/classifieds/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Onay Bekliyor']").exists());
    }
}