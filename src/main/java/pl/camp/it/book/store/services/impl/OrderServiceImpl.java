package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IEntitySaver;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.IOrderService;
import pl.camp.it.book.store.session.SessionObject;
import pl.camp.it.book.store.utils.OrderPositionsUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IEntitySaver entitySaver;

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
        order.setUser(this.sessionObject.getUser());
        order.setDate(LocalDateTime.now());
        order.setOrderPositions(basket);

        this.entitySaver.persistEntity(order);

        for(OrderPosition orderPosition : basket) {
            Book book = this.bookDAO.getBookById(orderPosition.getBook().getId()).get();
            book.setQuantity(book.getQuantity() - orderPosition.getQuantity());
            this.entitySaver.updateEntity(book);
        }

        basket.clear();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return this.orderDAO.getOrderById(id);
    }

    @Override
    public List<OrderPosition> getOrderPositionsByOrderId(int id) {
        Optional<Order> orderBox = this.orderDAO.getOrderById(id);
        if(orderBox.isPresent()) {
            return new ArrayList<>(orderBox.get().getOrderPositions());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public double calculateOrderSum(List<OrderPosition> orderPositions) {
        return OrderPositionsUtils.calculateOrderPositionsSum(orderPositions);
    }
}
