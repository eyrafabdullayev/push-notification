package az.eyrafabdullayev.example.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MOBILE_NOTIFICATION")
public class PushNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cif;
    private String udid;
    private String token;
    private String platform;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public PushNotificationEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCif() {
        return cif;
    }

    public PushNotificationEntity setCif(String cif) {
        this.cif = cif;
        return this;
    }

    public String getUdid() {
        return udid;
    }

    public PushNotificationEntity setUdid(String udid) {
        this.udid = udid;
        return this;
    }

    public String getToken() {
        return token;
    }

    public PushNotificationEntity setToken(String token) {
        this.token = token;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public PushNotificationEntity setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PushNotificationEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public PushNotificationEntity setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
