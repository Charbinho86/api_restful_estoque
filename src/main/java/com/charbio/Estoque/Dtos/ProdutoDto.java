package com.charbio.Estoque.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Classe Record, responsável por gravar as informações de entrada da Classe Controller.
public record ProdutoDto(@NotBlank String nome, @NotNull String descricao,
                         @NotNull int quantidade, @NotNull double valor,
                         double valorTotal) {

    // Método responsável pelo cálculo do valor total do produto, através da quantidade e do valor do produto.
    @Override
    public double valorTotal() {
        return quantidade * valor;
    }
}
