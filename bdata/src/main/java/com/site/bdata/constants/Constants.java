package com.site.bdata.constants;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Constants {
    /**
     * 视频分区的数字
     * 分区号  0(全站) 1(动画) 168(国创相关)
     * 3(音乐) 129(舞蹈) 4(游戏) 36(科技) 188(数码)
     * 160(生活),119(鬼畜),155(时尚),5(娱乐) 181(影视)
     */
    BV_RANKZONE__ALL(0, "全站"),
    BV_RANKZONE__ANIMATION(1, "动画"),
    BV_RANKZONE__AIMTRON(168, "国创相关"),
    BV_RANKZONE__MUSIC(3, "音乐"),
    BV_RANKZONE__DANCE(129, "舞蹈"),
    BV_RANKZONE__GAME(4, "游戏"),
    BV_RANKZONE__TECHNOLOGY(36, "科技"),
    BV_RANKZONE__DIGITAL(188, "数码"),
    BV_RANKZONE__LIFE(160, "生活"),
    BV_RANKZONE__DEVIL(119, "鬼畜"),
    BV_RANKZONE__FASHION(155, "时尚"),
    BV_RANKZONE__ENTERTAINMENT(5, "娱乐"),
    BV_RANKZONE_FILM(181, "影视");


    private final int value;
    private final String description;

    Constants(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    //讲枚举转换成list格式，这样前台遍历的时候比较容易，列如 下拉框 后台调用toList方法，你就可以得到code 和name了
    public static List toList() {
        List list = Lists.newArrayList();//Lists.newArrayList()其实和new ArrayList()几乎一模
        //  一样, 唯一它帮你做的(其实是javac帮你做的), 就是自动推导(不是"倒")尖括号里的数据类型.
        for (Constants constantEnum : Constants.values()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", constantEnum.getValue());
            map.put("description", constantEnum.getDescription());
            list.add(map);
        }
        return list;
    }

}
