Perfecto. Este paso es clave: si el enunciado está bien definido, el backend sale casi solo. Te lo voy a dejar como lo haría un equipo real antes de empezar a desarrollar: **claro, coherente, sin ambigüedades y orientado a API REST + Hibernate**.

---

# 📄 Enunciado unificado — Videoclub (versión backend profesional)

## 🎯 Objetivo

Desarrollar una API REST que simule el funcionamiento de un videoclub, permitiendo gestionar películas, usuarios y alquileres, utilizando **Spring Boot + Hibernate (JPA)** y siguiendo buenas prácticas de arquitectura (hexagonal o por capas).

---

# 🎬 Gestión de Películas

El sistema deberá permitir gestionar un catálogo de películas almacenadas en base de datos.

Cada película tendrá los siguientes atributos:

* `id` (String, identificador único)
* `title` (String)
* `genre` (String)
* `releaseYear` (Integer)
* `director` (String)
* `runningTime` (Integer, en minutos)
* `stock` (Integer, número de copias disponibles)
* `available` (Boolean, indica si la película está disponible para alquiler)
* `isNew` (Boolean, indica si es novedad)

### 📌 Reglas de negocio:

* Una película solo puede ser alquilada si `available = true`.
* El campo `available` podrá depender del stock o de su estado en alquiler (según implementación).
* El stock no puede ser negativo.

### 🌐 Endpoints:

* `GET /movies` → Obtener todas las películas
* `GET /movies/available` → Obtener solo las disponibles
* `GET /movies/{id}` → Obtener una película por ID
* `POST /movies` → Crear una película (admin)
* `PATCH /movies/{id}` → Actualizar datos de una película (admin)

---

# 🧾 Gestión de Alquileres

El sistema deberá permitir crear y gestionar alquileres de películas.

Cada alquiler tendrá los siguientes atributos:

* `id` (String, identificador único)
* `movieIds` (List<String>, lista de IDs de películas alquiladas)
* `price` (Double, calculado automáticamente)
* `active` (Boolean, indica si el alquiler está activo)

### 📌 Reglas de negocio:

* Solo se pueden alquilar películas que:

  * Existan en el sistema
  * Estén disponibles (`available = true`)
* Al crear un alquiler:

  * Las películas pasan a `available = false`
  * Se calcula el precio automáticamente:

    * 3€ por película normal
    * 5€ por película marcada como novedad (`isNew = true`)
* Al devolver un alquiler:

  * Las películas vuelven a estar disponibles (`available = true`)
  * El alquiler se marca como `active = false`
  * **El alquiler NO se elimina (persistencia histórica)**

### 🌐 Endpoints:

* `GET /rents` → Obtener todos los alquileres
* `GET /rents/{id}` → Obtener un alquiler por ID
* `GET /rents/by-movie/{movieId}` → Obtener alquileres asociados a una película
* `POST /rents` → Crear un alquiler
* `DELETE /rents/{id}` → Devolver un alquiler (borrado lógico)

---

# 👤 Gestión de Usuarios (Extra)

El sistema podrá gestionar usuarios opcionalmente.

Cada usuario tendrá:

* `id` (String)
* `name` (String)
* `password` (String, opcional en versión básica)
* `rents` (lista de alquileres asociados)

### 📌 Reglas:

* Un usuario puede tener alquileres asociados
* Se pueden realizar alquileres:

  * con usuario
  * sin usuario

### 🌐 Endpoints:

* `GET /users` → Listar usuarios
* `POST /users` → Crear usuario
* `GET /users/{id}/rents` → Obtener alquileres de un usuario

---

# 🔐 Administración

El sistema podrá incluir autenticación básica para administradores:

* Usuario y contraseña definidos en memoria o base de datos
* Solo administradores pueden:

  * Crear películas
  * Modificar películas
  * Consultar ciertos datos sensibles

*(La implementación de seguridad puede ser simplificada en esta práctica)*

---

# ⚙️ Consideraciones técnicas

* Uso de **Spring Boot**
* Persistencia con **Spring Data JPA (Hibernate)**
* Arquitectura recomendada:

  * Hexagonal o por capas (controller → service → domain → repository)
* Uso de:

  * DTOs (Request / Response)
  * Mappers
  * Validadores
  * Manejo global de excepciones

---

# 🚫 Restricciones importantes

* No se deben eliminar alquileres físicamente (usar borrado lógico)
* No se debe permitir alquilar películas no disponibles
* Validar todas las entradas del sistema
* Separar lógica de negocio de la capa de controladores

---

# ⭐ Extras opcionales

* Autenticación real (Spring Security)
* Búsqueda de alquileres por usuario
* Control de stock más avanzado
* Tests unitarios

---

# 🧠 Esto es lo importante (de verdad)

Si implementas esto bien, estás demostrando:

* Diseño de API REST real
* Control de reglas de negocio
* Separación de responsabilidades
* Nivel junior sólido tirando a mid

---

videoclub-backend/
│
├── src/main/java/com/videoclub
│   │
│   ├── VideoclubApplication.java
│   │
│   ├── domain
│   │   ├── model
│   │   │   ├── MovieModel.java
│   │   │   ├── RentModel.java
│   │   │   └── UserModel.java
│   │   │
│   │   ├── enums
│   │   │   ├── MovieGenre.java
│   │   │   ├── RentStatus.java
│   │   │   ├── UserRole.java
│   │   │   └── PriceType.java
│   │   │
│   │   └── ports
│   │       ├── MovieRepositoryPort.java
│   │       ├── RentRepositoryPort.java
│   │       └── UserRepositoryPort.java
│   │
│   ├── application
│   │   ├── dtos
│   │   │   ├── request
│   │   │   │   ├── CreateMovieRequest.java
│   │   │   │   ├── CreateRentRequest.java
│   │   │   │   └── CreateUserRequest.java
│   │   │   │
│   │   │   └── response
│   │   │       └── RentResponse.java
│   │   │
│   │   ├── services
│   │   │   ├── MovieService.java
│   │   │   ├── RentService.java
│   │   │   └── UserService.java
│   │   │
│   │   ├── mappers
│   │   │   ├── MovieMapper.java
│   │   │   ├── RentMapper.java
│   │   │   └── UserMapper.java
│   │   │
│   │   └── validators
│   │       ├── ValidationResult.java
│   │       └── RentValidator.java
│   │
│   ├── infrastructure
│   │   ├── persistence
│   │   │   ├── entities
│   │   │   │   ├── MovieEntity.java
│   │   │   │   ├── RentEntity.java
│   │   │   │   └── UserEntity.java
│   │   │   │
│   │   │   ├── repositories
│   │   │   │   ├── SpringDataMovieRepository.java
│   │   │   │   ├── SpringDataRentRepository.java
│   │   │   │   └── SpringDataUserRepository.java
│   │   │   │
│   │   │   └── adapters
│   │   │       ├── MovieRepositoryAdapter.java
│   │   │       ├── RentRepositoryAdapter.java
│   │   │       └── UserRepositoryAdapter.java
│   │   │
│   │   └── config
│   │       └── BeanConfig.java
│   │
│   └── interfaces
│       └── rest
│           ├── controllers
│           │   ├── MovieController.java
│           │   ├── RentController.java
│           │   └── UserController.java
│           │
│           └── handlers
│               └── GlobalExceptionHandler.java
│
└── src/main/resources
├── application.yml
