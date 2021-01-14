package com.ly.art;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;

@SuppressWarnings("deprecation")
@Configuration
@ImportResource({ "classpath*:/spring-config/shiro-context.xml"})
public class BootConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/public/");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Bean
    public FilterRegistrationBean getCharacterEncodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(encodingFilter);
        registrationBean.setInitParameters(new HashMap<String, String>() {
            private static final long serialVersionUID = -8317468808497029066L;
            {
                put("encoding", "UTF-8");
                put("forceEncoding", "true");
            }
        });
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Bean
    public FilterRegistrationBean getShiroFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy();
        registrationBean.setFilter(shiroFilter);
        registrationBean.setName("shiroFilter");
        registrationBean.setAsyncSupported(true);
        registrationBean.setInitParameters(new HashMap<String, String>() {
            private static final long serialVersionUID = 8069406587225256029L;
            {
                put("targetFilterLifecycle", "true");
            }
        });
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
