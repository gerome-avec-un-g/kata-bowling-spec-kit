package fr.geromeavecung.bowling;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BowlingCalculator {
    public int compute(Frames frames) {
        return Arrays.stream(frames.value().split(" "))
                .mapToInt(Integer::parseInt).sum();
    }
}
