package bookstore.service.impl;

import bookstore.dao.UserDao;
import bookstore.dao.impl.UserDaoImpl;
import bookstore.pojo.User;
import bookstore.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        return userDao.queryUserByUsername(username) != null;

    }
}
