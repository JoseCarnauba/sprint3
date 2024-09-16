package br.com.fiap.stockagil.repository;

import br.com.fiap.stockagil.entidade.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto getProdutoById(Long id);

    // Pesquisar por parte de uma String (ignorando maiúsculas e minúsculas)
    @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :parteNome, '%'))")
    List<Produto> findByParteNome(@Param("parteNome") String parteNome);

    // Pesquisar por intervalo de datas (usando data de criação fictícia)
    @Query("SELECT p FROM Produto p WHERE p.dataCriacao >= :dataInicio AND p.dataCriacao <= :dataFim")
    List<Produto> findByIntervaloDeDatas(@Param("dataInicio") LocalDate dataInicio,
                                         @Param("dataFim") LocalDate dataFim);

    // Pesquisar utilizando dois parâmetros (nome e preço)
    @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) = LOWER(:nome) AND p.preco = :preco")
    Page<Produto> findByNomeAndPreco(@Param("nome") String nome,
                                     @Param("preco") double preco,
                                     Pageable pageable);

    // Pesquisar navegando entre os atributos de relacionamentos (pedidos de picklist com status) - Não consegui implementar
    @Query("SELECT p FROM Produto p JOIN p.pedidoPicklists pp WHERE pp.status = :status")
    List<Produto> findByPedidoPicklistStatus(@Param("status") String status);

    // Consulta para somar a quantidade de produtos por nome
    @Query("SELECT COUNT(p) FROM Produto p WHERE LOWER (p.nome) = LOWER(:nome)")
    int somarQuantidadePorNome(@Param("nome") String nome);

}


