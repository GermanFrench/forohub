package com.forohub.foroparaalura.service;

import com.forohub.foroparaalura.domain.Usuario;
import com.forohub.foroparaalura.dto.DatosRegistroUsuarios;
import com.forohub.foroparaalura.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrar usuario con password encriptada
    public Usuario registrarUsuario(DatosRegistroUsuarios dto) {
        if (usuarioRepository.existsByLogin(dto.login())) {
            throw new RuntimeException("El login ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(dto.nombre())
                .login(dto.login())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        return usuarioRepository.save(usuario);
    }

    // Cargar usuario por login (UserDetails)
    public UserDetails loadUserByUsername(String username) {
        UserDetails user = usuarioRepository.findByLogin(username);
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return user;
    }
}
