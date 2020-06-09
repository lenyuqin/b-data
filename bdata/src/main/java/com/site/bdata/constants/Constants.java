package com.site.bdata.constants;

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

}
