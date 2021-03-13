package bookstore.service;

import bookstore.pojo.Cart;
import bookstore.pojo.Order;
import bookstore.pojo.OrderItem;

import java.util.List;
public interface OrderService {
    String createOrder(Cart cart, Integer userId);
    List<Order> showAllOrders();
    int sendOrder(String orderId);
    List<OrderItem> showOrderDetail(String orderId);
    List<Order> showMyOrders(Integer userId);
    int receiveOrder(String orderId);
}
