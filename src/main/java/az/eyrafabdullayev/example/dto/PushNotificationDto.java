package az.eyrafabdullayev.example.dto;

import java.time.LocalDateTime;

public class PushNotificationDto {

    private Long id;
    private String cif;
    private String udid;
    private String token;
    private String platform;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public PushNotificationDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCif() {
        return cif;
    }

    public PushNotificationDto setCif(String cif) {
        this.cif = cif;
        return this;
    }

    public String getUdid() {
        return udid;
    }

    public PushNotificationDto setUdid(String udid) {
        this.udid = udid;
        return this;
    }

    public String getToken() {
        return token;
    }

    public PushNotificationDto setToken(String token) {
        this.token = token;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public PushNotificationDto setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PushNotificationDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public PushNotificationDto setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
