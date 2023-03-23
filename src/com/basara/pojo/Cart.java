package com.basara.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author com.basara
 * @create 2022-11-11 4:48
 */
public class Cart {

    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    //添加商品
    public void addItem (CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        //如果这个商品是第一次放入
        if (item == null){
            items.put(cartItem.getId(),cartItem);
        } else {
            //如果不是第一次放入，就修改商品总数和总金额
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    //删除商品
    public void deleteItem(Integer id){
        items.remove(id);
    }
    //修改商品数量
    public void updateItem(Integer id,Integer count){
        // 先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem item = items.get(id);
        if(item != null){
            //修改数量为count的个数
            item.setCount(count);
            //修改总价 单价 * 个数
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    //清空购物车
    public void clear(){
        items.clear();
    }

    public Integer getTotalCount() {
        //遍历所以的商品，然后把他们的个数相加
        Integer TotalCount = 0;
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            TotalCount += entry.getValue().getCount();
        }
        return TotalCount;
    }


    public BigDecimal getTotalPrice() {
        //遍历所以的商品，然后把他们的总价相加
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        //总价和总数直接调用方法，因为这两个数据没必要做成属性，它们的值是不能set出来的，而是根据商品的变化动态求出的
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
