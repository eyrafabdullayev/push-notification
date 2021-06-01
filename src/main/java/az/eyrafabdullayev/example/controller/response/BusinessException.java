package az.eyrafabdullayev.example.controller.response;


public class BusinessException extends RuntimeException {

    private final BusinessResult result;

    private BusinessException(BusinessResult result) {
        super(result.getSystemMessage());
        this.result = result;
    }

    public void throwEx() {
        throw this;
    }
}
