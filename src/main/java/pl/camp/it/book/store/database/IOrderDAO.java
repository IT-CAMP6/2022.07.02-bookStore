package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderDAO {
    void persistOrder(Order order);
    Optional<Order> getOrderById(int id);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void deleteOrder(int id);
    List<Order> getOrdersByUserId(int id);
}
