package com.site.component.utils.text;

/**
 * @author lenyuqin
 * @data 2021/1/2
 * @describe 将中文转换为数字，这里暂时只有将万转化为数字万
 */
public class ChineseToNum {


    /**
     * 例子 1.6万=> 16000
     *
     * @param str
     * @return
     */
    public static Long videoChineseToNum(String str) throws Exception {
        if (str.isBlank()) {
            return 0L;
        } else {
            if (BVStringUtil.isContainChinese(str)) {
                String strs = str.split("万")[0];
                double num = Double.parseDouble(strs);
                double nums = num * 10000;
                return (long) nums;
            } else {
                return Long.valueOf(str);
            }
        }
    }


}
