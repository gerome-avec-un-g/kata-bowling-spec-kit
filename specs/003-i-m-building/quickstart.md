# Quickstart Guide: Bowling Score Calculator API

This guide provides instructions to quickly set up and use the Bowling Score Calculator API.

## 1. Prerequisites

- Java Development Kit (JDK) 21 or higher
- Apache Maven (for building the Spring Boot application)
- A tool for making HTTP requests (e.g., cURL, Postman, Insomnia)

## 2. Build the Application

Navigate to the root directory of the project and build the Spring Boot application using Maven:

```bash
mvn clean install
```

## 3. Run the Application

After a successful build, you can run the application from the command line:

```bash
java -jar target/bowling-score-calculator-api-0.0.1-SNAPSHOT.jar
```

(Note: The exact JAR name might vary based on the project's `pom.xml` configuration.)

The API will start on `http://localhost:8080` by default.

## 4. Make a Sample Request

You can use `cURL` to test the `/api/v1/score` endpoint. The API expects a JSON payload with a `rolls` string.

### Example: Perfect Game

```bash
curl -X POST \
  http://localhost:8080/api/v1/score \
  -H 'Content-Type: application/json' \
  -d '{"rolls": "X|X|X|X|X|X|X|X|X|X|X|X"}'
```

**Expected Response (200 OK)**:

```json
{
  "score": 300
}
```

### Example: Game with Spares and Strikes

```bash
curl -X POST \
  http://localhost:8080/api/v1/score \
  -H 'Content-Type: application/json' \
  -d '{"rolls": "X|7/|9-|X|-8|8/|-6|X|X|X||81"}'
```

**Expected Response (200 OK)**:

```json
{
  "score": 167
}
```

### Example: Invalid Roll Sequence

```bash
curl -X POST \
  http://localhost:8080/api/v1/score \
  -H 'Content-Type: application/json' \
  -d '{"rolls": "X|7/|11"}'
```

**Expected Response (400 Bad Request)**:

```json
{
  "error": "Invalid roll sequence: '11' is not a valid roll."
}
```
