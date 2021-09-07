# Desenvolvimento da API para agendamentos da sala para reuniões.

# Sobre o projeto 

Está API é o backend do projeto Sala de reuniões realizado durante a Bootcamp full stack Santander, oferecida pela plataforma Digital Innovation One.
Representa a API com um CRUD de agendamentos de reuniões.

Foram realizados os testes na classe de serviço e a documentação da API com o Swagger.  

## Pré-requisitos para executar o projeto:

Java: 11 ou superior.  
Maven: 3.8.1  
JUnit 5.   
Swagger: 3.0.0.

## Para executar a aplicação:

No diretório do projeto execute o seguinte comando no terminal:  
`mvn spring-boot:run` 

## Endpoints:

Método | Endpoint
:-----:|:--------:
 GET   | http://localhost:8080/swagger-ui/
 POST  | http://localhost:8080/meeting/create
 GET   | http://localhost:8080/meeting/{id}
 GET   | http://localhost:8080/meeting/list
 PUT   | http://localhost:8080/meeting/update/{id}
 DELETE| http://localhost:8080/meeting/delete/{id}


## Modelo Json para criar um agendamento:
Para adicionar uma nova reunião, envie uma requisição, método POST, para o endpoint http://localhost:8080/meeting/create com o modelo a seguir:
``` JSON
{
	"nameMeeting": "Nome da reunião",
	"date": "2021-10-08",
	"startHour": "14:00",
	"endHour": "16:00"
}
```
## Autor:

Ricardo Farias.

https://www.linkedin.com/in/ricardo-farias-04069359/

## Licença:

[![NPM](http://img.shields.io/npm/l/react)](https://github.com/ricardo14231/meeting-room-manager/blob/master/LICENSE)
