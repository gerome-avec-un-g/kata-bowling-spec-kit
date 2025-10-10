# Constitution for the Bowling Score Application

## Functional Requirements

- **FR-001**: The game consists of 10 frames.
- **FR-002**: In each frame, the player has two rolls to knock down 10 pins.
- **FR-003**: The score for a frame is the total number of pins knocked down in that frame.
- **FR-004**: If the player knocks down all 10 pins on the first roll of a frame, it is a "strike". The score for the frame is 10 plus the total of the next two rolls.
- **FR-005**: If the player knocks down all 10 pins in two rolls of a frame, it is a "spare". The score for the frame is 10 plus the total of the next one roll.
- **FR-006**: If the player rolls a strike in the tenth frame, they get two bonus rolls.
- **FR-007**: If the player rolls a spare in the tenth frame, they get one bonus roll.
- **FR-008**: The input to the scoring system is a sequence of integers representing the number of pins knocked down in each roll.
- **FR-009**: The output of the scoring system is the total score for the game.
- **FR-010**: A gutter game (all rolls are 0) should score 0.
- **FR-011**: A perfect game (12 strikes) should score 300.
