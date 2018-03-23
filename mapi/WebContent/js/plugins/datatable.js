/**
 * 秒变项目公共js
 */
;(function ($) {

  var Pagination = function (option, $ele) {
    if (!option) {
      this.$ele = $ele;
      return this;
    }
    var DEFAULT_AJAX = {
      'error': function (xhr, error, status) {
        if (error === "parsererror") {
          bootbox.alert({title: '提示', message: 'Json 格式错误'});
        }
        if (xhr.readyState === 4) {
          console.log(error, status);//有公共的ajaxerror统一处理
        }
      }
    };
    // 定义datatable默认加载参数
    var DEFAULT_OPTION = {
      // 开启服务器模式
      'serverSide': false,
      // 禁用排序
      'ordering': false,
      'pagingType': 'full_numbers',
      'ajax': null,
      'columns': null,
      // 表格首列按编号排序
      'fnDrawCallback': function () {
        var api = this.api();
        var startIndex = api.context[0]._iDisplayStart;//获取到本页开始的条数
        api.column(0).nodes().each(function (cell, i) {
          cell.innerHTML = startIndex + i + 1;
        });
      },
      // 汉化语言
      'language': {
        'lengthMenu': '每页 _MENU_ 条记录',
        'zeroRecords': '没有找到记录',
        'info': '显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项',
        'infoEmpty': '无记录',
        'infoFiltered': '(从 _MAX_ 条记录过滤)',
        'paginate': {
          'first': '首页',
          'last': '尾页',
          'next': '下一页',
          'previous': '上一页'
        }
      },
      // 分页dom结构（搜索框，分页组件）
      'dom': 't<"bottom"<"pull-left"i><"pull-left info"l><"pull-right"p>>'
    };
    // 合并option
    option = $.extend({}, DEFAULT_OPTION, option);
    option.ajax = $.extend({}, DEFAULT_AJAX, option.ajax);
    this.opt = option;
    this.$ele = $ele;
  };

  Pagination.prototype.init = function () {
    if (!this.opt) {
      return this;
    }
    // 发起真正的datatable请求
    this.$ele.DataTable(this.opt);
    // 移除与datatable与bootstrap冲突样式
    this.$ele.removeClass('dataTable');
    return this;
  };

  Pagination.prototype.repaint = function () {
    if (this.$ele.DataTable().page() > 0 && this.$ele.DataTable().rows().nodes().length <= 1) {
      this.$ele.DataTable().page('previous').draw(false);
    } else {
      this.$ele.DataTable().draw(false);
    }
  };

  Pagination.prototype.reload = function () {
    this.$ele.DataTable().ajax.reload();
  };

  Pagination.prototype.reDraw = function () {
    this.$ele.DataTable().draw(false);
  }

  $.fn.pagination = function (option) {
    var _pagination = new Pagination(option, this);
    return _pagination.init();
  }
})(jQuery);