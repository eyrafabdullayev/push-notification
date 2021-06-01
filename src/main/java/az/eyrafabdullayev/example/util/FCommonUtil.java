package az.eyrafabdullayev.example.util;

public class FCommonUtil {

    public boolean result;

    public <T> T setResult(boolean result) {
        this.result = result;
        return (T) this;
    }
}
