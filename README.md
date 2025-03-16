# 📡 Trace API - Spring Boot Reactive

## 📌 Descripción
Esta es una API backend desarrollada con **Spring Boot**, diseñada para gestionar trazas de mensajes en **MongoDB** de forma reactiva utilizando **Spring WebFlux** y **Spring Data MongoDB Reactive**. También cuenta con un sistema de métricas mediante **Spring Actuator** y **Prometheus**, además de una configuración centralizada con `application.yml`.

## 🚀 Tecnologías Utilizadas
- **Java 8**
- **Spring Boot 2.4.3**
- **Spring WebFlux**
- **Spring Data MongoDB Reactive**
- **Spring Actuator (Prometheus Metrics)**
- **Log4j2 (Configuración en YAML)**
- **Lombok** (para simplificar la gestión de POJOs)
- **Gradle** como gestor de dependencias

## 📂 Estructura del Proyecto
```
trace-api/
│── src/main/java/com/hacom/
│   ├── config/                # Configuración de la aplicación
│   ├── controller/            # Controladores REST
│   ├── model/                 # Modelos de datos
│   ├── repository/            # Repositorios para MongoDB
│   ├── service/               # Lógica de negocio
│   ├── TraceApiApplication.java  # Clase principal
│── src/main/resources/
│   ├── application.yml        # Configuración de la API
│   ├── log4j2.yml             # Configuración de logs
│── build.gradle               # Archivo de configuración de Gradle
│── README.md                  # Documentación
```

## ⚙️ Configuración
Toda la configuración de la API se maneja en **`application.yml`**, incluyendo la conexión a MongoDB y el puerto del servidor:

```yaml
app:
  mongodbDatabase: exampleDb
  mongodbUri: "mongodb://127.0.0.1:27017"
  apiPort: 9898

server:
  port: ${app.apiPort}

spring:
  data:
    mongodb:
      uri: ${app.mongodbUri}
      database: ${app.mongodbDatabase}

management:
  endpoints:
    web:
      exposure:
        include: prometheus
```

## 📌 Endpoints de la API

### 1️⃣ **Insertar un TraceMsg**
**`POST /api/traces`**

📌 **Descripción:** Inserta un mensaje en la base de datos MongoDB.

📥 **Ejemplo de Request:**
```json
{
  "sessionId": "ABC123",
  "payload": "Test message",
  "ts": "2024-03-15T10:00:00Z"
}
```

📤 **Ejemplo de Respuesta:**
```json
{
  "id": "6600123456789abcdef12345",
  "sessionId": "ABC123",
  "payload": "Test message",
  "ts": "2024-03-15T10:00:00Z"
}
```

---

### 2️⃣ **Obtener registros por rango de fecha**
**`GET /api/traces`**

📌 **Descripción:** Obtiene los mensajes almacenados en MongoDB dentro de un rango de fechas.

📥 **Ejemplo de Request (con `@RequestBody`)**
```json
{
  "from": "2024-03-14T00:00:00Z",
  "to": "2024-03-14T23:59:59Z"
}
```

📤 **Ejemplo de Respuesta:**
```json
[
  {
    "id": "6600123456789abcdef12345",
    "sessionId": "ABC123",
    "payload": "Test message",
    "ts": "2024-03-15T10:00:00Z"
  }
]
```

---

### 3️⃣ **Ver métricas en Prometheus**
**`GET /actuator/prometheus`**

📌 **Descripción:** Exposición de métricas de la API.

📤 **Ejemplo de Consulta:**
```sh
curl http://localhost:9898/actuator/prometheus | grep hacom_test_developer_insert_rx
```
📤 **Salida esperada:**
```
hacom_test_developer_insert_rx_total 3.0
```
(El valor incrementa con cada inserción).

## 📝 Registro de Logs (Log4j2)
Los logs se configuran mediante **log4j2.yml** en `src/main/resources/log4j2.yml`, capturando eventos como:
- **Recepción de una nueva solicitud**
- **Inserción exitosa en MongoDB**

Ejemplo de log generado en consola:
```
INFO  [TRACE-API] - Nuevo mensaje insertado: sessionId=ABC123, ts=2024-03-15T10:00:00Z
```

## 📊 Monitorización con Actuator y Prometheus
Se ha configurado **Spring Actuator** para exponer métricas en `/actuator/prometheus`. Esto permite visualizar estadísticas en **Grafana** o **Prometheus**.

## 🚀 Despliegue
1️⃣ **Clonar el repositorio:**
```sh
git clone https://github.com/limpjor/trace-api.git
cd trace-api
```

2️⃣ **Compilar y ejecutar la API:**
```sh
./gradlew bootRun
```

3️⃣ **Verificar que la API esté corriendo:**
```sh
curl http://localhost:9898/actuator/health
```
📤 **Respuesta esperada:**
```json
{
  "status": "UP"
}
```

## 📧 Contacto
Para dudas o mejoras, por favor envía un correo a: **rzegarra@hacom.com.pe**.

---

🚀 **Trace API - Implementación backend para manejo de trazas en MongoDB con Spring WebFlux** 🔥
