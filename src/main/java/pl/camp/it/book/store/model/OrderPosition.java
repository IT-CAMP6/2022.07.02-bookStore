package pl.camp.it.book.store.model;

public class OrderPosition {
    private int id;
    private Book book;
    private int quantity;
    private int orderId;

    public OrderPosition(int id, Book book, int quantity, int orderId) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public OrderPosition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
