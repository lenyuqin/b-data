package com.site.web.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.common.entity.BHomePageData;
import com.site.common.service.BHomePageDataService;
import com.site.component.utils.pvuv.PvuvString;
import com.site.component.utils.redis.RedisUtil;
import com.site.web.web.domain.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author lenyuqin
 * @describe 首页数据查询
 * @data 2020/12/21
 */
@Slf4j
@RestController
public class SysIndexController {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private BHomePageDataService bHomePageDataService;


    /**
     * 返回首页访问量的数据，
     *
     * @return Result
     */
    @GetMapping("/return")
    public Result<HashMap<String, Integer>> indexReturn() {
        HashMap<String, Integer> map = new HashMap<>();
        Result<HashMap<String, Integer>> hashMapResult = new Result<>();
        String key = PvuvString.PREFIX + DateUtil.format(DateUtil.date(), "yyyyMMdd");
        map.put("count", (Integer) redisUtil.get("count"));
        //map.put("")
        redisUtil.get(key);
        hashMapResult.setCode(200);
        hashMapResult.setData(map);
        hashMapResult.setMsg("success");
        return hashMapResult;
    }

    /**
     * 返回图表的数据,直接查询数据库的数据，然后缓存那里更新过来
     * 我那个数据不返回最新的日期，就返回昨天的访问量，然后后续在更新，对对对，就这样!!,这样就可以使用缓存了
     *
     * @return Result
     */
    //@Cacheable()
    @GetMapping("/index_data")
    public Result<HashMap<String, Object>> getChart() {
        //todo 这里面就直接查询数据库里面的数据
        HashMap<String, Object> map = new HashMap<>();
        Result<HashMap<String, Object>> hashMapResult = new Result<>();
        List<BHomePageData> list = bHomePageDataService.list(new QueryWrapper<BHomePageData>().lambda().orderByAsc(BHomePageData::getBvDay));
        ArrayList<Long> pvuv = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        for (BHomePageData bHomePageData : list) {
            pvuv.add(bHomePageData.getBvDayVisits());
            date.add(String.valueOf(bHomePageData.getBvDay()));
        }
        map.put("date", date);
        map.put("pvuv", pvuv);
        map.put("totalUp", list.get(list.size() - 1).getBvTotalUp());
        map.put("totalVideo", list.get(list.size() - 1).getBvTotalVideo());
        map.put("count", redisUtil.pfcount(PvuvString.COUNT));
        String key = PvuvString.PREFIX + DateUtil.format(DateUtil.date(), "yyyyMMdd");
        map.put("visits", redisUtil.hget("dailyCount", key));
        hashMapResult.setCode(200);
        hashMapResult.setSuccess(true);
        hashMapResult.setData(map);
        hashMapResult.setMsg("success");
        return hashMapResult;
    }


}
