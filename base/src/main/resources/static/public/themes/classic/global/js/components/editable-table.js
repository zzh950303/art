(function(window, document, $){
    "use strict";

    $.components.register("editableTable", {
        mode: "init",
        init: function (context, iframe) {
            var ifaram$ = iframe ? iframe.$ : $, defaults;

            if (!ifaram$.fn.editableTableWidget) {
                return;
            }

            defaults = $.components.getDefaults("editableTable");

            $('[data-plugin="editableTable"]', context).each(function () {
                var options = $.extend(true, {}, defaults, $(this).data(ifaram$));

                ifaram$(this).editableTableWidget(options);
            });
        }
    });
})(window, document, jQuery);