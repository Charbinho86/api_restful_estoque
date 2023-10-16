package com.charbio.Estoque.Controller;

import com.charbio.Estoque.Dtos.ProdutoDto;
import com.charbio.Estoque.Model.ProdutoModel;
import com.charbio.Estoque.Repositories.ProdutosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// Classe Controller, responsável pela manipulação dos dados onde ocorrem as solicitações para os métodos create,
// read, update, delete.

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    // Classe Repository, responsável pelo acesso a base de dados, onde estão persistidas as informações salvas.
    @Autowired
    private ProdutosRepository repository;

    // Método Postmapping, responsável pelo cadastro de novos produtos, através da regra de negócios do projeto.
    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody @Valid ProdutoDto produtoDto) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtoModel));
    }

    // Método Getmapping, responsável pela consulta da base de dados, obtendo um retorno de todos os produto na base.
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> buscarTodos() {
        List<ProdutoModel> produtoModelList = repository.findAll();
        if(!produtoModelList.isEmpty()) {
            for(ProdutoModel produtoModel: produtoModelList) {
                int codigo = produtoModel.getCodigo();
                produtoModel.add(linkTo(methodOn(ProdutoController.class).buscarPorId(codigo)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoModelList);
    }

    // Método Getmapping com filtro, responsável por fazer uma consulta na base de dados através do atributo comum,
    // como por exemplo, filtrar um produto através do seu código na base de dados e se existente, exibir o produto.
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Integer codigo) {
        Optional<ProdutoModel> produtO = repository.findById(codigo);
        if(produtO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        produtO.get().add(linkTo(methodOn(ProdutoController.class).buscarTodos()).withRel("Lista de Produtos."));
        return ResponseEntity.status(HttpStatus.OK).body(produtO.get());
    }

    // Método Putmapping, responsável pela atualização de um produto através de seu atributo comum, como por exemplo
    // o uso do código do produto para sua localização na base de dados e se existente a sua atualização.
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer codigo,
                                            @RequestBody @Valid ProdutoDto produtoDto) {
        Optional<ProdutoModel> atualizaProduto = repository.findById(codigo);
        return atualizaProduto.<ResponseEntity<Object>>map(produtoModel ->
                ResponseEntity.status(HttpStatus.OK).body(produtoModel)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!"));
    }

    // Método Deletemapping, responsável pela exclusão de um produto na base de dados através de seu atributo, como
    // por exemplo o uso do código do produto para sua localização no base de dados e se existente a sua exclusão.
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") Integer codigo) {
        Optional<ProdutoModel> deletaProduto = repository.findById(codigo);
        if(deletaProduto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        repository.delete(deletaProduto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso!");
    }
}
