package Model;

public class Candidate extends Person {

    public Candidate(String code, String firstName, String lastName) {
        super(code, firstName, lastName);
    }

    @Override
    public String getType() {
        return this.getClass().getName();
    }
}
