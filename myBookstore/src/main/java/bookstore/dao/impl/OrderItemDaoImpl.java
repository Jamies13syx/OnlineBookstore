package bookstore.dao.impl;

import bookstore.dao.OrderItemDao;
import bookstore.pojo.Order;
import bookstore.pojo.OrderItem;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`totalPrice`,`orderId`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `name`,`count`,`price`,`totalPrice`,`orderId` from t_order_item where `orderId`=?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
