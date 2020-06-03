package com.site.bdata.datasources;

import com.site.bdata.util.jsouputil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class bilibiliRank {

    public static void main(String[] args) throws IOException {

        String url = "https://www.bilibili.com/ranking";
        System.out.println("==========耐心等待几分钟==========");
        //        解析页面(jsoup返回document就是document对象)
        Document document = jsouputil.getHtmlContent(url);
        for (Element element : document.select("li[class=rank-item]")) {
            String href = element.select(".img").select("a").attr("href");
            String title = element.select(".img").select("img").attr("alt");
            String uphref = element.select(".detail").select("a").attr("href");
            String upname = element.select(".detail").select("a").select("span").text();
            System.out.println(href);
            System.out.println(title);
            System.out.println(uphref);
            System.out.println(upname);
        }



    }
}
