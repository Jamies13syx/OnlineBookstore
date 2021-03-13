package bookstore.service.impl;

import bookstore.dao.BookDao;
import bookstore.dao.OrderDao;
import bookstore.dao.OrderItemDao;
import bookstore.dao.impl.BookDaoImpl;
import bookstore.dao.impl.OrderDaoImpl;
import bookstore.dao.impl.OrderItemDaoImpl;
import bookstore.pojo.*;
import bookstore.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
//        System.out.println(order);
        // 保存订单
        orderDao.saveOrder(order);

        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookDao.updateBook(book);

        }
        // 清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }


    @Override
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public int receiveOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 2);
    }
}
