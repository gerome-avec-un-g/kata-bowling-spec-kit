# Data Model: Bowling Score Calculator API

## Entities

### Game

Represents a single game of American Ten-Pin Bowling.

-   **Attributes**:
    -   `rolls`: A sequence of individual roll scores (e.g., a string like "X|7/|9-|").
    -   `score`: The total calculated score for the game (integer).

### Roll

Represents a single attempt to knock down pins within a frame.

-   **Attributes**:
    -   `pinsKnockedDown`: The number of pins knocked down in this roll. Can be an integer (0-10) or a special character for strikes ('X'), spares ('/'), or misses ('-').

### Frame

Represents one of the ten frames in a bowling game.

-   **Attributes**:
    -   `rolls`: A list of `Roll` objects within this frame (1 or 2 rolls, or 3 in the tenth frame for strikes/spares).
    -   `frameScore`: The score calculated for this specific frame, including bonuses from subsequent rolls.
