# Sistema de Agendamento de Transferências
 Pequena documentação do README explicando decisões arquiteturais, versões de linguagem,
ferramentas utilizadas e instruções para a subida do projeto.

## Ferramentas

IDE (front e back): `Visual Studio Code`

Browser: `Google Chrome`

## Back-end
### Tecnologia e versões
* Spring Boot 2.7.1
* Java 11
### Design Patterns
* Padrão de projeto `Factory` para criação de instâncias do calculador de taxas
* Padrão de projeto `Strategy` para seleção do algoritmo de cálculo da taxa

## Front-end
### Tecnologia e versões
* Node 16.15.1
* React 18.2.0

## Subida do projeto
### Subida do servidor
O projeto utiliza um servidor Tomcat embarcado, que deve ser executado em ambiente Windows através do arquivo bat mvnw.bat, executando o seguinte comando no diretório raiz do projeto (`backend/AgendaTransacoes`):
```
mvnw spring-boot:run
```
A aplicação será inicia em `http://localhost:8080`.
### Subida da aplicação cliente
Com o servidor rodando, agora é hora de executar a aplicação cliente... Na raiz do projeto(`frontend/agenda_transacoes`) execute o comando a seguir para realizar o download das dependências:
```
npm install --legacy-peer-deps
```
Uma vez que as dependências foram baixadas a aplicação pode ser iniciada com o seguinte comando:
```
npm start
```
A aplicação será servida em `http://localhost:3000` e já pode ser acessada através de um navegador [link](http://localhost:3000).