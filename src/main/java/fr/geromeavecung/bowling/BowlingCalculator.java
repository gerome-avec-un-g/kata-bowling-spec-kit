package fr.geromeavecung.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    public BowlingCalculator() {}

    public int compute(Frames frames) {
        List<Frame> f = frames.value();
        if (f == null || f.size() != 10) throw new IllegalArgumentException("Frames must contain exactly 10 frames");

        int total = 0;
        for (int i = 0; i < 10; i++) {
            Frame frame = f.get(i);
            if (i < 9) {
                if (frame.type() == FrameType.STRIKE) {
                    total += 10 + strikeBonus(f, i);
                } else if (frame.type() == FrameType.SPARE) {
                    total += 10 + spareBonus(f, i);
                } else {
                    total += frame.roll1() + frame.roll2();
                }
            } else {
                // tenth frame: sum all rolls (roll3 may be null)
                total += frame.roll1() + frame.roll2();
                Integer r3 = frame.roll3();
                if (r3 != null) total += r3;
            }
        }
        return total;
    }

    private int spareBonus(List<Frame> frames, int idx) {
        // bonus is next roll (first roll of next frame)
        if (idx + 1 >= frames.size()) return 0;
        Frame next = frames.get(idx + 1);
        return next.roll1();
    }

    private int strikeBonus(List<Frame> frames, int idx) {
        int bonus = 0;
        // need next two rolls across subsequent frames
        int rollsNeeded = 2;
        int j = idx + 1;
        while (rollsNeeded > 0 && j < frames.size()) {
            Frame nf = frames.get(j);
            // first roll
            bonus += nf.roll1();
            rollsNeeded--;
            if (rollsNeeded == 0) break;
            // second roll: for strike frames, roll2 might be 0 (we represented strike as 10,0), but the true second roll after a strike is the next frame's first roll.
            // However, if nf.type()!=STRIKE or it's the tenth frame with a roll3, we should take nf.roll2() (or roll3 if present and required)
            if (nf.type() == FrameType.STRIKE && j < 9) {
                // if next frame is also a strike and not the tenth, its roll2 is a placeholder; we must continue to next frame to get second roll
                j++;
                if (j < frames.size()) {
                    Frame nextNext = frames.get(j);
                    bonus += nextNext.roll1();
                    rollsNeeded--;
                }
                break;
            } else {
                // take roll2 (could be 0) or if tenth and has roll3, the second subsequent roll may be roll2 or roll3 depending
                bonus += nf.roll2();
                rollsNeeded--;
                break;
            }
        }
        return bonus;
    }
}

