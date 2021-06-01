package az.eyrafabdullayev.example.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class PushNotificationMultipleRequestDto implements Serializable {

    private String cif;
    private String title;
    private String text;
}
