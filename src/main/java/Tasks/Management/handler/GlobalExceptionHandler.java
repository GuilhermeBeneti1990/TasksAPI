package Tasks.Management.handler;

import Tasks.Management.dto.ErrorResponse;
import Tasks.Management.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handlerApiException(ApiException exception) {
        HttpStatus status = exception.getHttpStatus();
        Tasks.Management.dto.ErrorResponse error = new Tasks.Management.dto.ErrorResponse(
                exception.getCode(),
                exception.getMessage(),
                status.value()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("OCORREU UM ERRO INTERNO: " + ex.getMessage());
        log.error("OCORREU UM ERRO INTERNO - CAUSA: " + ex.getCause());
        ErrorResponse error = new ErrorResponse("INTERNAL_SERVER_ERROR", "Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
