(function (window, document, $) {
    "use strict";

    $.components.register("slimScroll", {
        mode: "default",
        defaults: {
            height : '100%',
            size : '4px',
            color: $.configs.colors['blue-grey']['500'],
            position : 'right',
            distance : '1px',
            railVisible : true,
            railColor : $.configs.colors['blue-grey']['300'],
            railOpacity : 0.1,
            railDraggable : true,
            wheelStep : 15,
            borderRadius: '4px',
            railBorderRadius : '4px'
        }
    });

})(window, document, jQuery);
