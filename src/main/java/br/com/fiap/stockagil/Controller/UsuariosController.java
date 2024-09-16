package br.com.fiap.stockagil.Controller;

import br.com.fiap.stockagil.dto.CadastrarUsuario;
import br.com.fiap.stockagil.entidade.Usuario;
import br.com.fiap.stockagil.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Cadastrar usuario no banco de dados
    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastrarUsuario dados,
                                          UriComponentsBuilder uriBuilder) {
        Usuario usuario = new Usuario(dados);
        usuarioRepository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
    }

    // ===================================================================================================================

    // Implementação CP3

    // Pesquisar por parte de uma String
    @GetMapping("/pesquisar-nome")
    public List<Usuario> pesquisarPorParteNome(@RequestParam String parteNome) {
        return usuarioRepository.findByParteNomeContaining(parteNome);
    }

}
