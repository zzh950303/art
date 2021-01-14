(function (window, document, $) {
    "use strict";

    $.components.register("input-group-file", {
        api: function (context) {
            $(context).on("change", ".input-group-file [type=file]", function () {
                var $this = $(this),
                    $text = $(this).parents('.input-group-file').find('.form-control'), value = "";

                $.each($this[0].files, function (i, file) {
                    value += file.name + ", ";
                });
                value = value.substring(0, value.length - 2);

                $text.val(value);
            });
        }
    });
})(window, document, jQuery);