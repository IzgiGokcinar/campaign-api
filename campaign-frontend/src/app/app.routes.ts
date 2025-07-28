import { Routes } from '@angular/router';
import { CampaignListComponent } from './components/campaign-list/campaign-list.component';
import { CampaignAddComponent } from './components/campaign-add/campaign-add.component';
import { LoginComponent } from './pages/login/login.component';
import { AdminPanelComponent } from './pages/admin/admin.component';

export const routes: Routes = [
  { path: '', component: CampaignListComponent },
  { path: 'add', component: CampaignAddComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminPanelComponent },
  { path: '**', redirectTo: '' }
];






