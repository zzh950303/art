package com.ly.art.framework.base.req;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 请求基类
 */
public class BaseReq implements Serializable {

    private static final long serialVersionUID = -1982157432517321817L;

    private static final String CLASS_NAME = "class";

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Map<String, Object> getConditionParams() {
        Map<String, Object> condParams = Maps.newHashMap();

        Class<?> requestType = getClass();
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(requestType);
        for (PropertyDescriptor property : pds) {
            String name = property.getName();
            if (StringUtils.equals(name, CLASS_NAME)) {
                continue;
            }
            Field parameterField = ReflectionUtils.findField(requestType, name);
            Condition condition = getAnnotation(parameterField, Condition.class);
            if (condition == null) {
                continue;
            }
            Object val = ReflectionUtils.invokeMethod(property.getReadMethod(), this);
            if (val == null) {
                continue;
            }
            Object obj;
            if (val instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                obj = sdf.format(val);
            } else {
                obj = val;
            }
            if (obj instanceof String) {
                try {
                    obj = java.net.URLEncoder.encode((String) obj, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // EMPTY
                }
            }
            if (obj instanceof String[]) {
                try {
                    obj = StringUtils.join((String[]) obj, ',');
                } catch (Exception e) {
                    // EMPTY
                }
            }

            condParams.put(name, obj);
        }

        return condParams;
    }

    public String buildCondition(List<String> excludeParams) {
        StringBuilder cond = new StringBuilder();
        Map<String, Object> condParams = getConditionParams();
        if (MapUtils.isEmpty(condParams)) {
            return cond.toString();
        }

        for (Map.Entry<String, Object> entry : condParams.entrySet()) {
            if (CollectionUtils.isNotEmpty(excludeParams) && excludeParams.contains(entry.getKey())) {
                continue;
            }
            cond.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return cond.toString();
    }

    public String buildCondition() {
        return buildCondition(null);
    }

    public static <T extends Annotation> T getAnnotation(AnnotatedElement annotatedElement, Class<T> annotationType) {
        try {
            T ann = annotatedElement.getAnnotation(annotationType);
            if (ann == null) {
                for (Annotation metaAnn : annotatedElement.getAnnotations()) {
                    ann = metaAnn.annotationType().getAnnotation(annotationType);
                    if (ann != null) {
                        break;
                    }
                }
            }
            return ann;
        } catch (Exception ex) {
            return null;
        }
    }
}
