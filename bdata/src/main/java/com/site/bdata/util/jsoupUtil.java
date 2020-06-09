package com.site.bdata.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.DefaultJavaScriptErrorListener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Programmer Li
 */
public class jsoupUtil {
    private static String getHtmlPageResponse(String url) throws Exception {
        //请求超时时间,默认200秒
        int timeout = 9000;
        //等待异步JS执行时间,默认200秒
        int waitForBackgroundJavaScript = 9000;
        String result = "";
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //当JS执行出错的时候是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //当HTTP的状态非200时是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        // -----重点-----设置为我们自定义的错误处理类
        webClient.setJavaScriptErrorListener(new MyJSErrorListener());

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


    /**
     * 忽略html unit打印的所有js加载报错信息
     */
    public static class MyJSErrorListener extends DefaultJavaScriptErrorListener {
        @Override
        public void scriptException(HtmlPage page, ScriptException scriptException) {
        }

        @Override
        public void timeoutError(HtmlPage page, long allowedTime, long executionTime) {
        }

        @Override
        public void malformedScriptURL(HtmlPage page, String url, MalformedURLException malformedURLException) {

        }

        @Override
        public void loadScriptError(HtmlPage page, URL scriptUrl, Exception exception) {

        }

        @Override
        public void warn(String message, String sourceName, int line, String lineSource, int lineOffset) {

        }
    }

    public static void main(String[] args) {
        Document htmlContent = getHtmlContent("https://www.bilibili.com/video/BV1Vk4y1r7qs");
        System.out.println(htmlContent.getElementsByClass("ops").html());

    }
}
