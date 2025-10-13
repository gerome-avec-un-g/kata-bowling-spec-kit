package fr.geromeavecung.bowling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bowling/calculate")
public class BowlingCalculatorController {

    private final BowlingCalculator bowlingCalculator;

    public BowlingCalculatorController(BowlingCalculator bowlingCalculator) {
        this.bowlingCalculator = bowlingCalculator;
    }

    @PostMapping
    public ResponseEntity<ScoreCalculationResponse> createAnAuthor(@RequestBody ScoreCalculationRequest request) {
        int score = bowlingCalculator.compute(new Frames(request.frames()));
        return ResponseEntity.ok(new ScoreCalculationResponse(score));
    }

}
