package pl.camp.it.book.store.model.dto;

import pl.camp.it.book.store.configuration.Constants;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO {
    private int id;
    private String user;
    private Order.Status status;
    private LocalDateTime date;
    private Set<String> orderPositions = new HashSet<>();

    public OrderDTO(int id, String user, Order.Status status, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.date = date;
    }

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.user = Constants.host + "/api/user/" + order.getUser().getLogin();
        this.status = order.getStatus();
        this.date = order.getDate();
        for(OrderPosition orderPosition : order.getOrderPositions()) {
            this.orderPositions.add(Constants.host + "/api/orderPosition/" + orderPosition.getId());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Set<String> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<String> orderPositions) {
        this.orderPositions = orderPositions;
    }
}
