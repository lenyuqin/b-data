package com.site.bdata.controller.data;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.bdata.constants.bilibiliConstants;
import com.site.bdata.dto.AjaxPutPage;
import com.site.bdata.dto.AjaxResultPage;
import com.site.bdata.dto.RankCondition;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoRankService;
import com.site.bdata.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.stream.Collectors;

/**
 * (BVideoData)表控制层
 *
 * @author lmk
 * @since 2020-06-03 10:19:24
 */
@Api(tags = "BVideoData表控制层")
@Slf4j
@Controller
public class BVideoDataController {
    /**
     * 服务对象
     */
    @Resource
    private BVideoDataService bVideoDataService;


    /**
     * 查询视频播放量,时间  bvNumber=BV1hz411i7cD
     */
    @ResponseBody
    @GetMapping({"/BVideoData"})
    public AjaxResultPage getVideoData(BVideoData bVideoData) {
        AjaxResultPage<BVideoData> ajaxResultPage = new AjaxResultPage<>();
        List<BVideoData> bVideoDatalist = bVideoDataService.list(new QueryWrapper<BVideoData>().lambda()
                .eq(BVideoData::getBvNumber, bVideoData.getBvNumber()));
        int count = bVideoDataService.count(new QueryWrapper<BVideoData>().lambda().eq(BVideoData::getBvNumber, bVideoData.getBvNumber()));
        ajaxResultPage.setData(bVideoDatalist);
        ajaxResultPage.setCode(0);
        ajaxResultPage.setMsg("成功");
        ajaxResultPage.setCount(count);
        return ajaxResultPage;
    }


}