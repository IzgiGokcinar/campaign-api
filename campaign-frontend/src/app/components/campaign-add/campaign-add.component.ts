import { Component } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CampaignService } from '../../services/campaign.service';
import { Campaign } from '../../shared/models/campaign.model';

@Component({
  selector: 'app-campaign-add',
  standalone: true,
  imports: [NgIf, NgFor, FormsModule],
  templateUrl: './campaign-add.component.html',
  styleUrls: ['./campaign-add.component.scss']
})
export class CampaignAddComponent {
  newCampaign: Campaign = {
    id: 0,
    title: '',
    description: '',
    category: '',
    status: 'Aktif',
    createdAt: new Date().toISOString()
  };

  constructor(private campaignService: CampaignService) {}

  addCampaign(): void {
    console.log('Form gönderildi:', this.newCampaign);
    this.campaignService.createCampaign(this.newCampaign).subscribe({
      next: () => {
        alert('Kampanya başarıyla eklendi!');
        this.newCampaign = {
          id: 0,
          title: '',
          description: '',
          category: '',
          status: 'Aktif',
          createdAt: new Date().toISOString()
        };
      },
      error: (err) => {
        console.error('API Hatası:', err);
        alert('Kampanya eklenirken bir hata oluştu!');
      }
    });
  }
}

export class CampaignListComponent {
}
