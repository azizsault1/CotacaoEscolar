package cotacaoEscolar.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cotacaoEscolar.repository.json.JsonRepository;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEscolaLocal;
import cotacaoEscolar.service.ServicoEstabelecimento;
import cotacaoEscolar.service.ServicoListaMaterial;
import cotacaoEscolar.service.impl.ServicoDescricaoMaterialEscolarImpl;
import cotacaoEscolar.service.impl.ServicoEstabelecimentoLocal;
import cotacaoEscolar.service.impl.ServicoListaMaterialLocal;
import io.swagger.annotations.Api;

@Api
@Configuration
@SpringBootApplication(scanBasePackages = { "cotacaoEscolar" })
public class Application {

   private static JsonRepository tamburete;

   @Bean
   public ServicoEscola servicoEscola() {
      return new ServicoEscolaLocal(tamburete.meDaUmBancoDeEscola());
   }

   @Bean
   public ServicoListaMaterial getMaterial() {
      return new ServicoListaMaterialLocal(tamburete.meDaUmBancoDeListaMaterial());
   }

   @Bean
   public ServicoEstabelecimento getEstabelecimento() {
      return new ServicoEstabelecimentoLocal(tamburete.meDaUmBancoDeestabelecimentos());
   }

   @Bean
   public ServicoDescricaoMaterialEscolar getDescricao() {
      return new ServicoDescricaoMaterialEscolarImpl(tamburete.meDaUmBancoDeMaterial());
   }

   public static void main(final String[] args) throws IOException, GeneralSecurityException {
      tamburete = new JsonRepository("src/main/resources/dbfiles/");
      SpringApplication.run(Application.class, args);
   }

}
