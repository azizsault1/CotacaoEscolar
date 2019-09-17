package cotacaoEscolar.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IllegalError extends Exception {

   private static final long serialVersionUID = 1L;

   public IllegalError(final String message) {
      super(message);
   }

}
