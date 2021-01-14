(function(window, document, $){
    "use strict";

    $.components.register("table", {
        mode: "api",
        api: function (context) {
            var touch = typeof context.ontouchstart !== 'undefined',
                type = 'click';

            if (touch) {
                type = 'touchstart';
            }

            $(context).on(type, '.table-section', function (e) {
                if ("checkbox" !== e.target.type && "button" !== e.target.type && "a" !== e.target.tagName.toLowerCase() && !$(e.target).parent("div.checkbox-custom").length) {
                    if ($(this).hasClass("active")) {
                        $(this).removeClass("active");
                    } else {
                        $(this).siblings('.table-section').removeClass("active");
                        $(this).addClass("active");
                    }
                }
            });
        }
    });
})(window, document, jQuery);