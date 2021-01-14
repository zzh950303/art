(function(window, document, $){
    "use strict";

    $.components.register("selectable", {
        mode: "init",
        defaults: {
            allSelector: '.selectable-all',
            itemSelector: '.selectable-item',
            rowSelector: 'tr',
            rowSelectable: false,
            rowActiveClass: 'active',
            onChange: null
        },
        init: function (context, iframe) {
            var iframe$ = iframe ? iframe.$ : $, defaults;

            if (!iframe$.fn.asSelectable) {
                return;
            }

            defaults = $.components.getDefaults('selectable');

            $('[data-plugin="selectable"], [data-selectable="selectable"]', context).each(function () {
                var options = $.extend({}, defaults, $(this).data(iframe$));
                iframe$(this).asSelectable(options);
            });
        }
    });
})(window, document, jQuery);