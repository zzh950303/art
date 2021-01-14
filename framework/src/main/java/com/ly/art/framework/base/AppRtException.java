package com.ly.art.framework.base;

import java.util.HashMap;
import java.util.Map;

public class AppRtException extends RuntimeException{
    private static final long serialVersionUID = -4729161738021046262L;
    private String code;
    private String localizedMessage;
    private boolean immutable = false;
    private boolean logged = false;
    private Map<String, String> context = new HashMap();

    public AppRtException(String code) {
        super("AppRtException[" + code + "].");
        this.code = code;
    }

    public AppRtException(String code, Throwable t) {
        super("AppRtException[" + code + "].", t);
        this.code = code;
    }

    public AppRtException(String code, String message) {
        super("AppRtException[" + code + "]: " + message);
        this.code = code;
        this.localizedMessage = message;
    }

    public AppRtException(String code, String message, Throwable t) {
        super("AppRtException[" + code + "]: " + message, t);
        this.code = code;
        this.localizedMessage = message;
    }

    public AppRtException(String code, String message, Map<String, String> args) {
        super("AppRtException[" + code + "]: " + message);
        this.code = code;
        this.localizedMessage = message;
        this.context.putAll(args);
    }

    public AppRtException(String code, String message, Map<String, String> args, Throwable t) {
        super("AppRtException[" + code + "]: " + message, t);
        this.code = code;
        this.localizedMessage = message;
        this.context.putAll(args);
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
}
