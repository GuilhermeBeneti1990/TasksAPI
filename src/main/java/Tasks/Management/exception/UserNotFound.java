package Tasks.Management.exception;

import org.springframework.http.HttpStatus;

public class UserNotFound extends RuntimeException implements ApiException {

    private final String code = "USER_NOT_FOUND";
    private String message;

    public UserNotFound(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
