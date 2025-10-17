package fr.geromeavecung.bowling;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CompleteBowlingCalculatorTest {

    // Helper that builds a Frames instance and computes score using reflection
    private int score(String frames) throws Exception {
        Class<?> framesClass = Class.forName("fr.geromeavecung.bowling.Frames");
        Constructor<?> framesCtor = framesClass.getConstructor(String.class);
        Object framesObj = framesCtor.newInstance(frames);

        Class<?> calcClass = Class.forName("fr.geromeavecung.bowling.BowlingCalculator");
        Object calc = calcClass.getDeclaredConstructor().newInstance();

        Method compute = calcClass.getMethod("compute", framesClass);
        Object result = compute.invoke(calc, framesObj);
        return ((Integer) result).intValue();
    }

    @Test
    public void shouldScoreGutterGame() throws Exception {
        // 10 frames of 0 (using '-' to represent 0)
        String game = "- - - - - - - - - -";
        assertEquals(0, score(game));
    }

    @Test
    public void shouldScoreAllOnes() throws Exception {
        // 20 rolls of 1 -> represent as 10 frames "11 11 ..."
        String game = "11 11 11 11 11 11 11 11 11 11";
        assertEquals(20, score(game));
    }

    @Test
    public void shouldScoreSingleSpareFollowedByZeros() throws Exception {
        String game = "5/ - - - - - - - - -"; // spare then zeros
        assertEquals(10, score(game));
    }

    @Test
    public void shouldScoreSpareWithBonus() throws Exception {
        String game = "5/ 3- - - - - - - - -"; // spare then 3
        assertEquals(16, score(game)); // spare frame = 10 + 3, next frame 3+0
    }

    @Test
    public void shouldScoreSingleStrikeFollowedByZeros() throws Exception {
        String game = "X - - - - - - - - -";
        assertEquals(10, score(game));
    }

    @Test
    public void shouldScoreStrikeWithFollowingRolls() throws Exception {
        String game = "X 34 - - - - - - - -"; // strike then 3 and 4
        assertEquals(24, score(game)); // 17 + 7
    }

    @Test
    public void shouldScoreTwoConsecutiveStrikes() throws Exception {
        String game = "X X 34 - - - - - - -"; // X X 3 4 ...
        assertEquals(47, score(game)); // 23 + 17 + 7
    }

    @Test
    public void shouldScoreTurkey() throws Exception {
        String game = "X X X 42 - - - - - -"; // three strikes then 4 and 2
        // frame1=30, frame2=24, frame3=16, frame4=6 => total 76
        assertEquals(76, score(game));
    }

    @Test
    public void shouldScorePerfectGame() throws Exception {
        // 12 strikes (10 frames + 2 bonus)
        String game = "X X X X X X X X X XXX"; // common textual representation
        assertEquals(300, score(game));
    }

    @Test
    public void shouldScoreAllSparesWithFive() throws Exception {
        // 10 frames of 5/ with final bonus 5
        String game = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5";
        assertEquals(150, score(game));
    }

    @Test
    public void shouldScoreSpareInTenthFrameWithBonus() throws Exception {
        String game = "- - - - - - - - - 5/5"; // first 9 frames zeros, tenth spare with bonus 5
        assertEquals(15, score(game));
    }

    @Test
    public void shouldScoreStrikeInTenthFrameWithTwoBonusRolls() throws Exception {
        String game = "- - - - - - - - - X34"; // tenth frame strike then 3 and 4 as bonus
        assertEquals(17, score(game));
    }

    @Test
    public void shouldHandleStrikeFollowedBySpare() throws Exception {
        String game = "X 5/ - - - - - - - -"; // strike then spare
        // frame1 = 10 + 5 + 5 = 20, frame2 = 10 => total 30
        assertEquals(30, score(game));
    }

    @Test
    public void shouldHandleSpareFollowedByStrike() throws Exception {
        String game = "5/ X - - - - - - - -"; // spare then strike
        // frame1 = 10 + 10 = 20, frame2 = 10 => total 30
        assertEquals(30, score(game));
    }

    @Test
    public void shouldScoreMixedGameExample() throws Exception {
        // Common example: X 7/ 9- X -8 8/ -6 X X X81
        String game = "X 7/ 9- X -8 8/ -6 X X X81";
        // Known total for this example is 167
        assertEquals(167, score(game));
    }

    @Test
    public void shouldThrowOnNegativePins() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> new Frames("A - - - - - - - - -"));
    }

    @Test
    public void shouldThrowOnPinsGreaterThanTen() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> new Frames("97 - - - - - - - - -"));
    }

    @Test
    public void shouldThrowOnInvalidFrameSum() throws Exception {
        // use a single token '75' which represents a frame with sum 7+5 = 12 (>10) and must be rejected
        assertThrows(IllegalArgumentException.class, () -> new Frames("75 - - - - - - - - -"));
    }

    @Test
    public void shouldBeIdempotent() throws Exception {
        String game = "11 11 11 11 11 11 11 11 11 11";
        // build the Frames object once via reflection
        Class<?> framesClass = Class.forName("fr.geromeavecung.bowling.Frames");
        Constructor<?> framesCtor = framesClass.getConstructor(String.class);
        Object framesObj = framesCtor.newInstance(game);

        Class<?> calcClass = Class.forName("fr.geromeavecung.bowling.BowlingCalculator");
        Object calc = calcClass.getDeclaredConstructor().newInstance();
        Method compute = calcClass.getMethod("compute", framesClass);

        int s1 = ((Integer) compute.invoke(calc, framesObj)).intValue();
        int s2 = ((Integer) compute.invoke(calc, framesObj)).intValue();
        assertEquals(s1, s2);
    }
}
