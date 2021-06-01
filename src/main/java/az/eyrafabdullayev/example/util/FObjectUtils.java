package az.eyrafabdullayev.example.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import java.util.Objects;

public class FObjectUtils extends FCommonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T defaultObject(T obj, T defaultObj) {
        if (obj != null) return obj;
        return defaultObj;
    }

    public static FObjectUtils ifNull(Object obj) {
        FObjectUtils fStringUtils = new FObjectUtils();
        fStringUtils.result = obj == null;
        return fStringUtils;
    }

    public static FObjectUtils ifNotNull(Object obj) {
        FObjectUtils fStringUtils = new FObjectUtils();
        fStringUtils.result = obj != null;
        return fStringUtils;
    }

    public static FObjectUtils ifNotEquals(Object objCompare, Object objExpected) {
        FObjectUtils fStringUtils = new FObjectUtils();
        fStringUtils.result = ObjectUtils.notEqual(objCompare, objExpected);
        return fStringUtils;
    }

    public static <E> E copy(Object obj, Class<E> clazz) {
        if (Objects.isNull(obj)) return null;
        try {
            return objectMapper.readValue(objectMapper.writeValueAsBytes(obj), clazz);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean nonNullAll(Object... objects) {
        if (objects == null) return false;
        for (Object obj : objects) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
