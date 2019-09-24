Documentação do Sistema - Sistema Stock Exchange Restful
---------------------------------------------------------------------------
URL DE CHAMADA DO REST

- Após 100 iterações, o sistema cria um documento .txt na pasta relatorios/, com o nome de relatorio + dataAtual + .txt
API REST

- Para criar uma conta, a url de request é http://localhost:8080/contas, tipo POST, no formato json abaixo:
{
	"email": "joao@gmail.com",
	"valorDisponibilizado": 1000
}

- Para a listagem de contas, a url de request é http://localhost:8080/contas, tipo GET

Para a verificação de qual conta pertece a qual empresa, e a sua quantidade de ações, é necessário criar um registro da tabela empresa, com referência na tabela conta.

A inserção de um registro empresa é feita pela url http://localhost:8080/empresas/{idConta}/contas, tipo POST, no formato json abaixo
{
    "nome": "AXC",
    "acoes": 0
}

- Para criar um monitoramento, a url de request é http://localhost:8080/monitoramentos/{idEmpresa}, tipo POST, no formato json abaixo:
{
	"precoCompra": 10.2,
	"PrecoVenda": 10.5
}

- Para alterar um monitoramento, a url de request é http://localhost:8080/monitoramentos/{idMonitoramento}, tipo PUT, no formato json abaixo:
{
	"id": 1726,
	"precoCompra": 10.2,
	"PrecoVenda": 10.5
}

- Para deletar um monitoramento, a url de request é http://localhost:8080/monitoramentos/{idMonitoramento}, tipo DELETE

- Para a listagem de monitoramentos, a url de request é http://localhost:8080/monitoramentos, tipo GET

- Para a listagem de negociações, a url de request é http://localhost:8080/negociacoes, tipo GET

SOBRE O SISTEMA
-----------------------------------------------------------------------------------------------------
Utilizei para desenvolver o sistema JAVA8 com o framework Spring Boot, pois é uma ferramente que 
agiliza muito o processo de desenvolvimento, e eu realmente me interessei muito pela idéia dos 
seus desenvolvedores, além de ser uma ferramente sempre a frente no mercado, com um forte apoio da comunidade;

Para a simulação das negociações, utilizei a annotation @Scheduled, que a partir do argumento determinado, consigo
setar um periodo de tempo para a execução do método;

Toda a lógica de negócio foi implementada na classe Controller, para acelerar o processo de desenvolvimento;

Para o envio de e-mails, utilizei o servidor da Google, e com a interface JavaMailSender;
