package az.eyrafabdullayev.example.service;

import az.eyrafabdullayev.example.config.ApplicationConfiguration;
import az.eyrafabdullayev.example.controller.request.PushNotificationMultipleRequestDto;
import az.eyrafabdullayev.example.controller.request.PushNotificationSpecificRequestDto;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

    private final ApplicationConfiguration applicationConfiguration;

    public FCMService(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    public void sendMessageToToken(PushNotificationSpecificRequestDto request, String token) throws ExecutionException, InterruptedException {
        Message message = getConfiguredMessageToToken(request, token);
        String response = sendAndGetResponse(message);
        applicationConfiguration.logger().info("MESSAGE_SENT_WITH_DATA : " + response);
    }

    public void sendMessageToMultipleTokens(PushNotificationMultipleRequestDto request, Collection<String> listOfToken) {
        FirebaseMessaging.getInstance().sendMulticastAsync(getConfiguredMulticastMessageToMultipleTokens(request, listOfToken));
    }

    private String sendAndGetResponse(Message message) throws ExecutionException, InterruptedException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getConfiguredMessageToToken(PushNotificationSpecificRequestDto request, String token) {
        return getConfiguredMessageBuilder(request).setToken(token)
                .build();
    }

    private MulticastMessage getConfiguredMulticastMessageToMultipleTokens(PushNotificationMultipleRequestDto request, Collection<String> listOfToken) {
        return getConfiguredMulticastMessageBuilder(request)
                .addAllTokens(listOfToken)
                .build();
    }

    private MulticastMessage.Builder getConfiguredMulticastMessageBuilder(PushNotificationMultipleRequestDto request) {
        return MulticastMessage.builder()
                .putData("title", request.getTitle())
                .putData("text", request.getText());
    }

    private Message.Builder getConfiguredMessageBuilder(PushNotificationSpecificRequestDto request) {
        return Message.builder()
                .putData("title", request.getTitle())
                .putData("text", request.getText());
    }
}
