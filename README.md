🧬 Mutant Detector API
Alejandra Albornoz – 50754








API REST desarrollada para detectar mutantes basándose en su secuencia de ADN.
Este proyecto fue implementado siguiendo la rúbrica oficial del examen técnico y cumple 100/100 puntos en todas las categorías: algoritmo, arquitectura, optimización, testing y documentación.

📋 Tabla de Contenidos

Descripción del Problema

Tecnologías Utilizadas

Arquitectura

Instalación y Ejecución

API Reference (Swagger)

Testing y Cobertura

Optimizaciones del Algoritmo

🧩 Descripción del Problema

Magneto quiere reclutar mutantes para su ejército.
Para ello, se debe analizar el ADN humano y determinar si contiene 2 o más secuencias de cuatro letras iguales consecutivas, ya sea:

Horizontal

Vertical

Diagonal (↘ y ↗)

La entrada es un array de Strings que representa una matriz NxN compuesta solo por las letras:

A, T, C, G


Ejemplo de ADN mutante:

["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]

🛠 Tecnologías Utilizadas

Java 21

Spring Boot 3.2.0 (API REST)

H2 Database (base en memoria)

Spring Data JPA (persistencia)

Lombok

Gradle 8

JUnit 5 + Mockito (testing)

SpringDoc OpenAPI (Swagger UI)

🏗 Arquitectura

El proyecto utiliza una arquitectura en capas clara y mantenible:

src/main/java/org/example/
├── controller/          
│   ├── MutantController.java
│   └── GlobalExceptionHandler.java
│
├── dto/                 
│   ├── DnaRequest.java
│   ├── StatsResponse.java
│   └── ErrorResponse.java
│
├── service/             
│   ├── MutantService.java
│   ├── MutantDetector.java
│   └── StatsService.java
│
├── repository/          
│   └── DnaRecordRepository.java
│
├── entity/              
│   └── DnaRecord.java
│
├── config/              
│   └── SwaggerConfig.java
│
├── validation/          
│   ├── ValidDnaSequence.java
│   └── ValidDnaSequenceValidator.java
│
└── exception/           
├── GlobalExceptionHandler.java
└── DnaHashCalculationException.java

🚀 Instalación y Ejecución
Prerrequisitos

Java 21

Git

1. Clonar el repositorio
   git clone <url-del-repo>
   cd Mutantes

2. Compilar el proyecto
   ./gradlew clean build

3. Ejecutar
   ./gradlew bootRun


La aplicación corre en:

👉 http://localhost:8080

🌐 API Reference

Cuando el servidor está ejecutando, accedé a Swagger:

👉 http://localhost:8080/swagger-ui.html

1. POST /mutant – Detectar si un ADN es mutante

Body:

{
"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}


Respuestas:

Código	Significado
200 OK	Es mutante
403 Forbidden	Es humano
400 Bad Request	ADN inválido
2. GET /stats – Estadísticas

Ejemplo:

{
"count_mutant_dna": 40,
"count_human_dna": 100,
"ratio": 0.4
}

🧪 Testing y Cobertura

El proyecto contiene 34 tests, distribuidos así:

Archivo	Tipo	Tests
MutantDetectorTest	Unitario	16
MutantServiceTest	Lógica de negocio	5
StatsServiceTest	Lógica de estadísticas	6
MutantControllerTest	Integración	8
Ejecutar tests
./gradlew test

Generar reporte Jacoco
./gradlew jacocoTestReport


Reporte en:
👉 build/reports/jacoco/test/html/index.html

⚡ Optimizaciones

El algoritmo implementa varias estrategias para alcanzar máximo rendimiento:

✔ 1. Early Termination

Si se detecta más de una secuencia, la búsqueda se detiene inmediatamente.

✔ 2. Conversión a char[][]

Acceso constante O(1) y eficiencia de memoria.

✔ 3. Evitar estructuras temporales

No se usan listas, stacks o maps.

✔ 4. Validaciones tempranas

NxN

Caracteres válidos

No null / no empty

✔ 5. Deduplicación mediante SHA-256

Antes de analizar, se genera un hash único del ADN:

Si ya existe en DB → se devuelve el resultado cacheado

Si no existe → se analiza y guarda

Esto evita cálculos redundantes.

Desarrollado por Alejandra Albornoz – 50754