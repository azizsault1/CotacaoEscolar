package cotacaoEscolar.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cotacaoEscolar.app.ApplicationSwing.TipoDessaPorra;
import cotacaoEscolar.repository.LocalDb;
import cotacaoEscolar.repository.json.JsonRepository;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEscolaLocal;
import cotacaoEscolar.service.ServicoEstabelecimento;
import cotacaoEscolar.service.ServicoListaMaterial;
import cotacaoEscolar.service.impl.ServicoDescricaoMaterialEscolarLocal;
import cotacaoEscolar.service.impl.ServicoEstabelecimentoLocal;
import cotacaoEscolar.service.impl.ServicoListaMaterialLocal;
import io.swagger.annotations.Api;

@Api
@Configuration
@SpringBootApplication(scanBasePackages = { "cotacaoEscolar" })
public class Application {

   private static EscolhaUmBancoNessaPorra oowww;

   @Bean
   public ServicoEscola servicoEscola() {
      return new ServicoEscolaLocal(oowww.meDaUmBancoDeEscola());
   }

   @Bean
   public ServicoListaMaterial getMaterial() {
      return new ServicoListaMaterialLocal(oowww.meDaUmBancoDeListaMaterial());
   }

   @Bean
   public ServicoEstabelecimento getEstabelecimento() {
      return new ServicoEstabelecimentoLocal(oowww.meDaUmBancoDeestabelecimentos());
   }

   @Bean
   public ServicoDescricaoMaterialEscolar getDescricao() {
      return new ServicoDescricaoMaterialEscolarLocal(oowww.meDaUmBancoDeMaterial());
   }

   public static void main(final String[] args) throws IOException, GeneralSecurityException {
      final TipoDessaPorra bancoEscolhido = TipoDessaPorra.LOCAL;

      switch (bancoEscolhido) {
      case JSON:
         oowww = new JsonRepository("src/main/resources/dbfiles/");
         break;
      default:
         oowww = new LocalDb();
      }

      SpringApplication.run(Application.class, args);
   }

}
