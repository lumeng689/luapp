/**
 * Created by lum on 2016/2/26.
 */
/**
 * 参考  http://www.jianshu.com/p/b4c99207b1be
 */
(function () {
    // 1. 创建plugin代码
    var ColorButton = function ($select, options) {
        // 初始化
        this.$el = $select;
        this.colorIndex = 0;
        this.settings = $.extend({
            // 默认参数
            colors: ['#ff0', '#f0f', '#0ff']
        }, options || {});
        // binding 事件
        var that = this;
        this.$el.click(function (e) {
            console.log('clicked');
            e.preventDefault();
            that.changeColor();
        });
    };

    // 2. 为plugin添加方法
    $.extend(ColorButton.prototype, {
        // 定义方法
        changeColor: function () {
            this.$el.css({backgroundColor:this.getColor()});
        },
        getColor: function () {
            var currentIndex = this.colorIndex;
            this.colorIndex = (this.colorIndex + 1) % this.settings.colors;
            return this.settings.color[currentIndex];
        }
    });

    // 3. 将plugin 绑定到jQuery
    $.fn.colorButton = function (options) {
        return this.each(function () {
            var $select = $(this);
            if (!$select.data('ColorButton')) {
                $select.data('ColorButton', new ColorButton($select, options));
            }
        });
    }
})(jQuery);