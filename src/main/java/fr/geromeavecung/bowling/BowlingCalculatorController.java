package fr.geromeavecung.bowling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BowlingCalculatorController {

    @PostMapping("/score")
    public ResponseEntity<ScoreCalculationResponse> computeScore(@RequestBody ScoreCalculationRequest request) {
        if (request == null || request.getFrames() == null) {
            return ResponseEntity.badRequest().body(new ScoreCalculationResponse(0));
        }
        Frames frames = new Frames(request.getFrames());
        BowlingCalculator calc = new BowlingCalculator();
        int score = calc.compute(frames);
        return ResponseEntity.ok(new ScoreCalculationResponse(score));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ScoreCalculationResponse> handleBadInput(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ScoreCalculationResponse(0));
    }
}

