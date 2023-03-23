package com.basara.utils;

/**
 * @author com.basara
 * @create 2022-11-05 3:13
 */
public class WebUtils {

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
