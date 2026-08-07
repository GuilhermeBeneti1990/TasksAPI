package Tasks.Management.exception;

import org.springframework.http.HttpStatus;

public interface ApiException {
    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
