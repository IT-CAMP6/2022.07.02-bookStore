package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.IBasketService;
import pl.camp.it.book.store.session.SessionObject;
import pl.camp.it.book.store.utils.OrderPositionsUtils;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

@Service
public class BasketServiceImpl implements IBasketService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBookDAO bookDAO;

    @Override
    public void addBookToBasket(final int id) {
        Set<OrderPosition> basket = this.sessionObject.getBasket();
        /*for(OrderPosition orderPosition : basket) {
            if(orderPosition.getBook().getId() == id) {
                orderPosition.setQuantity(orderPosition.getQuantity() + 1);
                return;
            }
        }*/

        if(basket.stream()
                .filter(op -> op.getBook().getId() == id)
                .peek(op -> op.setQuantity(op.getQuantity() + 1))
                .findFirst().isPresent()) {
            return;
        }

        Optional<Book> bookBox = this.bookDAO.getBookById(id);
        if(bookBox.isPresent()) {
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setQuantity(1);
            orderPosition.setBook(bookBox.get());
            basket.add(orderPosition);
        }
    }

    @Override
    public double calculateBasketSum() {
        return OrderPositionsUtils.calculateOrderPositionsSum(this.sessionObject.getBasket());
    }
}
