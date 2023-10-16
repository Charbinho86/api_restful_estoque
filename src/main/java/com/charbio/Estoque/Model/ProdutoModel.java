package com.charbio.Estoque.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

// Classe Model, responsável pela regra de negócios do projeto. Classe que representa todos os atributos
// referente ao projeto.
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Table(name = "TB_Produtos")
public class ProdutoModel extends RepresentationModel<ProdutoModel> implements Serializable {

    // Atributo Serial Version UID responsável pela geração de código único, utilizado para a serialização da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Atributos como código sendo a chave primária e auto gerada, nome do produto, descrição do produto, quantidade
    // de produto, valor do produto e valor total do produto de acordo com a quantidade e valor.
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;

    private String nome;
    private String descricao;
    private int quantidade;
    private double valor;
    private double valorTotal;
}
