package az.eyrafabdullayev.example.controller.response;

import org.springframework.http.HttpStatus;

public enum BusinessStatus {
    SUCCESS(1, HttpStatus.OK, BusinessStatusType.SUCCESS, "Əməliyyat uğurla tamamlandı", "Successfully processed", "Успешно обработано"),
    BAD_REQUEST(-400, HttpStatus.BAD_REQUEST, BusinessStatusType.ERROR, "Melumatin dogrulugunu yoxlayin", "Make sure data is right", "Убедитесь, что данные верны"),
    ERROR_INTERNAL(-117, HttpStatus.INTERNAL_SERVER_ERROR, BusinessStatusType.ERROR, "Əməliyyat zamanı xəta baş verdi. Yenidən cəhd etməyiniz xahiş olunur.", "Error occurred while operation. Please try again", "Во время операции возникла ошибка. Пожалуйста, попробуйте ещё раз."),
    ;


    public final Integer code;
    public final BusinessStatusType type;
    public final HttpStatus httpStatus;
    public final String systemMessage;
    public final String messageAz;
    public final String messageEn;
    public final String messageRu;

    BusinessStatus(Integer code, HttpStatus httpStatus, BusinessStatusType type, String messageAz, String messageEn, String messageRu) {
        this.code = code;
        this.type = type;
        this.httpStatus = httpStatus;
        this.messageAz = messageAz;
        this.messageEn = messageEn;
        this.messageRu = messageRu;
        this.systemMessage = messageEn;
    }

    BusinessStatus(Integer code, HttpStatus httpStatus, BusinessStatusType type, String messageAz, String messageEn, String messageRu, String systemMessage) {
        this.code = code;
        this.type = type;
        this.httpStatus = httpStatus;
        this.messageAz = messageAz;
        this.messageEn = messageEn;
        this.messageRu = messageRu;
        this.systemMessage = systemMessage;
    }

    public static BusinessStatus getByCode(Integer code) {
        BusinessStatus[] values = BusinessStatus.values();
        for (BusinessStatus v : values) {
            if (v.code.equals(code)) {
                return v;
            }
        }
        return BusinessStatus.ERROR_INTERNAL;
    }

    public Integer getCode() {
        return code;
    }

    public enum BusinessStatusType {
        ERROR, SUCCESS
    }
}

