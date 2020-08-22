<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<@global.head "Tomato Admin - 权限管理">
    <style type="text/css">
        .margin-left {
            margin-right: 5px;
        }
    </style>
</@global.head>
<body class="app sidebar-mini rtl">
<@global.navbar></@global.navbar>
<@global.sidebar 7></@global.sidebar>
<@global.content "权限管理">
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
                            <th>序号</th>
                            <th>用户名</th>
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
                    <h5 class="modal-title">后台账号设置</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">
                    <form id="form-data" class="form-horizontal">
                        <input type="text" hidden name="id"/>
                        <div class="form-group row">
                            <label class="control-label col-auto">账号&nbsp;：</label>
                            <div class="col-auto">
                                <input name="userName" class="form-control" type="text" placeholder="请输入账号">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-auto">密码&nbsp;：</label>
                            <div class="col-auto">
                                <input name="password" class="form-control" type="password" placeholder="密码">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-auto">
                                <input type="text" name="roleUserList[0].userId" hidden>
                                <div class="animated-checkbox">
                                    <label>
                                        <input type="checkbox" value="1" name="roleUserList[0].state"><span class="label-text"> </span>
                                    </label>
                                </div>
                            </label>
                            <span class="col-auto">
                                功能分类
                            </span>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-auto">
                                <input type="text" name="roleUserList[1].userId" hidden>
                                <div class="animated-checkbox">
                                    <label>
                                        <input type="checkbox" value="1" name="roleUserList[1].state"><span class="label-text"> </span>
                                    </label>
                                </div>
                            </label>
                            <div class="col-auto">功能列表</div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-auto">
                                <input type="text" name="roleUserList[2].userId" hidden>
                                <div class="animated-checkbox">
                                    <label>
                                        <input type="checkbox" value="1" name="roleUserList[2].state"><span class="label-text"> </span>
                                    </label>
                                </div>
                            </label>
                            <div class="col-auto">品牌管理</div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-auto">
                                <input type="text" name="roleUserList[3].userId" hidden>
                                <div class="animated-checkbox">
                                    <label>
                                        <input type="checkbox" value="1" name="roleUserList[3].state"><span class="label-text"> </span>
                                    </label>
                                </div>
                            </label>
                            <div class="col-auto">商品管理</div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-auto">
                                <input type="text" name="roleUserList[4].userId" hidden>
                                <div class="animated-checkbox">
                                    <label>
                                        <input type="checkbox" value="1" name="roleUserList[4].state"><span class="label-text"> </span>
                                    </label>
                                </div>
                            </label>
                            <div class="col-auto">广告管理</div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-auto">
                                <input type="text" name="roleUserList[5].userId" hidden>
                                <div class="animated-checkbox">
                                    <label>
                                        <input type="checkbox" value="1" name="roleUserList[5].state"><span class="label-text"> </span>
                                    </label>
                                </div>
                            </label>
                            <div class="col-auto">订单管理</div>
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
                url: '${ctx}/role/getUserList',
                type: "get"
            },
            'columns': [
                {'data': 'id'},
                {'data': 'userName'},
                {'data':
                        function (row) {
                            return "<a class=\'btn btn-primary btn-sm\' href=\'javascript:editItem(" + row.id + ")\'><i class=\'fa fa-pencil-square-o\'></i>编辑权限</a>"+
                            '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                            "<a class=\'btn btn-info btn-sm\' href=\'javascript:removeItem(" + row.id + ")\'><i class='fa fa-eye\'></i>删除</a>";
                        }
                }
            ]
        });
    });

    $('#addNew').click(function () {
        $('#myModal').modal('show');
    });

    function editItem(id){
        $.ajax({
            url: '${ctx}/role/getUserById',
            type: 'get',
            data: {
                id: id
            },
            success: function (result) {
                var roleUserList = result.data.roleUserList;
                console.log(roleUserList);
                if (roleUserList) {
                    $('#form-data').fill(result.data);
                    $('input[name=userName]').prop('disabled', true);
                    $.each(roleUserList, function (i, roleUser) {
                        addRole(i,roleUser);
                    });
                }
                $('#form-data').fill(result);
                $('#myModal').modal('show');
            }
        });
    }

    // 保存栏目
    function saveItem() {
        var $form = $('#form-data');
        var url = '${ctx}/role/saveUser';
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
                    swal("操作失败！",'error');
                }
            }
        });
    }

    function removeItem(id) {
        $.ajax({
            url: '${ctx}/role/deleteUserById',
            type: 'post',
            data: {
                id: id
            },
            success: function (result) {
                if (result.code === 0) {
                    swal("操作成功！");
                    closePopup();
                    reloadDataTable();
                }else {
                    swal("操作失败！",'error');
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
        $('input[name=userName]').prop('disabled', false);
        $("input[name^='roleUserList']").each(function (i,item) {
            $(item).attr("checked", false);
        })
    });

    // 关闭弹出框
    function closePopup() {
        $('#form-data').clear();
        $('#myModal').modal('hide');
    }

    function addRole(i,roleUser) {
        if(roleUser.state){
            $("input[name='roleUserList["+i+"].state']").attr("checked", true);
        }
    }
</script>
</@global.script>
</body>
</html>