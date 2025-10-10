# Implementation Plan: Bowling Score Calculator API

**Branch**: `003-i-m-building` | **Date**: vendredi 10 octobre 2025 | **Spec**: ../../specs/003-i-m-building/spec.md
**Input**: Feature specification from `/specs/003-i-m-building/spec.md`

**Note**: This template is filled in by the `/speckit.plan` command. See `.specify/templates/commands/plan.md` for the execution workflow.

## Summary

This plan outlines the implementation of a REST API that calculates the total score for a single line of American Ten-Pin Bowling, given a valid sequence of rolls. The API will be built using Java 21 and Spring Boot 3.5, utilizing an in-memory database for data persistence.

## Technical Context

**Language/Version**: Java 21  
**Primary Dependencies**: Spring-Boot 3.5  
**Storage**: In-memory database  
**Testing**: jUnit  
**Target Platform**: Server environment (e.g., Linux server)  
**Project Type**: web  
**Performance Goals**: Respond to score calculation requests within 100 milliseconds for 99% of requests under normal load; process 1000 score calculation requests per second without error.  
**Constraints**: <100ms p99 response time  
**Scale/Scope**: Single line of American Ten-Pin Bowling score calculation

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- **Language**: Java - **PASS** (Matches constitution)
- **Framework**: Spring-Boot - **PASS** (Matches constitution)
- **API**: RESTful services - **PASS** (Matches constitution)
- **Data Persistence**: In-memory database - **VIOLATION** (Constitution specifies "Hibernate with Spring Data JPA")
- **Testing**: jUnit - **PASS** (Matches constitution)

## Project Structure

### Documentation (this feature)

```
specs/003-i-m-building/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── bowling/
│   │               ├── model/
│   │               ├── service/
│   │               └── controller/
│   └── resources/
├── test/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── bowling/
│   │               ├── model/
│   │               ├── service/
│   │               └── controller/
│   └── resources/
```

**Structure Decision**: The project will follow a standard Spring Boot application structure, with `src/main/java` for source code and `src/test/java` for tests, organized by package (`com.example.bowling`). Within the `bowling` package, sub-packages for `model`, `service`, and `controller` will be used to align with Clean Architecture principles.

## Complexity Tracking

*Fill ONLY if Constitution Check has violations that must be justified*

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| Data Persistence: In-memory database vs. Hibernate with Spring Data JPA | For a simple kata, an in-memory database simplifies setup and development, allowing focus on the core scoring logic without the overhead of a full persistent database configuration. | Using Hibernate with Spring Data JPA would introduce unnecessary complexity for a stateless scoring API where data persistence beyond the request lifecycle is not required. |