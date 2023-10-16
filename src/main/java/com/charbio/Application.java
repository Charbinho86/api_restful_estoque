package com.charbio;

/*
*
* Projeto para cadastro e persistência em banco de dados de produtos, com a utilização de API Restful sendo possível
* a utilização através de aplicações como Postman para cadastro, pesquisa de todos os produtos, pesquisa pelo id do
* código, e também a exclusão de produtos de acordo com o id do produto.
*
*@autor:Charbio Silva
*
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	// Classe Application, principal classe para executar o programa através do
	// método main de acordo com as regras de negócio do projeto.
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
