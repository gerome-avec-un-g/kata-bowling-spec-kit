package com.example.bowling.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrameTest {

    @Test
    void testFrameCreation() {
        Frame frame = new Frame();
        assertNotNull(frame.getRolls());
        assertTrue(frame.getRolls().isEmpty());
    }

    @Test
    void testAddRollToFrame() {
        Frame frame = new Frame();
        Roll roll1 = new Roll("5");
        frame.addRoll(roll1);
        assertEquals(1, frame.getRolls().size());
        assertEquals("5", frame.getRolls().get(0).getPinsKnockedDown());
    }

    @Test
    void testSetFrameScore() {
        Frame frame = new Frame();
        frame.setFrameScore(10);
        assertEquals(10, frame.getFrameScore());
    }
}
