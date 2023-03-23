package com.basara.service.impl;

import com.basara.mapper.BookMapper;
import com.basara.pojo.Book;
import com.basara.pojo.Page;
import com.basara.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author basara
 * @create 2022-12-10 4:50
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;


    public List<Book> queryBooks() {
        return bookMapper.queryBooks();
    }

    public Book queryBookById(Integer id) {
        return bookMapper.queryBookById(id);
    }

    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    public int deleteBook(Integer id) {
        return bookMapper.deleteBookById(id);
    }

    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    public Page<Book> getBookPage(Integer pageNum) {
        Page<Book> page = new Page<Book>();
        Integer pages = getPages(4);//获取总页数
        page.setPageNum(pageNum,pages);//数据边界的有效检查
        //开启分页功能，第一个参数是当前页，第二个是一页几行数据
        PageHelper.startPage(page.getPageNum(), 4);
        //查询所有图书信息
        List<Book> bookList = queryBooks();
        //创建分页信息，第一个参数是 数据，第二个参数是可选的跳转页面数量(5就是显示5个：6,7,8,9,10）
        PageInfo<Book> pageInfo = new PageInfo<Book>(bookList, 5);
        page.setPageInfo(pageInfo);
        return page;
    }

    public Integer getPages(Integer itemNumforPage) {
        Integer bookTotal = bookMapper.getBookTotal();
        Integer PageTotal = bookTotal / itemNumforPage;
        if (bookTotal % itemNumforPage > 0){
            PageTotal += 1;
        }
        return PageTotal;
    }

    public Integer getPagesByPrice(Integer itemNumforPage, Integer min, Integer max) {
        Integer bookTotal = bookMapper.getBookTotalByPrice(min,max);
        Integer PageTotal = bookTotal / itemNumforPage;
        if (bookTotal % itemNumforPage > 0){
            PageTotal += 1;
        }
        return PageTotal;
    }


    public Page<Book> pageByPrice(int pageNum, int min, int max) {
        Page<Book> page = new Page<Book>();
        Integer pages = getPagesByPrice(4,min,max);//获取总页数
        page.setPageNum(pageNum,pages);//数据边界的有效检查
        //开启分页功能，第一个参数是当前页，第二个是一页几行数据
        PageHelper.startPage(page.getPageNum(), 4);
        //查询出min-max的价格的图书
        List<Book> bookList = bookMapper.queryForPagesItemsByPrice(min, max);
        //创建分页信息，第一个参数是集合数据，第二个参数是可选的跳转页面数量(5就是显示5个：6,7,8,9,10）
        PageInfo<Book> pageInfo = new PageInfo<Book>(bookList, 5);
        page.setPageInfo(pageInfo);
        return page;
    }
}
