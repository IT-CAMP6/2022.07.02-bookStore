package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.IBookService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    public List<Book> getAllBooks() {
        return this.bookDAO.getBooks();
    }

    @Override
    public List<Book> getFilteredBooks(String pattern) {
        return this.bookDAO.getFilteredBooks(pattern);
        /*for(Book book : books) {
            if(book.getTitle().toLowerCase().contains(pattern.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(pattern.toLowerCase())) {
                result.add(book);
            }
        }*/
        /*Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if(!book.getTitle().toLowerCase().contains(pattern.toLowerCase()) &&
                    !book.getAuthor().toLowerCase().contains(pattern.toLowerCase())) {
                iterator.remove();
            }
        }

        return books;*/
    }
}
