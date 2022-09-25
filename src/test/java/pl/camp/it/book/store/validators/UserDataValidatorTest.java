package pl.camp.it.book.store.validators;

import org.junit.Test;
import pl.camp.it.book.store.exceptions.ValidationException;

public class UserDataValidatorTest {

    @Test
    public void correctLoginValidationTest() {
        String login = "admin";

        UserDataValidator.validateLogin(login);
    }

    @Test(expected = ValidationException.class)
    public void incorrectLoginValidationTest() {
        String login = "abc";

        UserDataValidator.validateLogin(login);
    }

    @Test
    public void correctPasswordValidationTest() {
        String password = "admin";

        UserDataValidator.validatePassword(password);
    }

    @Test(expected = ValidationException.class)
    public void incorrectPasswordValidationTest() {
        String password = "abc";

        UserDataValidator.validatePassword(password);
    }

    @Test
    public void equalPasswordsValidationTest() {
        String pass1 = "password";
        String pass2 = "password";

        UserDataValidator.validatePasswordsMatch(pass1, pass2);
    }

    @Test(expected = ValidationException.class)
    public void notEqualPasswordValidationTest() {
        String pass1 = "password1";
        String pass2 = "password2";

        UserDataValidator.validatePasswordsMatch(pass1, pass2);
    }
}
