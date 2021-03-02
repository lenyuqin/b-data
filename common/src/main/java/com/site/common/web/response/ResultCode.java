package com.site.common.web.response;

import lombok.Getter;

/**
 * Describe: 统 一 返 回 状 态 码
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200,"success"),
    /**
     * 失败
     */
    FAILURE(500,"failure");

    /**
     * 状 态 码
     * */
    @Getter
    private final int code;

    /**
     * 携 带 消 息
     * */
    @Getter
    private final String message;

    /**
     * 构 造 方 法
     * */
    ResultCode(int code, String message) {

        this.code = code;

        this.message = message;
    }

}
