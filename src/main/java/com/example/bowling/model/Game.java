package com.example.bowling.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Frame> frames;
    private int totalScore;

    public Game() {
        this.frames = new ArrayList<>();
        this.totalScore = 0;
    }

    public void roll(String rollsString) {
        frames.clear();
        String[] frameStrings = rollsString.split("\|");
        List<Roll> allRolls = new ArrayList<>();

        // Parse all rolls first
        for (String frameStr : frameStrings) {
            if (frameStr.isEmpty()) continue;
            for (char rollChar : frameStr.toCharArray()) {
                allRolls.add(new Roll(String.valueOf(rollChar)));
            }
        }

        int rollIndex = 0;
        for (int i = 0; i < 10; i++) { // 10 frames
            Frame frame = new Frame();
            if (rollIndex >= allRolls.size()) break; // No more rolls

            Roll firstRoll = allRolls.get(rollIndex);
            frame.addRoll(firstRoll);
            rollIndex++;

            if (firstRoll.getPinsKnockedDown().equalsIgnoreCase("X")) {
                // Strike, move to next frame
            } else {
                if (rollIndex >= allRolls.size()) break; // No more rolls
                Roll secondRoll = allRolls.get(rollIndex);
                frame.addRoll(secondRoll);
                rollIndex++;
            }
            frames.add(frame);
        }

        // Handle bonus rolls for 10th frame
        if (frames.size() == 10) {
            Frame tenthFrame = frames.get(9);
            if (tenthFrame.isStrike() || tenthFrame.isSpare()) {
                // Add bonus rolls to the 10th frame for scoring purposes
                while (rollIndex < allRolls.size()) {
                    tenthFrame.addRoll(allRolls.get(rollIndex));
                    rollIndex++;
                }
            }
        }
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            Frame currentFrame = frames.get(i);
            if (currentFrame.isStrike()) {
                score += 10 + strikeBonus(i);
            } else if (currentFrame.isSpare()) {
                score += 10 + spareBonus(i);
            } else {
                score += currentFrame.getScore();
            }
        }
        this.totalScore = score;
        return totalScore;
    }

    private int strikeBonus(int frameIndex) {
        int bonus = 0;
        List<Roll> subsequentRolls = getSubsequentRolls(frameIndex, 2);
        for (Roll roll : subsequentRolls) {
            if (roll.getPinsKnockedDown().equalsIgnoreCase("X")) {
                bonus += 10;
            } else if (roll.getPinsKnockedDown().equalsIgnoreCase("/")) {
                // This case should not happen if parsing is correct, as spare is always second roll
                // For now, assume it's 10 - previous roll value
                bonus += (10 - subsequentRolls.get(subsequentRolls.indexOf(roll) - 1).getNumericValue());
            } else if (roll.getPinsKnockedDown().equalsIgnoreCase("-")) {
                bonus += 0;
            } else {
                bonus += Integer.parseInt(roll.getPinsKnockedDown());
            }
        }
        return bonus;
    }

    private int spareBonus(int frameIndex) {
        List<Roll> subsequentRolls = getSubsequentRolls(frameIndex, 1);
        if (!subsequentRolls.isEmpty()) {
            Roll nextRoll = subsequentRolls.get(0);
            if (nextRoll.getPinsKnockedDown().equalsIgnoreCase("X")) {
                return 10;
            } else if (nextRoll.getPinsKnockedDown().equalsIgnoreCase("-")) {
                return 0;
            } else {
                return Integer.parseInt(nextRoll.getPinsKnockedDown());
            }
        }
        return 0;
    }

    private List<Roll> getSubsequentRolls(int frameIndex, int count) {
        List<Roll> subsequentRolls = new ArrayList<>();
        int rollsCollected = 0;
        for (int i = frameIndex + 1; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            for (Roll roll : frame.getRolls()) {
                subsequentRolls.add(roll);
                rollsCollected++;
                if (rollsCollected == count) {
                    return subsequentRolls;
                }
            }
        }
        // For 10th frame bonus rolls, they are added directly to the 10th frame
        if (frameIndex == 9 && (frames.get(9).isStrike() || frames.get(9).isSpare())) {
            for (int i = 2; i < frames.get(9).getRolls().size(); i++) { // Start from 3rd roll in 10th frame
                subsequentRolls.add(frames.get(9).getRolls().get(i));
                rollsCollected++;
                if (rollsCollected == count) {
                    return subsequentRolls;
                }
            }
        }
        return subsequentRolls;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getTotalScore() {
        return totalScore;
    }
}