(function (window, document, $) {
    "use strict";

    $.components.register("gridstack", {
        mode: "init",
        defaults: {
            cellHeight: 80,
            verticalMargin: 20
        },
        init: function (context, iframe) {
            var iframe$ = iframe ? iframe.$ : $;

            if (!iframe$.fn.gridstack) {
                return;
            }

            var defaults = $.components.getDefaults("gridstack");

            $('[data-plugin="gridstack"]', context).each(function () {
                var options = $.extend(true, {}, defaults, $(this).data(iframe$));

                iframe$(this).gridstack(options);
            });
        }
    });
})(window, document, jQuery);