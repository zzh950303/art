(function (window, document, $) {
    "use strict";

    $.components.register("isotope", {
        mode: "init",
        defaults: {},
        init: function (context, iframe) {
            var iframe$ = iframe ? iframe.$ : $,
                _window = iframe ? iframe : window, defaults;

            if (typeof iframe$.fn.isotope === "undefined") {
                return;
            }
            defaults = $.components.getDefaults('isotope');

            var callback = function () {
                $('[data-plugin="isotope"]', context).each(function () {
                    var $this = iframe$(this),
                        options = $.extend(true, {}, defaults, $this.data());

                    $this.isotope(options);
                });
            };
            if (context !== document) {
                callback();
            } else {
                $(_window).on('load', function () {
                    callback();
                });
            }
        }
    });
})(window, document, jQuery);