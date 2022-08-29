package pl.camp.it.book.store.model.dto;

import io.swagger.annotations.ApiModel;
import pl.camp.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "BookList", description = "Object to handle list of books")
public class BooksResponse {

    private List<Book> books = new ArrayList<>();

    public BooksResponse(List<Book> books) {
        this.books = books;
    }

    public BooksResponse() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
