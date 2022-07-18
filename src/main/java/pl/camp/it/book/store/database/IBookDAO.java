package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    List<Book> getBooks();
    List<Book> getFilteredBooks(String pattern);
    Optional<Book> getBookById(int id);
}
