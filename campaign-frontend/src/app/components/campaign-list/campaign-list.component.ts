import { Component, OnInit } from '@angular/core';
import { CampaignService } from '../../services/campaign.service';
import { Campaign } from '../../shared/models/campaign.model';
import { NgIf, NgFor } from '@angular/common';
import {interval, Subscription} from 'rxjs';

@Component({
  selector: 'app-campaign-list',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './campaign-list.component.html',
  styleUrls: ['./campaign-list.component.scss']
})
export class CampaignListComponent implements OnInit {
  campaigns: Campaign[] = [];
  refreshSub!: Subscription;

  constructor(private campaignService: CampaignService) {}

  ngOnInit(): void {
    this.loadActiveCampaigns();

    this.refreshSub = interval(5000).subscribe(() => {
      this.loadActiveCampaigns();
    });
  }

  ngOnDestroy(): void {
    this.refreshSub.unsubscribe();
  }

  loadActiveCampaigns() {
    this.campaignService.getAllCampaigns().subscribe(data => {
      this.campaigns = data.filter(c => c.status === 'Aktif');
    });
  }
}
