package cotacaoEscolar.model.v1;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.helpers.EscolaValidator;
import cotacaoEscolar.service.ServicoEscola;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "Instituição pública ou privada destinado ao ensino coletivo.")
public class EscolaReal implements Comparable<Escola>, Escola {

   private final transient ServicoEscola servico;
   private final String nome;

   EscolaReal(final ServicoEscola servico, final String nome) {
      this.nome = nome;
      this.servico = servico;
   }

   @Override
   public String getNome() {
      return this.nome;
   }

   @Override
   public String toString() {
      return this.nome;
   }

   @Override
   public Escola salvar() throws FoiNao {
      return this.servico.salvar(this);
   }

   @Override
   public boolean souNova() {
      return false;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((this.nome == null) ? 0 : this.nome.hashCode());
      return result;
   }

   @Override
   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (this.getClass() != obj.getClass()) {
         return false;
      }
      final Escola other = (Escola) obj;
      if (this.nome == null) {
         if (other.getNome() != null) {
            return false;
         }
      } else if (!this.nome.equals(other.getNome())) {
         return false;
      }
      return true;
   }

   @Override
   public int compareTo(final Escola o) {
      return this.nome.compareTo(o.getNome());
   }

   public static Escola create(final ServicoEscola servico, final Escola escola) {
      validar(escola.getNome());
      return new EscolaReal(servico, escola.getNome());
   }

   private static void validar(final String nome) {
      EscolaValidator.create(nome).validate();
   }

}
