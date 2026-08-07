package Tasks.Management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidateError {
    private String field;
    private String message;
}
