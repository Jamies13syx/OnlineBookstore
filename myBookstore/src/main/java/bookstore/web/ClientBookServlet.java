package bookstore.web;

import bookstore.pojo.Book;
import bookstore.pojo.Page;
import bookstore.service.BookService;
import bookstore.service.impl.BookServiceImpl;
import bookstore.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);// 没有参数直接设为默认值
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
//        System.out.println(page);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);// 没有参数直接设为默认值
        int min = WebUtil.parseInt(req.getParameter("min"), 0);
        int max = WebUtil.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min")!= null){
            stringBuilder.append("&min=").append(min);
        }
        if(req.getParameter("max")!= null){
            stringBuilder.append("&max=").append(max);
        }
        page.setUrl(stringBuilder.toString());
//        System.out.println(page);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
