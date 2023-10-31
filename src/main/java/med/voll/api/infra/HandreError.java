package med.voll.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HandreError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception) {

        var erros = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(ExceptionError400Dto::new).toList());

    }

    private record ExceptionError400Dto(String camp, String mensseger) {

        public ExceptionError400Dto(FieldError erro) {

            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
