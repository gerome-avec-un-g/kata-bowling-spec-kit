package fr.geromeavecung.bowling;

public class ScoreCalculationResponse {
    private int score;

    public ScoreCalculationResponse() {}

    public ScoreCalculationResponse(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

