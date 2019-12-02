<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<@global.head "Tomato Admin - 订单管理">
    <style type="text/css">
        .margin-left {
            margin-right: 5px;
        }
    </style>
</@global.head>
<body class="app sidebar-mini rtl">
<@global.navbar></@global.navbar>
<@global.sidebar 5></@global.sidebar>
<@global.content "订单管理">
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <form class="form-inline" id="queryForm">
                        <div class="form-group col-auto">
                            <label class="col-form-label">
                                用户手机:
                            </label>
                            <input type="text" name="user_phone" id="user_phone" class="form-control" placeholder="输入手机号"/>
                        </div>
                        <div class="form-group col-auto">
                            <label class="col-form-label">
                                支付方式:
                            </label>
                            <select name="pay_type" class="form-control">
                                <option value="">请选择</option>
                                <option value="1">支付宝</option>
                                <option value="2">微信</option>
                                <option value="3">IOS</option>
                            </select>
                        </div>
                        <div class="form-group col-auto">
                            <label class="col-form-label">
                                交易状态:
                            </label>
                            <select name="state" class="form-control">
                                <option value="">请选择</option>
                                <option value="0">等待支付</option>
                                <option value="1">交易成功;</option>
                                <option value="2">交易取消</option>
                            </select>
                        </div>
                        <div class="form-group col-auto">
                            <label class="col-form-label">
                                开始时间:
                            </label>
                            <input type="text" name="start_time" id="start_time" class="form-control" placeholder="请选择开始时间" readonly/>
                        </div>
                        <div class="form-group col-auto">
                            <label class="col-form-label">
                                结束时间:
                            </label>
                            <input type="text" name="end_time" id="end_time" class="form-control" placeholder="请选择结束时间" readonly/>
                        </div>
                        <div class="form-group col-auto">
                            <button id="query" class="btn btn-primary right" type="button">
                                <i class="fa fa-fw fa-lg fa-check-circle"></i>查询
                            </button>
                        </div>
                        <div class="form-group col-auto">
                            <button id="export" class="btn btn-primary right" type="button">
                                <i class="fa fa-fw fa-lg fa-check-circle"></i>导出
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <table class="table table-hover table-bordered" id="sampleTable">
                        <thead>
                        <tr>
                            <th>订单号</th>
                            <th>用户ID</th>
                            <th>用户手机号</th>
                            <th>订单金额(元)</th>
                            <th>订单类型</th>
                            <th>支付类型</th>
                            <th>时间</th>
                            <th>交易状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal"
         tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">新增条目</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">
                    <form id="form-data" class="form-horizontal">
                        <input name="id" type="text" hidden>
                        <input name="imgUrl" type="text" hidden id="imgUrl">
                        <div class="form-group row">
                            <label class="control-label col-md-3">上传图片&nbsp;：</label>
                            <div class="col-md-8">
                                <input id="uploadImg" name="file" type="file" multiple>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">链接地址&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="linkUrl" class="form-control" type="text" placeholder="请输入链接地址">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">描述&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="description" class="form-control" type="text" placeholder="描述">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" onclick="saveItem()">保存</button>
                    <button class="btn btn-secondary" type="button" onclick="closePopup()">关闭</button>
                </div>
            </div>
        </div>
    </div>
</@global.content>


