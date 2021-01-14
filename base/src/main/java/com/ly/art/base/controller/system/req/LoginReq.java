package com.ly.art.base.controller.system.req;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginReq {

    @NotBlank
    private String account;

    @NotBlank
    private String password;

    @NotBlank
    private String captchaVerification;

}
