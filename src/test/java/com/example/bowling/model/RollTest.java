package com.example.bowling.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RollTest {

    @Test
    void testRollCreation() {
        Roll roll = new Roll("5");
        assertEquals("5", roll.getPinsKnockedDown());
    }

    @Test
    void testStrikeRoll() {
        Roll roll = new Roll("X");
        assertEquals("X", roll.getPinsKnockedDown());
    }

    @Test
    void testMissRoll() {
        Roll roll = new Roll("-");
        assertEquals("-", roll.getPinsKnockedDown());
    }

    @Test
    void testSpareRoll() {
        Roll roll = new Roll("/");
        assertEquals("/", roll.getPinsKnockedDown());
    }
}
