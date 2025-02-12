# API Rest - Consulta de Noticias

Este proyecto es una API Rest para la consulta de noticias. La API permite buscar noticias a partir de un texto proporcionado como parámetro 

## Requisitos

- Java 17
- Maven

Uso
Endpoint de Consulta de Noticias
URL: /api/noticias/consulta
Método: GET
Parámetros:
q : Texto para buscar noticias.

Ejemplo de Solicitud:
curl -H  "http://localhost:8080/api/noticias/consulta?q=texto"

Desarrollo
Compilar y Ejecutar
Para compilar y ejecutar el proyecto, usa los siguientes comandos:

mvn clean install
mvn spring-boot:run
