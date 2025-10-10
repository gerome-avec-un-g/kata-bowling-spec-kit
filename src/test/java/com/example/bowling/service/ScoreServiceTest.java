package com.example.bowling.service;

import com.example.bowling.exception.InvalidRollSequenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreServiceTest {

    private ScoreService scoreService;

    @BeforeEach
    void setUp() {
        scoreService = new ScoreService();
    }

    @Test
    void testPerfectGameScore() {
        assertEquals(300, scoreService.calculateScore("X|X|X|X|X|X|X|X|X|X|X|X"));
    }

    @Test
    void testMixedGameScore() {
        assertEquals(167, scoreService.calculateScore("X|7/|9-|X|-8|8/|-6|X|X|X||81"));
    }

    @Test
    void testEmptyRollSequence() {
        assertThrows(InvalidRollSequenceException.class, () -> scoreService.calculateScore(""));
    }

    @Test
    void testInvalidCharacters() {
        assertThrows(InvalidRollSequenceException.class, () -> scoreService.calculateScore("A|X"));
    }

    @Test
    void testTooManyFrames() {
        assertThrows(InvalidRollSequenceException.class, () -> scoreService.calculateScore("X|X|X|X|X|X|X|X|X|X|X|X|X"));
    }

    @Test
    void testInvalidRollsPerFrame() {
        assertThrows(InvalidRollSequenceException.class, () -> scoreService.calculateScore("5|5|5"));
    }

    @Test
    void testSumGreaterThanTenWithoutSpare() {
        assertThrows(InvalidRollSequenceException.class, () -> scoreService.calculateScore("7|4"));
    }
}
