package com.example.bowling.model;

public class Roll {
    private String pinsKnockedDown;

    public Roll(String pinsKnockedDown) {
        this.pinsKnockedDown = pinsKnockedDown;
    }

    public String getPinsKnockedDown() {
        return pinsKnockedDown;
    }

    public int getNumericValue() {
        if (pinsKnockedDown.equalsIgnoreCase("X")) {
            return 10;
        } else if (pinsKnockedDown.equalsIgnoreCase("/")) {
            // This should be handled in Frame logic, as '/' value depends on previous roll
            return 0; // Placeholder, actual value determined by context
        } else if (pinsKnockedDown.equalsIgnoreCase("-")) {
            return 0;
        } else {
            return Integer.parseInt(pinsKnockedDown);
        }
    }

    public void setPinsKnockedDown(String pinsKnockedDown) {
        this.pinsKnockedDown = pinsKnockedDown;
    }

    @Override
    public String toString() {
        return "Roll{" +
               "pinsKnockedDown='" + pinsKnockedDown + '\'' +
               '}';
    }
}
