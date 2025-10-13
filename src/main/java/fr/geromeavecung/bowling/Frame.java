package fr.geromeavecung.bowling;

public record Frame(int roll1, int roll2) {

    public Frame(String frameAsString) {
        this(
                Character.getNumericValue(frameAsString.charAt(0)),
                Character.getNumericValue(frameAsString.charAt(1))
        );
    }

    public int computeScore() {
        return roll1 + roll2;
    }
}
