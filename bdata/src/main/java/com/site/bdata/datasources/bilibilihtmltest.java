package com.site.bdata.datasources;



import com.site.bdata.util.numberUtil;

import java.io.IOException;

/**
 * @author Programmer Li
 *
 */
public class bilibilihtmltest {
    /**
     * 点赞数193800
     * 13.4万
     * 12.4万
     * 2.3万 动态 微博 QQ QQ空间 贴吧 将视频贴到博客或论坛 视频地址 复制 嵌入代码 复制 微信扫一扫分享
     * 总播放数2216846
     * 历史累计弹幕数13280
     * */
    public static void main(String[] args) throws IOException {

//        String a="125万 动态 微博 QQ QQ空间 贴吧 将视频贴到博客或论坛 视频地址 复制 嵌入代码 复制 微信扫一扫分享";
//        int i = a.lastIndexOf("万");
//        System.out.println(i);
//        String[] s = a.split(" ");
//        System.out.println(s[0].substring(0,s[0].length()-1));
//        String s="点赞数193800";
//        System.out.println(s.substring(3));

        String a="134";
//        int i = a.lastIndexOf("万");
        long numChange = numberUtil.numChange(a);
        System.out.println(numChange);
    }

}

