package cotacaoEscolar.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InternalError extends ResponseEntityExceptionHandler {

   @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
   protected ResponseEntity<Object> habdlerException(final RuntimeException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro nao identificado.");
   }
}
