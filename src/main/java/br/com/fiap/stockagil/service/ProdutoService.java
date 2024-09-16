package br.com.fiap.stockagil.service;

import br.com.fiap.stockagil.dto.CadastrarProduto;
import br.com.fiap.stockagil.entidade.Produto;
import br.com.fiap.stockagil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criarProduto(CadastrarProduto cadastrarProduto) {
        Produto produto = new Produto(cadastrarProduto);
        return produtoRepository.save(produto);
    }


}
