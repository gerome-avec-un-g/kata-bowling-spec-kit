package fr.geromeavecung.bowling;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.provider.Arguments;

public class ParametrizedBowlingCalculatorTest {

    private static Stream<Arguments> gamesProvider() {
        return Stream.of(
            Arguments.of("- - - - - - - - - -", 0, "Gutter game"),
            Arguments.of("11 11 11 11 11 11 11 11 11 11", 20, "All ones"),
            Arguments.of("5/ 3- - - - - - - - -", 16, "Spare then 3"),
            Arguments.of("X 34 - - - - - - - -", 24, "Strike then 3 and 4"),
            Arguments.of("X X 34 - - - - - - -", 47, "Two consecutive strikes"),
            Arguments.of("X X X 42 - - - - - -", 76, "Turkey then 4 and 2"),
            Arguments.of("X X X X X X X X X XXX", 300, "Perfect game"),
            Arguments.of("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5", 150, "All spares with 5"),
            Arguments.of("- - - - - - - - - 5/5", 15, "Spare in tenth with bonus"),
            Arguments.of("- - - - - - - - - X34", 17, "Strike in tenth with two bonus rolls"),
            Arguments.of("X 7/ 9- X -8 8/ -6 X X X81", 167, "Mixed example (common)"),
            Arguments.of("X 5/ - - - - - - - -", 30, "Strike followed by spare"),
            Arguments.of("5/ X - - - - - - - -", 30, "Spare followed by strike"),
            Arguments.of("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-", 90, "All 9- frames"),
            Arguments.of("X 9/ 5- - - - - - - -", 40, "Strike then spare then 5")
        );
    }

    @ParameterizedTest(name = "{2}: {0} => {1}")
    @MethodSource("gamesProvider")
    @DisplayName("Bowling score parameterized tests")
    public void shouldComputeExpectedScore(String game, int expected, String description) {
        Frames frames = new Frames(game);
        BowlingCalculator calc = new BowlingCalculator();
        int score = calc.compute(frames);
        assertEquals(expected, score, description + " should have score " + expected);
    }
}
