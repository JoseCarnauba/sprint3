package br.com.fiap.stockagil.entidade;

import br.com.fiap.stockagil.dto.CadastrarUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @Column(name = "data_criacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "usuario")
    private List<PedidoPicklist> pedidos;

    @OneToMany(mappedBy = "usuario")
    private List<PedidoTO> pedido;

    public Usuario(CadastrarUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.dataCriacao = LocalDateTime.now();
    }
}
