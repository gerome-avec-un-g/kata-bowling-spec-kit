# Feature Specification: Bowling Score Calculator API

**Feature Branch**: `003-i-m-building`  
**Created**: vendredi 10 octobre 2025  
**Status**: Draft  
**Input**: User description: "I'm building a REST API which, given a valid sequence of rolls for one line of American Ten-Pin Bowling, produces the total score for the game."

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Calculate Score for a Perfect Game (Priority: P1)

This story describes the primary function of the API: calculating the score for a perfect game of bowling. A perfect game consists of 12 strikes, resulting in a score of 300.

**Why this priority**: This is a fundamental and easily verifiable scenario that demonstrates the core scoring logic for strikes.

**Independent Test**: Can be fully tested by providing a perfect game roll sequence and verifying the score is 300.

**Acceptance Scenarios**:

1.  **Given** a valid sequence of rolls representing a perfect game ("X|X|X|X|X|X|X|X|X|X|X|X"), **When** the API calculates the score, **Then** the total score is 300.

---

### User Story 2 - Calculate Score for a Game with Spares and Strikes (Priority: P1)

This story covers the calculation of scores for games that include a mix of strikes, spares, and open frames, which are common occurrences in bowling.

**Why this priority**: This scenario is crucial for validating the API's ability to handle the various scoring rules beyond just perfect games.

**Independent Test**: Can be fully tested by providing a game with mixed spares and strikes and verifying the correct score.

**Acceptance Scenarios**:

1.  **Given** a valid sequence of rolls with spares and strikes (e.g., "X|7/|9-|X|-8|8/|-6|X|X|X||81"), **When** the API calculates the score, **Then** the total score is correctly computed based on American Ten-Pin Bowling rules.

---

### User Story 3 - Handle Invalid Roll Sequences (Priority: P2)

This story ensures the API gracefully handles and provides informative feedback for roll sequences that do not conform to the rules of American Ten-Pin Bowling.

**Why this priority**: Robust error handling is essential for a user-friendly and reliable API, preventing unexpected behavior and guiding users to provide valid input.

**Independent Test**: Can be tested by providing various invalid roll sequences and verifying appropriate error responses.

**Acceptance Scenarios**:

1.  **Given** an invalid sequence of rolls (e.g., "X|7/|11" where '11' is an impossible roll), **When** the API attempts to calculate the score, **Then** the API returns an error indicating invalid input.
2.  **Given** an incomplete sequence of rolls (e.g., "X|7/|9" which is less than 10 frames), **When** the API attempts to calculate the score, **Then** the API returns an error indicating an incomplete game.

---

### Edge Cases

-   What happens when an empty roll sequence is provided? The API should return an error indicating an incomplete game.
-   How does the system handle non-numeric or invalid characters in the roll sequence that are not part of standard bowling notation (e.g., 'A', '#')? The API should return an error for invalid input.
-   What happens if more than 10 frames are provided in the roll sequence? The API should return an error for invalid input.
-   What happens if a frame has more than two rolls and is not a strike (e.g., "5|5|5")? The API should return an error for invalid input.
-   What happens if the sum of two rolls in a frame is greater than 10 and it's not a spare (e.g., "7|4")? The API should return an error for invalid input.

## Requirements *(mandatory)*

### Functional Requirements

-   **FR-001**: The API MUST accept a sequence of rolls for a single line of American Ten-Pin Bowling as input.
-   **FR-002**: The API MUST validate the provided sequence of rolls according to American Ten-Pin Bowling rules.
-   **FR-003**: The API MUST calculate the total score for a valid sequence of rolls according to American Ten-Pin Bowling rules.
-   **FR-004**: The API MUST return the calculated total score for a valid input.
-   **FR-005**: The API MUST return a clear error message for invalid or incomplete roll sequences.
-   **FR-006**: The API MUST support standard bowling notation for rolls (e.g., 'X' for strike, '/' for spare, '-' for miss).

### Key Entities *(include if feature involves data)*

-   **Game**: Represents a single game of bowling.
    *   Attributes: `rolls` (sequence of individual roll scores), `score` (total calculated score).
-   **Roll**: Represents a single attempt to knock down pins.
    *   Attributes: `pinsKnockedDown` (number of pins, or special character like 'X', '/', '-').
-   **Frame**: Represents a single frame in a bowling game.
    *   Attributes: `rolls` (list of rolls within the frame), `frameScore` (score for the frame).

## Success Criteria *(mandatory)*

### Measurable Outcomes

-   **SC-001**: The API MUST correctly calculate the score for 100% of valid bowling game sequences provided in test cases.
-   **SC-002**: The API MUST respond to score calculation requests within 100 milliseconds for 99% of requests under normal load.
-   **SC-003**: The API MUST return an appropriate error response for 100% of invalid roll sequences.
-   **SC-004**: The API MUST be able to process 1000 score calculation requests per second without error.