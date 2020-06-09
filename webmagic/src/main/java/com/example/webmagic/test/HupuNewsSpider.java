package com.example.webmagic.test;

import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class HupuNewsSpider implements PageProcessor {
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        // 文章页，匹配 https://voice.hupu.com/nba/七位数字.html
        if (page.getUrl().regex("https://voice\\.hupu\\.com/nba/[0-9]{7}\\.html").match()) {
            page.putField("Title", page.getHtml().xpath("/html/body/div[4]/div[1]/div[1]/h1/text()").toString());
            page.putField("Content",
                    page.getHtml().xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/p/text()").all().toString());
        }
        // 列表页
        else {
            // 文章url /html/body/div[3]/div[1]/div[2]/ul/li[59]/div[1]/h4/a
            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[3]/div[1]/div[2]/ul/li/div[1]/h4/a/@href").all());
            // 翻页url /html/body/div[3]/div[1]/div[3]/a[4]
            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[3]/div[1]/div[3]/a[@class='page-btn-prev']/@href").all());
        }
    }

    public static void main(String[] args) {
        Spider.create(new HupuNewsSpider()).addUrl("https://voice.hupu.com/nba/1").addPipeline(new MysqlPipeline())
                .thread(3).run();
    }

    // 自定义实现Pipeline接口
    static class MysqlPipeline implements Pipeline {

        public MysqlPipeline() {
        }

        @Override
        public void process(ResultItems resultitems, Task task) {
            Map<String, Object> mapResults = resultitems.getAll();
            Iterator<Map.Entry<String, Object>> iter = mapResults.entrySet().iterator();
            Map.Entry<String, Object> entry;
            // 输出到控制台
            while (iter.hasNext()) {
                entry = iter.next();
                System.out.println(entry.getKey() + "：" + entry.getValue());
            }
//            // 持久化
//            News news = new News();
//            if (!mapResults.get("Title").equals("")) {
//                news.setTitle((String) mapResults.get("Title"));
//                news.setContent((String) mapResults.get("Content"));
//            }
//            try {
//                InputStream is = Resources.getResourceAsStream("conf.xml");
//                SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
//                SqlSession session = sessionFactory.openSession();
//                session.insert("add", news);
//                session.commit();
//                session.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
