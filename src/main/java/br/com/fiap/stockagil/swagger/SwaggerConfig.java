package br.com.fiap.stockagil.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API StockAgil",
                description = "API para gestão de pedidos de produtos",
                version = "1.0",
                contact = @Contact(name = "Jose", email = "jose.dcarnauba@gmail.com")
        ),
        servers = @Server(url = "http://localhost:8080"),
        security = @SecurityRequirement(name = "apiJwt")
)
@SecurityScheme(
        name = "apiJwt",
        description = "Autenticação baseada em JWT",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
