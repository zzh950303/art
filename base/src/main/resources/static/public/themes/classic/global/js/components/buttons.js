(function (window, document, $) {
    "use strict";

    $.components.register("buttons", {
        mode: "api",
        defaults: {},
        api: function (context, iframe) {
            var iframe$ = iframe ? iframe.$ : $;

            $(context).on('click.site.loading', '[data-loading-text]', function () {
                var $btn = $(this),
                    text = $btn.text(),
                    i = 20,
                    loadingText = $btn.data('loadingText', iframe$);

                $btn.text(loadingText + '(' + i + ')').css('opacity', '.6');

                var timeout = setInterval(function () {
                    $btn.text(loadingText + '(' + (--i) + ')');
                    if (i === 0) {
                        clearInterval(timeout);
                        $btn.text(text).css('opacity', '1');
                    }
                }, 1000);
            });

            $(context).on('click.site.morebutton', '[data-more]', function () {
                var $target = $($(this).data('more', iframe$));
                $target.toggleClass('show');
            });
        }
    });
})(window, document, jQuery);