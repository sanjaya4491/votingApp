package Model;

import java.util.List;

public class BallotRace extends BallotItem {
    private List<Candidate> candidates;

    public BallotRace(String code, String description, String title, List<Candidate> candidates) {
        super(code, description, title);
        this.candidates = candidates;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public String getType() {
        return this.getClass().getName();
    }
}
