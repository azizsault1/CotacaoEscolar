package cotacaoEscolar.escola.controller.rest;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.escola.model.Escola;
import cotacaoEscolar.escola.service.ServicoEscola;
import cotacaoEscolar.item.model.DescricaoMaterialEscolar;
import cotacaoEscolar.item.service.ServicoItem;

@RestController("/")
public class EscolasController {

	@Qualifier("servicoEscolaLocal")
	private final ServicoEscola servicoEscola;
	
	@Qualifier("servicoItemLocal")
	private final ServicoItem servicoItens;
	
	@Autowired
	public EscolasController(ServicoEscola servicoEscola, ServicoItem servicoItem) {
		this.servicoEscola = servicoEscola;
		this.servicoItens = servicoItem;
	}
	
	@GetMapping(value = "escolas")
   public List<Escola> todas() {
		return servicoEscola.todas();
	}
	
	@GetMapping(value = "itens")
	   public List<DescricaoMaterialEscolar> todosItens() {
			return servicoItens.todasDescricoes();
		}
	
}
