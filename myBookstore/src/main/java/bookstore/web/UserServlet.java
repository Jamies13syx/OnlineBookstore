package bookstore.web;
import bookstore.pojo.User;
import bookstore.service.UserService;
import bookstore.service.impl.UserServiceImpl;
import bookstore.utils.WebUtil;
import com.google.gson.Gson;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtil.copyParamToBean(req.getParameterMap(), new User());
        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        if ((user = userService.login(user)) == null) {
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = WebUtil.copyParamToBean(req.getParameterMap(), new User());
        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        String email = req.getParameter("email");
        //获取验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");

        if(token!=null && token.equalsIgnoreCase(code)){
            if(userService.existUsername(username)){
                req.setAttribute("msg", "用户名["+username+"]已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }
            else {
                userService.registerUser(user);
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

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
       req.getSession().invalidate();
       resp.sendRedirect(req.getContextPath());
    }
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        boolean existsUsername = userService.existUsername(username);
        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
