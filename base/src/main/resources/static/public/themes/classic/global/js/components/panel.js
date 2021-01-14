(function(window, document, $){
    "use strict";

    $.components.register("panel", {
        api: function (context, iframe) {
            var $doc = $(context), iframe$ = iframe ? iframe.$ : $,
                _window = iframe ? iframe : window;

            $doc.off('click.site.panel');

            $doc.on('click.site.panel', '[data-toggle="panel-fullscreen"]', function (e) {
                e.preventDefault();
                var $this = $(this),
                    $panel = $this.closest('.panel');

                var api = $panel.data('panel-api', iframe$);
                api.toggleFullscreen();
            });

            $doc.on('click.site.panel', '[data-toggle="panel-collapse"]', function (e) {
                e.preventDefault();
                var $this = $(this),
                    $panel = $this.closest('.panel');

                var api = $panel.data('panel-api', iframe$);
                api.toggleContent();
            });

            $doc.on('click.site.panel', '[data-toggle="panel-close"]', function (e) {
                e.preventDefault();
                var $this = $(this),
                    $panel = $this.closest('.panel');

                var api = $panel.data('panel-api', iframe$);
                api.close();
            });

            $doc.on('click.site.panel', '[data-toggle="panel-refresh"]', function (e) {
                e.preventDefault();
                var $this = $(this);
                var $panel = $this.closest('.panel');

                var api = $panel.data('panel-api', iframe$);
                var callback = $this.data('loadCallback', iframe$);

                if ($.isFunction(_window[callback])) {
                    api.load(_window[callback]);
                } else {
                    api.load();
                }
            });
        },

        init: function (context, iframe) {
            /*
            * 这里所能触发的自定义事件仅为统一和frame中的。父级无法触发子级自定义事件
            * */
            var iframe$ = iframe ? iframe.$ : $;

            $('.panel', context).each(function () {
                var $this = $(this);

                var isFullscreen = false;
                var isClose = false;
                var isCollapse = false;
                var isLoading = false;

                var $fullscreen = $this.find('[data-toggle="panel-fullscreen"]');
                var $collapse = $this.find('[data-toggle="panel-collapse"]');
                var $loading;
                var self = this;

                if ($this.hasClass('is-collapse')) {
                    isCollapse = true;
                }

                var api = {
                    load: function (callback) {
                        var type = $this.data('loader-type', iframe$);
                        if (!type) {
                            type = 'default';
                        }

                        $loading = $('<div class="panel-loading">' +
                            '<div class="loader loader-' + type + '"></div>' +
                            '</div>');

                        $loading.appendTo($this);

                        $this.addClass('is-loading');
                        $this.trigger('loading.uikit.panel');
                        isLoading = true;

                        if ($.isFunction(callback)) {
                            callback.call(self, this.done);
                        }
                    },
                    done: function () {
                        if (isLoading === true) {
                            $loading.remove();
                            $this.removeClass('is-loading');
                            $this.trigger('loading.done.uikit.panel');
                        }
                    },
                    toggleContent: function () {
                        if (isCollapse) {
                            this.showContent();
                        } else {
                            this.hideContent();
                        }
                    },

                    showContent: function () {
                        if (isCollapse !== false) {
                            $this.removeClass('is-collapse');

                            if ($collapse.hasClass('wb-plus')) {
                                $collapse.removeClass('wb-plus').addClass('wb-minus');
                            }

                            $this.trigger('shown.uikit.panel');

                            isCollapse = false;
                        }
                    },

                    hideContent: function () {
                        if (isCollapse !== true) {
                            $this.addClass('is-collapse');

                            if ($collapse.hasClass('wb-minus')) {
                                $collapse.removeClass('wb-minus').addClass('wb-plus');
                            }

                            $this.trigger('hidden.uikit.panel');
                            isCollapse = true;
                        }
                    },

                    toggleFullscreen: function () {
                        if (isFullscreen) {
                            this.leaveFullscreen();
                        } else {
                            this.enterFullscreen();
                        }
                    },
                    enterFullscreen: function () {
                        if (isFullscreen !== true) {
                            $this.addClass('is-fullscreen');

                            if ($fullscreen.hasClass('wb-expand')) {
                                $fullscreen.removeClass('wb-expand').addClass('wb-contract');
                            }

                            $this.trigger('enter.fullscreen.uikit.panel');
                            isFullscreen = true;
                        }
                    },
                    leaveFullscreen: function () {
                        if (isFullscreen !== false) {
                            $this.removeClass('is-fullscreen');

                            if ($fullscreen.hasClass('wb-contract')) {
                                $fullscreen.removeClass('wb-contract').addClass('wb-expand');
                            }

                            $this.trigger('leave.fullscreen.uikit.panel');
                            isFullscreen = false;
                        }
                    },
                    toggle: function () {
                        if (isClose) {
                            this.open();
                        } else {
                            this.close();
                        }
                    },
                    open: function () {
                        $this.on('open.uikit.panel', function () {
                            var $that = $(this);

                            if($that.siblings().length){
                                $that.show();
                            }else{
                                $that.parent().show();
                            }
                        });

                        if (isClose !== false) {
                            $this.removeClass('is-close');
                            $this.trigger('open.uikit.panel');

                            isClose = false;
                        }
                    },
                    close: function () {
                        $this.on('close.uikit.panel', function () {
                            var $that = $(this);

                            if($that.siblings().length){
                                $that.hide();
                            }else{
                                $that.parent().hide();
                            }
                        });

                        if (isClose !== true) {

                            $this.addClass('is-close');
                            $this.trigger('close.uikit.panel');

                            isClose = true;
                        }
                    }
                };

                $this.data('panel-api', api, iframe$);
            });
        }
    });
})(window, document, jQuery);