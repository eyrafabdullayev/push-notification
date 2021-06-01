package az.eyrafabdullayev.example.service;

import az.eyrafabdullayev.example.dto.PushNotificationDto;
import az.eyrafabdullayev.example.controller.request.PushNotificationMultipleRequestDto;
import az.eyrafabdullayev.example.controller.request.PushNotificationSpecificRequestDto;
import az.eyrafabdullayev.example.exception.NotFoundException;
import az.eyrafabdullayev.example.mapper.PushNotificationMapper;
import az.eyrafabdullayev.example.repository.PushNotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Transactional
public class PushNotificationService {

    private final PushNotificationRepository pushNotificationRepository;
    private final FCMService fcmService;

    public PushNotificationService(PushNotificationRepository pushNotificationRepository,
                                   FCMService fcmService) {
        this.pushNotificationRepository = pushNotificationRepository;
        this.fcmService = fcmService;
    }

    public void insertOrUpdate(PushNotificationDto pushNotification) {
        pushNotification.setId(pushNotificationRepository.save(PushNotificationMapper.INSTANCE.toEntity(pushNotification)).getId());
    }

    public List<PushNotificationDto> getAllByCif(String cif) {
        return pushNotificationRepository.findAllByCifOrderByCreatedAtDesc(cif)
                .orElseThrow(NotFoundException::new)
                .stream()
                .map(PushNotificationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public PushNotificationDto getByCifAndUdid(String cif, String udid) {
        return PushNotificationMapper.INSTANCE.toDto(
                pushNotificationRepository.findFirstByCifAndUdid(cif, udid)
                        .orElseThrow(NotFoundException::new)
        );
    }

    public void sendPushNotificationToSpecificDevice(PushNotificationSpecificRequestDto request) throws ExecutionException, InterruptedException {
        fcmService.sendMessageToToken(request,
                getByCifAndUdid(request.getCif(), request.getUdid()).getToken()
        );
    }

    public void sendPushNotificationToMultipleDevices(PushNotificationMultipleRequestDto request) {
        fcmService.sendMessageToMultipleTokens(request,
                getAllByCif(request.getCif()).stream()
                        .map(PushNotificationDto::getToken)
                        .collect(Collectors.toList())
        );
    }
}
