package bookstore.dao;

import bookstore.pojo.Order;
import bookstore.pojo.OrderItem;
import java.util.List;

public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
