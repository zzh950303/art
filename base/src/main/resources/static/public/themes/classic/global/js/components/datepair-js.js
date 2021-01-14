(function(window, document, $){
    "use strict";

    $.components.register("datepair", {
        mode: "default",
        defaults: {
            startClass: 'datepair-start',
            endClass: 'datepair-end',
            timeClass: 'datepair-time',
            dateClass: 'datepair-date'
        }
    });
})(window, document, jQuery);