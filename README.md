# 🚀 ForoHub - API REST

![ForoHub Banner](https://img.shields.io/badge/ForoHub-API%20REST-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green) ![Java](https://img.shields.io/badge/Java-17+-red) ![MySQL](https://img.shields.io/badge/MySQL-8.0+-orange)

## 📋 Descripción

ForoHub es una API REST desarrollada en Spring Boot que replica el funcionamiento de un foro de discusión. Los usuarios pueden crear tópicos, responder, y gestionar discusiones de manera segura mediante autenticación JWT.

Este proyecto forma parte del **Challenge Back-End** de Alura Latam y demuestra la implementación completa de un CRUD con las mejores prácticas de desarrollo.

## ✨ Funcionalidades

### 🔐 Autenticación y Autorización
* Login con JWT (JSON Web Token)
* Middleware de seguridad para rutas protegidas
* Expiración automática de tokens (1 hora)

### 📝 Gestión de Tópicos (CRUD Completo)
* ✅ **Crear** nuevo tópico (requiere autenticación)
* 👀 **Consultar** todos los tópicos (público)
* 🔍 **Consultar** tópico específico (público)
* ✏️ **Actualizar** tópico existente (requiere autenticación)
* 🗑️ **Eliminar** tópico (requiere autenticación)

### 🔍 Características Adicionales
* Paginación de resultados
* Ordenamiento por fecha de creación
* Búsqueda por curso y año
* Validación de duplicados
* Manejo de errores personalizado

## 🛠️ Tecnologías Utilizadas

* **Java 17+**
* **Spring Boot 3.4.8**
* **Spring Security** - Autenticación y autorización
* **Spring Data JPA** - Persistencia de datos
* **JWT (Auth0)** - Tokens de autenticación
* **MySQL 8+** - Base de datos
* **Flyway** - Migraciones de base de datos
* **Maven** - Gestión de dependencias
* **Lombok** - Reducción de código boilerplate

## 🏗️ Arquitectura

```
src/
├── main/
│   ├── java/com/alura/forohub/
│   │   ├── controller/         # Controladores REST
│   │   ├── dto/                # Objetos de transferencia de datos
│   │   ├── infra/              # Configuraciones de infraestructura
│   │   │   ├── errores/        # Manejo de excepciones
│   │   │   └── security/       # Configuración de seguridad
│   │   ├── model/              # Entidades JPA
│   │   ├── repository/         # Repositorios de datos
│   │   └── service/            # Lógica de negocio
│   └── resources/
│       ├── application.properties
│       └── db/migration/       # Scripts SQL de Flyway
```

## 🚀 Instalación y Configuración

### 1. Prerrequisitos

```bash
- Java 17 o superior
- MySQL 8.0 o superior
- Maven 3.6 o superior
```

### 2. Clonar el repositorio

```bash
git clone https://github.com/Leo-Rodriguez-S/forohub
cd forohub
```

### 3. Configurar Base de Datos

```sql
CREATE DATABASE forohub_db;
```

### 4. Configurar application.properties

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/forohub_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

# JWT
api.security.token.secret=mi-secreto-super-secreto
```

### 5. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## 📚 Uso de la API

### 🔑 Autenticación

**POST** `/login`

```json
{
    "login": "admin",
    "clave": "123456"
}
```

**Respuesta:**

```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 📋 Endpoints Principales

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| POST | `/login` | Autenticar usuario | ❌ |
| GET | `/topicos` | Listar todos los tópicos | ❌ |
| GET | `/topicos/{id}` | Obtener tópico específico | ❌ |
| POST | `/topicos` | Crear nuevo tópico | ✅ |
| PUT | `/topicos/{id}` | Actualizar tópico | ✅ |
| DELETE | `/topicos/{id}` | Eliminar tópico | ✅ |
| GET | `/topicos/buscar` | Buscar por curso y año | ❌ |

### 📝 Ejemplos de Uso

**Crear Tópico:**

```bash
curl -X POST http://localhost:8080/topicos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "titulo": "¿Cómo usar Spring Security?",
    "mensaje": "Necesito ayuda para configurar Spring Security en mi proyecto",
    "autorId": 1,
    "cursoId": 1
  }'
```

**Listar Tópicos:**

```bash
curl -X GET "http://localhost:8080/topicos?page=0&size=10"
```

**Buscar por Curso y Año:**

```bash
curl -X GET "http://localhost:8080/topicos/buscar?curso=Spring Boot&anio=2024"
```

## 🗄️ Base de Datos

### Estructura de Tablas

- **usuarios**: Información de usuarios del foro
- **cursos**: Categorías de cursos disponibles
- **topicos**: Temas de discusión creados por usuarios

### Datos de Ejemplo

El proyecto incluye datos de prueba:

**Usuarios:**
- `admin` / `123456` (Administrador)
- `usuario1` / `123456` (Usuario regular)

**Cursos:**
- Spring Boot (Programación)
- Java Básico (Programación)
- Python (Programación)
- HTML y CSS (Front-end)

## 🔒 Seguridad

* **JWT Authentication**: Tokens seguros con expiración
* **Password Hashing**: BCrypt para cifrado de contraseñas
* **CORS Configuration**: Configuración de origen cruzado
* **Input Validation**: Validación de datos de entrada
* **Error Handling**: Manejo seguro de excepciones

## 📈 Funcionalidades Avanzadas

* **Paginación**: Resultados paginados para mejor rendimiento
* **Filtros de Búsqueda**: Búsqueda por curso y año
* **Validación de Duplicados**: Previene tópicos repetidos
* **Ordenamiento**: Por fecha de creación ascendente
* **Soft Delete**: Eliminación lógica de registros

## 🧪 Testing

Para ejecutar las pruebas:

```bash
mvn test
```

## 📄 Documentación API

Una vez ejecutada la aplicación, puedes acceder a la documentación Swagger en:

```
http://localhost:8080/swagger-ui.html
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 👨‍💻 Autor

Desarrollado por Leonardo Rodríguez como parte del Challenge Back-End de Alura Latam.

## 📞 Soporte

Si tienes problemas o preguntas, puedes:
- Contactarme

---

⭐ **¡No olvides dar una estrella si te gustó el proyecto!** ⭐