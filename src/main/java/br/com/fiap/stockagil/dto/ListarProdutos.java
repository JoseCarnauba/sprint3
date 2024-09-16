package br.com.fiap.stockagil.dto;

import br.com.fiap.stockagil.entidade.Produto;

public record ListarProdutos(Long id, String nome, String descricao, String categoria, int quantidadeEstoque) {
    public ListarProdutos(Produto dados) {
        this(
                dados.getId(),
                dados.getNome(),
                dados.getDescricao(),
                dados.getCategoria(),
                dados.getQuantidadeEstoque());
    }

}
