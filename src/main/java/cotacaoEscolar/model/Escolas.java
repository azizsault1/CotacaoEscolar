package cotacaoEscolar.model;

import java.util.Collection;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.service.ServicoEscola;

public interface Escolas {
   public Collection<Escola> todas();

   public Escola inserir(Escola escola) throws FoiNao;

   public static Escolas create(final ServicoEscola servico) {
      return new Escolas() {

         @Override
         public Collection<Escola> todas() {
            return servico.todas();
         }

         @Override
         public Escola inserir(final Escola escola) throws FoiNao {
            EscolaReal.create(servico, escola);
            return servico.salvar(escola);
         }
      };
   }
}
