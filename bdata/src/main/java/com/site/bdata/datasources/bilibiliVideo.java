package com.site.bdata.datasources;


import com.site.bdata.util.jsouputil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class bilibiliVideo {
    public static void BVdata(){

    }








    public static void main(String[] args) throws Exception {
        Document document = jsouputil.getHtmlContent("https://www.bilibili.com/video/BV1uf4y127Ab");
        String like = document.select(".ops").select("span").eq(0).attr("title");
        String coin = document.select(".ops").select("span").eq(1).text();
        String collect = document.select(".ops").select("span").eq(2).text();
        String share = document.select(".ops").select("span").eq(3).text();
        String view = document.select("#viewbox_report").select("span[class=view]").attr("title");
        String dm = document.select("#viewbox_report").select("span[class=dm]").attr("title");
        System.out.println(like);
        System.out.println(coin);
        System.out.println(collect);
        System.out.println(share);
        System.out.println(view);
        System.out.println(dm);


    }
}
