(function(window, document, $){
    "use strict";

    $.components.register("formatter", {
        mode: "init",
        defaults: {
            persistent: true
        },

        init: function (context, iframe) {
            var iframe$ = iframe ? iframe.$ : $;

            if (!iframe$.fn.formatter) {
                return;
            }

            var defaults = $.components.getDefaults("formatter"),
                browserName = navigator.userAgent.toLowerCase(),
                ieOptions;

            if (/msie/i.test(browserName) && !/opera/.test(browserName)) {
                ieOptions = {
                    persistent: false
                };
            } else {
                ieOptions = {};
            }

            $('[data-plugin="formatter"]', context).each(function () {
                var options = $.extend({}, defaults, ieOptions, $(this).data(iframe$));

                if (options.pattern) {
                    options.pattern = options.pattern.replace(/\[\[/g, '{{').replace(/\]\]/g, '}}');
                }

                iframe$(this).formatter(options);
            });
        }
    });
})(window, document, jQuery);