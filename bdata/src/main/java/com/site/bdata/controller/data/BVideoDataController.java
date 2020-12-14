package com.site.bdata.controller.data;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.site.bdata.dto.form.AjaxResultPage;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.service.BVideoDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import java.util.List;

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
        int count = bVideoDataService.count(new QueryWrapper<BVideoData>()
                .lambda().eq(BVideoData::getBvNumber, bVideoData.getBvNumber()));
        ajaxResultPage.setData(bVideoDatalist);
        ajaxResultPage.setCode(0);
        ajaxResultPage.setMsg("成功");
        ajaxResultPage.setCount(count);
        return ajaxResultPage;
    }


}