<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<@global.head "Tomato Admin"></@global.head>
<body class="app sidebar-mini rtl">
<@global.navbar></@global.navbar>
<@global.sidebar 1></@global.sidebar>
<@global.content "功能列表">
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <button id="addNew" class="btn btn-primary" type="button"><i class="fa fa-fw fa-lg fa-check-circle"></i>新增</button>
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
                            <th>标题</th>
                            <th>类型</th>
                            <th>创建时间</th>
                            <th>状态</th>
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
            <div class="modal-content" style="width:900px;">
                <div class="modal-header">
                    <h5 class="modal-title">新增条目</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">
                    <fieldset id="fieldset">
                        <form id="form-data" class="form-horizontal" enctype="multipart/form-data">
                            <input name="id" type="text" hidden>
                            <div class="form-group row">
                                <label class="control-label col-md-3">标题&nbsp;：</label>
                                <div class="col-md-8">
                                    <input name="title" class="form-control" type="text" placeholder="请输入标题">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-md-3">标题&nbsp;：</label>
                                <div class="col-md-8">
                                    <select name="type" class="form-control">
                                        <option value="0">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-md-3">功能说明&nbsp;：</label>
                                <div class="col-md-8">
                                    <textarea name="functions" class="form-control" rows="4" placeholder="请输入功能说明"></textarea>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-md-3">操作介绍&nbsp;：</label>
                                <div class="col-md-8">
                                    <div id="operates"></div>
                                    <input type="text" name="operates" hidden/>
                                </div>
                            </div>
                        </form>
                    </fieldset>
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
    // 初始化富文本框
    var E = window.wangEditor;
    var editor = new E('#operates');
    editor.customConfig.uploadImgShowBase64 = true;
    editor.customConfig.uploadImgServer = '${ctx}/v2/upload';
    editor.customConfig.uploadImgHooks = {
        fail: function (xhr, editor, result) {
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
            sweetAlert(result.msg);
        }
    };
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'image',  // 插入图片
        'video'  // 插入视频
    ];
    editor.customConfig.customAlert = function (info) {
        // info 是需要提示的内容
    };
    editor.customConfig.uploadImgParams = {
        imgdir: 'intro'  // 属性值会自动进行 encode ，此处无需 encode
    };
    editor.customConfig.uploadImgParamsWithUrl = true;
    editor.create();
</script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: '${ctx}/admin/listIntroductionType',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var data = result.data;
                var $select = $('#form-data').find('[name="type"]');
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    $select.append('<option value="' + item.id + '">' + item.name + '</option>')
                }
            }
        });

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
                url: '${ctx}/admin/listIntroduction',
                type: "POST"
            },
            'columns': [
                {'data': 'id'},
                {'data': 'title'},
                {
                    'data': function (row) {
                        if (row.introductionType) {
                            return row.introductionType.name;
                        }
                        return '未知';
                    }
                },
                {
                    'data': function (row) {
                        return (new Date(row.createTime)).pattern('yyyy-MM-dd HH:mm:ss');
                    }
                },
                {
                    'data': function (row) {
                        return (row.status === 1) ? '有效' : '无效';
                    }
                },
                {
                    'data':
                            function (row) {
                                var a = "<a  class=\'btn btn-sm btn-warning\' href=\'javascript:disableItem(" + row.id + ")\'><i class=\'fa fa-ban\'></i>停用</a>" +
                                        '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                        "<a class=\'btn btn-info btn-sm\' href=\'javascript:viewItem(" + row.id + ")\'><i class='fa fa-eye\'></i>查看</a>";
                                var b = "<a class=\'btn btn-sm btn-success\' href=\'javascript:enableItem(" + row.id + ")\'><i class=\'fa fa-check\'></i>启用</a>" +
                                        '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                        "<a class=\'btn btn-primary btn-sm\' href=\'javascript:editItem(" + row.id + ")\'><i class=\'fa fa-pencil-square-o\'></i>修改</a>" +
                                        '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                        "<a class='btn btn-info btn-sm' href=\'javascript:viewItem(" + row.id + ")\'><i class='fa fa-eye'></i>查看</a>" +
                                        '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                        "<a class=\'btn btn-danger btn-sm\' href=\'javascript:removeItem(" + row.id + ")\'><i class=\'fa fa-trash-o\'></i>删除</a>";
                                var c = (row.status === 1) ? a : b;
                                return c;
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
            url: '${ctx}/admin/getIntroductionById',
            type: 'get',
            data: {
                id: id
            },
            success: function (result) {
                $('#form-data').fill(result);
                $('.modal').modal('show');
                editor.txt.html(result.operates);
            }
        });
    }

    // 保存栏目
    function saveItem() {
        var $form = $('#form-data');
        $form.find('input[name="operates"]').val(editor.txt.html());
        var url = '${ctx}/admin/saveIntroduction';
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
        $('.row-introduce:gt(0)').remove();
        var $form = $('#form-data');
        $form.clear();
        $('#fieldset').attr('disabled', false);
        editor.txt.html('');
        editor.$textElem.attr('contenteditable', true);
    });

    // 关闭弹出框
    function closePopup() {
        $('.modal').modal('hide');
    }

    function viewItem(id) {
        $.ajax({
            url: '${ctx}/admin/getIntroductionById',
            type: 'get',
            data: {
                id: id
            },
            success: function (result) {
                editor.$textElem.attr('contenteditable', false);
                $('#fieldset').attr('disabled', true);
                $('#form-data').fill(result);
                editor.txt.html(result.operates);
                $('.modal').modal('show');
            }
        });
    }

    function enableItem(id) {
        swal({
                    title: "确定启用吗？",
                    text: "启用即发布到线上环境！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                },
                function () {
                    $.ajax({
                        url: '${ctx}/admin/enableIntroductionById',
                        type: 'get',
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

    function disableItem(id) {
        swal({
                    title: "确定停用吗？",
                    text: "停用即撤回线上环境的部署！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                },
                function () {
                    $.ajax({
                        url: '${ctx}/admin/disableIntroductionById',
                        type: 'get',
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
                        url: '${ctx}/admin/removeIntroductionById',
                        type: 'get',
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
</script>
</@global.script>
</body>
</html>