package beans.configuration;

import beans.daos.mocks.UserDAOMock;
import com.booking.beans.models.User;
import com.booking.beans.services.UserService;
import com.booking.beans.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/12/2016
 * Time: 1:36 PM
 */
@Configuration
public class TestUserServiceConfiguration {

    @Bean
    public User testUser1() {
        return new User(0, "Dmytro Babichev", "dmitriy.vbabichev@gmail.com", java.time.LocalDate.of(1992, 4, 29));
    }

    @Bean
    public User testUser2() {
        return new User(1, "Dmytro Babichev", "laory@yandex.ru", java.time.LocalDate.of(1992, 4, 29));
    }

    @Bean
    public UserDAOMock userDAO() {
        return new UserDAOMock(Arrays.asList(testUser1(), testUser2()));
    }

    @Bean(name = "testUserServiceImpl")
    public UserService userServiceImpl() {
        return new UserServiceImpl(userDAO());
    }
}
