package br.com.fiap.stockagil.repository;

import br.com.fiap.stockagil.entidade.PedidoPicklist;
import br.com.fiap.stockagil.entidade.Usuario; // Importe a entidade Usuario, se necessário
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PedidoPicklistRepository extends JpaRepository<PedidoPicklist, Long> {

    // a. Pesquisar por parte de uma String (ignorando maiúsculas e minúsculas)
    @Query("SELECT pp FROM PedidoPicklist pp WHERE LOWER(pp.status) LIKE LOWER(CONCAT('%', :parteStatus, '%'))")
    List<PedidoPicklist> findByParteStatus(@Param("parteStatus") String parteStatus);

    // b. Pesquisar por intervalo de datas
    @Query("SELECT pp FROM PedidoPicklist pp WHERE pp.dataPedido BETWEEN :dataInicio AND :dataFim")
    List<PedidoPicklist> findByIntervaloDeDatas(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);

    // c. Pesquisar utilizando dois parâmetros (status e quantidade)
    @Query("SELECT pp FROM PedidoPicklist pp WHERE pp.status = :status AND pp.quantidade = :quantidade")
    List<PedidoPicklist> findByStatusAndQuantidade(@Param("status") String status, @Param("quantidade") int quantidade);

    // Pesquisar navegando entre os atributos de relacionamentos (usuários com picklists de status específico)
    @Query("SELECT pp FROM PedidoPicklist pp JOIN pp.usuario u WHERE u.nome = :nome")
    List<PedidoPicklist> findByUsuarioNome(@Param("nome") String nome);

    // e. Pesquisar por qualquer parâmetro (contar picklists por status)
    @Query("SELECT COUNT(pp) FROM PedidoPicklist pp WHERE pp.status = :status")
    Long countByStatus(@Param("status") String status);

}


