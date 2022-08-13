package pl.camp.it.book.store;

import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.session.SessionObject;

import javax.persistence.Entity;

public class Main {
    public static void main(String[] args) {
        SessionObject book = new SessionObject();

        boolean result = book.getClass().isAnnotationPresent(Entity.class);
        System.out.println(result);
    }
}
