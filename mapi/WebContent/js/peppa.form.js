;(function ($) {
    $.fn.extend({
        // 表单自动填充
        fill: function (data) {
            var $this = $(this);
            if (data) {
                $.each(data, function (k, v) {
                    //
                    if ("undefined" === typeof v || v == null) {
                        return;
                    }
                    var selector;
                    if (Object.prototype.toString.call(v) === '[object Array]') {
                        // 数组
                        var index = 0;
                        $.each(v, function (m, n) {
                            if (typeof(n) === 'object') {
                                // 对象数组
                                $.each(n, function (name, value) {
                                    selector = '[name="' + k + '[' + index + '].' + name + '"]';
                                    $this.find(selector).val(value);
                                });
                            } else {
                                // 其他类型数组
                                selector = '[m="' + k + '[' + index + '].' + m + '"]';
                                $this.find(selector).val(n);
                            }
                            // 实体类中对象的索引游标
                            index++;
                        });
                    } else if (typeof(v) === 'object') {
                        // 对象
                        $.each(v, function (name, value) {
                            selector = '[name="' + k + '.' + name + '"]';
                            $this.find(selector).val(value);
                        });
                    } else {
                        // 其它基本类型
                        selector = '[name="' + k + '"]';
                        $this.find(selector).val(v);
                        // 图片格式绑定src
                        if (typeof(v) === "string" && (v.endsWith(".jpg") || v.endsWith(".png") || v.endsWith(".bmp")
                                || v.endsWith(".gif") || v.endsWith(".jpeg"))) {
                            $this.find('img' + selector).attr("src", v);
                        }
                    }
                });
            }
        },
        clear: function () {
            var $this = $(this);
            $this.find('input').val('');
            $this.find('textarea').val('');
            $this.find('img').attr('src', '');
        }
    });
})(jQuery);