package fr.geromeavecung.bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    public Frames(String input) {
        if (input == null) throw new IllegalArgumentException("Input cannot be null");
        // normalize common variations: lowercase x, 0 as '-', allow '|' or ',' as separators
        String normalized = input.trim().replace('x', 'X').replace('|', ' ').replace(',', ' ').replace('0', '-');
        String[] tokens = normalized.split("\\s+");
        if (tokens.length == 0) throw new IllegalArgumentException("Empty input");

        List<Frame> parsed = new ArrayList<>();

        int idx = 0;
        // parse frames until we have 9 frames or run out tokens
        while (parsed.size() < 9 && idx < tokens.length) {
            String t = tokens[idx++];
            Frame f = parseNormalFrame(t);
            parsed.add(f);
        }

        if (idx >= tokens.length) {
            throw new IllegalArgumentException("Missing 10th frame");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < tokens.length; i++) sb.append(tokens[i]);
        String tenthToken = sb.toString();
        // allow a single '-' to mean two zeros for the tenth frame
        if ("-".equals(tenthToken)) tenthToken = "--";
        Frame tenth = parseTenthFrame(tenthToken);
        parsed.add(tenth);

        if (parsed.size() != 10) throw new IllegalArgumentException("Invalid number of frames parsed: " + parsed.size());

        this.frames = Collections.unmodifiableList(parsed);
    }

    private Frame parseNormalFrame(String token) {
        if (token == null || token.isEmpty()) throw new IllegalArgumentException("Empty frame token");
        if (token.equals("X")) {
            return new Frame(10, 0, FrameType.STRIKE);
        }
        if (token.length() == 1 && token.equals("-")) {
            return new Frame(0, 0, FrameType.DEFAULT);
        }
        if (token.length() == 1 && Character.isDigit(token.charAt(0))) {
            int r1 = charToPins(token.charAt(0));
            return new Frame(r1, 0, FrameType.DEFAULT);
        }
        if (token.length() == 2) {
            char c1 = token.charAt(0);
            char c2 = token.charAt(1);
            int r1 = charToPins(c1);
            if (c2 == '/') {
                if (r1 < 0 || r1 > 10) throw new IllegalArgumentException("Invalid spare notation");
                int r2 = 10 - r1;
                return new Frame(r1, r2, FrameType.SPARE);
            } else {
                int r2 = charToPins(c2);
                if (r1 < 0 || r2 < 0) throw new IllegalArgumentException("Invalid pins");
                if (r1 + r2 > 10) throw new IllegalArgumentException("Frame pins exceed 10");
                return new Frame(r1, r2, FrameType.DEFAULT);
            }
        }
        throw new IllegalArgumentException("Invalid frame token: " + token);
    }

    private Frame parseTenthFrame(String token) {
        if (token == null || token.isEmpty()) throw new IllegalArgumentException("Empty tenth frame token");
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (c == 'X') {
                rolls.add(10);
            } else if (c == '-') {
                rolls.add(0);
            } else if (c == '/') {
                if (rolls.isEmpty()) throw new IllegalArgumentException("Spare without previous roll");
                int last = rolls.get(rolls.size()-1);
                if (last < 0 || last > 10) throw new IllegalArgumentException("Invalid previous roll for spare");
                rolls.add(10 - last);
            } else if (Character.isDigit(c)) {
                rolls.add(Character.getNumericValue(c));
            } else {
                throw new IllegalArgumentException("Invalid character in tenth frame: " + c);
            }
            if (rolls.size() > 3) throw new IllegalArgumentException("Too many rolls in tenth frame");
        }

        if (rolls.size() < 2) throw new IllegalArgumentException("Tenth frame must have at least two rolls");

        int r1 = rolls.get(0);
        int r2 = rolls.get(1);
        int r3 = rolls.size() == 3 ? rolls.get(2) : -1;

        if (r1 < 0 || r1 > 10) throw new IllegalArgumentException("Invalid pins in tenth frame");
        if (r2 < 0 || r2 > 10) throw new IllegalArgumentException("Invalid pins in tenth frame");
        if (r3 != -1 && (r3 < 0 || r3 > 10)) throw new IllegalArgumentException("Invalid pins in tenth frame");

        FrameType type;
        if (r1 == 10) type = FrameType.STRIKE;
        else if (r1 + r2 == 10) type = FrameType.SPARE;
        else type = FrameType.DEFAULT;

        if (r3 == -1) {
            return new Frame(r1, r2, type);
        } else {
            return new Frame(r1, r2, r3, type);
        }
    }

    private int charToPins(char c) {
        if (c == '-') return 0;
        if (Character.isDigit(c)) {
            return Character.getNumericValue(c);
        }
        throw new IllegalArgumentException("Invalid pin character: " + c);
    }

    public List<Frame> value() {
        return frames;
    }

    @Override
    public String toString() {
        return "Frames{" + "frames=" + frames + '}';
    }
}
