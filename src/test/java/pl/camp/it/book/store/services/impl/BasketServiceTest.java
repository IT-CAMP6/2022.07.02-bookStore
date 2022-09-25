package pl.camp.it.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.IBasketService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

public class BasketServiceTest extends GenericServiceTest {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBasketService basketService;

    @Test
    public void addExistingBookToBasket() {
        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setQuantity(1);
        orderPosition1.setBook(
                new Book(1, "ksdfg", "sdfgdf", "asdf", 23.423, "asdf", 12355));

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setQuantity(1);
        orderPosition2.setBook(
                new Book(2, "ksdfg", "sdfgdf", "asdf", 123.23, "asdf", 12355));

        this.sessionObject.getBasket().add(orderPosition1);
        this.sessionObject.getBasket().add(orderPosition2);
        final int bookId = 1;
        int expectedQuantity = 2;
        int expectedBasketSize = 2;

        this.basketService.addBookToBasket(bookId);

        OrderPosition orderPosition = this.sessionObject.getBasket().stream()
                .filter(op -> op.getBook().getId() == bookId)
                .findFirst().get();

        Assert.assertEquals(expectedQuantity, orderPosition.getQuantity());
        Assert.assertEquals(expectedBasketSize, this.sessionObject.getBasket().size());
    }

    @Test
    public void addNotExistingBookToBasket() {
        final int bookId = 3;
        Mockito
                .when(this.bookDAO.getBookById(bookId))
                .thenReturn(Optional.of(
                        new Book(3, "ksdfg", "sdfgdf", "asdf", 23.423, "asdf", 12355)));
        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setQuantity(1);
        orderPosition1.setBook(
                new Book(1, "ksdfg", "sdfgdf", "asdf", 23.423, "asdf", 12355));

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setQuantity(1);
        orderPosition2.setBook(
                new Book(2, "ksdfg", "sdfgdf", "asdf", 123.23, "asdf", 12355));

        this.sessionObject.getBasket().add(orderPosition1);
        this.sessionObject.getBasket().add(orderPosition2);
        int expectedQuantity = 1;
        int expectedBasketSize = 3;

        this.basketService.addBookToBasket(bookId);

        OrderPosition orderPosition = this.sessionObject.getBasket().stream()
                .filter(op -> op.getBook().getId() == bookId)
                .findFirst().get();

        Assert.assertEquals(expectedQuantity, orderPosition.getQuantity());
        Assert.assertEquals(expectedBasketSize, this.sessionObject.getBasket().size());
    }

    @Test
    public void addNotExistingInDBBookToBasket() {
        final int bookId = 3;
        Mockito
                .when(this.bookDAO.getBookById(bookId))
                .thenReturn(Optional.empty());
        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setQuantity(1);
        orderPosition1.setBook(
                new Book(1, "ksdfg", "sdfgdf", "asdf", 23.423, "asdf", 12355));

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setQuantity(1);
        orderPosition2.setBook(
                new Book(2, "ksdfg", "sdfgdf", "asdf", 123.23, "asdf", 12355));

        this.sessionObject.getBasket().add(orderPosition1);
        this.sessionObject.getBasket().add(orderPosition2);
        final int expectedQuantity = 1;
        int expectedBasketSize = 2;

        this.basketService.addBookToBasket(bookId);

        Optional<OrderPosition> orderPosition = this.sessionObject.getBasket().stream()
                .filter(op -> op.getBook().getId() == bookId)
                .findFirst();

        if(orderPosition.isPresent()) {
            Assert.fail();
        }

        Assert.assertEquals(expectedQuantity,
                this.sessionObject.getBasket().stream()
                .filter(op -> op.getBook().getId() == 1)
                .findFirst().get().getQuantity());

        Assert.assertEquals(expectedQuantity,
                this.sessionObject.getBasket().stream()
                        .filter(op -> op.getBook().getId() == 2)
                        .findFirst().get().getQuantity());

        Assert.assertEquals(expectedBasketSize, this.sessionObject.getBasket().size());
    }

}
