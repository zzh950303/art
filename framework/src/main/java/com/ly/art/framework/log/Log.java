package com.ly.art.framework.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Log {

    /**
     * 操作描述
     **/
    public String desc() default "";

    /**
     * 忽略的操作参数，剩余的参数都要记录 如有多个则逗号隔开
     **/
    public String ignoreArgs() default "";

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
