package com.site.bdata.constants;

/**
 * 使用枚举类型中的values()方法获取枚举类型中的成员变量
 *
 * @author pan_junbiao
 */
public class ShowEnum {
    // 循环由values()方法返回的数组
    public static void main(String[] args) {
//        System.out.println(Constants.BV_RANKZONE__ANIMATION.getValue());
//        for (Constants e : Constants.values()) {
//            System.out.println(e.getValue());
//        }
        System.out.println(bilibiliConstants.RANK_URL_PREFIX +LeaderboardTypeConstants.ALL.getValue()+"/"+ 1 + bilibiliConstants.RANK_URL_SUFFIX);
    }
}
