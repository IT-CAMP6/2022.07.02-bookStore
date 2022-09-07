package pl.camp.it.book.store.controllers.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.camp.it.book.store.services.IBasketService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/basket")
public class BasketRestController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBasketService basketService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void addBookToBasket(@PathVariable int id) {
        if(!this.sessionObject.isLogged()) {
            return;
        }
        this.basketService.addBookToBasket(id);
    }
}
