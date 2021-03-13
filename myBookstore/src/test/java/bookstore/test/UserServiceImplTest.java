package bookstore.test;

import bookstore.pojo.User;
import bookstore.service.UserService;
import bookstore.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserService userService = new UserServiceImpl();
    @Test
    void registerUser() {
        userService.registerUser(new User(null, "ioio", "asd22", "ioio@qq.com"));
    }

    @Test
    void login() {
        System.out.println(userService.login(new User(null, "ioio", "asd22", "ioio@qq.com")));
    }

    @Test
    void existUsername() {
        System.out.println(userService.existUsername("ioio"));
    }
}