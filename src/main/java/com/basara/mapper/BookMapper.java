package com.basara.mapper;

import com.basara.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author basara
 * @create 2022-12-10 2:18
 */
public interface BookMapper {

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
     * 查询全部图书数量
     * @return
     */
    Integer getBookTotal();

    /**
     * 查询价格区间的全部图书数量
     * @return
     */
    Integer getBookTotalByPrice(@Param("min") int min,@Param("max") int max);
//
//    /**
//     * 查询图书数量
//     * @return
//     */
//    Integer queryForPageTotalCount();
//
//    /**
//     * 查询每页的图书集合
//     * @return
//     */
//    List<Book> queryForPagesItems(int begin, int pageSize);
//
//    /**
//     * 查询价格区间的商品数量
//     * @param min
//     * @param max
//     * @return
//     */
//    Integer queryForPageTotalCountByPrice(int min, int max);
//
    /**
     * 查询价格区间的每页商品信息
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForPagesItemsByPrice(@Param("min") int min,@Param("max") int max);

}
