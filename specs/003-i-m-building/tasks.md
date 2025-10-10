# Tasks: Bowling Score Calculator API

**Input**: Design documents from `/specs/003-i-m-building/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: The feature specification and constitution mandate TDD/BDD, so tests are included.

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`
- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

## Path Conventions
- **Single project**: `src/`, `tests/` at repository root
- Paths shown below assume single project - adjust based on plan.md structure

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and basic structure

- [ ] T001 Create project structure per implementation plan (`src/main/java`, `src/test/java`, `com.example.bowling`, `model`, `service`, `controller` packages)
- [ ] T002 Initialize Java 21 / Spring Boot 3.5 project with Maven dependencies (Spring Web, JUnit)
- [ ] T003 [P] Configure linting and formatting tools (e.g., Checkstyle, Spotless)

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure that MUST be complete before ANY user story can be implemented

**⚠️ CRITICAL**: No user story work can begin until this phase is complete

- [ ] T004 Setup in-memory database configuration (e.g., H2 database for Spring Boot)
- [ ] T005 Configure global error handling and logging infrastructure
- [ ] T006 Create base `Roll` and `Frame` entities/classes (without full scoring logic yet)

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Calculate Score for a Perfect Game (Priority: P1) 🎯 MVP

**Goal**: Implement the core scoring logic for a perfect game.

**Independent Test**: Provide a perfect game roll sequence and verify the score is 300.

### Tests for User Story 1

**NOTE: Write these tests FIRST, ensure they FAIL before implementation**

- [ ] T007 [P] [US1] Write unit tests for `Roll` parsing and basic validation in `src/test/java/com/example/bowling/model/RollTest.java`
- [ ] T008 [P] [US1] Write unit tests for `Frame` creation and basic scoring (e.g., open frames) in `src/test/java/com/example/bowling/model/FrameTest.java`

### Implementation for User Story 1

- [ ] T009 [US1] Implement `Roll` class in `src/main/java/com/example/bowling/model/Roll.java`
- [ ] T010 [US1] Implement `Frame` class in `src/main/java/com/example/bowling/model/Frame.java`
- [ ] T011 [US1] Implement `Game` class with perfect game scoring logic in `src/main/java/com/example/bowling/model/Game.java`
- [ ] T012 [US1] Write integration test for `/score` endpoint with perfect game input in `src/test/java/com/example/bowling/controller/ScoreControllerIntegrationTest.java`
- [ ] T013 [US1] Implement `ScoreService` with perfect game scoring logic in `src/main/java/com/example/bowling/service/ScoreService.java`
- [ ] T014 [US1] Implement `ScoreController` with `/score` endpoint in `src/main/java/com/example/bowling/controller/ScoreController.java`

**Checkpoint**: At this point, User Story 1 should be fully functional and testable independently

---

## Phase 4: User Story 2 - Calculate Score for a Game with Spares and Strikes (Priority: P1)

**Goal**: Extend scoring logic to handle spares and strikes correctly.

**Independent Test**: Provide a game with mixed spares and strikes and verify the correct score.

### Tests for User Story 2

- [ ] T015 [P] [US2] Write unit tests for `Game` class to cover spares and strikes in `src/test/java/com/example/bowling/model/GameTest.java`

### Implementation for User Story 2

- [ ] T016 [US2] Update `Game` class to correctly calculate scores for spares and strikes in `src/main/java/com/example/bowling/model/Game.java`
- [ ] T017 [US2] Update `ScoreService` to use the enhanced `Game` scoring logic in `src/main/java/com/example/bowling/service/ScoreService.java`
- [ ] T018 [US2] Write integration test for `/score` endpoint with mixed game input in `src/test/java/com/example/bowling/controller/ScoreControllerIntegrationTest.java`

**Checkpoint**: At this point, User Stories 1 AND 2 should both work independently

---

## Phase 5: User Story 3 - Handle Invalid Roll Sequences (Priority: P2)

**Goal**: Implement robust input validation and error handling.

**Independent Test**: Provide various invalid roll sequences and verify appropriate error responses.

