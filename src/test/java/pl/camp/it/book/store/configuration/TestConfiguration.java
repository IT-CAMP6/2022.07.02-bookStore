package pl.camp.it.book.store.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.camp.it.book.store.database.*;
import pl.camp.it.book.store.database.hibernate.*;

@Configuration
@ComponentScan(basePackages = {
        "pl.camp.it.book.store.controllers",
        "pl.camp.it.book.store.services",
        "pl.camp.it.book.store.session"
})
public class TestConfiguration {

    /*@Bean
    public IBookDAO bookDAO() {
        return new BookDAOStub();
    }

    @Bean
    public IEntitySaver entitySaver() {
        return new EntitySaverStub();
    }

    @Bean
    public IOrderDAO orderDAO() {
        return new OrderDAOStub();
    }

    @Bean
    public IOrderPositionDAO orderPositionDAO() {
        return new OrderPositionDAOStub();
    }

    @Bean
    public IUserDAO userDAO() {
        return new UserDAOStub();
    }*/

    /*@Bean
    public IBookDAO bookDAO() {
        return Mockito.mock(IBookDAO.class);
    }*/
}
