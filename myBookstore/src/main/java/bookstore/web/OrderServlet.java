package bookstore.web;

import bookstore.pojo.Cart;
import bookstore.pojo.Order;
import bookstore.pojo.OrderItem;
import bookstore.pojo.User;
import bookstore.service.OrderService;
import bookstore.service.impl.OrderServiceImpl;
import bookstore.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
//        System.out.println(loginUser);
        Integer userId = loginUser.getId();
//        System.out.println("userid:"+userId);
//        调用orderService.createOrder(Cart,Userid);生成订单

        String orderId = orderService.createOrder(cart, userId);
        req.getSession().setAttribute("orderId",orderId);
//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        int ret = orderService.sendOrder(orderId);
        if(ret == -1){
            req.setAttribute("error", "修改失败");
        }
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showAllOrders");
//        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItems", orderItems);
        req.getRequestDispatcher("/pages/order/orderDetail.jsp").forward(req, resp);
    }
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
        List<Order> myOrders = orderService.showMyOrders(userId);
//        System.out.println(myOrders);
        req.setAttribute("myOrders", myOrders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        int ret = orderService.receiveOrder(orderId);
        if(ret == -1){
            req.setAttribute("error", "修改失败");
        }
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showMyOrders");
    }
}
