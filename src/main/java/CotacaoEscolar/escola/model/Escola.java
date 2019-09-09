package CotacaoEscolar.escola.model;

/**
 * Escreva a descrição da classe Escola aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class Escola {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final String nome;

   /**
    * COnstrutor para objetos da classe Escola
    */
   public Escola(final String nome) {
      this.nome = nome;
   }

   @Override
   public String toString() {
      return this.nome;
   }

}
