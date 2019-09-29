package cotacaoEscolar.repository.json;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import cotacaoEscolar.app.EscolhaUmBancoNessaPorra;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;
import cotacaoEscolar.repository.EscolaRepository;
import cotacaoEscolar.repository.EstabelecimentoRepository;
import cotacaoEscolar.repository.ListaMaterialRepository;
import io.jsondb.JsonDBTemplate;
import io.jsondb.crypto.DefaultAESCBCCipher;
import io.jsondb.crypto.ICipher;

public class JsonRepository implements EscolhaUmBancoNessaPorra {

   private final JsonDBTemplate repository;

   public JsonRepository(final String dbFilesLocation) throws IOException, GeneralSecurityException {

      this.createDirectory(dbFilesLocation);
      // this.addEntity(dbFilesLocation, "pojos.json");
      this.addEntity(dbFilesLocation, "escolas.json");
      this.addEntity(dbFilesLocation, "descricoes.json");
      this.addEntity(dbFilesLocation, "listaMateriais.json");
      this.addEntity(dbFilesLocation, "estabelecimentos.json");
      this.addEntity(dbFilesLocation, "itens.json");
      this.addEntity(dbFilesLocation, "produtos.json");

      // Java package name where POJO's are present
      final String baseScanPackage = "cotacaoEscolar.repository.pojos";

      final ICipher cipher = new DefaultAESCBCCipher("1r8+24pibarAWgS85/Heeg==");
      this.repository = new JsonDBTemplate(dbFilesLocation, baseScanPackage, cipher);
   }

   private void createDirectory(final String dbFilesLocation) {
      final File direcotry = new File(dbFilesLocation);
      if (!direcotry.exists()) {
         direcotry.mkdir();
      }
   }

   private void addEntity(final String directory, final String entity) throws IOException {
      final File escolasJson = new File(directory, entity);

      if (!escolasJson.exists()) {
         escolasJson.createNewFile();
      }
   }

   public void salvar(final Object entity) {
      this.repository.upsert(entity);

   }

   public <T> List<T> pegaAPorraToda(final Class<T> class1) {
      return this.repository.findAll(class1);
   }

   public <T> T pegaEssaCaralha(final Object id, final Class<T> entity) {
      return this.repository.findById(id, entity);
   }

   @Override
   public DescricaoMaterialEscolarRepository meDaUmBancoDeMaterial() {
      return new JsonMaterialEscolar(this);
   }

   @Override
   public EscolaRepository meDaUmBancoDeEscola() {
      return new JsonEscola(this);
   }

   @Override
   public ListaMaterialRepository meDaUmBancoDeListaMaterial() {
      return new JsonListaMaterial(this);
   }

   @Override
   public EstabelecimentoRepository meDaUmBancoDeestabelecimentos() {
      return new JsonEstabelecimentos(this);
   }

}
