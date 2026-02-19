package com.forohub.foroparaalura.controller;

import com.forohub.foroparaalura.domain.Respuesta;
import com.forohub.foroparaalura.dto.DatosRespuesta;
import com.forohub.foroparaalura.repository.RespuestaRepository;
import com.forohub.foroparaalura.repository.TopicoRepository;
import com.forohub.foroparaalura.repository.UsuarioRepository;
import com.forohub.foroparaalura.service.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    private final RespuestaService service;

    public RespuestaController(RespuestaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Respuesta>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Respuesta> crear(@RequestBody @Valid DatosRespuesta datos) {
        Respuesta respuesta = service.crear(datos);
        return ResponseEntity
                .created(URI.create("/respuestas/" + respuesta.getId()))
                .body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> actualizar(@PathVariable Long id,
                                                @RequestBody @Valid DatosRespuesta datos) {
        return ResponseEntity.ok(service.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


