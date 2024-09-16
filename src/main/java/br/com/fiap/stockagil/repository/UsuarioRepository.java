package br.com.fiap.stockagil.repository;

import br.com.fiap.stockagil.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Pesquisar por parte de uma String (ignorando maiúsculas e minúsculas)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :parteNome, '%'))")
    List<Usuario> findByParteNomeContaining(@Param("parteNome") String parteNome);

    // Pesquisar por intervalo de datas (usando data de criação fictícia)
    @Query("SELECT u FROM Usuario u WHERE u.dataCriacao BETWEEN :dataInicio AND :dataFim")
    List<Usuario> findByIntervaloDeDatas(@Param("dataInicio") LocalDateTime dataInicio,
                                         @Param("dataFim") LocalDateTime dataFim);

    // Pesquisar utilizando dois parâmetros (nome e email)
    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.email = :email")
    List<Usuario> findByNomeAndEmail(@Param("nome") String nome, @Param("email") String email);

    // Novo método para buscar por email
    Optional<Usuario> findByEmail(String email);
}
