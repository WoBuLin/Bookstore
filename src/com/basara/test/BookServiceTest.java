package com.basara.test;

import com.basara.pojo.Book;
import com.basara.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author com.basara
 * @create 2022-11-05 23:32
 */
public class BookServiceTest {

    BookServiceImpl bookService = new BookServiceImpl();

    @Test
    public void update() {
        Book book = new Book(55,"大家好帅", "com/basara",new BigDecimal(888),999,0,null);
        bookService.updateBook(book);
    }

    @Test
    public void add() {
        Book book = new Book(55,"我好帅", "com/basara",new BigDecimal(888),999,0,null);
        bookService.addBook(book);
    }

    @Test
    public void delete() {
        bookService.deleteBook(23);
    }

    @Test
    public void queryForList() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void queryBookForId() {
        System.out.println(bookService.queryBook(23));
    }

}