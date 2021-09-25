package Model;

public class BallotIssue extends BallotItem {

    public BallotIssue(String code, String description, String title) {
        super(code, description, title);
    }

    @Override
    public String getType() {
        return this.getClass().getName();
    }
}
