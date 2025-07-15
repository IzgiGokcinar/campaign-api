package com.nevitech.campaign_api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Başlık boş olamaz")
    @Size(min = 10, max = 50, message = "Başlık 10-50 karakter arasında olmalı")
    @Pattern(regexp = "^[\\p{L}\\p{N}].*", message = "Başlık harf veya rakam ile başlamalı")
    @Column(nullable = false, length = 50)
    private String title;

    @NotBlank(message = "Açıklama boş olamaz")
    @Size(min = 20, max = 200, message = "Açıklama 20-200 karakter arasında olmalı")
    @Column(nullable = false, length = 200)
    private String description;

    @NotBlank(message = "Kategori boş olamaz")
    @Pattern(
            regexp = "^(Tamamlayıcı Sağlık Sigortası|TSS|Özel Sağlık Sigortası|ÖSS|Hayat Sigortası|Diğer)$",
            message = "Geçerli bir kategori giriniz"
    )
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