### Tests for User Story 3

- [ ] T019 [P] [US3] Write unit tests for input validation in `ScoreServiceTest.java` (e.g., empty sequence, invalid characters, too many frames, invalid rolls per frame)

### Implementation for User Story 3

- [ ] T020 [US3] Implement input validation logic in `ScoreService` to check for invalid or incomplete roll sequences in `src/main/java/com/example/bowling/service/ScoreService.java`
- [ ] T021 [US3] Implement custom exception handling for invalid input in `src/main/java/com/example/bowling/exception/InvalidRollSequenceException.java`
- [ ] T022 [US3] Update `ScoreController` to handle `InvalidRollSequenceException` and return appropriate HTTP 400 responses in `src/main/java/com/example/bowling/controller/ScoreController.java`
- [ ] T023 [US3] Write integration tests for `/score` endpoint with invalid inputs in `src/test/java/com/example/bowling/controller/ScoreControllerIntegrationTest.java`

**Checkpoint**: All user stories should now be independently functional

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Improvements that affect multiple user stories

- [ ] T024 Run quickstart.md validation
- [ ] T025 Code cleanup and refactoring
- [ ] T026 Update `README.md` with API usage instructions and link to `quickstart.md`

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - BLOCKS all user stories
- **User Stories (Phase 3+)**: All depend on Foundational phase completion
  - User stories can then proceed in parallel (if staffed)
  - Or sequentially in priority order (P1 → P2 → P3)
- **Polish (Final Phase)**: Depends on all desired user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories
- **User Story 2 (P2)**: Can start after Foundational (Phase 2) - May integrate with US1 but should be independently testable
- **User Story 3 (P3)**: Can start after Foundational (Phase 2) - May integrate with US1/US2 but should be independently testable

### Within Each User Story

- Tests (if included) MUST be written and FAIL before implementation
- Models before services
- Services before endpoints
- Core implementation before integration
- Story complete before moving to next priority

### Parallel Opportunities

- All Setup tasks marked [P] can run in parallel
- All Foundational tasks marked [P] can run in parallel (within Phase 2)
- Once Foundational phase completes, all user stories can start in parallel (if team capacity allows)
- All tests for a user story marked [P] can run in parallel
- Models within a story marked [P] can run in parallel
- Different user stories can be worked on in parallel by different team members

---

## Parallel Example: User Story 1

```bash
# Launch all tests for User Story 1 together:
Task: "Write unit tests for `Roll` parsing and basic validation in `src/test/java/com/example/bowling/model/RollTest.java`"
Task: "Write unit tests for `Frame` creation and basic scoring (e.g., open frames) in `src/test/java/com/example/bowling/model/FrameTest.java`"

# Launch all models for User Story 1 together:
Task: "Implement `Roll` class in `src/main/java/com/example/bowling/model/Roll.java`"
Task: "Implement `Frame` class in `src/main/java/com/example/bowling/model/Frame.java`"
```

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational (CRITICAL - blocks all stories)
3. Complete Phase 3: User Story 1
4. **STOP and VALIDATE**: Test User Story 1 independently
5. Deploy/demo if ready

### Incremental Delivery

1. Complete Setup + Foundational → Foundation ready
2. Add User Story 1 → Test independently → Deploy/Demo (MVP!)
3. Add User Story 2 → Test independently → Deploy/Demo
4. Add User Story 3 → Test independently → Deploy/Demo
5. Each story adds value without breaking previous stories

### Parallel Team Strategy

With multiple developers:

1. Team completes Setup + Foundational together
2. Once Foundational is done:
   - Developer A: User Story 1
   - Developer B: User Story 2
   - Developer C: User Story 3
3. Stories complete and integrate independently

---

## Notes

- [P] tasks = different files, no dependencies
- [Story] label maps task to specific user story for traceability
- Each user story should be independently completable and testable
- Verify tests fail before implementing
- Commit after each task or logical group
- Stop at any checkpoint to validate story independently
- Avoid: vague tasks, same file conflicts, cross-story dependencies that break independence
