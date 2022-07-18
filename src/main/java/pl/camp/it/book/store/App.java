package pl.camp.it.book.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.camp.it.book.store.configuration.AppConfiguration;
import pl.camp.it.book.store.database.memory.BookDatabase;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        /*ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        BookDatabase database = context.getBean(BookDatabase.class);
        System.out.println(database.getBooks().get(0).getTitle());*/
    }
}
