package com.site.bdata.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author Programmer Li
 */
public class jsoupUtil {
    private static String getHtmlPageResponse(String url) throws Exception {
        //请求超时时间,默认200秒
        int timeout = 20000;
        //等待异步JS执行时间,默认200秒
        int waitForBackgroundJavaScript = 20000;
        String result = "";
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //当JS执行出错的时候是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //当HTTP的状态非200时是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        //是否启用CSS
        webClient.getOptions().setCssEnabled(false);
        //很重要，启用JS
        webClient.getOptions().setJavaScriptEnabled(true);
        //很重要，设置支持AJAX
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置“浏览器”的请求超时时间
        webClient.getOptions().setTimeout(timeout);
        //设置JS执行的超时时间
        webClient.setJavaScriptTimeout(timeout);
        HtmlPage page;
        try {
            page = webClient.getPage(url);
        } catch (Exception e) {
            webClient.close();
            throw e;
        }
        //该方法阻塞线程
        webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);
        result = page.asXml();
        webClient.close();
        return result;
    }

    public static Document getHtmlContent(String url){
        // 发起请求
        String content = null;
        try {
            content = getHtmlPageResponse(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 解析网页 得到文档对象
        return Jsoup.parse(content);
    }

    public static void main(String[] args) {
        Document htmlContent = getHtmlContent("https://www.bilibili.com/video/BV1Vk4y1r7qs");
        System.out.println(htmlContent.getElementsByClass("ops").html());

    }
}
