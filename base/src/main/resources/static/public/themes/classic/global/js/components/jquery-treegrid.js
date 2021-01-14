(function(window, document, $){
    "use strict";

    $.components.register("treegrid", {
        mode: "default",
        defaults: {
            expanderExpandedClass: 'icon wb-triangle-down',
            expanderCollapsedClass: 'icon wb-triangle-right'
        }
    });
})(window, document, jQuery);