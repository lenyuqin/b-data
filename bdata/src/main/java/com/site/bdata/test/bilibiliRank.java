package com.site.bdata.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class bilibiliRank {

    public static void main(String[] args) throws IOException {

        String url = "https://www.bilibili.com/ranking";
        //        解析页面(jsoup返回document就是document对象)
        Document document = Jsoup.parse(new URL(url), 30000);

        Elements rank = document.getElementsByClass("rank-item");
        for (Element element : rank) {
            //标题
            String title=element.getElementsByTag("img").eq(0).attr("alt");
            String href=element.getElementsByTag("a").eq(0).attr("href");
            String viewCounts=element.getElementsByClass("data-box").eq(0).text();
            String upzhu=element.getElementsByClass("data-box").eq(2).text();
            String commentCounts=element.getElementsByClass("data-box").eq(1).text();


            String synthesisScore=element.getElementsByClass("pts").eq(0).text();

            System.out.println("标题=>"+title);
            System.out.println("地址=>"+href);
            System.out.println("播放量=>"+viewCounts);
            System.out.println("up主=>"+upzhu);
            System.out.println("评论总数=>"+commentCounts);
            System.out.println("综合得分=>"+synthesisScore);
            System.out.println("==================");

        }


    }
}
