package cotacaoEscolar.model.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.service.ServicoEscola;
import io.jsondb.annotation.Id;
import io.swagger.annotations.ApiModel;

@JsonSerialize
@ApiModel(description = "Instituição pública ou privada destinado ao ensino coletivo.")
public class EscolaReal implements Comparable<Escola>, Escola {

   private ServicoEscola servico;
   private final String nome;

   EscolaReal(final String nome) {
      this.nome = nome;
   }

   @Override
   @Id
   public String getNome() {
      return this.nome;
   }

   @Override
   public String toString() {
      return this.nome;
   }

   public void addService(final ServicoEscola servico) {
      this.servico = servico;
   }

   @Override
   public void salvar() throws FoiNao {
      if (this.servico == null) {
         throw new IllegalArgumentException(
               "Opa, alguém esqueceu de adicionar o serviço, faça isso nas entidades que usem escola quando ela vier do banco de dados");
      }
      this.servico.salvar(this);
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
   public boolean validate() {
      return !this.getNome().isEmpty();
   }

   @Override
   public int compareTo(final Escola o) {
      return this.nome.compareTo(o.getNome());
   }

   @JsonCreator
   public static Escola create(@JsonProperty("nome") final String nome) {
      if ((nome == null) || nome.trim().isEmpty()) {
         throw new IllegalError("Uma escola não pode ser criada sem nome.");
      }

      if (nome.trim().length() < 4) {
         throw new IllegalError("Ahhh qual é uma escola tem mais de 4 letras.");
      }

      if (nome.length() > 100) {
         throw new IllegalError("Acho uma melhor ideia dar uma abreviada no nome dessa escola.");
      }
      return new EscolaReal(nome);
   }

}
