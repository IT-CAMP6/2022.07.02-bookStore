package pl.camp.it.book.store.services;

public interface IBasketService {
    void addBookToBasket(int id);
    double calculateBasketSum();
}
