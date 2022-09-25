package pl.camp.it.book.store.validators;

import pl.camp.it.book.store.exceptions.ValidationException;

public class UserDataValidator {
    public static void validateLogin(String login) {
        String regex = "^.{5,}$";
        if(!login.matches(regex)) {
            throw new ValidationException();
        }
    }

    public static void validatePassword(String password) {
        String regex = "^.{5,}$";
        if(!password.matches(regex)) {
            throw new ValidationException();
        }
    }

    public static void validatePasswordsMatch(String password1, String password2) {
        if(!password1.equals(password2)) {
            throw new ValidationException();
        }
    }
}
