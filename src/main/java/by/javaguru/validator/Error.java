package by.javaguru.validator;

public class Error {
    private final String code;
    private final String message;

    private Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Error of(String code, String message) {
        return new Error(code, message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
