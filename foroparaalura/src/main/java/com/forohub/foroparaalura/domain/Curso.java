package com.forohub.foroparaalura.domain;

import com.forohub.foroparaalura.controller.Categoria;
import com.forohub.foroparaalura.dto.DatosRegistroCurso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private Categoria categoria;

    // Relación con Topico
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Topico> topicos;

    public Curso(@Valid DatosRegistroCurso datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();

    }
}
