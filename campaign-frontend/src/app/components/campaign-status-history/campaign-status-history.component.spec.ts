import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampaignStatusHistoryComponent } from './campaign-status-history.component';

describe('CampaignStatusHistoryComponent', () => {
  let component: CampaignStatusHistoryComponent;
  let fixture: ComponentFixture<CampaignStatusHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CampaignStatusHistoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CampaignStatusHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
