package br.com.fiap.stockagil.repository;

import br.com.fiap.stockagil.entidade.PedidoTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PedidoTORepository extends JpaRepository<PedidoTO, Long> {

    // a. Pesquisar por parte de uma String (ignorando maiúsculas e minúsculas)
    @Query("SELECT p FROM PedidoTO p WHERE LOWER(p.status) LIKE LOWER(CONCAT('%', :parteStatus, '%'))")
    List<PedidoTO> findByParteStatus(@Param("parteStatus") String parteStatus);

    // b. Pesquisar por intervalo de datas
    @Query("SELECT p FROM PedidoTO p WHERE p.dataPedido BETWEEN :dataInicio AND :dataFim")
    List<PedidoTO> findByIntervaloDeDatas(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);

    // c. Pesquisar utilizando dois parâmetros (status e data de pedido)
    @Query("SELECT p FROM PedidoTO p WHERE p.status = :status AND p.dataPedido = :dataPedido")
    List<PedidoTO> findByStatusAndDataPedido(@Param("status") String status, @Param("dataPedido") Date dataPedido);

    // d. Pesquisar navegando entre os atributos de relacionamentos (usuários com pedidos de status específico)
    @Query("SELECT p FROM PedidoTO p JOIN p.usuario u WHERE u.nome = :nome")
    List<PedidoTO> findByUsuarioNome(@Param("nome") String nome);

    // e. Pesquisar por qualquer parâmetro (contar pedidos por status)
    @Query("SELECT COUNT(p) FROM PedidoTO p WHERE p.status = :status")
    Long countByStatus(@Param("status") String status);

    Long countByUsuarioId(Long usuarioId);
}



