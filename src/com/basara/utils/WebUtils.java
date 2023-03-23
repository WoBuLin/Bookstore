package com.basara.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author com.basara
 * @create 2022-11-05 3:13
 */
public class WebUtils {

    /**
     * 将Map中的值注入到对应的javabean中
     */
    public static <T> T copyParamToBean(Map value,T bean){
        try {
            System.out.println("注入之前" + bean);
            BeanUtils.populate(bean,value);
            System.out.println("注入之后" + bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将String类型的str解析成int类型的，如果解析出错，就返回默认值
     * @param str
     * @param defaultInt
     * @return
     */
    public static int pareStrToInt(String str,int defaultInt){
        try {
            int i = Integer.parseInt(str);
            return i;
        } catch (NumberFormatException e) {
//            e.printStackTrace();
            return defaultInt;
        }
    }
}
