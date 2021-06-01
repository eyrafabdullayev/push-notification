package az.eyrafabdullayev.example.exception;

import az.eyrafabdullayev.example.config.ApplicationConfiguration;
import az.eyrafabdullayev.example.controller.response.BusinessResult;
import az.eyrafabdullayev.example.controller.response.BusinessStatus;
import az.eyrafabdullayev.example.controller.response.CommonResponse;
import az.eyrafabdullayev.example.util.FStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ApplicationConfiguration applicationConfiguration;
    private final HttpServletRequest httpServletRequest;

    public GlobalExceptionHandler(ApplicationConfiguration applicationConfiguration,
                                  HttpServletRequest httpServletRequest) {
        this.applicationConfiguration = applicationConfiguration;
        this.httpServletRequest = httpServletRequest;
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<CommonResponse<Object>> resourceNotFound() {
        applicationConfiguration.logger().info(BusinessStatus.BAD_REQUEST.systemMessage);
        return CommonResponse.instance(BusinessResult.of(BusinessStatus.BAD_REQUEST), defineLang(httpServletRequest));
    }

    @ExceptionHandler({ExecutionException.class, InterruptedException.class})
    public ResponseEntity<CommonResponse<Object>> duringSendingNotification() {
        applicationConfiguration.logger().info(BusinessStatus.ERROR_INTERNAL.systemMessage);
        return CommonResponse.instance(BusinessResult.of(BusinessStatus.ERROR_INTERNAL), defineLang(httpServletRequest));
    }

    private String defineLang(HttpServletRequest httpServletRequest) {
        String lang = "az";
        String[] parameterValues = httpServletRequest.getParameterValues("lang");
        if(FStringUtils.isNotBlank(parameterValues[0]))
            lang = FStringUtils.getLang(parameterValues[0]);
        return lang;
    }
}
