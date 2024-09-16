package br.com.fiap.stockagil.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration; // Tempo de expiração em milissegundos

    public String generateToken(UserDetails user) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public boolean isTokenValid(String token) {
        String username = getUsernameFromToken(token);
        RowSet userDetails = null;
        return username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Date expiration = decodedJWT.getExpiresAt();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // Se houver exceção, consideramos o token como expirado ou inválido
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (Exception e) {
            return null; // Se houver exceção, consideramos que não foi possível extrair o nome de usuário
        }
    }
}
