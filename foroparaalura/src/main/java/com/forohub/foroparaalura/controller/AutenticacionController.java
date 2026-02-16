package com.forohub.foroparaalura.controller;

import com.forohub.foroparaalura.domain.Usuario;
import com.forohub.foroparaalura.dto.DatosAutenticacion;
import com.forohub.foroparaalura.infra.security.DatosTokenJWT;
import com.forohub.foroparaalura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){

        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.password());
        var authenticacion = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) authenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));

    }
}
