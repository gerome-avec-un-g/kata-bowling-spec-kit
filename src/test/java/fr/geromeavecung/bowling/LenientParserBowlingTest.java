package fr.geromeavecung.bowling;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LenientParserBowlingTest {

    private static Stream<Arguments> lenientGames() {
        return Stream.of(
            Arguments.of("x x x x x x x x x xxx", 300, "lowercase x perfect game"),
            Arguments.of("10 10 10 10 10 10 10 10 10 101010", 300, "" + "perfect game using 10 tokens"),
            Arguments.of("x|x|x|x|x|x|x|x|x|xxx", 300, "pipes as separators"),
            Arguments.of("X,7/,9-,X,-8,8/,-6,X,X,X81", 167, "commas as separators"),
            Arguments.of("5/ F- F- F- F- F- F- F- F- F-", 10, "fouls as '-' (F)")
        );
    }

    @ParameterizedTest(name = "{2}: {0} => {1}")
    @MethodSource("lenientGames")
    @DisplayName("Lenient parser acceptance tests")
    public void shouldParseLenientFormats(String game, int expected, String description) {
        Frames frames = new Frames(game);
        BowlingCalculator calc = new BowlingCalculator();
        int score = calc.compute(frames);
        assertEquals(expected, score, description + " should compute expected score");
    }
}
