package az.eyrafabdullayev.example.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.ResponseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;

public class CommonResponse<T> implements Serializable {
    private Integer code;
    private BusinessResult.BusinessResultType type;
    private String message;
    private String systemMessage;
    private T data;
    private String processId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private static CommonResponse instance(Integer code,
                                           BusinessResult.BusinessResultType type,
                                           String message,
                                           String systemMessage,
                                           Object data,
                                           String processId) {
        return new CommonResponse()
                .setCode(code != null ? code : BusinessStatus.ERROR_INTERNAL.code)
                .setType(type != null ? type : BusinessResult.BusinessResultType.ERROR)
                .setMessage(message)
                .setSystemMessage(systemMessage)
                .setData(data)
                .setTimestamp(LocalDateTime.now())
                .setProcessId(processId);
    }

    public static <E> ResponseEntity<CommonResponse<E>> instance(BusinessResult businessResult, String lang) {
        if (businessResult == null) businessResult = BusinessResult.of(BusinessStatus.ERROR_INTERNAL);
        return ResponseEntity.status(businessResult.getHttpStatus())
                .body(CommonResponse.instance(
                        businessResult.getCode(),
                        businessResult.getType(),
                        getMessage(businessResult, lang),
                        businessResult.getSystemMessage(),
                        businessResult.getResponseData(),
                        businessResult.getProcessId()
                ));
    }

    private static String getMessage(BusinessResult businessResult, String lang) {
        String message = businessResult.getMessageAz();
        if (lang != null) {
            if (lang.equalsIgnoreCase("en")) {
                message = businessResult.getMessageEn();
            } else if (lang.equalsIgnoreCase("ru")) {
                message = businessResult.getMessageRu();
            }
        }
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public CommonResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public CommonResponse setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
        return this;
    }

    public String getProcessId() {
        return processId;
    }

    public CommonResponse setProcessId(String processId) {
        this.processId = processId;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResponse setData(T data) {
        this.data = data;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public CommonResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public BusinessResult.BusinessResultType getType() {
        return type;
    }

    public CommonResponse setType(BusinessResult.BusinessResultType type) {
        this.type = type;
        return this;
    }
}

