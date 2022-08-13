package pl.camp.it.book.store.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.camp.it.book.store.database.IEntitySaver;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.exceptions.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IEntitySaver entitySaver;

    @Resource
    SessionObject sessionObject;


    public void authenticate(User user) {
        Optional<User> userFromDatabase = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDatabase.isPresent() &&
                userFromDatabase.get().getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            this.sessionObject.setUser(userFromDatabase.get());
        }
    }

    public void register(User user) {
        if(this.userDAO.isLoginExist(user.getLogin())) {
            throw new LoginAlreadyExistException();
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.entitySaver.persistEntity(user);
    }

    public void logout() {
        this.sessionObject.setUser(null);
    }

    @Override
    public void addCommonInfoToModel(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("user", this.sessionObject.getUser());
    }
}
