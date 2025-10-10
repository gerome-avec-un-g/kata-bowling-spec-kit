package com.example.bowling.controller;

import com.example.bowling.BowlingScoreCalculatorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BowlingScoreCalculatorApplication.class)
@AutoConfigureMockMvc
public class ScoreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPerfectGameScore() throws Exception {
        String rolls = "X|X|X|X|X|X|X|X|X|X|X|X";
        mockMvc.perform(post("/api/v1/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rolls\": \"" + rolls + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(300));
    }

    @Test
    void testMixedGameScore() throws Exception {
        String rolls = "X|7/|9-|X|-8|8/|-6|X|X|X||81";
        mockMvc.perform(post("/api/v1/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rolls\": \"" + rolls + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(167));
    }

    @Test
    void testInvalidRollSequenceEmpty() throws Exception {
        String rolls = "";
        mockMvc.perform(post("/api/v1/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rolls\": \"" + rolls + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Roll sequence cannot be empty."));
    }

    @Test
    void testInvalidRollSequenceInvalidCharacter() throws Exception {
        String rolls = "A|X";
        mockMvc.perform(post("/api/v1/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rolls\": \"" + rolls + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid character in roll sequence: 'A'"));
    }

    @Test
    void testInvalidRollSequenceTooManyFrames() throws Exception {
        String rolls = "X|X|X|X|X|X|X|X|X|X|X|X|X";
        mockMvc.perform(post("/api/v1/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rolls\": \"" + rolls + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Incomplete game: less than 10 frames.")); // This error message is from Game.roll, not ideal
    }

    @Test
    void testInvalidRollSequenceSumGreaterThanTen() throws Exception {
        String rolls = "7|4";
        mockMvc.perform(post("/api/v1/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rolls\": \"" + rolls + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid frame: sum of rolls in an open frame cannot exceed 10."));
    }
}
