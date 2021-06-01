package az.eyrafabdullayev.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import java.util.Arrays;
import java.util.Objects;

public class FStringUtils extends FCommonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static FStringUtils ifBlank(String str) {
        FStringUtils fStringUtils = new FStringUtils();
        fStringUtils.result = StringUtils.isBlank(str);
        return fStringUtils;
    }

    public static String emptyIfNull(Object value) {
        if (Objects.isNull(value)) return "";
        if (StringUtils.isBlank(value.toString())) return "";
        return value.toString();
    }

    public static String getSystemMessage(Exception ex) {
        if (ex == null) return null;
        if (Objects.nonNull(ex.getCause())) {
            return ex.getCause().toString();
        }
        if (Objects.nonNull(ex.getLocalizedMessage())) {
            return ex.getLocalizedMessage();
        }
        if (Objects.nonNull(ex.getMessage())) {
            return ex.getMessage();
        }
        if (Objects.nonNull(ex.getStackTrace())) {
            String stackTrace = ExceptionUtils.getStackTrace(ex);
            return stackTrace.substring(0, Math.min(stackTrace.length(), 1000));
        }
        return null;
    }

    public static String getLang(String lang) {
        if (StringUtils.isBlank(lang)) return "az";
        if (Arrays.asList("az", "en", "ru").contains(lang.toLowerCase())) {
            return lang.toLowerCase();
        }
        return "az";
    }

    public static boolean isBlank(String str) {
        if (StringUtils.isBlank(str)) return true;
        return str.trim().equalsIgnoreCase("null");
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String sp(String str, String seperator) {
        if (isNotBlank(str)) {
            return str + (isNotBlank(seperator) ? seperator : "");
        }
        return "";
    }

    public static String beautify(Object obj) {
        if (obj == null) return "";
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            return obj.toString();
        }
    }
}
