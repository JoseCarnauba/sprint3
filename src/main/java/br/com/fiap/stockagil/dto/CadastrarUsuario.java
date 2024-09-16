package br.com.fiap.stockagil.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

public record CadastrarUsuario (

        @NotBlank
        String nome,

        @NotNull @Email
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Pattern(regexp = "^[a-zA-Z0-9]{6,8}$", message = "A senha deve ter entre 6 e 8 caracteres alfanuméricos")
        String senha,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDateTime dataCriacao) {
}


