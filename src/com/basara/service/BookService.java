package com.basara.service;

import com.basara.pojo.Book;
import com.basara.pojo.Page;

import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-05 23:12
 */
public interface BookService {
    /**
     * 修改商品
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 添加商品
     * @param book
     */
    public void addBook(Book book);

    /**
     * 通过id删除商品
     * @param id
     */
    public void deleteBook(Integer id);

    /**
     * 查询所有商品
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 通过id查询商品
     * @param id
     * @return
     */
    public Book queryBook(Integer id);

    /**
     * 获取分页模型
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
