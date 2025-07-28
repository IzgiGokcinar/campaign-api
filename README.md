# Sigorta Kampanya Portalı

Bu proje, sigorta şirketlerinin kampanyalarını yönetebileceği ve son kullanıcıların sadece aktif kampanyaları görebileceği bir **kampanya yönetim sistemidir**. Uygulama iki ana bölümden oluşur:

- **Backend:** Spring Boot ile geliştirilmiş RESTful API.
- **Frontend:** Angular ile geliştirilmiş kullanıcı ve yönetici arayüzü.

# Proje Özellikleri

### 👤 Yönetici Paneli

<img width="1846" height="844" alt="Ekran görüntüsü 2025-07-28 175755" src="https://github.com/user-attachments/assets/ed3c084a-8c59-40f7-ab36-cb6e7f0931ff" />

- Yeni kampanya ekleme (başlık, açıklama, kategori, durum).

  <img width="1872" height="499" alt="Ekran görüntüsü 2025-07-28 175807" src="https://github.com/user-attachments/assets/f4c9a30b-ae54-4ee0-884a-2477f1361813" />


- Kampanya durumunu değiştirme (Aktif, Pasif, Onay Bekliyor).

  <img width="778" height="349" alt="Ekran görüntüsü 2025-07-28 175838" src="https://github.com/user-attachments/assets/4dd0141d-b870-4ecd-b1aa-b3ec84919708" />


- Kampanyayı silme.
- Kampanya durumu değiştiğinde "Kaydet" butonu ile veri güncelleme.

 <img width="1863" height="707" alt="Ekran görüntüsü 2025-07-28 175821" src="https://github.com/user-attachments/assets/fa75d410-1418-4e4d-b145-10851ee25b61" />


- Aynı kampanyadan tekrar eklenmesi durumunda “Mükerrer” olarak işaretleme.
- Kampanya durum geçmişini takip etme (backend'de hazır, frontend'e entegre edilebilir).

### 👥 Kullanıcı Paneli
- Sadece **Aktif** durumdaki kampanyaları görür.

  <img width="1890" height="848" alt="Ekran görüntüsü 2025-07-28 175934" src="https://github.com/user-attachments/assets/45ee8207-d345-4d65-ad36-f9cc61259359" />


- Kampanyalar şık kart yapıları ile sütunlu grid olarak listelenir.

  <img width="581" height="231" alt="image" src="https://github.com/user-attachments/assets/daa918d0-1349-414f-be2c-01ea65d00e04" />


- Her kampanya: başlık, açıklama ve kategori içerir.
- Kampanya durumu yönetici tarafından "Aktif" yapıldığında kullanıcı arayüzüne otomatik yansır.

## Kullanılan Teknolojiler

### Backend
- **Java 11+**
- **Spring Boot 2.7.18**
- Maven
- H2 Database (test ve geliştirme amaçlı)
- JPA/Hibernate
- RESTful API
- CORS yapılandırması
- Durum geçmişi takibi (CampaignStatusHistory)

### Frontend
- **Angular**
- TypeScript
- Standalone Components
- NgModel ile iki yönlü veri bağlama
- Angular HTTPClient ile REST API bağlantısı
- Responsive ve kullanıcı dostu tasarım

##  Kurulum ve Çalıştırma

### Backend (Spring Boot)

1. Proje dizinine git:  cd campaign-api
2. Projeyi başlat: mvn spring-boot:run
3. Uygulama varsayılan olarak http://localhost:9090 portunda çalışacak.

### Frontend (Angular)
1. Proje dizinine git: cd campaign-frontend
2. Proje bağımlılıklarını yükle: npm install
3. Projeyi başlat: npm start/ ng serve
4. Uygulama varsayılan olarak http://localhost:4200 portunda çalışacak.


## Postman

- Projenin backendinde yer alan komutlar için Postman kullanıldı.
- Postman Testleri ile proje test edildi.
- Postman dökümantasyon linki: https://izgigokcinar-2380569.postman.co/workspace/Izgi-Gokcinar's-Workspace~6e061698-ad25-43a8-861b-c9161687ba8b/collection/47009204-95eb8d4a-36bc-4d96-98c1-368421230525?action=share&creator=47009204
