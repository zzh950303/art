package com.ly.art.framework.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppBizException extends Exception {
    private static final long serialVersionUID = 7366300105025102660L;
    private String code;
    private String localizedMessage;
    private boolean immutable = false;
    private boolean logged = false;
    private Map<String, String> context = new HashMap();
    private String[] errorArgs;

    public AppBizException(String code) {
        super("AppBizException[" + code + "].");
        this.code = code;
    }

    public AppBizException(String code, Throwable t) {
        super("AppBizException[" + code + "].", t);
        this.code = code;
    }

    public AppBizException(String code, String message) {
        super("AppBizException[" + code + "]: " + message);
        this.localizedMessage = message;
        this.code = code;
    }

    public AppBizException(String code, String message, Throwable t) {
        super("AppBizException[" + code + "]: " + message, t);
        this.code = code;
        this.localizedMessage = message;
    }

    public AppBizException(String code, String message, Map<String, String> args) {
        super("AppBizException[" + code + "]: " + message);
        this.code = code;
        this.localizedMessage = message;
        this.context.putAll(args);
    }

    public AppBizException(String code, String message, String... args) {
        super("AppBizException[" + code + "]: " + message);
        this.code = code;
        this.localizedMessage = message;
        if (args != null && args.length > 0) {
            this.errorArgs = (String[]) Arrays.copyOf(args, args.length);
        }

    }

    public AppBizException(String code, String message, Map<String, String> args, Throwable t) {
        super("AppBizException[" + code + "]: " + message, t);
        this.code = code;
        this.localizedMessage = message;
        this.context.putAll(args);
    }

    public String getMessage() {
        return this.getLocalizedMessage();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getContext() {
        return this.context;
    }

    public void setContext(Map<String, String> args) {
        this.context = args;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public String getLocalizedMessage() {
        return this.localizedMessage;
    }

    public boolean isImmutable() {
        return this.immutable;
    }

    public void setImmutable(boolean immutable) {
        this.immutable = immutable;
    }

    public boolean isLogged() {
        return this.logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String[] getErrorArgs() {
        return this.errorArgs;
    }

    public void setErrorArgs(String[] errorArgs) {
        this.errorArgs = errorArgs;
    }
}
