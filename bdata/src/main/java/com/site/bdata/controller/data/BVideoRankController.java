package com.site.bdata.controller.data;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.bdata.constants.bilibiliConstants;
import com.site.bdata.dto.form.AjaxPutPage;
import com.site.bdata.dto.form.AjaxResultPage;
import com.site.bdata.dto.form.RankCondition;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoRankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * (BVideoRank)表控制层
 *
 * @author lmk
 * @since 2020-06-03 10:20:01
 */
@Api(tags = "排行榜数据控制层")
@Controller
public class BVideoRankController {
    /**
     * 服务对象
     */
    @Resource
    private BVideoRankService bVideoRankService;


    /**
     * 查询排行榜数据
     */
    @ResponseBody
    @GetMapping({"/allRank/list"})
    public AjaxResultPage<BVideoRank> getAllBVideoData(AjaxPutPage<BVideoRank> ajaxPutPage, RankCondition condition) {
        AjaxResultPage<BVideoRank> ajaxResultPage = new AjaxResultPage<>();
        Page<BVideoRank> pagenum = ajaxPutPage.putPageToPage();
        List<BVideoRank> records = null;
        if (condition.getBvRankzone() != null) {
            if (!condition.getBvTime().equals("")) {
                Timestamp dateBegin = Timestamp.valueOf(condition.getBvTime() + bilibiliConstants.DATE_BEGIN);
                Timestamp dateEnd = Timestamp.valueOf(condition.getBvTime() + bilibiliConstants.DATE_END);
                records = bVideoRankService.page(pagenum, new QueryWrapper<BVideoRank>()
                        .lambda().eq(BVideoRank::getBvRankzone, condition.getBvRankzone())
                        .between(BVideoRank::getBvTime, dateBegin, dateEnd)
                        .orderByDesc(BVideoRank::getBvTime).orderByAsc(BVideoRank::getBvRanknum)).getRecords();
            } else {
                records = bVideoRankService.page(pagenum, new QueryWrapper<BVideoRank>()
                        .lambda().eq(BVideoRank::getBvRankzone, condition.getBvRankzone())
                        .orderByDesc(BVideoRank::getBvTime).orderByAsc(BVideoRank::getBvRanknum)).getRecords();
            }
        } else {
            condition.setBvRankzone(0);
            records = bVideoRankService.page(pagenum, new QueryWrapper<BVideoRank>()
                    .lambda().eq(BVideoRank::getBvRankzone, condition.getBvRankzone())
                    .orderByDesc(BVideoRank::getBvTime).orderByAsc(BVideoRank::getBvRanknum)).getRecords();
        }
        ajaxResultPage.setData(records);
        ajaxResultPage.setCode(0);
        ajaxResultPage.setMsg("成功");
        ajaxResultPage.setCount(pagenum.getTotal());
        return ajaxResultPage;
    }


}