package br.com.fiap.stockagil.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarProduto(

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotBlank
        String categoria,

        @NotNull
        int quantidadeEstoque,

        @NotNull
        double preco) {

}

