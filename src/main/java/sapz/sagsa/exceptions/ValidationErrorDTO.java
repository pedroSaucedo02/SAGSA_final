package sapz.sagsa.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidationErrorDTO extends StandardErrorDTO {

    @Builder.Default
    private List<FieldErrorDTO> errors = new ArrayList<>();

    public void addError(String field, String message) {
        errors.add(new FieldErrorDTO(field, message));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldErrorDTO {
        private String field;
        private String message;
    }
}