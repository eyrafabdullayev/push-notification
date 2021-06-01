package az.eyrafabdullayev.example.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PushNotificationSpecificRequestDto {

    private String cif;
    private String udid;
    private String title;
    private String text;
}
