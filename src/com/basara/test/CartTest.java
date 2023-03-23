package com.basara.test;

import com.basara.pojo.Cart;
import com.basara.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author com.basara
 * @create 2022-11-11 5:03
 */
public class CartTest {

    @Test
    public void addItemTest() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"蛋炒饭",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(2,"炒面",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(1,"蛋炒饭",1,new BigDecimal(23),new BigDecimal(23)));
        System.out.println(cart);
    }

    @Test
    public void delete() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"蛋炒饭",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(2,"炒面",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(3,"炒饼",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(1,"蛋炒饭",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(1,"蛋炒饭",1,new BigDecimal(23),new BigDecimal(23)));

        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void update() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"蛋炒饭",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(2,"炒面",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(3,"炒饼",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(1,"蛋炒饭",1,new BigDecimal(23),new BigDecimal(23)));
        cart.addItem(new CartItem(1,"蛋炒饭",1,new BigDecimal(23),new BigDecimal(23)));

        cart.deleteItem(2);

        cart.updateItem(3,10);

        System.out.println(cart);
    }
}