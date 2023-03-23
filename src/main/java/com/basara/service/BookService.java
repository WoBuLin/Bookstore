package com.basara.service;

import com.basara.pojo.Book;
import com.basara.pojo.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author basara
 * @create 2022-12-10 4:50
 */

public interface BookService {

    /**
     * 查询返回所有的book的集合
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 按照id查询book
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);
    /**
     * 修改book
     * @param book
     */
    public int updateBook(Book book);

    /**
     * 删除book
     * @param id
     */
    public int deleteBook(Integer id);

    /**
     * 添加book
     * @param book
     */
    public int addBook(Book book);

    /**
     * 查询返回PageInfo分页信息
     * @param pageNum
     * @return
     */
    Page<Book> getBookPage(Integer pageNum);

    /**
     * 查询返回分页的页数
     * @return
     */
    Integer getPages(Integer itemNumforPage);

    /**
     * 查询价格区间的分页的页数
     * @return
     */
    Integer getPagesByPrice(Integer itemNumforPage,Integer min,Integer max);

    /**
     * 查询返回价格区间的分页信息
     * @param pageNum
     * @param max
     * @return
     */
    Page<Book> pageByPrice(int pageNum, int min, int max);

}
