package pl.camp.it.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.IOrderService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

public class OrderServiceTest extends GenericServiceTest {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderService orderService;

    @Test
    public void tooBigQuantityInPosition() {
        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setQuantity(1);
        orderPosition1.setBook(
                new Book(1, "ksdfg", "sdfgdf", "asdf", 23.423, "asdf", 10));

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setQuantity(100);
        orderPosition2.setBook(
                new Book(2, "ksdfg", "sdfgdf", "asdf", 123.23, "asdf", 10));

        this.sessionObject.getBasket().add(orderPosition1);
        this.sessionObject.getBasket().add(orderPosition2);
        int expectedBasketSize = 1;

        this.orderService.confirmOrder();

        Assert.assertEquals(expectedBasketSize, this.sessionObject.getBasket().size());
        Assert.assertTrue(this.sessionObject.getBasket().stream().noneMatch(op -> op.getBook().getId() == 2));
        Mockito.verify(this.entitySaver, Mockito.never()).persistEntity(Mockito.any());
        Mockito.verify(this.entitySaver, Mockito.never()).updateEntity(Mockito.any());
    }

    @Test
    public void confirmCorrectBasket() {
        Mockito
                .when(this.bookDAO.getBookById(1))
                .thenReturn(Optional.of(new Book(1,
                        "ksdfg",
                        "sdfgdf",
                        "asdf",
                        23.423,
                        "asdf",
                        10)));

        Mockito
                .when(this.bookDAO.getBookById(2))
                .thenReturn(Optional.of(new Book(2,
                        "ksdfg",
                        "sdfgdf",
                        "asdf",
                        123.23,
                        "asdf",
                        10)));
        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setQuantity(1);
        orderPosition1.setBook(
                new Book(1, "ksdfg", "sdfgdf", "asdf", 23.423, "asdf", 10));

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setQuantity(1);
        orderPosition2.setBook(
                new Book(2, "ksdfg", "sdfgdf", "asdf", 123.23, "asdf", 10));

        this.sessionObject.getBasket().add(orderPosition1);
        this.sessionObject.getBasket().add(orderPosition2);
        int positionCount = 2;
        int expectedBasketSize = 0;

        this.orderService.confirmOrder();

        Mockito.verify(this.entitySaver, Mockito.times(1)).persistEntity(Mockito.any());
        Mockito.verify(this.entitySaver, Mockito.times(positionCount)).updateEntity(Mockito.any());
        Assert.assertEquals(expectedBasketSize, this.sessionObject.getBasket().size());
    }
}
