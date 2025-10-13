package fr.geromeavecung.bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @CsvSource({
            "01 01 01 01 01 01 01 01 01 01,10",
            "45 00 00 00 00 00 00 00 00 00,9", // maximum sum without spare
    })
    void compute_score_sum_of_all_frames(String frames, String expectedScore) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isEqualTo(Integer.parseInt(expectedScore));
    }

    @ParameterizedTest
    @CsvSource({
            "0- 00 00 00 00 00 00 00 00 00,10",
            "00 0- 00 00 00 00 00 00 00 00,10",
            "0- 01 00 00 00 00 00 00 00 00,12",
            "0- 10 00 00 00 00 00 00 00 00,12",
            "0- 11 00 00 00 00 00 00 00 00,14",
            "0- 11 10 00 00 00 00 00 00 00,15",
            "00 00 00 00 00 00 00 00 00 0- 10,12",
            "00 00 00 00 00 00 00 00 00 0- 0-,30",
    })
    void compute_score_spare(String frames, String expectedScore) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isEqualTo(Integer.parseInt(expectedScore));
    }

    @ParameterizedTest
    @CsvSource({
            "x 00 00 00 00 00 00 00 00 00,10",
//            "00 0- 00 00 00 00 00 00 00 00,10",
//            "0- 01 00 00 00 00 00 00 00 00,12",
//            "0- 10 00 00 00 00 00 00 00 00,12",
//            "0- 11 00 00 00 00 00 00 00 00,14",
//            "0- 11 10 00 00 00 00 00 00 00,15",
//            "00 00 00 00 00 00 00 00 00 0- 10,12",
//            "00 00 00 00 00 00 00 00 00 0- 0-,30",
    })
    void compute_score_strike(String frames, String expectedScore) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        int score = bowlingCalculator.compute(new Frames(frames));
        assertThat(score).isEqualTo(Integer.parseInt(expectedScore));
    }

    @ParameterizedTest
    @CsvSource({
            "00 00 00 00 00 00 00 00 00,minimum number of frames is 10",
            "55 00 00 00 00 00 00 00 00 00,sum of roll equal or superior to 10 is not allowed. use spare instead",
            "00 00 00 00 00 00 00 00 00 0-,spare on frame 10 requires 11 frames",
            "00 00 00 00 00 00 00 00 00 0- 00 00,spare on frame 10 requires 11 frames",
    })
    void compute_score_errors(String frames, String expectedErrorMessage) {
        BowlingCalculator bowlingCalculator = new BowlingCalculator();
        assertThatThrownBy(() -> bowlingCalculator.compute(new Frames(frames)))
                .hasMessage(expectedErrorMessage);
    }

    // errors : strange characters
    // ten frames + 2 bonus
    // The maximum score is 300, achieved by getting twelve strikes in a row

}
