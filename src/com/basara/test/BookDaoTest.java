package com.basara.test;

import com.basara.dao.impl.BookDaoImpl;
import com.basara.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author com.basara
 * @create 2022-11-05 22:47
 */
public class BookDaoTest {

    BookDaoImpl bookDao = new BookDaoImpl();

    @Test
    public void queryAllBook() {
        List<Book> books = bookDao.queryBooks();
        for (Book book:books){
            System.out.println(book);
        }
    }

    @Test
    public void queryBookForId() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void update() {
        Book book = new Book(30,"万历十叭叭叭年", "啥啥啥", new BigDecimal(55), 9999, 99, null);
        bookDao.updateBook(book);
    }

    @Test
    public void delete() {
        bookDao.deleteBookById(22);
    }

    @Test
    public void add() {
        Book book = new Book(30, "太晚那些事儿", "那年明月", new BigDecimal(55), 9999, 99, null);
        bookDao.addBook(book);
    }
}