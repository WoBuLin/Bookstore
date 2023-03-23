package com.basara.dao;

import com.basara.pojo.Book;

import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-05 21:30
 */
public interface BookDao {
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
    public int deleteBookById(Integer id);

    /**
     * 添加book
     * @param book
     */
    public int addBook(Book book);

    /**
     * 查询图书数量
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 查询每页的图书集合
     * @return
     */
    List<Book> queryForPagesItems(int begin,int pageSize);

    /**
     * 查询价格区间的商品数量
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 查询价格区间的每页商品信息
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForPagesItemsByPrice(int begin, int pageSize, int min, int max);


}
