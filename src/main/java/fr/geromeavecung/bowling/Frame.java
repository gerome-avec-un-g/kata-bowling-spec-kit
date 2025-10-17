package fr.geromeavecung.bowling;

public class Frame {
    private final Integer roll1;
    private final Integer roll2;
    private final Integer roll3; // only used for 10th frame when bonus rolls exist
    private final FrameType type;

    public Frame(int r1, int r2, FrameType type) {
        this.roll1 = r1;
        this.roll2 = r2;
        this.roll3 = null;
        this.type = type;
    }

    public Frame(int r1, int r2, int r3, FrameType type) {
        this.roll1 = r1;
        this.roll2 = r2;
        this.roll3 = r3;
        this.type = type;
    }

    // convenience constructor from token is handled in Frames parser

    public int totalDownedPins() {
        int total = (roll1 == null ? 0 : roll1) + (roll2 == null ? 0 : roll2);
        if (roll3 != null) total += roll3;
        return total;
    }

    public int roll1() { return roll1 == null ? 0 : roll1; }
    public int roll2() { return roll2 == null ? 0 : roll2; }
    public Integer roll3() { return roll3; }
    public FrameType type() { return type; }

    @Override
    public String toString() {
        return "Frame{" + "r1=" + roll1 + ", r2=" + roll2 + ", r3=" + roll3 + ", type=" + type + '}';
    }
}

