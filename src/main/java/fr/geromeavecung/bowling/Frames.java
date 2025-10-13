package fr.geromeavecung.bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Frames(List<Frame> value) {

    public Frames(String framesAsString) {
        this( Arrays.stream(framesAsString.split(" "))
                .map(Frame::new)
                .toList());
    }


    public int computeScore() {
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < value.size(); i++) {
            Frame frame = value.get(i);
            int frameScore = frame.totalDownedPins();
            if (frame.type() == FrameType.SPARE) {
                if (i + 1 < value.size()) {
                    Frame nextFrame = value.get(i + 1);
                    frameScore += nextFrame.totalDownedPins();
                }
            }
            scores.add(frameScore);
        }
        System.out.println(scores);
        return scores.stream().mapToInt(score -> score).sum();
    }
}
