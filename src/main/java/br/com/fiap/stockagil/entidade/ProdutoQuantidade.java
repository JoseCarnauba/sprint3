package br.com.fiap.stockagil.entidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoQuantidade {

    private String nome;
    private int quantidade;

    public ProdutoQuantidade(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

}
