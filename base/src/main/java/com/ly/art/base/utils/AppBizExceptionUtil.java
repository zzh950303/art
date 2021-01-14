package com.ly.art.base.utils;


import com.ly.art.base.consts.ResultCodeMessage;
import com.ly.art.framework.base.AppBizException;

public class AppBizExceptionUtil {

    public static AppBizException build(ResultCodeMessage code) {
        return new AppBizException(code.getCode(), code.getMessage());
    }
}
