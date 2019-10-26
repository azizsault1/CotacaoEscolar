# Vamos Comparar?
**OBJETIVO DO PROJETO**: Esse projeto tem como objetivo facilitar a ardua busca pelo preço perfeito dos materiais escolares.
Vamos comparar é uma ferramenta simples e colaborativa de comparação de preços de materias escolares. ​

Onde os pais podem informar o preço para os produtos contidos em uma lista de materias de uma escola ou consultar a lista de materias com a cotação já calculada.​

Nas próximas versões donos de estabelecimentos de materiais escolares poderão se cadastrar e informar o valor dos próprios produtos;​

As escolas poderão adicionar a suas listas de materiais escolares disponibilizando-as paras os pais para realizar as cotações;

**VERSÃO: 1.0**

**AUTORES: Aziz Sault, Alexandre da Hora**


**PRINCIPAIS PONTOS CRITICOS:**
>* PC1. Coloboração dos usuários para manter os preços de cada produto atualizados;

**POSSIVEIS MELHORIAS:**
>* Ref.:  PC1. Layout mais agradável e fácil para estimular o usuário a atualizar os preços;
>*  Ref.: PC1. Gameficação da atualização de preços, dando pontos para o usuário que atualiza;

**DESCRIÇÃO DO PROCESSO DE NEGÓCIO:**

## COMO INICIAR O PROJETO:
### Como baixar esse projeto?

Para o backend:
```
git clone: https://github.com/azizsault1/CotacaoEscolar.git
```
E para o FrontEnd:
```https://github.com/alexhsx/CotacaoEscolarFontend.git```

### Como Rodar o seu projeto?
1. Para isso você precisa do gradle: [Gradle](https://gradle.org/install/);
* Ou com a ajuda do gradlew.bat que já vem com o projeto:
* Rode o comando: ```gradle init```
* Rodando o comando: ```gradle tasks``` é possível ver a lista de comandos disponíveis.
* Se você rodar o comando ```gradle test```você roda os testes unitários.
* Se você usa o eclipse você pode rodar o comando: ```gradle eclipse``` que o projeto irá criar os arquivos de configurações para o eclipse;
* Se você deseja rodar o backend como um projeto Swing, vai no pacote:  ```cotacaoEscolar.app.ApplicationSwing``` e é só rodar;
* Rodando o comando: ```gradle bootRun``` você consegue subir o backend com Spring;
* Para ter acesso às Urls disponíveis no backend, depois de iniciar o Spring: ```http://localhost:8080/swagger-ui.html ```

### Diagramas:

#### Diagrama de Arquitetura:
![Diagrama de Arquitetura](https://github.com/azizsault1/CotacaoEscolar/blob/master/doc/diagramas/Arquitetura.png)

#### Diagrama de Classes do FrontEnd:
![Diagrama de Classe do FrontEnd](https://github.com/azizsault1/CotacaoEscolar/blob/master/doc/diagramas/diagrama_classe_cotacao_frontend.png)
 
#### Diagrama de Classes do BackEnd:
![Diagrama de Classe do BackEnd](https://github.com/azizsault1/CotacaoEscolar/blob/master/doc/diagramas/DiagramaClasse.png)

#### Diagrama de Sequencia do FrontEnd:
![Diagrama de Sequencia do FrontEnd](https://github.com/azizsault1/CotacaoEscolar/blob/master/doc/diagramas/diagrama_sequencia_frontend_cotacao.png)

#### Diagrama de Sequencia do BackEnd:
![Diagrama de Sequencia do Backend](https://github.com/azizsault1/CotacaoEscolar/blob/master/doc/diagramas/cotacao.png)

#### Casos de teste:

| Id |   Cenário    |                                                                 Fluxo                                                                         |
|----|:------------:|-----------------------------------------------------------------------------------------------------------------------------------------------|
| 01 |   Sucesso    | O usuário insere a escola e a série, o sistema carrega a lista da escola, o usuário requisita o resultado, o sistema apresenta-o com sucesso. |
| 02 |   Sucesso    | O usuário insere a escola e a série, o sistema não encontra a lista da escola.                                                                |
| 03 |   Sucesso    | Após não encontrar o produto na lista de retorno da pesquisa, o usuário informa o produto e adiciona-os na lista da escola e serie pesquisada.|   
| 04 |   Sucesso    | Seleciona os produtos e adiciona-o na lista de produtos e realiza a busca, o sistema.                                                         |
| 05 |   Sucesso    | O usuário insere a escola e a série, o sistema carrega a lista da escola, o usuário adiciona produtos na lista de produtos, o usuário <br>    | 
|    |              | seleciona a busca do resultado e o sistema apresenta-o com sucesso.                                                                           |
| 06 |    Erro      | Buscar resultado sem produto cadastrado no sistema.                                                                                           |
| 07 |    Erro      | Salvar uma escola vazia.                                                                                                                      |
| 08 |    Erro      | Salvar uma série sem escola.                                                                                                                  |
| 09 |    Sucesso   | Realizar uma cotação.                                                                                                                         |
