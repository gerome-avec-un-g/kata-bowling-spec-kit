package fr.geromeavecung.bowling;

public record Frame(int roll1, int roll2, FrameType type) {

    public Frame(String frameAsString) {
        this(
                computeRoll1(frameAsString),
                computeRoll2(frameAsString),
                computeFrameType(frameAsString)
        );
    }

    public Frame {
        if (type == FrameType.DEFAULT && (roll1 + roll2) > 9) {
            throw new IllegalArgumentException("sum of roll equal or superior to 10 is not allowed. use spare instead");
        }
    }

    private static int computeRoll1(String frameAsString) {
        if (frameAsString.contains("-") || frameAsString.contains("x")) {
            return 0;
        }
        return Character.getNumericValue(frameAsString.charAt(0));
    }

    private static int computeRoll2(String frameAsString) {
        if (frameAsString.contains("-") || frameAsString.contains("x")) {
            return 10;
        }
        return Character.getNumericValue(frameAsString.charAt(1));
    }

    private static FrameType computeFrameType(String frameAsString) {
        if (frameAsString.contains("x")) {
            return FrameType.STRIKE;
        }
        if (frameAsString.contains("-")) {
            return FrameType.SPARE;
        }
        return FrameType.DEFAULT;
    }

    public int totalDownedPins() {
        return roll1 + roll2;
    }
}
