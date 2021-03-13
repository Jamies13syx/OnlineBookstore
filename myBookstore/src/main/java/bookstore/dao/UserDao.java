package bookstore.dao;

import bookstore.pojo.User;

public interface UserDao {
    User queryUserByUsername(String username);
    int saveUser(User user);
    User queryUserByUsernameAndPassword(String username, String password);

}
