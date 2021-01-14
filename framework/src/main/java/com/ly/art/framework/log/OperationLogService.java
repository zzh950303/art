package com.ly.art.framework.log;

/**
 * 操作日志服务接口
 */
public interface OperationLogService {

    void save(OperationLogInfo logInfo);
}
