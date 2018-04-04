<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <!-- Twitter meta-->
    <title>Tomato Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/font-awesome.min.css">
</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header"><a class="app-header__logo" href="index.ftl">Tomato</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">
        <li class="app-search">
            <input class="app-search__input" type="search" placeholder="Search">
            <button class="app-search__button"><i class="fa fa-search"></i></button>
        </li>
        <!--Notification Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Show notifications"><i
                class="fa fa-bell-o fa-lg"></i></a>
            <ul class="app-notification dropdown-menu dropdown-menu-right">
                <li class="app-notification__title">You have 4 new notifications.</li>
                <div class="app-notification__content">
                    <li><a class="app-notification__item" href="javascript:;"><span class="app-notification__icon"><span
                            class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-primary"></i><i
                            class="fa fa-envelope fa-stack-1x fa-inverse"></i></span></span>
                        <div>
                            <p class="app-notification__message">Lisa sent you a mail</p>
                            <p class="app-notification__meta">2 min ago</p>
                        </div>
                    </a></li>
                    <li><a class="app-notification__item" href="javascript:;"><span class="app-notification__icon"><span
                            class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-danger"></i><i
                            class="fa fa-hdd-o fa-stack-1x fa-inverse"></i></span></span>
                        <div>
                            <p class="app-notification__message">Mail server not working</p>
                            <p class="app-notification__meta">5 min ago</p>
                        </div>
                    </a></li>
                    <li><a class="app-notification__item" href="javascript:;"><span class="app-notification__icon"><span
                            class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-success"></i><i
                            class="fa fa-money fa-stack-1x fa-inverse"></i></span></span>
                        <div>
                            <p class="app-notification__message">Transaction complete</p>
                            <p class="app-notification__meta">2 days ago</p>
                        </div>
                    </a></li>
                    <div class="app-notification__content">
                        <li><a class="app-notification__item" href="javascript:;"><span
                                class="app-notification__icon"><span class="fa-stack fa-lg"><i
                                class="fa fa-circle fa-stack-2x text-primary"></i><i
                                class="fa fa-envelope fa-stack-1x fa-inverse"></i></span></span>
                            <div>
                                <p class="app-notification__message">Lisa sent you a mail</p>
                                <p class="app-notification__meta">2 min ago</p>
                            </div>
                        </a></li>
                        <li><a class="app-notification__item" href="javascript:;"><span
                                class="app-notification__icon"><span class="fa-stack fa-lg"><i
                                class="fa fa-circle fa-stack-2x text-danger"></i><i
                                class="fa fa-hdd-o fa-stack-1x fa-inverse"></i></span></span>
                            <div>
                                <p class="app-notification__message">Mail server not working</p>
                                <p class="app-notification__meta">5 min ago</p>
                            </div>
                        </a></li>
                        <li><a class="app-notification__item" href="javascript:;"><span
                                class="app-notification__icon"><span class="fa-stack fa-lg"><i
                                class="fa fa-circle fa-stack-2x text-success"></i><i
                                class="fa fa-money fa-stack-1x fa-inverse"></i></span></span>
                            <div>
                                <p class="app-notification__message">Transaction complete</p>
                                <p class="app-notification__meta">2 days ago</p>
                            </div>
                        </a></li>
                    </div>
                </div>
                <li class="app-notification__footer"><a href="#">See all notifications.</a></li>
            </ul>
        </li>
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i
                class="fa fa-user fa-lg"></i></a>
            <ul class="dropdown-menu settings-menu dropdown-menu-right">
                <li><a class="dropdown-item" href="page-user.html"><i class="fa fa-cog fa-lg"></i> Settings</a></li>
                <li><a class="dropdown-item" href="page-user.html"><i class="fa fa-user fa-lg"></i> Profile</a></li>
                <li><a class="dropdown-item" href="page-login.ftl"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
            </ul>
        </li>
    </ul>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user"><img class="app-sidebar__user-avatar"
                                        src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg"
                                        alt="User Image">
        <div>
            <p class="app-sidebar__user-name">John Doe</p>
            <p class="app-sidebar__user-designation">Frontend Developer</p>
        </div>
    </div>
    <ul class="app-menu">
        <li><a class="app-menu__item active" href="${ctx}/admin/index"><i class="app-menu__icon fa fa-list"></i><span
                class="app-menu__label">功能介绍</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">UI Elements</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="bootstrap-components.html"><i class="icon fa fa-circle-o"></i>
                    Bootstrap Elements</a></li>
                <li><a class="treeview-item" href="https://fontawesome.com/v4.7.0/icons/" target="_blank"
                       rel="noopener"><i class="icon fa fa-circle-o"></i> Font Icons</a></li>
                <li><a class="treeview-item" href="ui-cards.html"><i class="icon fa fa-circle-o"></i> Cards</a></li>
                <li><a class="treeview-item" href="widgets.html"><i class="icon fa fa-circle-o"></i> Widgets</a></li>
            </ul>
        </li>
        <li><a class="app-menu__item" href="charts.html"><i class="app-menu__icon fa fa-pie-chart"></i><span
                class="app-menu__label">Charts</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">Forms</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="form-components.html"><i class="icon fa fa-circle-o"></i> Form
                    Components</a></li>
                <li><a class="treeview-item" href="form-custom.html"><i class="icon fa fa-circle-o"></i> Custom
                    Components</a></li>
                <li><a class="treeview-item" href="form-samples.html"><i class="icon fa fa-circle-o"></i> Form
                    Samples</a></li>
                <li><a class="treeview-item" href="form-notifications.html"><i class="icon fa fa-circle-o"></i> Form
                    Notifications</a></li>
            </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-th-list"></i><span class="app-menu__label">Tables</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="table-basic.html"><i class="icon fa fa-circle-o"></i> Basic
                    Tables</a></li>
                <li><a class="treeview-item" href="table-data-table.html"><i class="icon fa fa-circle-o"></i> Data
                    Tables</a></li>
            </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-file-text"></i><span class="app-menu__label">Pages</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="blank-page.html"><i class="icon fa fa-circle-o"></i> Blank Page</a>
                </li>
                <li><a class="treeview-item" href="page-login.ftl"><i class="icon fa fa-circle-o"></i> Login Page</a>
                </li>
                <li><a class="treeview-item" href="page-lockscreen.html"><i class="icon fa fa-circle-o"></i> Lockscreen
                    Page</a></li>
                <li><a class="treeview-item" href="page-user.html"><i class="icon fa fa-circle-o"></i> User Page</a>
                </li>
                <li><a class="treeview-item" href="page-invoice.html"><i class="icon fa fa-circle-o"></i> Invoice
                    Page</a></li>
                <li><a class="treeview-item" href="page-calendar.html"><i class="icon fa fa-circle-o"></i> Calendar Page</a>
                </li>
                <li><a class="treeview-item" href="page-mailbox.html"><i class="icon fa fa-circle-o"></i> Mailbox</a>
                </li>
                <li><a class="treeview-item" href="page-error.html"><i class="icon fa fa-circle-o"></i> Error Page</a>
                </li>
            </ul>
        </li>
    </ul>
</aside>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-list"></i> 功能介绍</h1>
        <#--<p>A free and modular admin template</p>-->
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
        </ul>
    </div>

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
                                <label class="control-label col-md-3">等级&nbsp;：</label>
                                <div class="col-md-8">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="radio" value="1" name="type" checked>初级
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="radio" value="2" name="type">高级
                                        </label>
                                    </div>
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

</main>


<!-- Essential javascripts for application to work-->
<script src="${ctx}/js/jquery-3.2.1.min.js"></script>
<script src="${ctx}/js/popper.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="${ctx}/js/plugins/pace.min.js"></script>
<!-- Page specific javascripts-->
<script type="text/javascript" src="${ctx}/js/plugins/chart.js"></script>

<!-- Page specific javascripts-->
<!-- Data table plugin-->
<script type="text/javascript" src="${ctx}/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/sweetalert.min.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/wangEditor-3.1.0/release/wangEditor.js"></script>
<script type="text/javascript" src="${ctx}/js/string.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/peppa.form.js"></script>
<script type="text/javascript" src="${ctx}/js/date-format.js"></script>
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
                        return (row.type === 1) ? '初级' : '进阶';
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
</body>
</html>