# Sigorta Kampanya Portalı

Bu proje, sigorta şirketlerinin kampanyalarını yönetebileceği ve son kullanıcıların sadece aktif kampanyaları görebileceği bir **kampanya yönetim sistemidir**. Uygulama iki ana bölümden oluşur:

- **Backend:** Spring Boot ile geliştirilmiş RESTful API.
- **Frontend:** Angular ile geliştirilmiş kullanıcı ve yönetici arayüzü.

# Proje Özellikleri

### 👤 Yönetici Paneli
- Yeni kampanya ekleme (başlık, açıklama, kategori, durum).
- Kampanya durumunu değiştirme (Aktif, Pasif, Onay Bekliyor).
- Kampanyayı silme.
- Kampanya durumu değiştiğinde "Kaydet" butonu ile veri güncelleme.
- Aynı kampanyadan tekrar eklenmesi durumunda “Mükerrer” olarak işaretleme.
- Kampanya durum geçmişini takip etme (backend'de hazır, frontend'e entegre edilebilir).

### 👥 Kullanıcı Paneli
- Sadece **Aktif** durumdaki kampanyaları görür.
- Kampanyalar şık kart yapıları ile 2 sütunlu grid olarak listelenir.
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


## POSTMAN 

- Projenin backendinde yer alan komutlar için Postman kullanıldı.
- Postman Testleri ile proje test edildi.
- Postman dökümantasyon linki: https://izgigokcinar-2380569.postman.co/workspace/Izgi-Gokcinar's-Workspace~6e061698-ad25-43a8-861b-c9161687ba8b/collection/47009204-95eb8d4a-36bc-4d96-98c1-368421230525?action=share&creator=47009204
