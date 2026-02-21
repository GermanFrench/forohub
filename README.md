# 🌐 ForoHub - Proyecto Final AluraLatam

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-green?logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-orange?logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-auth-red)
![Maven](https://img.shields.io/badge/Maven-3.9.2-purple?logo=apachemaven&logoColor=white)
![CI](https://github.com/GermanFrench/forohub/actions/workflows/ci.yml/badge.svg)

---

## 📌 Descripción
ForoHub es una aplicación web de foro educativo donde los usuarios pueden registrarse, iniciar sesión y gestionar tópicos relacionados con cursos específicos.  
Cuenta con **CRUD completo** para usuarios, cursos y tópicos, utilizando **JWT** para autenticación y control de acceso a los endpoints.

El proyecto incluye:
- Seguridad con JWT (**Auth0** y **JJWT**)  
- Control de acceso por roles  
- Soft delete para usuarios y tópicos  
- Paginación y filtrado de tópicos  
- Registro de usuarios con contraseña encriptada con BCrypt  

---

## 🛠 Tecnologías utilizadas
- **Lenguaje:** Java 17  
- **Framework:** Spring Boot 3.5  
- **Seguridad:** Spring Security + JWT  
- **Base de datos:** MySQL 8  
- **Persistencia:** JPA / Hibernate  
- **Migraciones:** Flyway  
- **Dependencias:** Maven  
- **Testing:** Spring Boot Test, Spring Security Test  

---

## ⚙ Instalación y ejecución

> ℹ️ El proyecto Maven se encuentra dentro de la carpeta `foroparaalura/`.

1. **Clonar el repositorio:**

```bash
git clone https://github.com/GermanFrench/forohub.git
cd forohub/foroparaalura
```

2. **Configurar la base de datos en `foroparaalura/src/main/resources/application.properties`:**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foroparaalura
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=tu_clave_super_segura
jwt.expiration=3600000
```

3. **Ejecutar la aplicación:**

```bash
./mvnw spring-boot:run
```

---

## 🔑 Autenticación (JWT)
Para interactuar con los endpoints protegidos, primero debes registrarte y luego iniciar sesión para obtener un token JWT.

**Login**

URL: `POST http://localhost:8080/login`

Body JSON:

```json
{
  "username": "usuario@example.com",
  "password": "tu_contraseña"
}
```

Respuesta:

```json
{
  "token": "<JWT_TOKEN>"
}
```

**Enviar token en solicitudes protegidas:**

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 📋 Endpoints principales

**Usuarios**
- `POST /usuarios` → Registrar usuario
- `GET /usuarios` → Listar usuarios
- `PUT /usuarios/{id}` → Actualizar usuario
- `DELETE /usuarios/{id}` → Desactivar usuario (soft delete)

**Cursos**
- `POST /cursos` → Crear curso
- `GET /cursos` → Listar cursos
- `PUT /cursos/{id}` → Actualizar curso
- `DELETE /cursos/{id}` → Eliminar curso

**Tópicos**
- `POST /topicos` → Crear tópico
- `GET /topicos` → Listar tópicos paginados
- `GET /topicos/todos` → Listar todos los tópicos
- `GET /topicos/primeros` → Listar primeros 10 tópicos
- `GET /topicos/buscar?curso=Matematica&anio=2026` → Filtrar por curso y año
- `GET /topicos/{id}` → Obtener tópico por id
- `PUT /topicos/{id}` → Actualizar tópico
- `DELETE /topicos/{id}` → Eliminar tópico (soft delete)

---

## 🧪 Pruebas
Se recomienda usar Postman o Insomnia para probar los endpoints.  
Recuerda enviar siempre el token JWT en el header `Authorization` para endpoints protegidos.

Para ejecutar los tests del proyecto:

```bash
cd foroparaalura
./mvnw test
```

---

## 📂 Estructura del proyecto

```
forohub/
└─ foroparaalura/               # Módulo Maven principal
   ├─ src/main/java/com/forohub/foroparaalura
   │  ├─ controller/            # Controladores REST
   │  ├─ domain/                # Entidades JPA
   │  ├─ dto/                   # Data Transfer Objects
   │  ├─ infra/security/        # Seguridad, JWT y filtros
   │  ├─ repository/            # Repositorios JPA
   │  └─ service/               # Lógica de negocio
   ├─ src/main/resources/
   │  ├─ application.properties # Configuración de la aplicación
   │  └─ db/migration/          # Migraciones Flyway
   └─ pom.xml
```

---

## 👨‍💻 Autor
Germán French  
[LinkedIn](https://www.linkedin.com/in/german-french/)

---

## 📜 Licencia
Proyecto de aprendizaje, no comercial.

---

## 💡 Notas finales
- Todos los endpoints protegidos requieren JWT válido.
- Se implementó soft delete para tópicos y usuarios.
- La contraseña de usuarios se guarda encriptada con BCrypt.
- JWT tiene expiración configurable en `foroparaalura/src/main/resources/application.properties`.
- Se recomienda probar todas las funcionalidades con Postman/Insomnia antes de subir a producción.
