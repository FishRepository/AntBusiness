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
        <div class="modal-dialog" role="document" >
            <div class="modal-content" style="width:900px;">
                <div class="modal-header">
                    <h5 class="modal-title">新增条目</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group row">
                            <label class="control-label col-md-3">标题&nbsp;：</label>
                            <div class="col-md-8">
                                <input id="title" name="title" class="form-control" type="text" placeholder="Enter full name">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">等级&nbsp;：</label>
                            <div class="col-md-8">
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio" value="1" name="type">初级
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
                                <textarea id="functionDes" name="functionDes" class="form-control" rows="4" placeholder="Enter your address"></textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">操作介绍&nbsp;：</label>
                            <div class="col-md-8">
                                <button id="addOne" class="btn btn-sm btn-success" type="button"><i class="fa fa-plus-circle"></i>增加</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button id="minusOne" class="btn btn-danger btn-sm" type="button"><i class="fa fa-minus-circle"></i>删除</button>
                            </div>
                        </div>
                        <div class="form-group row" id="div-opIntroduceTxt1">
                            <label class="control-label col-md-3">操作介绍一&nbsp;：</label>
                            <div class="col-md-8">
                                <textarea id="opIntroduceTxt1" name="opIntroduceTxt1" class="form-control" rows="4" placeholder="Enter your address"></textarea>
                            </div>
                        </div>
                        <div class="form-group row" id="div-opIntroduceImg1">
                            <label class="control-label col-md-3">图片一&nbsp;：</label>
                            <div class="col-md-8">
                                <input id="input-opIntroduceImg1" name="imgFile" class="form-control" type="file">
                            </div>
                        </div>
                        <div class="form-group row" id="preview-Img1" style="display: none">
                            <label class="control-label col-md-3">图片一预览&nbsp;：</label>
                            <div class="col-md-8">
                                <img style="height: 200px; width: 100%; display: block;" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22318%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20318%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_158bd1d28ef%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A16pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_158bd1d28ef%22%3E%3Crect%20width%3D%22318%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22129.359375%22%20y%3D%2297.35%22%3EImage%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="Card image">
                            </div>
                            <input />
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button">保存</button>
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">关闭</button>
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
<script type="text/javascript" src="${ctx}/js/plugins/bootstrap-notify.min.js"></script>
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
            // // 分页dom结构（搜索框，分页组件）
            // 'dom': 't<"bottom"<"pull-left"i><"pull-left info"l><"pull-right"p>>',
            'ajax': {
                url:'/admin/listGuide',
                type:"POST"
                // ,
                // data:getFormJson($('#queryForm'))
            },
            'columns': [
                { 'data': 'id'},
                { 'data': 'title' },
                { 'data': function(row){
                        var status = (row.type === 1) ? '初级' : '进阶';
                        return status;
                    }
                },
                { 'data': 'createTime' },
                { 'data': function(row){
                        var status = (row.status === 1) ? '有效' : '无效';
                        return status;
                    }
                },
                { 'data':
                    function(row){
                        var a = "<a  class=\'btn btn-sm btn-warning\' href=\'javascript:disable("+row.id+")\'><i class=\'fa fa-ban\'></i>停用</a>" +
                                '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                "<a class=\'btn btn-info btn-sm\' href=\'javascript:viewPreview("+row.id+")\'><i class='fa fa-eye\'></i>查看</a>";
                        var b = "<a class=\'btn btn-sm btn-success\' href=\'javascript:enable("+row.id+")\'><i class=\'fa fa-check\'></i>启用</a>" +
                                '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                "<a class=\'btn btn-primary btn-sm\' href=\'javascript:updatePreview("+row.id+")\'><i class=\'fa fa-pencil-square-o\'></i>修改</a>"+
                                '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                "<a class='btn btn-info btn-sm' href=\'javascript:viewPreview("+row.id+")\'><i class='fa fa-eye'></i>查看</a>"+
                                '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                "<a class=\'btn btn-danger btn-sm\' href=\'javascript:del("+row.id+")\'><i class=\'fa fa-trash-o\'></i>删除</a>";
                        var c = (row.status === 1) ? a:b;
                        return c;
                    }
                }
            ]
        });
    });

    $('#addNew').click(function(){
        $('.modal').modal('show');
    });

    $('#addOne').click(function(){
        //获取当前操作说明数组长度
        var index = $("div[id^='div-opIntroduceTxt']").length;
        if(index<5){
            var indexCh = index===1?'二':index===2?'三':index===3?'四':index===4?'五':'';
            var htmlVar = buildOpIntroduceDiv(index+1,indexCh);
            $('.form-horizontal').append(htmlVar);
        }else{
            alert("最多只能添加五条操作介绍!");
        }
    });

    $('#minusOne').click(function(){
        //获取当前操作说明数组长度
        var index = $("div[id^='div-opIntroduceTxt']").length;
        if(index>1){
            $('#div-opIntroduceTxt'+index).remove();
            $('#div-opIntroduceImg'+index).remove();
        }else{
            alert("至少保留一条操作介绍!");
        }
    });
    
    function buildOpIntroduceDiv(index, indexCh) {
        var htmlVar = '';
        htmlVar+='<div class="form-group row" id="div-opIntroduceTxt'+index+'">';
        htmlVar+='    <label class="control-label col-md-3">操作介绍'+indexCh+'&nbsp;：</label>';
        htmlVar+='   <div class="col-md-8">';
        htmlVar+='       <textarea id="opIntroduceTxt'+index+'" name="opIntroduceTxt'+index+'" class="form-control" rows="4" placeholder="Enter your address"></textarea>';
        htmlVar+='    </div>';
        htmlVar+='</div>';
        htmlVar+='<div class="form-group row" id="div-opIntroduceImg'+index+'">';
        htmlVar+='    <label class="control-label col-md-3">图片'+indexCh+'&nbsp;：</label>';
        htmlVar+='   <div class="col-md-8">';
        htmlVar+='       <input id="opIntroduceImg'+index+'" name="opIntroduceImg'+index+'" class="form-control" type="file">';
        htmlVar+='   </div>';
        htmlVar+='</div>';
        htmlVar+='<div class="form-group row" id="preview-Img'+index+'" style="display: none">';
        htmlVar+='    <label class="control-label col-md-3">图片'+indexCh+'预览&nbsp;：</label>';
        htmlVar+='    <div class="col-md-8">';
        htmlVar+='        <img style="height: 200px; width: 100%; display: block;" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22318%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20318%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_158bd1d28ef%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A16pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_158bd1d28ef%22%3E%3Crect%20width%3D%22318%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22129.359375%22%20y%3D%2297.35%22%3EImage%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="Card image">';
        htmlVar+='    </div>';
        htmlVar+='</div>';
        return htmlVar;
    }
    
    function notify(title, msg){
        $.notify({
            title: title+" : ",
            message: msg,
            icon: 'fa fa-close'
        },{
            type: "danger"
        });
    }
</script>

</body>
</html>