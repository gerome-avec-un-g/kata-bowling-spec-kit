package fr.geromeavecung.bowling;

public class ScoreCalculationRequest {
    private String frames;

    public ScoreCalculationRequest() {}

    public ScoreCalculationRequest(String frames) {
        this.frames = frames;
    }

    public String getFrames() {
        return frames;
    }

    public void setFrames(String frames) {
        this.frames = frames;
    }
}

