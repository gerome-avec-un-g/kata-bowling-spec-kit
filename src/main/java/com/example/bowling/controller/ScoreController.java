package com.example.bowling.controller;

import com.example.bowling.exception.InvalidRollSequenceException;
import com.example.bowling.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/score")
    public Map<String, Integer> calculateScore(@RequestBody Map<String, String> payload) {
        String rolls = payload.get("rolls");
        int score = scoreService.calculateScore(rolls);
        return Map.of("score", score);
    }

    @ExceptionHandler(InvalidRollSequenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidRollSequenceException(InvalidRollSequenceException ex) {
        return Map.of("error", ex.getMessage());
    }
}
