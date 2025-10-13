package fr.geromeavecung.bowling;

import java.util.Arrays;
import java.util.List;

public record Frames(List<Frame> value) {

    public Frames(String framesAsString) {
        this( Arrays.stream(framesAsString.split(" "))
                .map(Frame::new)
                .toList());
    }


    public int computeScore() {
        return value.stream()
                .mapToInt(frame -> frame.computeScore())
                .sum();
    }
}
