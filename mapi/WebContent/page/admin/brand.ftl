<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<@global.head "Tomato Admin - 品牌管理">
    <style type="text/css">
        .margin-left {
            margin-left: 15px;
        }
    </style>
</@global.head>
<body class="app sidebar-mini rtl">
<@global.navbar></@global.navbar>
<@global.sidebar 2></@global.sidebar>
<@global.content "品牌管理">
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <button id="addNew" class="btn btn-primary" type="button"><i
                            class="fa fa-fw fa-lg fa-check-circle"></i>新增
                    </button>
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
                            <th>ID</th>
                            <th>品牌名称</th>
                            <th>下载码</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade"
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
                        <input name="brandId" type="text" hidden>
                        <div class="form-group row">
                            <label class="control-label col-md-3">品牌名称&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="brandName" class="form-control" type="text" placeholder="请输入名称">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">下载码&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="brandDownloadcode" class="form-control" type="text" placeholder="请输入下载码">
                            </div>
                        </div>
                        <div class="form-group row">
                            <button class="btn btn-primary margin-left" type="button" onclick="addAgent()">新增代理</button>
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
                url: '${ctx}/admin/listBrand',
                type: "get"
            },
            'columns': [
                {'data': 'brandId'},
                {'data': 'brandName'},
                {'data': 'brandDownloadcode'},
                {
                    'data':
                            function (row) {
                                return "<a class=\'btn btn-primary btn-sm\' href=\'javascript:editItem(" + row.brandId + ")\'><i class=\'fa fa-pencil-square-o\'></i>编辑</a>" +
                                        '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                        "<a class=\'btn btn-info btn-sm\' href=\'javascript:removeItem(" + row.brandId + ")\'><i class='fa fa-eye\'></i>删除</a>";
                            }
                }
            ]
        });
    });

    $('#addNew').click(function () {
        $('.modal').modal('show');
    });

    // 编辑栏目
    function editItem(id) {
        $.ajax({
            url: '${ctx}/admin/getBrandById',
            type: 'get',
            data: {
                id: id
            },
            success: function (result) {
                var agents = result.agents;
                if (agents) {
                    $.each(agents, function (i, agent) {
                        addAgent(agent);
                    });
                }
                $('#form-data').fill(result);
                $('.modal').modal('show');
            }
        });
    }

    // 保存栏目
    function saveItem() {
        var $form = $('#form-data');
        var url = '${ctx}/admin/saveBrand';
        $form.ajaxSubmit({
            url: url,
            type: 'POST',
            dataType: 'json',
            beforeSubmit: function () {
                console.log('before');
            },
            success: function (result) {
                if (result.success) {
                    closePopup();
                    reloadDataTable();
                }
            }
        });
    }

    // 重新加载表格
    function reloadDataTable() {
        $('#sampleTable').DataTable().ajax.reload();
    }

    // 弹出框关闭事件
    $('.modal').on('hidden.bs.modal', function () {
        var $form = $('#form-data');
        $form.clear();
        clearAgent();
    });

    // 关闭弹出框
    function closePopup() {
        $('.modal').modal('hide');
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
                        url: '${ctx}/admin/removeBrandById',
                        type: 'post',
                        data: {
                            id: id
                        },
                        success: function () {
                            swal("操作成功！");
                            reloadDataTable();
                        }
                    });
                }
        );
    }

    function addAgent(agent) {
        var agentName = agent ? agent.agentlevel_name : '';
        var $form = $('#form-data');
        $form.append('<div class="form-group row js-agent">' +
                '<input name="agents[' + $form.find('.js-agent').length + '].agentlevel_name" class="form-control col-md-8 margin-left" type="text" placeholder="请输入代理名称" value="' + agentName + '">' +
                '<button class="btn btn-primary margin-left" type="button" onclick="removeRow()">删除</button>' +
                '</div>')
    }

    function removeRow() {
        $(getEventTarget()).parent('.js-agent').remove();
        formatAgentName();
    }

    function clearAgent() {
        $('#form-data').find('.js-agent').remove();
    }

    function formatAgentName() {
        $('#form-data').find('.js-agent').each(function (i, item) {
            $(item).find('input').attr('name', 'agents[' + i + '].agentlevel_name');
        });
    }

    function getEvent() {
        if (document.all) return window.event;
        func = getEvent.caller;
        while (func !== null) {
            var arg0 = func.arguments[0];
            if (arg0) {
                if ((arg0.constructor === Event || arg0.constructor === MouseEvent) || (typeof (arg0) === "object" && arg0.preventDefault && arg0.stopPropagation)) {
                    return arg0;
                }
            }
            func = func.caller;
        }
        return null;
    }

    function getEventTarget() {
        event = getEvent();
        return event.srcElement || event.target;
    }
</script>
</@global.script>
</body>
</html>