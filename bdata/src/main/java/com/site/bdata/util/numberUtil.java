package com.site.bdata.util;

/**
 * @author Programmer Li
 */
public class numberUtil {
    public static long numChange(String str) {
        long num=0;
        if (str.lastIndexOf("万") != -1) {
            num = (long) (Float.parseFloat(str.substring(0, str.length() - 1))*1000);
        }else {
            num=Long.parseLong(str);
        }
        return num;
    }
}
