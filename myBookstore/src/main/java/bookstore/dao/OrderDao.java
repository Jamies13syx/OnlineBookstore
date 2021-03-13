package bookstore.dao;

import bookstore.pojo.Order;
import java.util.List;

public interface OrderDao {
    int saveOrder(Order order);
    List<Order> queryOrders();
    int changeOrderStatus(String orderId, Integer status);
    List<Order> queryOrdersByUserId(Integer userId);
}
