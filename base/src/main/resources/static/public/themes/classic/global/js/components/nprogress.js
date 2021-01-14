(function(window, document, $){
    "use strict";

    $.components.register("nprogress", {
        mode: "init",
        defaults: {
            minimum: 0.15,
            trickleRate: 0.07,
            trickleSpeed: 360,
            showSpinner: false,
            template: '<div class="bar" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'
        },
        init: function (context, iframe) {
            var NProgress = (iframe && iframe.NProgress) ? iframe.NProgress : window.NProgress;

            if (typeof NProgress === "undefined") {
                return;
            }

            NProgress.configure(this.defaults);
        }
    });
})(window, document, jQuery);