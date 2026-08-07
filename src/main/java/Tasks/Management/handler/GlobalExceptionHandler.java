package Tasks.Management.handler;

import Tasks.Management.dto.ErrorResponse;
import Tasks.Management.dto.ValidateError;
import Tasks.Management.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handlerApiException(ApiException exception) {
        HttpStatus status = exception.getHttpStatus();
        ErrorResponse error = new ErrorResponse(
                exception.getCode(),
                exception.getMessage(),
                status.value()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerValidationException(MethodArgumentNotValidException validException) {
        List<ValidateError> errors = validException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidateError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        ErrorResponse errorResponse = new ErrorResponse(
                "FIELD_VALIDATE_ERROR",
                "Existem campos não preenchidos corretamente",
                HttpStatus.BAD_REQUEST.value(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("OCORREU UM ERRO INTERNO: " + ex.getMessage());
        log.error("OCORREU UM ERRO INTERNO - CAUSA: " + ex.getCause());
        ErrorResponse error = new ErrorResponse("INTERNAL_SERVER_ERROR", "Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
