import Model.Address;
import Model.Validations;
import com.mysql.cj.xdevapi.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationsTest {

    @Test
    public void validPasswordShouldReturnTrue(){
        Assertions.assertEquals(Validations.passwordValidator("Test123!"), true);
    }

    @Test
    public void passwordMustHaveNumberCheck(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.passwordValidator("noNumberHere!"));
        assertEquals("Password must have a number!", exception.getMessage());
    }

    @Test
    public void passwordMustHaveLowerCaseCheck(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.passwordValidator("NOLOWERCASEHERE1!"));
        assertEquals("Password must have lower case letter!", exception.getMessage());
    }

    @Test
    public void passwordMustHaveUpperCaseCheck(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.passwordValidator("nouppercasehere1!"));
        assertEquals("Password must have upper case letter!", exception.getMessage());
    }

    @Test
    public void passwordMustHaveSpecialCharacter(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.passwordValidator("ThereIsNoSpecialCharacterHere1"));
        assertEquals("Password must have special character!", exception.getMessage());
    }

    @Test
    public void passwordMustNotHaveSpaceCheck(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.passwordValidator("thereIsA spaceHere1!"));
        assertEquals("Password must not have white space!", exception.getMessage());
    }

    @Test
    public void passwordMustBeAtLeastEightCharactersLong(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.passwordValidator("One1!"));
        assertEquals("Password must be at least 8 characters long!", exception.getMessage());
    }

    @Test
    public void validEmailShouldReturnTrue(){
        Assertions.assertEquals(Validations.emailValidator("test123!@gmail.com"), true);
    }

    @Test
    public void invalidEmailShouldReturnFalse(){
        Assertions.assertEquals(Validations.emailValidator("notAnEmail"), false);
    }

    @Test
    public void validFirstNameShouldReturnTrue(){
        Assertions.assertEquals(Validations.firstNameValidator("Sajal"), true);
    }

    @Test
    public void invalidFirstNameShouldReturnFalse(){
        Assertions.assertEquals(Validations.firstNameValidator("; OR TRUE"), false);
    }

    @Test
    public void validLastNameShouldReturnTrue(){
        Assertions.assertEquals(Validations.firstNameValidator("Risal"), true);
    }

    @Test
    public void invalidLastNameShouldReturnFalse(){
        Assertions.assertEquals(Validations.firstNameValidator("; OR TRUE"), false);
    }

    public Address getTestAddress(){
        return new Address("725", "Lincoln", "NE", "12345", "US");
    }

    @Test
    public void validAddressShouldReturnTrue(){
        Address a = getTestAddress();
        assertEquals(Validations.addressValidator(a), true);
    }

    @Test
    public void invalidStreetShouldThrowInvalidStreet(){
        Address a = getTestAddress();
        a.setStreet("invalid street!");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.addressValidator(a));
        assertEquals("Not a valid street!", exception.getMessage());
    }

    @Test
    public void invalidCityShouldThrowInvalidCity(){
        Address a = getTestAddress();
        a.setCity("1");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.addressValidator(a));
        assertEquals("Not a valid city!", exception.getMessage());
    }

    @Test
    public void invalidStateShouldThrowInvalidState(){
        Address a = getTestAddress();
        a.setState("1");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.addressValidator(a));
        assertEquals("Not a valid state!", exception.getMessage());
    }

    @Test
    public void invalidZipCodeShouldThrowInvalidZipCode(){
        Address a = getTestAddress();
        a.setZipCode("1");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.addressValidator(a));
        assertEquals("Not a valid zipCode!", exception.getMessage());
    }

    @Test
    public void invalidCountryShouldThrowInvalidCountry(){
        Address a = getTestAddress();
        a.setCountry("1");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Validations.addressValidator(a));
        assertEquals("Not a valid country!", exception.getMessage());
    }

}















