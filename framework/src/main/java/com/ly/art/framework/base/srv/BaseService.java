package com.ly.art.framework.base.srv;

import com.ly.art.framework.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseService implements InitializingBean, DisposableBean, BeanNameAware {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected String beanName;

    @Autowired
    protected HttpServletRequest httpServletRequest;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public final void destroy() throws Exception {
        close();
    }

    protected void close() throws Exception {
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        init();
    }

    protected void init() throws Exception {
    }

    protected String getIpAddr() {
        return IpUtil.getIpAddr(httpServletRequest);
    }
}
