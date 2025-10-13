package fr.geromeavecung.bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingCalculatorTest {

    @Test
    void compute_score_0() {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames("0 0 0 0 0 0 0 0 0 0"));
        assertThat(score).isZero();
    }

    @Test
    void compute_score_1() {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames("1 0 0 0 0 0 0 0 0 0"));
        assertThat(score).isOne();
    }

    // ten frames + 3 bonus

}
