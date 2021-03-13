package bookstore.service;

import bookstore.pojo.User;

public interface UserService {
    void registerUser(User user);
    User login(User user);
    boolean existUsername(String username);
}
