(function (window, document, $) {
    "use strict";

    $.components.register("gauge", {
        mode: "init",
        defaults: {
            lines: 12,
            angle: 0.12,
            lineWidth: 0.4,
            pointer: {
                length: 0.68,
                strokeWidth: 0.03,
                color: $.colors("blue-grey", 400)
            },
            limitMax: true,
            colorStart: $.colors("blue-grey", 200),
            colorStop: $.colors("blue-grey", 200),
            strokeColor: $.colors("purple", 500),
            generateGradient: true
        },
        init: function (context, iframe) {
            var iframe$ = iframe ? iframe.$ : $, Gauge = (iframe && iframe.Gauge) ? iframe.Gauge : window.Gauge, defaults;

            if (typeof Gauge === undefined) {
                return;
            }

            defaults = $.components.getDefaults("gauge");

            $('[data-plugin="gauge"]', context).each(function () {
                var $this = $(this),
                    options = $this.data(iframe$),
                    $text = $this.find('.gauge-label'),
                    $canvas = $this.find("canvas");

                options = $.extend(true, {}, defaults, options);

                if ($canvas.length === 0) {
                    return;
                }

                var gauge = new Gauge($canvas[0]).setOptions(options);

                $this.data("gauge", gauge, iframe$);

                gauge.animationSpeed = 50;
                gauge.maxValue = $this.data('max-value', iframe$);

                gauge.set($this.data("value", iframe$));

                if ($text.length > 0) {
                    gauge.setTextField($text[0]);
                }
            });
        }
    });

    $.components.register("donut", {
        mode: "init",
        defaults: {
            lines: 12,
            angle: 0.3,
            lineWidth: 0.08,
            pointer: {
                length: 0.9,
                strokeWidth: 0.035,
                color: $.colors("blue-grey", 400)
            },
            limitMax: false, // If true, the pointer will not go past the end of the gauge
            colorStart: $.colors("blue-grey", 200),
            colorStop: $.colors("blue-grey", 200),
            strokeColor: $.colors("purple", 500),
            generateGradient: true
        },
        init: function (context, iframe) {
            var iframe$ = iframe ? iframe.$ : $, Donut = (iframe && iframe.Donut) ? iframe.Donut : window.Donut, defaults;

            if (typeof Donut === undefined) {
                return;
            }

            defaults = $.components.getDefaults("donut");

            $('[data-plugin="donut"]', context).each(function () {
                var $this = $(this),
                    options = $this.data(iframe$),
                    $text = $this.find('.donut-label'),
                    $canvas = $this.find("canvas");

                options = $.extend(true, {}, defaults, options);

                if ($canvas.length === 0) {
                    return;
                }

                var donut = new Donut($canvas[0]).setOptions(options);

                $this.data("donut", donut, iframe$);

                donut.animationSpeed = 50;
                donut.maxValue = $this.data('max-value', iframe$);

                donut.set($this.data("value", iframe$));

                if ($text.length > 0) {
                    donut.setTextField($text[0]);
                }
            });
        }
    });
})(window, document, jQuery);