package pl.camp.it.book.store.controllers.rest.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.dto.BooksResponse;
import pl.camp.it.book.store.services.IBookService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/book")
public class BookRestController {

    @Autowired
    IBookService bookService;

    @Autowired
    IBookDAO bookDAO;

    @ApiOperation(value = "Find books", notes = "Return all books or books matching to pattern")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BooksResponse getBooks(
            @ApiParam(value = "Pattern to find in title or author", example = "Java")
            @RequestParam(required = false)
            String pattern) {
        BooksResponse booksResponse = new BooksResponse();
        if(pattern == null) {
            booksResponse.setBooks(this.bookService.getAllBooks());
        } else {
            booksResponse.setBooks(this.bookService.getFilteredBooks(pattern));
        }
        return booksResponse;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> bookBox = this.bookDAO.getBookById(id);
        if(bookBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bookBox.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book, @PathVariable int id) {
        book.setId(id);
        this.bookDAO.updateBook(book);
        return book;
    }
}
