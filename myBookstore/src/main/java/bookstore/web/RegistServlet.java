package bookstore.web;

import bookstore.pojo.User;
import bookstore.service.UserService;
import bookstore.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if("abcde".equalsIgnoreCase(code)){
            if(userService.existUsername(username)){
                req.setAttribute("msg", "用户名["+username+"]已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }
            else {
                userService.registerUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        }
        else {
            req.setAttribute("msg", "验证码错误!");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }


    }
}
