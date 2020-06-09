package com.example.webmagic.test;

import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class BilibiliSpider implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public Site getSite() {
        return site;
    }
    @Override
    public void process(Page page) {
        // //*[@id="app"]/div[1]/div/div[1]/div[2]/div[3]/ul/li[1]/div[2]/div[2]/a
        //  https://www.bilibili.com/ranking
        // https://www.bilibili.com/video/BV15Z4y1H7jh
        // //*[@id="app"]/div[1]/div/div[1]/div[2]/div[3]/ul/li[1]/div[2]/div[2]/a
        // //*[@id="app"]/div[1]/div/div[1]/div[2]/div[3]/ul/li[1]/div[2]/div[1]/a/div/img
        // //*[@id="app"]/div[1]/div/div[1]/div[2]/div[3]/ul/li[1]/div[2]/div[2]/div[1]/span[1]
        // //*[@id="arc_toolbar_report"]/div[1]/span[1]

        page.getUrl().regex("https://www\\.bilibili\\.com/video/[0-9 a-z A-Z]{12}\\.html").match();
        String like = page.getHtml().xpath("//*[@id=\"arc_toolbar_report\"]/div[1]/span[1]/@title").all().toString();
        System.out.println(like);
        page.putField("like",like);


//        String num = page.getHtml().xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div[2]/div[3]/ul/li[1]/div[2]/div[2]/div[1]/span[1]/text()").all().toString();
//        String src = page.getHtml().xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div[2]/div[3]/ul/li[1]/div[2]/div[1]/a/div/img/@src").all().toString();
//        String title = page.getHtml().xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div[2]/div[3]/ul/li/div[2]/div[2]/a/text()").all().toString();
//        String href = page.getHtml().xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div[2]/div[3]/ul/li/div[2]/div[2]/a/@href").all().toString();
//        System.out.println("src====>"+src);
//        System.out.println("num====>"+num);
//        System.out.println("title====>"+title);
//        System.out.println("href====>"+href);
//        page.putField("title",title);
//        page.putField("src",src);
//        page.putField("href",href);

    }
    // 自定义实现Pipeline接口
    static class MysqlPipeline implements Pipeline {

        public MysqlPipeline() {
        }

        @Override
        public void process(ResultItems resultitems, Task task) {
            Map<String, Object> mapResults = resultitems.getAll();
            System.out.println("============================");
//            System.out.println(mapResults);
            Iterator<Map.Entry<String, Object>> iter = mapResults.entrySet().iterator();
            Map.Entry<String, Object> entry;
            // 输出到控制台
            while (iter.hasNext()) {
                entry = iter.next();
                System.out.println(entry.getKey() + "：" + entry.getValue());
            }
        }
    }


    public static void main(String[] args) {
        Spider.create(new BilibiliSpider()).addUrl("https://www.bilibili.com/video/BV15Z4y1H7jh")
                .addPipeline(new MysqlPipeline()).thread(3).run();
    }
}
