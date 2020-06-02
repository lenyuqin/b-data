package com.site.bdata.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URL;
import java.sql.Driver;

public class bilibiliVideo {

    public static void main(String[] args) throws Exception {
        //设置webdriver路径
        System.setProperty("webdriver.chrome.driver",bilibiliVideo.class.getClassLoader().getResource("chromedriver.exe").getPath());

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bilibili.com/video/BV1hV411C7yL");
    }
}
