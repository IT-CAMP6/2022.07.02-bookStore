package pl.camp.it.book.store.utils;

import pl.camp.it.book.store.model.OrderPosition;

import java.util.Collection;

public class OrderPositionsUtils {
    public static double calculateOrderPositionsSum(Collection<OrderPosition> orderPositions) {
        double sum = 0.0;
        for(OrderPosition orderPosition : orderPositions) {
            sum += orderPosition.getQuantity() * orderPosition.getBook().getPrice();
        }

        return sum;
    }
}
