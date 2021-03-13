package bookstore.service.impl;

import bookstore.dao.BookDao;
import bookstore.dao.impl.BookDaoImpl;
import bookstore.pojo.Book;
import bookstore.pojo.Page;
import bookstore.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);//总记录数
        Integer pageTotal = (pageTotalCount - 1)/pageSize + 1;//总页码数
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);//传入特定页码
        page.setPageSize(pageSize);
        int begin = (page.getPageNo() - 1) * pageSize;//当前页码包含图书
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);//总记录数
        Integer pageTotal = (pageTotalCount - 1)/pageSize + 1;//总页码数
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);//传入特定页码
        page.setPageSize(pageSize);
        int begin = (page.getPageNo() - 1) * pageSize;//当前页码包含图书
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);
        return page;
    }
}
