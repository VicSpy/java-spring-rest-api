package med.voll.api.infra.excpetions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoExcpetion;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ValidacaoExcpetion.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoExcpetion ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
