package com.site.bdata.datasources;


import com.site.bdata.constants.bilibiliConstants;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.util.DateUtils;
import com.site.bdata.util.jsoupUtil;
import com.site.bdata.util.numberUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class bilibiliVideo {
    public static BVideoData BVdata(BVideoHistory bVideoHistory){
        /**
         * 点赞数193800
         * 13.4万
         * 12.4万
         * 2.3万 动态 微博 QQ QQ空间 贴吧 将视频贴到博客或论坛 视频地址 复制 嵌入代码 复制 微信扫一扫分享
         * 总播放数2216846
         * 历史累计弹幕数13280
         * */
        Document document = jsoupUtil.getHtmlContent(bilibiliConstants.VIDEO_URL+bVideoHistory.getBvNumber());
        String like = document.select(".ops").select("span").eq(0).attr("title").substring(3);
        String coin = document.select(".ops").select("span").eq(1).text();
        String collect = document.select(".ops").select("span").eq(2).text();
        String[] shareArrary = document.select(".ops").select("span").eq(3).text().split(" ");
        String share=shareArrary[0];
        String view = document.select("#viewbox_report").select("span[class=view]").attr("title").substring(4);
        String dm = document.select("#viewbox_report").select("span[class=dm]").attr("title").substring(7);
        BVideoData bVideoData = new BVideoData();
        bVideoData.setBvNumber(bVideoHistory.getBvNumber());
        bVideoData.setBvUp(bVideoHistory.getBvUp());
        bVideoData.setBvUpuuid(bVideoHistory.getBvUpuuid());
        bVideoData.setBvTitle(bVideoHistory.getBvTitle());
        bVideoData.setBvTime(DateUtils.getLocalCurrentDate());
        bVideoData.setBvCoinnum(numberUtil.numChange(coin));
        bVideoData.setBvCollectnum(numberUtil.numChange(collect));
        bVideoData.setBvDmnum(numberUtil.numChange(dm));
        bVideoData.setBvLikenum(numberUtil.numChange(like));
        bVideoData.setBvViewnum(numberUtil.numChange(view));
        bVideoData.setBvSharenum(numberUtil.numChange(share));
        return bVideoData;
    }

}
