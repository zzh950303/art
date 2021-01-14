package com.ly.art.base.controller.system.data;

import java.util.ArrayList;
import java.util.List;

public class MenuData {

    /**
     * 编号
     */
    private Long id;

    /**
     * 文本
     */
    private String text;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接
     */
    private String url;

    /**
     * 权限
     */
    private String permission;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 父级菜单id
     */
    private Long parentId;

    /**
     * 子菜单
     */
    private List<MenuData> childList;

    private boolean childHasNoPermissionLimit;

    private List<String> childPermissionList;

    private String childPermissionStr;

    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<MenuData> getChildList() {
        if (childList == null) {
            childList = new ArrayList<>();
        }

        return childList;
    }

    public void setChildList(List<MenuData> childList) {
        this.childList = childList;
    }

    public boolean isChildHasNoPermissionLimit() {
        return childHasNoPermissionLimit;
    }

    public void setChildHasNoPermissionLimit(boolean childHasNoPermissionLimit) {
        this.childHasNoPermissionLimit = childHasNoPermissionLimit;
    }

    public List<String> getChildPermissionList() {
        if (childPermissionList == null) {
            childPermissionList = new ArrayList<>();
        }

        return childPermissionList;
    }

    public void setChildPermissionList(List<String> childPermissionList) {
        this.childPermissionList = childPermissionList;
    }

    public String getChildPermissionStr() {
        return childPermissionStr;
    }

    public void setChildPermissionStr(String childPermissionStr) {
        this.childPermissionStr = childPermissionStr;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
