package az.eyrafabdullayev.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(ApplicationConfiguration.class);
    }
}
