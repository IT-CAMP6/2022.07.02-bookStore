package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
    Optional<Order> getOrderById(int id);
    List<OrderPosition> getOrderPositionsByOrderId(int id);
    double calculateOrderSum(List<OrderPosition> orderPositions);
}
