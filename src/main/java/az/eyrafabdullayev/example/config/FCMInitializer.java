package az.eyrafabdullayev.example.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class FCMInitializer {

    private final ApplicationConfiguration applicationConfiguration;
    private final ApplicationProperties applicationProperties;

    public FCMInitializer(ApplicationConfiguration applicationConfiguration,
                          ApplicationProperties applicationProperties) {
        this.applicationConfiguration = applicationConfiguration;
        this.applicationProperties = applicationProperties;
    }

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(applicationProperties.getFirebaseConfigPath()).getInputStream()))
                    .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                applicationConfiguration.logger().info("FIRE_BASE_APPLICATION_HAS_BEEN_INITIALIZED");
            }
        } catch (IOException ex) {
            applicationConfiguration.logger().info(ex.getMessage());
        }
    }
}
