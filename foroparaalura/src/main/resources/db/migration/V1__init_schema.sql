CREATE TABLE usuarios (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  login VARCHAR(150) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE cursos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  categoria VARCHAR(50) NOT NULL
);

CREATE TABLE topicos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  mensaje TEXT NOT NULL,
  fecha_de_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status ENUM('ABIERTO','CERRADO','RESUELTO') NOT NULL,

  autor_id BIGINT NOT NULL,
  curso_id BIGINT NOT NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE,

  CONSTRAINT fk_topico_autor
    FOREIGN KEY (autor_id) REFERENCES usuarios(id) ON DELETE CASCADE,

  CONSTRAINT fk_topico_curso
    FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE CASCADE
);

CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(1000) NOT NULL,
    topico_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    solucion BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),

    CONSTRAINT fk_respuesta_topico
        FOREIGN KEY (topico_id)
        REFERENCES topicos(id),

    CONSTRAINT fk_respuesta_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
);


