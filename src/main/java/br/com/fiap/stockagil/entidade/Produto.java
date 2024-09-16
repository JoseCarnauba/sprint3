package br.com.fiap.stockagil.entidade;

import br.com.fiap.stockagil.dto.CadastrarProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 100)
    private String nome;

    private String descricao;

    private String categoria;

    private int quantidadeEstoque;

    private double preco;

    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<PedidoPicklist> pedidoPicklists;

    public Produto(CadastrarProduto dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.categoria = dados.categoria();
        this.quantidadeEstoque = dados.quantidadeEstoque();
        this.preco = dados.preco();
        this.dataCriacao = LocalDate.now();
    }

    public void atualizarProduto(Produto dados) {
        if (Objects.nonNull(dados.getNome())) {
            this.nome = dados.getNome();
        }
        if (Objects.nonNull(dados.getDescricao())) {
            this.descricao = dados.getDescricao();
        }
        if (Objects.nonNull(dados.getCategoria())) {
            this.categoria = dados.getCategoria();
        }
        this.quantidadeEstoque = dados.getQuantidadeEstoque();
        if (dados.getPreco() > 0) {
            this.preco = dados.getPreco();
        }
    }
        public Long id() {
            return id;
    }
}

