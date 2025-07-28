import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CampaignService } from '../../services/campaign.service';
import { Campaign, CampaignStatus } from '../../shared/models/campaign.model';

@Component({
  selector: 'app-admin-panel',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminPanelComponent implements OnInit {
  campaigns: Campaign[] = [];

  newCampaign: Partial<Campaign> & { status: CampaignStatus } = {
    title: '',
    description: '',
    category: 'TSS',
    status: 'Aktif'
  };

  constructor(private campaignService: CampaignService) {}

  ngOnInit(): void {
    this.loadCampaigns();
  }

  loadCampaigns(): void {
    this.campaignService.getAllCampaigns().subscribe(data => {
      this.campaigns = data;
    });
  }

  addCampaign(): void {
    const campaignToCreate: Campaign = {
      ...(this.newCampaign as Campaign),
      id:0,
      status: 'Aktif',
      createdAt: new Date().toISOString(),
    }

    this.campaignService.createCampaign(campaignToCreate).subscribe(() => {
      this.loadCampaigns();
      this.newCampaign = {
        title: '',
        description: '',
        category: 'TSS',
        status: "Aktif"
      };
    });
  }

  changeStatus(campaign: Campaign, status: CampaignStatus): void {
    const updated = { ...campaign, status };

    this.campaignService.updateCampaign(updated).subscribe(() => {
      this.loadCampaigns();
    });
  }

  deleteCampaign(id: number): void {
    if (confirm('Bu kampanyayı silmek istediğinize emin misiniz?')) {
      this.campaignService.deleteCampaign(id).subscribe(() => {
        this.loadCampaigns();
      });
    }
  }

  saveCampaign(campaign: Campaign): void {
    this.campaignService.updateCampaign(campaign).subscribe(() => {
      this.loadCampaigns();
    });
  }

}
