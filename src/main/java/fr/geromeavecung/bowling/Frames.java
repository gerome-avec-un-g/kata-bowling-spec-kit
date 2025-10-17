package fr.geromeavecung.bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Frames(List<Frame> value) {

    public Frames(String framesAsString) {
        this(Arrays.stream(framesAsString.split(" "))
                .map(Frame::new)
                .toList());
    }

    public Frames {
        if (value.size() < 10) {
            throw new IllegalArgumentException("minimum number of frames is 10");
        }
        if (value.size() == 10 && value.getLast().type() == FrameType.SPARE) {
            throw new IllegalArgumentException("spare on frame 10 requires 11 frames");
        }
        if (value.size() > 11 && value.get(9).type() == FrameType.SPARE) {
            throw new IllegalArgumentException("spare on frame 10 requires 11 frames");
        }
    }

    public int computeScore() {
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Frame frame = value.get(i);
            int frameScore = frame.totalDownedPins();
            if (frame.type() == FrameType.SPARE) {
                if (i + 1 < value.size()) {
                    Frame nextFrame = value.get(i + 1);
                    if (nextFrame.type()==FrameType.STRIKE) {
                        frameScore += 10;
                    } else {
                        frameScore += nextFrame.roll1();
                    }
                }
            }
            if (frame.type() == FrameType.STRIKE) {
                if (i + 1 < value.size()) {
                    Frame nextFrame = value.get(i + 1);
                    frameScore += nextFrame.totalDownedPins();
                }
                if (i + 2 < value.size()) {
                    Frame nextFrame = value.get(i + 2);
                    frameScore += nextFrame.totalDownedPins();
                }
            }
            scores.add(frameScore);
        }
        System.out.println(scores);
        return scores.stream().mapToInt(score -> score).sum();
    }
}
