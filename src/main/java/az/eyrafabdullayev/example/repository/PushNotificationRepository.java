package az.eyrafabdullayev.example.repository;

import az.eyrafabdullayev.example.entity.PushNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PushNotificationRepository extends JpaRepository<PushNotificationEntity, Long> {

    Optional<List<PushNotificationEntity>> findAllByCifOrderByCreatedAtDesc(String cif);
    Optional<PushNotificationEntity> findFirstByCifAndUdid(String cif, String udid);
}
