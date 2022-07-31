package pl.camp.it.book.store.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int userId;
    private Status status;
    private LocalDateTime date;

    public Order(int id, int userId, Status status, LocalDateTime date) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.date = date;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public enum Status {
        NEW("Nowy"),
        PAID("Opłacony"),
        CONFIRMED("Potwierdzony"),
        SENT("Wysłany"),
        DONE("Zakończony");

        private String textValue;

        Status(String textValue) {
            this.textValue = textValue;
        }

        public String getTextValue() {
            return textValue;
        }
    }
}
