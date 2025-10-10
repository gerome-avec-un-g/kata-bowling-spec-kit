package com.example.bowling.service;

import com.example.bowling.exception.InvalidRollSequenceException;
import com.example.bowling.model.Game;
import com.example.bowling.model.Roll;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScoreService {

    private static final Pattern VALID_ROLL_CHARACTERS = Pattern.compile("[X/-]?\\d?");

    public int calculateScore(String rollsString) {
        if (rollsString == null || rollsString.trim().isEmpty()) {
            throw new InvalidRollSequenceException("Roll sequence cannot be empty.");
        }

        // Basic character validation
        String cleanedRollsString = rollsString.replace("|", "");
        for (char c : cleanedRollsString.toCharArray()) {
            if (!isValidRollCharacter(String.valueOf(c))) {
                throw new InvalidRollSequenceException("Invalid character in roll sequence: '" + c + "'");
            }
        }

        Game game = new Game();
        try {
            game.roll(rollsString);
            // Further structural validation after parsing into frames
            if (game.getFrames().size() < 10) {
                throw new InvalidRollSequenceException("Incomplete game: less than 10 frames.");
            }
            // More detailed validation for rolls within frames (e.g., sum > 10 without spare)
            for (int i = 0; i < 10; i++) {
                if (game.getFrames().size() > i) {
                    com.example.bowling.model.Frame frame = game.getFrames().get(i);
                    if (frame.getRolls().size() == 2 && !frame.isSpare() && !frame.isStrike()) {
                        int firstRollValue = frame.getRolls().get(0).getNumericValue();
                        int secondRollValue = frame.getRolls().get(1).getNumericValue();
                        if (firstRollValue + secondRollValue > 10) {
                            throw new InvalidRollSequenceException("Invalid frame: sum of rolls in an open frame cannot exceed 10.");
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {
            throw new InvalidRollSequenceException("Invalid roll value: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidRollSequenceException("Invalid roll sequence structure: " + e.getMessage());
        }

        return game.score();
    }

    private boolean isValidRollCharacter(String s) {
        return s.matches("[0-9X/-]");
    }
}
