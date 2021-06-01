package az.eyrafabdullayev.example.controller;

import az.eyrafabdullayev.example.controller.response.BusinessResult;
import az.eyrafabdullayev.example.controller.response.BusinessStatus;
import az.eyrafabdullayev.example.controller.response.CommonResponse;
import az.eyrafabdullayev.example.dto.PushNotificationDto;
import az.eyrafabdullayev.example.controller.request.PushNotificationAddRequestDto;
import az.eyrafabdullayev.example.controller.request.PushNotificationMultipleRequestDto;
import az.eyrafabdullayev.example.controller.request.PushNotificationSpecificRequestDto;
import az.eyrafabdullayev.example.service.PushNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("notification")
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Object>> addToken(
            @RequestBody PushNotificationAddRequestDto notificationAddRequest,
            @RequestParam(required = false) String lang
    ) {
        pushNotificationService.insertOrUpdate(new PushNotificationDto().setCif(notificationAddRequest.getCif())
                .setToken(notificationAddRequest.getToken())
                .setPlatform(notificationAddRequest.getPlatform())
                .setUdid(notificationAddRequest.getUdid())
        );
        return CommonResponse.instance(BusinessResult.of(BusinessStatus.SUCCESS), lang);
    }

    @PostMapping("/push/specific-device")
    public ResponseEntity<CommonResponse<Object>> pushNotification(
            @RequestBody PushNotificationSpecificRequestDto request,
            @RequestParam(required = false) String lang
    ) throws ExecutionException, InterruptedException {
        pushNotificationService.sendPushNotificationToSpecificDevice(request);
        return CommonResponse.instance(BusinessResult.of(BusinessStatus.SUCCESS), lang);
    }

    @PostMapping("/push/multiple-devices")
    public ResponseEntity<CommonResponse<Object>> pushNotificationToMultipleDevices(
            @RequestBody PushNotificationMultipleRequestDto request,
            @RequestParam(required = false) String lang
    ) {
        pushNotificationService.sendPushNotificationToMultipleDevices(request);
        return CommonResponse.instance(BusinessResult.of(BusinessStatus.SUCCESS), lang);
    }
}
