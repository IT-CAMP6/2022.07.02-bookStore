package pl.camp.it.book.store.database.hibernate;

import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderDAOStub implements IOrderDAO {
    @Override
    public void persistOrder(Order order) {

    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void updateOrder(Order order) {



        
    }

    @Override
    public void deleteOrder(int id) {

    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        return null;
    }
}
