package com.site.bdata.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;

public class jsouptest {
    public static void main(String[] args) throws IOException {
        String url = "https://www.toutiao.com/";
//        Connection connect = Jsoup.connect(url);
//        Document document = connect.get();
//        System.out.println(document);

        //构造一个webClient 模拟Chrome 浏览器
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
//屏蔽日志信息
//        LogFactory.getLog().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
//        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
//支持JavaScript
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(5000);
        HtmlPage rootPage = webClient.getPage(url);
        //设置一个运行JavaScript的时间
        webClient.waitForBackgroundJavaScript(5000);
        String html = rootPage.asXml();

        Document document = Jsoup.parse(html);
        System.out.println(document);

    }
}
