package pl.camp.it.book.store.database.hibernate;

import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.model.OrderPosition;

import java.util.List;
import java.util.Optional;

public class OrderPositionDAOStub implements IOrderPositionDAO {
    @Override
    public void persistOrderPosition(OrderPosition orderPosition) {

    }

    @Override
    public Optional<OrderPosition> getOrderPositionById(int id) {
        return Optional.empty();
    }

    @Override
    public List<OrderPosition> getAllOrderPositions() {
        return null;
    }

    @Override
    public void updateOrderPosition(OrderPosition orderPosition) {

    }

    @Override
    public void deleteOrderPosition(int id) {

    }

    @Override
    public List<OrderPosition> getOrderPositionsByOrderId(int id) {
        return null;
    }
}
