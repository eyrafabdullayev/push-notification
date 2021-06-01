package az.eyrafabdullayev.example.mapper;

import az.eyrafabdullayev.example.dto.PushNotificationDto;
import az.eyrafabdullayev.example.entity.PushNotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PushNotificationMapper {

    PushNotificationMapper INSTANCE = Mappers.getMapper(PushNotificationMapper.class);

    PushNotificationEntity toEntity(PushNotificationDto dto);
    PushNotificationDto toDto(PushNotificationEntity entity);
}
