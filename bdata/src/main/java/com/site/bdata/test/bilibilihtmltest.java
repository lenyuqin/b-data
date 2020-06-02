package com.site.bdata.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * @author Programmer Li
 */
public class bilibilihtmltest {
    public static void main(String[] args) throws IOException {
//        请求页面     https://www.bilibili.com/video/online.html(需要联网)

        String url = "https://www.bilibili.com/ranking";
//        解析页面(jsoup返回document就是document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
//        System.out.println(document);

//        Element app = document.getElementById("app");
//        Elements ebox = document.getElementsByClass("ebox");
//        for (Element element : ebox) {
////            System.out.println("标题->"+element.getElementsByTag("img").eq(0).attr("alt"));
////            String video="视频地址->"+"https:"+element.getElementsByTag("a").eq(0).attr("href");
////            String videos = element.getElementsByTag("a").eq(0).attr("href");
////            String[] split = videos.split("/");
////            System.out.println("视频bv号->"+split[4]);
//            System.out.println("播放数量->"+element.getElementsByClass("play").text());
//            System.out.println("评论数->"+element.getElementsByClass("dm").text());
//            System.out.println("up主名称->"+element.getElementsByClass("author").text());
//            System.out.println("在线人数->"+element.getElementsByTag("b").text());
//        }

    }
}
