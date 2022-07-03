package pl.camp.it.book.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.exceptions.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;


    public void authenticate(User user) {
        Optional<User> userFromDatabase = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDatabase.isPresent() && userFromDatabase.get().getPassword().equals(user.getPassword())) {
            this.sessionObject.setLogged(true);
        }
    }

    public void register(User user) {
        if(this.userDAO.isLoginExist(user.getLogin())) {
            throw new LoginAlreadyExistException();
        }
        this.userDAO.addUser(user);
    }

    public void logout() {
        this.sessionObject.setLogged(false);
    }
}
