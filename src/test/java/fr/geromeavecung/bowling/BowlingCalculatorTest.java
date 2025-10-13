package fr.geromeavecung.bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingCalculatorTest {

    @Test
    void compute_score_0() {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames("00 00 00 00 00 00 00 00 00 00"));
        assertThat(score).isZero();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "10 00 00 00 00 00 00 00 00 00",
            "01 00 00 00 00 00 00 00 00 00",
            "00 01 00 00 00 00 00 00 00 00",
    })
    void compute_score_1(String frames) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isOne();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "20 00 00 00 00 00 00 00 00 00",
            "00 20 00 00 00 00 00 00 00 00",
            "10 10 00 00 00 00 00 00 00 00"
    })
    void compute_score_2(String frames) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource({"01 01 01 01 01 01 01 01 01 01,10"})
    void compute_score_sum_of_all_frames(String frames, String expectedScore) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isEqualTo(Integer.parseInt(expectedScore));
    }

    @ParameterizedTest
    @CsvSource({
            "0- 00 00 00 00 00 00 00 00 00,10",
            "00 0- 00 00 00 00 00 00 00 00,10",
            "0- 01 00 00 00 00 00 00 00 00,12"
    })
    void compute_score_spare(String frames, String expectedScore) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isEqualTo(Integer.parseInt(expectedScore));
    }

    // ten frames + 3 bonus
    // The maximum score is 300, achieved by getting twelve strikes in a row

}
