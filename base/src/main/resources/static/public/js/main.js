(function (docuemnt, window, $) {
    'use strict';

    // 通用方法封装处理
    $.common = $.common || {};

    $.extend($.common, {
        // 判断字符串是否为空
        isEmpty: function (value) {
            if (value == null || this.trim(value) == "") {
                return true;
            }
            return false;
        },
        // 判断一个字符串是否为非空串
        isNotEmpty: function (value) {
            return !$.common.isEmpty(value);
        },
        // 空对象转字符串
        nullToStr: function(value) {
            if ($.common.isEmpty(value) || $.common.equals(value, "null")) {
                return "";
            }
            return value;
        },
        // 是否显示数据 为空默认为显示
        visible: function (value) {
            if ($.common.isEmpty(value) || value == true) {
                return true;
            }
            return false;
        },
        // 空格截取
        trim: function (value) {
            if (value == null) {
                return "";
            }
            return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
        },
        // 比较两个字符串（大小写敏感）
        equals: function (str, that) {
            return str == that;
        },
        // 比较两个字符串（大小写不敏感）
        equalsIgnoreCase: function (str, that) {
            return String(str).toUpperCase() === String(that).toUpperCase();
        },
        // 将字符串按指定字符分割
        split: function (str, sep, maxLen) {
            if ($.common.isEmpty(str)) {
                return null;
            }
            var value = String(str).split(sep);
            return maxLen ? value.slice(0, maxLen - 1) : value;
        },
        // 字符串格式化(%s )
        sprintf: function (str) {
            var args = arguments, flag = true, i = 1;
            str = str.replace(/%s/g, function () {
                var arg = args[i++];
                if (typeof arg === 'undefined') {
                    flag = false;
                    return '';
                }
                return arg;
            });
            return flag ? str : '';
        },
        // 指定随机数返回
        random: function (min, max) {
            return Math.floor((Math.random() * max) + min);
        },
        // 判断字符串是否是以start开头
        startWith: function(value, start) {
            var reg = new RegExp("^" + start);
            return reg.test(value)
        },
        // 判断字符串是否是以end结尾
        endWith: function(value, end) {
            var reg = new RegExp(end + "$");
            return reg.test(value)
        },
        // 数组去重
        uniqueFn: function(array) {
            var result = [];
            var hashObj = {};
            for (var i = 0; i < array.length; i++) {
                if (!hashObj[array[i]]) {
                    hashObj[array[i]] = true;
                    result.push(array[i]);
                }
            }
            return result;
        },
        // 数组中的所有元素放入一个字符串
        join: function(array, separator) {
            if ($.common.isEmpty(array)) {
                return null;
            }
            return array.join(separator);
        },
        // 获取form下所有的字段并转换为json对象
        formToJSON: function(formId) {
             var json = {};
             $.each($("#" + formId).serializeArray(), function(i, field) {
                 json[field.name] = field.value;
             });
            return json;
        }
    });

    // 通用方法封装处理
    $.core = $.core || {};

    $.extend($.core, {
        defaultExceptionHandle: function(result) {
            var code = result.code
            if (code == 'OSS.401') {
                window.location.href = 'loginPage';
            } else {
                parent.layer.msg(result.message, {icon: 5, time: 1000});
            }
        },
        parseJSON: function(str) {
            var obj = null;
            if (typeof str == 'string') {
                try {
                    obj = JSON.parse(str);
                } catch(e) {
                    //empty
                }
            }
            return obj;
        },
        postJSON: function (url, data, isRequestBody) {
            if (data == undefined) {
                data = {};
            }
            var contentType = 'application/x-www-form-urlencoded;charset=utf-8';
            if (isRequestBody) {
                contentType = 'application/json;charset=utf-8';
                data = JSON.stringify(data);
            }

            var load = parent.layer.load();
            var jsonData;
            $.ajax({
                url: url,
                type: "POST",
                data: data,
                contentType: contentType,
                async: false,
                error: function () {
                    parent.layer.close(load);
                    parent.layer.msg("出错了", {icon: 5, time: 1000});
                },
                dataType: "json",
                success: function (data) {
                    jsonData = data;
                }
            });

            parent.layer.close(load);
            return jsonData;
        },
        getJSON: function (url, data) {
            if (data == undefined) {
                data = {};
            }
            var jsonData;
            $.ajax({
                url: url,
                type: "GET",
                data: data,
                async: false,
                error: function () {
                    parent.layer.msg("出错了", {icon: 5, time: 1000});
                },
                dataType: "json",
                success: function (data) {
                    jsonData = data;
                }
            });
            return jsonData;
        },
        openImg: function(url) {
            var data = {
                "title": "", //相册标题
                "id": 1, //相册id
                "start": 0, //初始显示的图片序号，默认0
                "data": [   //相册包含的图片，数组格式
                    {
                        "alt": "",
                        "pid": 1, //图片id
                        "src": url, //原图地址
                        "thumb": url //缩略图地址
                    }
                ]
            };
            parent.layer.photos({
                closeBtn:true,//关闭按钮
                photos: data,
                anim: 5 //0-6的选择，指定弹出图片动画类型
            });
        },
        openUrlFullScreen: function(url, title, fun) {
            var index = parent.layer.open({
                type: 2,
                title: title,
                content: url,
                maxmin: true,
                end: function () {
                    if (fun) {
                        fun();
                    }
                }
            });
            parent.layer.full(index);
        },
        openUrl: function (url, title, width, height, fun) {
            parent.layer.open({
                type: 2,
                shadeClose: false,
                title: title,
                shade: 0.3,
                area: [width, height], // 宽高
                content: url,
                end: function () {
                    if (fun) {
                        fun();
                    }
                }
            });
        },
        promptarea: function (title, fun, defaualtValue) {
            parent.layer.prompt({
                formType: 2,
                value: defaualtValue,
                title: title,
                area: ['400px', '350px']
            }, function (value, index, elem) {
                fun(value);
                parent.layer.close(index);
            });
        },
        prompt: function (title, fun, defaualtValue) {
            parent.layer.prompt({
                formType: 3,
                value: defaualtValue,
                title: title
            }, function (value, index, elem) {
                fun(value);
                parent.layer.close(index);
            });
        }
    });

})(document, window, jQuery);

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};