package cotacaoEscolar.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InternalError extends ResponseEntityExceptionHandler {

   @ExceptionHandler(value = { IllegalArgumentException.class, IllegalError.class })
   protected ResponseEntity<Object> habdlerException(final RuntimeException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
   }

   @ExceptionHandler(value = { FoiNao.class })
   protected ResponseEntity<Object> conseguiSalvarNaoVei(final Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
   }

   @ExceptionHandler(value = { Exception.class })
   protected ResponseEntity<Object> naoSeiQueBODeu(final Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nao sei qual foi o B.O.");
   }
}
