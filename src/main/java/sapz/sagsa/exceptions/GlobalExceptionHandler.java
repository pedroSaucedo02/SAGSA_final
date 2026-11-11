package sapz.sagsa.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<StandardErrorDTO> handleEntityNotFound(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorDTO err = StandardErrorDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Recurso não encontrado")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<StandardErrorDTO> handleRegraNegocio(RegraNegocioException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorDTO err = StandardErrorDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Violação de regra de negócio")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO err = ValidationErrorDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Erro de validação")
                .message("Um ou mais campos do pedido são inválidos.")
                .path(request.getRequestURI())
                .build();

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardErrorDTO> handleDataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardErrorDTO err = StandardErrorDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Conflito de integridade de dados")
                .message("Não foi possível concluir a operação devido a restrições de integridade da base de dados (chave duplicada ou registo associado).")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
    public ResponseEntity<StandardErrorDTO> handleAuthentication(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardErrorDTO err = StandardErrorDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Falha de autenticação")
                .message("Credenciais inválidas ou token expirado/ausente.")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorDTO> handleGenericException(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardErrorDTO err = StandardErrorDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Erro interno do servidor")
                .message("Ocorreu um erro inesperado no servidor.")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(err);
    }
}