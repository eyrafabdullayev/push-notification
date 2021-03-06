package az.eyrafabdullayev.example.controller.response;

import az.eyrafabdullayev.example.util.FStringUtils;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class BusinessResult implements Serializable {

    private Integer code;
    private BusinessResultType type;
    private HttpStatus httpStatus;
    private String systemMessage;
    private String stepsSystemMessage;
    private String messageAz;
    private String messageEn;
    private String messageRu;
    private Serializable responseData;
    private String processId;

    public BusinessResult() {
    }

    private BusinessResult(Serializable responseData, Integer code, BusinessResultType type, HttpStatus httpStatus, String systemMessage, String messageAz, String messageEn, String messageRu) {
        this.responseData = responseData;
        this.code = code;
        this.type = type;
        this.httpStatus = httpStatus;
        this.systemMessage = systemMessage;
        this.messageAz = messageAz;
        this.messageEn = messageEn;
        this.messageRu = messageRu;
    }

    public static BusinessResult ofSystemMessage(BusinessStatus businessStatus, String systemMessage, String stepsSystemMessage, String message) {
        BusinessResult br = of(businessStatus).setSystemMessage(systemMessage).setStepsSystemMessage(stepsSystemMessage);
        return br.setMessageAz(br.getMessageAz() + message)
                .setMessageEn(br.getMessageEn() + message)
                .setMessageRu(br.getMessageRu() + message);
    }

    public static BusinessResult of(BusinessResult businessResult) {
        return new BusinessResult()
                .setSystemMessage(businessResult.getSystemMessage())
                .setCode(businessResult.getCode())
                .setHttpStatus(businessResult.getHttpStatus())
                .setMessageAz(businessResult.getMessageAz())
                .setMessageEn(businessResult.getMessageEn())
                .setMessageRu(businessResult.getMessageRu())
                .setResponseData(businessResult.getResponseData())
                .setType(businessResult.getType());

    }

    public static BusinessResult of(Serializable responseData, Integer code, String systemMessage, BusinessResultType type, HttpStatus httpStatus) {
        return new BusinessResult(responseData, code, type, httpStatus, systemMessage, null, systemMessage, null);
    }

    public static BusinessResult of(BusinessStatus businessStatus, Serializable responseData) {
        return new BusinessResult(
                responseData,
                businessStatus.code,
                BusinessResultType.valueOf(businessStatus.type.name()),
                businessStatus.httpStatus,
                businessStatus.systemMessage,
                businessStatus.messageAz,
                businessStatus.messageEn,
                businessStatus.messageRu
        );
    }

    public static BusinessResult of(BusinessStatus businessStatus) {
        return of(businessStatus, null);
    }

    public static BusinessResult ofArgs(BusinessStatus businessStatus, Serializable responseData, Object... args) {
        BusinessResult br = of(businessStatus, responseData);
        return br
                .setMessageAz(String.format(br.getMessageAz(), args))
                .setMessageEn(String.format(br.getMessageEn(), args))
                .setMessageRu(String.format(br.getMessageRu(), args))
                .setSystemMessage(String.format(br.getSystemMessage(), args));
    }

    public static BusinessResult ofEx(Exception ex) {
        return of(BusinessStatus.ERROR_INTERNAL).setSystemMessage(FStringUtils.getSystemMessage(ex));
    }

    public Integer getCode() {
        return code;
    }

    public BusinessResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public BusinessResultType getType() {
        return type;
    }

    public BusinessResult setType(BusinessResultType type) {
        this.type = type;
        return this;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BusinessResult setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public BusinessResult setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
        return this;
    }

    public String getMessageAz() {
        return messageAz;
    }

    public BusinessResult setMessageAz(String messageAz) {
        this.messageAz = messageAz;
        return this;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public BusinessResult setMessageEn(String messageEn) {
        this.messageEn = messageEn;
        return this;
    }

    public String getMessageRu() {
        return messageRu;
    }

    public BusinessResult setMessageRu(String messageRu) {
        this.messageRu = messageRu;
        return this;
    }

    public Serializable getResponseData() {
        return responseData;
    }

    public BusinessResult setResponseData(Serializable responseData) {
        this.responseData = responseData;
        return this;
    }

    public String getProcessId() {
        return processId;
    }

    public BusinessResult setProcessId(String processId) {
        this.processId = processId;
        return this;
    }

    public String getStepsSystemMessage() {
        return stepsSystemMessage;
    }

    public BusinessResult setStepsSystemMessage(String stepsSystemMessage) {
        this.stepsSystemMessage = stepsSystemMessage;
        return this;
    }

    public enum BusinessResultType {
        ERROR, SUCCESS
    }
}

