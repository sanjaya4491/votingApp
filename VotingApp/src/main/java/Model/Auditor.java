package Model;

public class Auditor extends ApplicationUser {

    public Auditor(String code, String firstName, String lastName, String email, String pwd) {
        super(code, firstName, lastName, email, pwd);
    }

    public String getType() {
        return this.getClass().getName();
    }
}
