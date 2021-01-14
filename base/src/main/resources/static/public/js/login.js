(function (window, document, $) {
    'use strict';

    var vaildateLoginForm = function() {
        var $loginForm = $("#loginForm");

        $loginForm.formValidation({
            locale: 'zh_CN',
            framework: 'bootstrap',
            excluded: ':disabled',
            icon: {
                valid: 'icon wb-check',
                invalid: 'icon wb-close',
                validating: 'icon wb-refresh'
            },
            fields: {
                loginName: {
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '密码必须大于6且小于30个字符'
                        }
                    }
                }
            }
        }).on('success.form.fv', function (e) {
            // Prevent form submissio
            e.preventDefault();

            //开启提交按钮
            $loginForm.data('formValidation').disableSubmitButtons(false)
        });

        var fv = $loginForm.data('formValidation');
        fv.validate();
        return fv.isValid();
    }

    var loginSubmit = function(captchaVerification) {
        //登录清空admui.base.contentTabs
        if (localStorage) {
            localStorage.removeItem('admui.base.contentTabs');
        }

        $.ajax({
            url: 'signin',
            type: 'POST',
            data: {
                'account': $.trim($('#loginName').val()),
                'password': $.trim($('#password').val()),
                'captchaVerification': $.trim(captchaVerification)
            },
            dataType: 'JSON',
            success: function(response) {
                if ('0000' != response.code) {
                    swal({
                        title: response.message,
                        type: 'error',
                        timer: 1500,
                        buttonsStyling: false,
                        confirmButtonClass: "btn btn-danger"
                    });
                } else {
                    window.location.href = 'homePage';
                }
                $('#loginForm').data('formValidation').resetForm();
            },
            error: function() {
                $('#loginForm').data('formValidation').resetForm();
                toastr.error('服务器异常，请稍后再试！');
            }
        });
    }

    // 初始化验证码  弹出式
    $('#captcha-panel').slideVerify({
        baseUrl: '',  //服务器请求地址, 默认地址为当前根路径;
        mode:'pop',     //展示模式
        containerId:'login-btn',//pop模式 必填 被点击之后出现行为验证码的元素id
        imgSize : {       //图片的大小对象,有默认值{ width: '310px',height: '155px'},可省略
            width: '400px',
            height: '200px',
        },
        barSize:{          //下方滑块的大小对象,有默认值{ width: '310px',height: '50px'},可省略
            width: '400px',
            height: '40px',
        },
        beforeCheck:function(){  //检验参数合法性的函数  mode ="pop"有效
            return vaildateLoginForm();
        },
        ready : function() {},  //加载完毕的回调
        success : function(params) { //成功的回调
            loginSubmit(params['captchaVerification']);
        },
        error : function() {}//失败的回调
    });

})(window, document, jQuery);