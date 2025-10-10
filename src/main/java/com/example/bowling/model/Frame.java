package com.example.bowling.model;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private List<Roll> rolls;
    private Integer frameScore;

    public Frame() {
        this.rolls = new ArrayList<>();
    }

    public List<Roll> getRolls() {
        return rolls;
    }

    public void addRoll(Roll roll) {
        this.rolls.add(roll);
    }

    public Integer getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(Integer frameScore) {
        this.frameScore = frameScore;
    }

    public boolean isStrike() {
        return rolls.size() == 1 && rolls.get(0).getPinsKnockedDown().equalsIgnoreCase("X");
    }

    public boolean isSpare() {
        return rolls.size() == 2 && (rolls.get(0).getNumericValue() + rolls.get(1).getNumericValue() == 10);
    }

    public int getScore() {
        int score = 0;
        for (Roll roll : rolls) {
            if (roll.getPinsKnockedDown().equalsIgnoreCase("X")) {
                score += 10;
            } else if (roll.getPinsKnockedDown().equalsIgnoreCase("/")) {
                // This will be handled by Game class for spare bonus
                score += (10 - rolls.get(0).getNumericValue());
            } else if (roll.getPinsKnockedDown().equalsIgnoreCase("-")) {
                score += 0;
            } else {
                score += Integer.parseInt(roll.getPinsKnockedDown());
            }
        }
        return score;
    }

    @Override
    public String toString() {
        return "Frame{" +
               "rolls=" + rolls +
               ", frameScore=" + frameScore +
               '}';
    }
}
