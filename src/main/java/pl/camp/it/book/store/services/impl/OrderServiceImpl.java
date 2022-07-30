package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.IOrderService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Autowired
    IBookDAO bookDAO;

    @Override
    public void confirmOrder() {
        Set<OrderPosition> basket = this.sessionObject.getBasket();

        boolean flag = false;
        Iterator<OrderPosition> iterator = basket.iterator();
        while (iterator.hasNext()) {
            OrderPosition orderPosition = iterator.next();
            if(orderPosition.getQuantity() > orderPosition.getBook().getQuantity()) {
                iterator.remove();
                flag = true;
            }
        }

        if(flag) {
            return;
        }

        Order order = new Order();
        order.setStatus(Order.Status.NEW);
        order.setUserId(this.sessionObject.getUser().getId());
        order.setDate(LocalDateTime.now());

        this.orderDAO.persistOrder(order);

        for(OrderPosition orderPosition : basket) {
            orderPosition.setOrderId(order.getId());
            this.orderPositionDAO.persistOrderPosition(orderPosition);
            Book book = this.bookDAO.getBookById(orderPosition.getBook().getId()).get();
            book.setQuantity(book.getQuantity() - orderPosition.getQuantity());
            this.bookDAO.updateBook(book);
        }

        basket.clear();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
