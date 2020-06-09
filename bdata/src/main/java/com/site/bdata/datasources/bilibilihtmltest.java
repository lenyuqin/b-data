package com.site.bdata.datasources;



import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.site.bdata.util.jsoupUtil;
import com.site.bdata.util.numberUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author Programmer Li
 *
 */
public class bilibilihtmltest {
    /**
     * 点赞数193800
     * 13.4万
     * 12.4万
     * 2.3万 动态 微博 QQ QQ空间 贴吧 将视频贴到博客或论坛 视频地址 复制 嵌入代码 复制 微信扫一扫分享
     * 总播放数2216846
     * 历史累计弹幕数13280
     * https://api.bilibili.com/x/relation/stat?vmid=10330740&jsonp=jsonp
     * http://api.bilibili.com/x/web-interface/view?bvid=BV1kQ4y1P7Nm
     * */
    public static void main(String[] args) throws IOException {
        String url="http://api.bilibili.com/x/web-interface/view?bvid=BV1UK4y1x7ht";
        String body = HttpRequest.get(url).timeout(2000).execute().body();
        String code = JSON.parseObject(body).getString("code");
        if(code.equals("-404")){
            System.out.println("视频不存在");
        }else {
            System.out.println(body);
        }
//        Object data = JSON.parseObject(body).getJSONObject("data").getJSONObject("stat");


    }

}

