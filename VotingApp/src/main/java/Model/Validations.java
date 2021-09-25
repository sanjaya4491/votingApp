package Model;

import org.apache.commons.validator.routines.EmailValidator;

public class Validations {
    public static boolean passwordValidator(String pwd) {
        if (!pwd.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must have a number!");
        } else if (!pwd.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Password must have lower case letter!");
        } else if (!pwd.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must have upper case letter!");
        } else if (!pwd.matches(".*[!@#$%^&+=].*")) {
            throw new IllegalArgumentException("Password must have special character!");
        } else if (pwd.matches(".*\\s.*")) {
            throw new IllegalArgumentException("Password must not have white space!");
        } else if (pwd.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long!");
        } else {
            return true;
        }
    }

    public static boolean emailValidator(String email) {
        EmailValidator v = EmailValidator.getInstance();
        return v.isValid(email);
    }

    public static boolean firstNameValidator(String firstName) {
        return firstName.matches("^[a-zA-Z]*$");
    }

    public static boolean lastNameValidator(String lastName) {
        return lastName.matches("^[a-zA-Z]*$");
    }

    public static boolean addressValidator(Address a) {
        if (!a.getStreet().matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Not a valid street!");
        } else if (!a.getCity().matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Not a valid city!");
        } else if (!a.getState().matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Not a valid state!");
        } else if (!a.getZipCode().matches("^\\d{5}(?:[-\\s]\\d{4})?$")) {
            throw new IllegalArgumentException("Not a valid zipCode!");
        } else if (!a.getCountry().matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Not a valid country!");
        } else {
            return true;
        }
    }
}
