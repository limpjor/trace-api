# TRACE API

Este proyecto consiste en una aplicación backend desarrollada en **Java 8** con **Spring Boot**, implementando una API RESTful que permite almacenar y consultar datos en una base de datos **MongoDB** de manera reactiva.

## Tecnologías Utilizadas

- **Java 8**
- **Spring Boot (2.4.3 recomendado)**
- **Spring WebFlux**
- **Spring Data MongoDB Reactive**
- **Spring Log4j2**
- **Spring Actuator** (exponer métricas para Prometheus)
- **Gradle** (preferido, aunque se puede usar Maven)
- **Lombok** (opcional)

## Configuración
Toda la configuración se realiza mediante el archivo **application.yml**. Las variables de configuración son:

```yaml
mongodbDatabase: exampleDb
mongodbUri: "mongodb://127.0.0.1:27017"
apiPort: 9898
```

## Endpoints de la API

### 1. Insertar un mensaje (POST `/trace`)
Registra un nuevo mensaje en la base de datos MongoDB.

#### Request Body:
```json
{
  "sessionId": "abc123",
  "payload": "Mensaje de prueba",
  "ts": "2024-03-15T12:00:00Z"
}
```

#### Modelo:
```java
public class TraceMsg {
  @Id
  private ObjectId _id;
  private String sessionId;
  private String payload;
  private OffsetDateTime ts;
}
```

### 2. Obtener mensajes por rango de fecha (GET `/trace`)
Obtiene los mensajes almacenados en MongoDB dentro de un rango de fechas.

#### Request Body:
```json
{
  "from": "2024-03-01T00:00:00Z",
  "to": "2024-03-15T23:59:59Z"
}
```

#### Modelo:
```java
public class DateRangeRequest {
  @NotNull
  private OffsetDateTime from;
  @NotNull
  private OffsetDateTime to;
}
```

## Logging

- Se deben registrar logs cuando:
  - Se recibe una solicitud de inserción.
  - Se almacena un mensaje en la base de datos.
- Se debe utilizar **log4j2.yml** en lugar de log4j2.xml.

## Monitoreo con Actuator

Se exponen métricas en formato Prometheus, incluyendo un contador **hacom.test.developer.insert.rx**, que se incrementa en cada inserción.

## Ejecución del Proyecto

1. Clonar el repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd <NOMBRE_DEL_PROYECTO>
   ```

2. Construir el proyecto con Gradle:
   ```bash
   ./gradlew build
   ```

3. Ejecutar la aplicación:
   ```bash
   java -jar build/libs/<nombre-del-jar>.jar
   ```

## Repositorio

Este proyecto debe subirse a un repositorio público en GitHub y compartirse con **rzegarra@hacom.com.pe**.

---

Cualquier duda o mejora, sírvase comunicarla. ¡Buena suerte!

