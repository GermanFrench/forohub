package com.forohub.foroparaalura.infra.security;

import com.forohub.foroparaalura.domain.Usuario;
import com.forohub.foroparaalura.service.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtService jwtService;

    public TokenService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String obtenerUsername(String token) {
        return jwtService.extractUsername(token);
    }

    public boolean validarToken(String token, UserDetails user) {
        return jwtService.isTokenValid(token, user);
    }

    public String generarToken(Usuario usuario) {
        return jwtService.generateToken(usuario);
    }
}
