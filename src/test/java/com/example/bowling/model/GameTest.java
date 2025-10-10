package com.example.bowling.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testPerfectGame() {
        game.roll("X|X|X|X|X|X|X|X|X|X|X|X");
        assertEquals(300, game.score());
    }

    @Test
    void testAllZeros() {
        game.roll("0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0");
        assertEquals(0, game.score());
    }

    @Test
    void testAllFivesAndSpares() {
        game.roll("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/|5");
        assertEquals(150, game.score());
    }

    @Test
    void testMixedGame() {
        game.roll("X|7/|9-|X|-8|8/|-6|X|X|X||81");
        assertEquals(167, game.score());
    }

    @Test
    void testAnotherMixedGame() {
        game.roll("X|X|X|X|X|X|X|X|X|X|X|X");
        assertEquals(300, game.score());
    }
}
