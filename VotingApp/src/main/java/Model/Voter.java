package Model;

public class Voter extends ApplicationUser {
    private Address address;

    public Voter(String code, String firstName, String lastName, String email, String pwd, Address address) {
        super(code, firstName, lastName, email, pwd);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String getType() {
        return this.getClass().getName();
    }
}
