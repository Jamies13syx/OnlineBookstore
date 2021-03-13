package bookstore.test;

import bookstore.dao.UserDao;
import bookstore.dao.impl.UserDaoImpl;
import bookstore.pojo.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

//    @Test
//    void queryUserByUsername() {
//        UserDao userDao = new UserDaoImpl();
//        if(userDao.queryUserByUsername("admin") == null){
//            System.out.println("ok");
//        }
//        else {
//            System.out.println("no");
//        }
//    }

    @Test
    void saveUser() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(null, "james", "aaa1111", "james@qq.com")));
    }

//    @Test
//    void queryUserByUsernameAndPassword() {
//        UserDao userDao = new UserDaoImpl();
//        if(userDao.queryUserByUsernameAndPassword("admin", "a11") == null){
//            System.out.println("ok");
//        }
//        else {
//            System.out.println("no");
//        }
//    }
}