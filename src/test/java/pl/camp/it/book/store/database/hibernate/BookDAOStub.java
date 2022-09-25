package pl.camp.it.book.store.database.hibernate;

import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public class BookDAOStub implements IBookDAO {
    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public List<Book> getFilteredBooks(String pattern) {
        return null;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return Optional.empty();
    }

    @Override
    public void updateBook(Book book) {

    }
}
