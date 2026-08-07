package Tasks.Management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    public ErrorResponse(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
        validateError = null;
    }

    private String code;
    private String message;
    private int status;
    private List<ValidateError> validateError;

}
