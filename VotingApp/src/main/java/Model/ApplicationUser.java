package Model;

public abstract class ApplicationUser extends Person {
    private String email;
    private String pwd;

    public ApplicationUser(String code, String firstName, String lastName, String email, String pwd) {
        super(code, firstName, lastName);
        this.email = email;
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public abstract String getType();
}
