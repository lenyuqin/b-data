package com.site.component.utils.pvuv;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author lenyuqin
 * @data 2020/12/27
 */
public class PvuvUtils {

    public static String getDailyKey() {
        return PvuvString.PREFIX + DateUtil.format(DateUtil.date(), "yyyyMMdd");
    }

    public static String getYesterdayKey() {
        return PvuvString.PREFIX + DateUtil.format(DateUtil.yesterday(), "yyyyMMdd");
    }


    public static Date getYesterday() {
        return DateUtil.yesterday();
    }

}
