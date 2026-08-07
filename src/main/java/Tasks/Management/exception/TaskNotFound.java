package Tasks.Management.exception;

import org.springframework.http.HttpStatus;

public class TaskNotFound extends RuntimeException implements ApiException {

    private final String code = "TASK_NOT_FOUND";
    private String message;

    public TaskNotFound(String message) {
        this.message = message;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
