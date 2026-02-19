package com.forohub.foroparaalura.service;

import com.forohub.foroparaalura.domain.Respuesta;
import com.forohub.foroparaalura.domain.Topico;
import com.forohub.foroparaalura.domain.Usuario;
import com.forohub.foroparaalura.dto.DatosRespuesta;
import com.forohub.foroparaalura.repository.RespuestaRepository;
import com.forohub.foroparaalura.repository.TopicoRepository;
import com.forohub.foroparaalura.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public RespuestaService(RespuestaRepository respuestaRepository,
                            TopicoRepository topicoRepository,
                            UsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Respuesta> listar() {
        return respuestaRepository.findAll();
    }

    public Respuesta buscarPorId(Long id) {
        return respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));
    }

    public Respuesta crear(DatosRespuesta datos) {

        Topico topico = topicoRepository.findById(datos.topicoId())
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        Usuario usuario = usuarioRepository.findById(datos.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(datos.mensaje());
        respuesta.setFechaCreacion(LocalDateTime.now());
        respuesta.setTopico(topico);
        respuesta.setAutor(usuario);

        return respuestaRepository.save(respuesta);
    }

    public Respuesta actualizar(Long id, DatosRespuesta datos) {

        Respuesta respuesta = buscarPorId(id);
        respuesta.setMensaje(datos.mensaje());

        return respuestaRepository.save(respuesta);
    }

    public void eliminar(Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new RuntimeException("Respuesta no encontrada");
        }
        respuestaRepository.deleteById(id);
    }
}
