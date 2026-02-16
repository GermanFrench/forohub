package com.forohub.foroparaalura.service;

import com.forohub.foroparaalura.domain.*;
import com.forohub.foroparaalura.domain.Usuario;
import com.forohub.foroparaalura.dto.*;
import com.forohub.foroparaalura.dto.DatosActualizarTopico;
import com.forohub.foroparaalura.dto.DatosRegistroTopico;
import com.forohub.foroparaalura.dto.TopicoResponseDto;
import com.forohub.foroparaalura.infra.exception.RecursoNoEncontradoException;
import com.forohub.foroparaalura.repository.CursoRepository;
import com.forohub.foroparaalura.repository.TopicoRepository;
import com.forohub.foroparaalura.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository,
                         UsuarioRepository usuarioRepository,
                         CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    // =============================
    // CREAR TÓPICO
    // =============================
    @Transactional
    public TopicoResponseDto crearTopico(DatosRegistroTopico dto) {

        Usuario autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Curso no encontrado"));

        if (topicoRepository.existsByTituloAndMensaje(dto.titulo(), dto.mensaje())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }

        Topico topico = Topico.builder()
                .titulo(dto.titulo())
                .mensaje(dto.mensaje())
                .fechaDeCreacion(LocalDateTime.now())
                .status(Status.ABIERTO)
                .autor(autor)
                .curso(curso)
                .activo(true) // <--- esto es obligatorio con @Builder
                .build();


        Topico guardado = topicoRepository.save(topico);

        return mapearADTO(guardado);
    }

    // =============================
    // LISTAR CON PAGINACIÓN
    // =============================
    public Page<TopicoResponseDto> listar(Pageable pageable) {
        return topicoRepository
                .findByActivoTrue(pageable)
                .map(this::mapearADTO);
    }


    // =============================
    // PRIMEROS 10 ORDENADOS ASC
    // =============================
    public List<TopicoResponseDto> listarPrimeros10() {
        return topicoRepository
                .findTop10ByOrderByFechaDeCreacionAsc()
                .stream()
                .map(this::mapearADTO)
                .toList();
    }

    // =============================
    // BUSCAR POR CURSO Y AÑO
    // =============================
    public List<TopicoResponseDto> buscarPorCursoYAnio(String curso, int anio) {
        return topicoRepository
                .buscarPorCursoYAnio(curso, anio)
                .stream()
                .map(this::mapearADTO)
                .toList();
    }

    // =============================
    // MAPPER
    // =============================
    private TopicoResponseDto mapearADTO(Topico topico) {
        return new TopicoResponseDto(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus().name(),
                new AutorDTO(
                        topico.getAutor().getId(),
                        topico.getAutor().getNombre(),
                        topico.getAutor().getLogin()
                ),
                new CursoDTO(
                        topico.getCurso().getId(),
                        topico.getCurso().getNombre(),
                        topico.getCurso().getCategoria().name()
                )
        );
    }

    public TopicoResponseDto obtenerPorId(Long id) {

        Topico topico = topicoRepository
                .findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tópico no encontrado"
                        )
                );


        return mapearADTO(topico);
    }

    public TopicoResponseDto actualizarTopico(Long id, DatosActualizarTopico dto) {

        Topico topico = topicoRepository
                .findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado o desactivado"));

        Usuario autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        return mapearADTO(topicoRepository.save(topico));
    }



    // ELIMINACIÓN (SOFT DELETE)
    @Transactional
    public void eliminar(Long id) {

        Topico topico = topicoRepository
                .findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tópico no encontrado"
                        )
                );

        topico.setActivo(false);
    }
    public List<TopicoResponseDto> listarTodos() {
        return topicoRepository
                .findByActivoTrue()
                .stream()
                .map(this::mapearADTO)
                .toList();
    }

}


