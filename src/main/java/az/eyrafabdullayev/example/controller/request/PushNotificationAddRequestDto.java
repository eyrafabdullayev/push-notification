package az.eyrafabdullayev.example.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PushNotificationAddRequestDto implements Serializable {

    private String cif;
    private String token;
    private String platform;
    private String udid;
}
