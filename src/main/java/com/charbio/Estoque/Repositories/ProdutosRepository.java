package com.charbio.Estoque.Repositories;

import com.charbio.Estoque.Model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

//Classe Repository, responsável pela persistência dos dados em um Banco de Dados do tipo relacional, para manipulação
//dados como create, read, update, delete na base de dados.

public interface ProdutosRepository extends JpaRepository<ProdutoModel, Integer> {
}
