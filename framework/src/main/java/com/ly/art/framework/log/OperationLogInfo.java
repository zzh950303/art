package com.ly.art.framework.log;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 操作日志信息
 */
@Setter
@Getter
public class OperationLogInfo {

    /**
     * 请求URI
     */
    private String requestURI;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParamters;

    /**
     * 操作员
     */
    private String operator;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 创建时间
     */
    private Date crtTime;
}
