package com.site.datasourse.constants;

/**
 * @author Programmer Li
 */
public enum LeaderboardTypeConstants {
    /**
     *ORIGIN(原创榜单)
     * ALL(全站)
     *ROOKIE(新人榜)
     * */

    ORIGIN("origin", "原创"),
    ALL("all", "全站"),
    ROOKIE("rookie", "新人");

    private final String value;
    private final String description;

    LeaderboardTypeConstants(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

}
