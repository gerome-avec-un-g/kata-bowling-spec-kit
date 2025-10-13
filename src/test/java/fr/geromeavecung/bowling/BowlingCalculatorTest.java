package fr.geromeavecung.bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingCalculatorTest {

    @Test
    void compute_score_0() {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames("0 0 0 0 0 0 0 0 0 0"));
        assertThat(score).isZero();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 0 0 0 0 0 0 0 0 0", "0 1 0 0 0 0 0 0 0 0"})
    void compute_score_1(String frames) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isOne();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 0 0 0 0 0 0 0 0 0", "0 2 0 0 0 0 0 0 0 0", "1 1 0 0 0 0 0 0 0 0"})
    void compute_score_2(String frames) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isEqualTo(2);
    }

    // ten frames + 3 bonus

}
