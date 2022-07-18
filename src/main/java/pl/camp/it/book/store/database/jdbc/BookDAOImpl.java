package pl.camp.it.book.store.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAOImpl implements IBookDAO {

    @Autowired
    Connection connection;
    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("isbn"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Problem z baza !!");
        }
        return books;
    }

    @Override
    public List<Book> getFilteredBooks(String pattern) {
        List<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook WHERE title like ? OR author like ?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, "%" + pattern + "%");
            ps.setString(2, "%" + pattern + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("isbn"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Problem z baza !!");
        }
        return books;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        try {
            String sql = "SELECT * FROM tbook WHERE id = ?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return Optional.of(new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("isbn"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Problem z baza !!");
        }
        return Optional.empty();
    }
}
