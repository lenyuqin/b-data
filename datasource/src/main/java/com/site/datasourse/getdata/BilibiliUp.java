package com.site.datasourse.getdata;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.site.common.entity.BAuthorBasedata;
import com.site.common.entity.BAuthorData;
import com.site.common.service.BAuthorBasedataService;
import com.site.common.service.BAuthorDataService;
import com.site.common.service.BVideoDataService;
import com.site.common.service.BVideoHistoryService;
import com.site.datasourse.constants.bilibiliConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬取作者信息
 *
 * @author lenyuqin
 * @data 2020/12/31
 */
@Slf4j
@Service
public class BilibiliUp {

    @Resource
    private BVideoDataService bVideoDataService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BAuthorBasedataService bAuthorBasedataService;
    @Resource
    private BAuthorDataService bAuthorDataService;


    public void getAuthorLikeList() {
        ArrayList<BAuthorData> baseDataArrayList = new ArrayList<>();
        List<BAuthorBasedata> list = bAuthorBasedataService.list(new QueryWrapper<BAuthorBasedata>().lambda().select(BAuthorBasedata::getBvUpuuid));
        log.info("作者集合总数======>" + list.size());
        for (BAuthorBasedata bAuthorBasedata : list) {
            log.info("bAuthorBasedata======>" + bAuthorBasedata.getBvUpuuid());
            String body = HttpRequest.get(bilibiliConstants.UP_LIKES_AND_VIEWS + bAuthorBasedata.getBvUpuuid() + bilibiliConstants.JSONP)
                    .cookie(bilibiliConstants.MY_COOKIE).header("user-agent", bilibiliConstants.USER_AGENT).timeout(5000).execute().body();
            JSONObject data = JSON.parseObject(body).getJSONObject("data");
            Integer view = data.getJSONObject("archive").getInteger("view");
            Integer likes = data.getInteger("likes");
            BAuthorData bAuthorData = new BAuthorData();
            bAuthorData.setBvUpLikes(likes);
            bAuthorData.setBvUpuuid(Math.toIntExact(bAuthorBasedata.getBvUpuuid()));
            bAuthorData.setBvUpView(view);
            bAuthorDataService.save(bAuthorData);
            baseDataArrayList.add(bAuthorData);
        }
        log.info("集合总数======>" + baseDataArrayList.size());
    }


    public void getAuthorFollowerList() {
        ArrayList<BAuthorData> baseDataArrayList = new ArrayList<>();
        List<BAuthorBasedata> list = bAuthorBasedataService.list(new QueryWrapper<BAuthorBasedata>().lambda().select(BAuthorBasedata::getBvUpuuid));
        log.info("作者集合总数======>" + list.size());
        for (BAuthorBasedata bAuthorBasedata : list) {
            log.info("bAuthorBaseData======>" + bAuthorBasedata.getBvUpuuid());
            String body = HttpRequest.get(bilibiliConstants.UP_FOLLOWS_NUM + bAuthorBasedata.getBvUpuuid() + bilibiliConstants.JSONP).cookie(bilibiliConstants.MY_COOKIE).timeout(5000).execute().body();
            JSONObject data = JSON.parseObject(body).getJSONObject("data");
            Integer follower = data.getInteger("follower");
            BAuthorData bAuthorData = new BAuthorData();
            bAuthorData.setBvUpuuid(Math.toIntExact(bAuthorBasedata.getBvUpuuid()));
            bAuthorData.setBvUpFollower(follower);
            bAuthorDataService.saveOrUpdate(bAuthorData);
            baseDataArrayList.add(bAuthorData);
        }
        log.info("集合总数======>" + baseDataArrayList.size());
    }
}
