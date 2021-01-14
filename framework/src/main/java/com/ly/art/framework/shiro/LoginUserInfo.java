package com.ly.art.framework.shiro;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class LoginUserInfo implements Serializable {

    private static final long serialVersionUID = 2043630042570549083L;

    /**
     * 登录账户
     */
    private String account;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 加密Salt
     */
    private String salt;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后一次登录IP
     */
    private String lastLoginIp;

    /**
     * 所属部门编号
     */
    private Long deptId;

    /**
     * 对应的角色
     */
    private List<String> roles;

    /**
     * 对应的角色
     */
    private List<String> roleCodes;

    /**
     * 对应的权限
     */
    private List<String> permissions;

    /**
     * 所属商户
     */
    private String merchantNo;
}