<@global.script>
    <script type="text/javascript">
        $(function () {
            $('#sampleTable').dataTable({
                // 开启服务器模式
                'serverSide': false,
                'ordering': false,
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
                'ajax': {
                    url: '${ctx}/payorder/list',
                    type: "post",
                    data: function () {
                        return $('#queryForm').serialize();
                    }
                },
                'columns': [
                    {'data': 'order_no'},
                    {'data': 'account_id'},
                    {'data': 'user_phone'},
                    {'data':
                        function (row) {
                            return (row.order_amount/100).toFixed(2);
                        }
                    },
                    {'data':
                        function (row){
                            switch (row.order_type) {
                                case 1:
                                    return '年费充值';
                                case 2:
                                    return '连续包月';
                                case 3:
                                    return '月费充值';
                                case 4:
                                    return '兑换下载';
                                default: return '充值'
                            }
                        }
                    },
                    {'data':
                        function (row){
                            switch (row.pay_type) {
                                case 1:
                                    return '支付宝';
                                case 2:
                                    return '微信';
                                case 3:
                                    return 'IOS';
                                default: return '充值'
                            }
                        }
                    },
                    {'data': 'create_time'},
                    {'data':
                        function (row){
                            switch (row.state) {
                                case 0:
                                    return '等待支付';
                                case 1:
                                    return '交易成功';
                                case 2:
                                    return '交易取消';
                                default: return '交易'
                            }
                        }
                    },
                    {'data':
                        function (row) {
                            return "<a class=\'btn btn-primary btn-sm\' href=\'javascript:editItem(" + row.order_no + ")\'>" +
                                   "<i class=\'fa fa-pencil-square-o\'></i>编辑</a>";
                        }
                    }
                ]
            });
        });

        //日期控件
        $('#start_time').datepicker({
            language: "zh-CN",
            format: "yyyy-mm-dd",
            onSelect: function(date){
                $('#start_time').val(date);
            }
        });

        $('#end_time').datepicker({
            language: "zh-CN",
            format: "yyyy-mm-dd"
        });

        $('#query').click(function () {
            reloadDataTable();
        });
    
        $('#export').click(function () {

        });
        
        $('#addNew').click(function () {
            // addAgent({agentlevel_name: '零售价'});
            $('#myModal').modal('show');
        });

        // 编辑栏目
        function editItem(id) {
            $.ajax({
                url: '${ctx}/adImg/getAdImgById',
                type: 'get',
                data: {
                    id: id
                },
                success: function (result) {
                    if(result.code === 0){
                        var adImg = result.data;
                        $('#form-data').fill(adImg);
                        $('#myModal').modal('show');
                    }else{
                        swal("操作失败！");
                    }
                }
            });
        }

        // 保存栏目
        function saveItem() {
            var $form = $('#form-data');
            var url = '${ctx}/adImg/save';
            // 清空disabled属性，防止未提交至后台
            $form.find('[disabled]').prop('disabled', false);
            $form.ajaxSubmit({
                url: url,
                type: 'POST',
                dataType: 'json',
                beforeSubmit: function () {
                    console.log('before');
                },
                success: function (result) {
                    if (result.code === 0) {
                        swal("操作成功！");
                        closePopup();
                        reloadDataTable();
                    }else {
                        swal("操作失败！");
                    }

                }
            });
        }

        // 重新加载表格
        function reloadDataTable() {
            $('#sampleTable').DataTable().ajax.reload();
        }

        // 弹出框关闭事件
        $('#myModal').on('hidden.bs.modal', function () {
            var $form = $('#form-data');
            $form.clear();
            clearAgent();
        });

        // 关闭弹出框
        function closePopup() {
            $('#myModal').modal('hide');
        }

        function removeItem(id) {
            swal({
                    title: "确定删除该项吗？",
                    text: "删除将移除此项！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                },
                function () {
                    $.ajax({
                        url: '${ctx}/adImg/delete',
                        type: 'post',
                        data: {
                            id: id
                        },
                        success: function (res) {
                            if(res.code === 0){
                                swal("操作成功！");
                                reloadDataTable();
                            }else {
                                swal("操作失败！");
                            }
                        },
                        error: function () {
                            swal("操作失败！");
                        }
                    });
                }
            );
        }

        function clearAgent() {
            $('#form-data').find('.js-agent').remove();
        }

        function formatAgentName() {
            $('#form-data').find('.js-agent').each(function (i, item) {
                $(item).find('.js-id').attr('name', 'agents[' + i + '].agentlevel_id');
                $(item).find('.js-name').attr('name', 'agents[' + i + '].agentlevel_name');
                $(item).find('.js-default').attr('name', 'agents[' + i + '].agentlevel_default');
            });
        }
    </script>
</@global.script>
</body>
</html>