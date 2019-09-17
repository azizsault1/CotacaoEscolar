package cotacaoEscolar.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;

@Api
@Configuration
@SpringBootApplication(scanBasePackages = { "cotacaoEscolar" })
public class Application {

   public static void main(final String[] args) {
      SpringApplication.run(Application.class, args);
   }

}
