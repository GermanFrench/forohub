package com.forohub.foroparaalura.service;

import com.forohub.foroparaalura.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "mi_clave_super_segura_mas_larga_32_chars"; // >32 chars
    private final long EXPIRATION_MS = 3600000; // 1 hora

    private final Algorithm algoritmo = Algorithm.HMAC256(SECRET);

    // Generar token para un UserDetails
    public String generateToken(UserDetails user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .sign(algoritmo);
    }

    // Extraer username del token
    public String extractUsername(String token) {
        return getDecodedJWT(token).getSubject();
    }

    // Validar token
    public boolean isTokenValid(String token, UserDetails user) {
        try {
            DecodedJWT decoded = getDecodedJWT(token);
            return decoded.getSubject().equals(user.getUsername())
                    && decoded.getExpiresAt().after(new Date());
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    // Decodificar y verificar token
    private DecodedJWT getDecodedJWT(String token) {
        JWTVerifier verifier = JWT.require(algoritmo).build();
        return verifier.verify(token);
    }
}
