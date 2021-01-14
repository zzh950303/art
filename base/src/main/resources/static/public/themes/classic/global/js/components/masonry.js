(function(window, document, $){
    "use strict";

    $.components.register("masonry", {
        mode: "init",
        defaults: {
            itemSelector: ".masonry-item"
        },
        init: function (context, iframe) {
            var iframe$ = iframe ? iframe.$ : $, defaults;

            if (typeof iframe$.fn.masonry === "undefined") {
                return;
            }

            defaults = $.components.getDefaults('masonry');

            $('[data-plugin="masonry"]', context).each(function () {
                var $this = iframe$(this),
                    options = $.extend(true, {}, defaults, $this.data());

                $this.masonry(options);
            });
        }
    });
})(window, document, jQuery);