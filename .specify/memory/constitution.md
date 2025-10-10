<!--
Sync Impact Report:
- Version change: 1.0.0 -> 1.1.0
- Modified principles:
  - Data Persistence (within Technology Stack)
- Added sections: None
- Removed sections: None
- Templates requiring updates:
  - C:\Users\parmentier.g\IdeaProjects\kata-bowling-spec-kit\.specify\templates\plan-template.md (⚠ pending): The "Constitution Check" section needs to be updated to reflect the new allowance for in-memory databases. While the check itself is dynamic, the template's example or guidance might need to be updated to reflect this flexibility.
  - C:\Users\parmentier.g\IdeaProjects\kata-bowling-spec-kit\.specify\templates\tasks-template.md (⚠ pending): Similar to `plan-template.md`, the guidance or examples related to database setup might need to be adjusted to acknowledge in-memory options.
- Follow-up TODOs: None
-->
# kata-bowling-spec-kit Constitution

## Core Principles

### I. Clean Architecture
The application MUST follow the principles of Clean Architecture, ensuring a separation of concerns. This means that the business logic is independent of the UI, database, or any external agency. The architecture will be composed of layers, with the domain and application layers at the core, and the interface and infrastructure layers at the periphery.

### II. Domain-Driven Design (DDD)
The application MUST be structured according to Domain-Driven Design (DDD) principles. This includes the use of a ubiquitous language, a rich domain model, and the clear definition of bounded contexts.

### III. Test-Driven Development (TDD)
The development process MUST follow Test-Driven Development (TDD). All production code is written only after a failing automated test has been written. The process follows the Red-Green-Refactor cycle.

### IV. Behavior-Driven Development (BDD)
The development process MUST incorporate Behavior-Driven Development (BDD). User stories and acceptance criteria should be defined as executable scenarios, preferably using Gherkin syntax. This ensures that the application's behavior is clearly defined and tested from the user's perspective.

### V. Code Quality Principles
The code MUST adhere to the following principles:
- **SOLID**: The five SOLID principles of object-oriented design will be followed to create maintainable and flexible software.
- **YAGNI (You Ain't Gonna Need It)**: No code should be added on the assumption that it will be needed in the future.
- **KISS (Keep It Simple, Stupid)**: Simplicity is a key goal. Solutions should be as simple as possible.
- **DRY (Don't Repeat Yourself)**: Every piece of knowledge must have a single, unambiguous, authoritative representation within a system.

## Technology Stack

The application will be built using the following technologies:
- **Language**: Java
- **Framework**: Spring-Boot
- **API**: RESTful services
- **Data Persistence**: Hibernate with Spring Data JPA (for persistent storage) or in-memory databases (for specific use cases like kata or testing)
- **Testing**: jUnit

## Development Workflow

The development workflow will be centered around TDD and BDD.
1.  A new feature begins with the definition of BDD scenarios.
2.  For each scenario, corresponding acceptance tests are created.
3.  Unit tests are written for each component (TDD).
4.  Code is written to pass the tests.
5.  The code is refactored to improve its design.

## Governance

This constitution is the single source of truth for the development principles of this project. Any deviation must be justified and documented. All code reviews must ensure compliance with this constitution.

**Version**: 1.1.0 | **Ratified**: 2025-10-10 | **Last Amended**: 2025-10-10