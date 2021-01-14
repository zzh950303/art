(function (window, document) {
    'use strict';

    var btn;

    if (typeof $ === 'undefined') {
        btn = document.getElementById('closeTab');

        btn.innerHTML = '退回上一页';

        btn.onclick = function () {
            history.go(-1);
        };
    } else if (typeof $.site.contentTabs !== 'undefined') {
        $(document).on('click', '#closeTab', function () {
            $.site.contentTabs.closeTab();
        });
    }

})(window, document);