package sapz.sagsa.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StandardErrorDTO {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}