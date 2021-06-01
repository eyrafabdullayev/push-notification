package az.eyrafabdullayev.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

    private String firebaseConfigPath;

    public String getFirebaseConfigPath() {
        return firebaseConfigPath;
    }

    @Value("${api.firebase-configuration-file}")
    public ApplicationProperties setFirebaseConfigPath(String firebaseConfigPath) {
        this.firebaseConfigPath = firebaseConfigPath;
        return this;
    }
}
