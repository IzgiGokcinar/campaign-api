export type CampaignStatus = "Aktif" | "Pasif" | "Mükerrer";

export interface Campaign {
  id: number;
  title: string;
  description: string;
  category: string;
  status: CampaignStatus;
  createdAt: string;
}
