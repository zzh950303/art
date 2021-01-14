package com.ly.art.framework.log;

import com.alibaba.fastjson.JSON;
import com.ly.art.framework.shiro.ShiroUtils;
import com.ly.art.framework.utils.IpUtil;
import com.ly.art.framework.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

/**
 * 操作日志记录处理
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private final static String SPLIT_COMMA = ",";

    private static Set<String> logExcludeParameters = new HashSet<>();

    @Autowired
    private OperationLogService operationLogService;

    static {
        try {
            logExcludeParameters = PropertiesUtil.getAll("/log_exclude_parameters.properties").keySet();
        } catch (Exception e) {
            // EMPTY
        }
    }

    @Pointcut("@annotation(com.ly.art.framework.log.Log)")
    public void logPointCut() {
    }

    @After("logPointCut()")
    public void doSystemLog(JoinPoint point) {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            Method method = this.getMethod(point);

            // 获得注解
            Log annotation = method.getAnnotation(Log.class);
            if (annotation == null) {
                return;
            }

            String requestParamters = StringUtils.EMPTY;
            // 是否保存请求的参数
            if (annotation.isSaveRequestData()) {
                String ignoreArgsStr = annotation.ignoreArgs();
                Map<String, String[]> reqParams = request.getParameterMap();
                requestParamters = getRequestParamters(reqParams, ignoreArgsStr);
            }

            String uri = request.getRequestURI();
            String requestMethod = request.getMethod();
            String desc = annotation.desc();

            OperationLogInfo logInfo = new OperationLogInfo();
            logInfo.setRequestURI(uri);
            logInfo.setRequestMethod(requestMethod);
            logInfo.setRequestParamters(requestParamters);
            logInfo.setOperator(ShiroUtils.getAccount());
            logInfo.setIp(IpUtil.getIpAddr(request));
            logInfo.setDescription(desc);
            logInfo.setCrtTime(new Date());

            operationLogService.save(logInfo);
        } catch (Exception e) {
            log.error("save log error", e);
        }

    }

    private Method getMethod(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            method = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return method;
    }

    private String getRequestParamters(Map<String, String[]> params, String ignoreArgsStr) throws Exception {
        List<String> ignoreArgs = new ArrayList<>();
        if (StringUtils.isNotBlank(ignoreArgsStr)) {
            String[] ignoreArgsArr = ignoreArgsStr.split(SPLIT_COMMA);
            ignoreArgs = Arrays.asList(ignoreArgsArr);
        }

        Map<String, String[]> logParams = new HashMap<>();
        if ((null != params) && !params.isEmpty()) {
            Set<Entry<String, String[]>> entrySets = params.entrySet();
            for (Entry<String, String[]> entry : entrySets) {
                String key = entry.getKey();
                if (logExcludeParameters.contains(key) || ignoreArgs.contains(key)) {
                    continue;
                }

                String[] values = entry.getValue();
                String[] newValues = new String[values.length];
                int i = 0;
                for (String value : values) {
                    newValues[i] = new String(value.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
                    i++;
                }
                logParams.put(key, newValues);
            }
        }

        return JSON.toJSONString(logParams);
    }
}
