package br.com.fiap.stockagil.service;

import br.com.fiap.stockagil.dto.CadastrarUsuario;
import br.com.fiap.stockagil.entidade.Usuario;
import br.com.fiap.stockagil.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência via construtor
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(CadastrarUsuario cadastrarUsuario) {
        // Criar o usuário a partir do DTO
        Usuario usuario = new Usuario(cadastrarUsuario);
        // Salvar e retornar o usuário
        return usuarioRepository.save(usuario);
    }
}

