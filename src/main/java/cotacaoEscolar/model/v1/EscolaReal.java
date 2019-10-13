package cotacaoEscolar.model.v1;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.helpers.EscolaAutoSave;
import cotacaoEscolar.model.v1.helpers.EscolaValidator;
import cotacaoEscolar.service.ServicoEscola;

public class EscolaReal implements Comparable<Escola>, EscolaAutoSave {

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
   public Escola save() throws FoiNao {
      return this.servico.salvar(this);
   }

   @Override
   public boolean canIPersist() {
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

   public static EscolaAutoSave create(final ServicoEscola servico, final String escola) {
      validar(escola);
      return new EscolaReal(servico, escola);
   }

   private static void validar(final String nome) {
      EscolaValidator.create(nome).validate();
   }

}
