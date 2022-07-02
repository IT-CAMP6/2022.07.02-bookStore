package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<User> getUsers();
    Optional<User> getUserByLogin(String login);
    boolean isLoginExist(String login);
    void addUser(User user);
}
