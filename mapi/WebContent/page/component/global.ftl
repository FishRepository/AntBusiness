<#assign ctx=request.getContextPath()/>

<#macro head title>
<head>
    <title>${title}</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/font-awesome.min.css">
    <#nested>
</head>
</#macro>

<#macro script>
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
    <!-- fileinput-->
    <link href="${ctx}/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/js/fileinput.js" type="text/javascript"></script>
    <script src="${ctx}/js/local/zh.js" type="text/javascript"></script>
    <!-- datepicker-->
    <link href="${ctx}/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <script src="${ctx}/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript" charset="UTF-8"></script>

    <#nested>
</#macro>

<#macro navbar>
<header class="app-header">
    <a class="app-header__logo" href="${ctx}/admin/index">Tomato</a>
    <!-- Sidebar toggle button-->
    <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
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
                    <li>
                        <a class="app-notification__item" href="javascript:void(0);">
                            <span class="app-notification__icon">
                                <span class="fa-stack fa-lg">
                                <i class="fa fa-circle fa-stack-2x text-primary"></i>
                                <i class="fa fa-envelope fa-stack-1x fa-inverse"></i>
                                </span>
                            </span>
                            <div>
                                <p class="app-notification__message">Lisa sent you a mail</p>
                                <p class="app-notification__meta">2 min ago</p>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a class="app-notification__item" href="javascript:void(0);">
                            <span class="app-notification__icon">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x text-danger"></i>
                                    <i class="fa fa-hdd-o fa-stack-1x fa-inverse"></i>
                                </span>
                            </span>
                            <div>
                                <p class="app-notification__message">Mail server not working</p>
                                <p class="app-notification__meta">5 min ago</p>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a class="app-notification__item" href="javascript:void(0);">
                            <span class="app-notification__icon">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x text-success"></i>
                                    <i class="fa fa-money fa-stack-1x fa-inverse"></i>
                                </span>
                            </span>
                            <div>
                                <p class="app-notification__message">Transaction complete</p>
                                <p class="app-notification__meta">2 days ago</p>
                            </div>
                        </a>
                    </li>
                    <div class="app-notification__content">
                        <li>
                            <a class="app-notification__item" href="javascript:void(0);">
                                <span class="app-notification__icon">
                                    <span class="fa-stack fa-lg">
                                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                                        <i class="fa fa-envelope fa-stack-1x fa-inverse"></i>
                                    </span>
                                </span>
                                <div>
                                    <p class="app-notification__message">Lisa sent you a mail</p>
                                    <p class="app-notification__meta">2 min ago</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a class="app-notification__item" href="javascript:void(0);">
                                <span class="app-notification__icon">
                                    <span class="fa-stack fa-lg">
                                        <i class="fa fa-circle fa-stack-2x text-danger"></i>
                                        <i class="fa fa-hdd-o fa-stack-1x fa-inverse"></i>
                                    </span>
                                </span>
                                <div>
                                    <p class="app-notification__message">Mail server not working</p>
                                    <p class="app-notification__meta">5 min ago</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a class="app-notification__item" href="javascript:void(0);">
                                <span class="app-notification__icon">
                                    <span class="fa-stack fa-lg">
                                        <i class="fa fa-circle fa-stack-2x text-success"></i>
                                        <i class="fa fa-money fa-stack-1x fa-inverse"></i>
                                    </span>
                                </span>
                                <div>
                                    <p class="app-notification__message">Transaction complete</p>
                                    <p class="app-notification__meta">2 days ago</p>
                                </div>
                            </a>
                        </li>
                    </div>
                </div>
                <li class="app-notification__footer"><a href="#">See all notifications.</a></li>
            </ul>
        </li>
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i
                class="fa fa-user fa-lg"></i></a>
            <ul class="dropdown-menu settings-menu dropdown-menu-right">
                <li><a class="dropdown-item" href="javascript:void(0);"><i class="fa fa-cog fa-lg"></i> Settings</a></li>
                <li><a class="dropdown-item" href="javascript:void(0);"><i class="fa fa-user fa-lg"></i> Profile</a></li>
                <li><a class="dropdown-item" href="javascript:void(0);"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
            </ul>
        </li>
    </ul>
</header>
</#macro>

<#macro sidebar index>
<aside class="app-sidebar">
    <div class="app-sidebar__user">
        <img class="app-sidebar__user-avatar" src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg" alt="User Image">
        <div>
            <p class="app-sidebar__user-name">John Doe</p>
            <p class="app-sidebar__user-designation">Frontend Developer</p>
        </div>
    </div>
    <ul class="app-menu">
        <li>
            <a class="app-menu__item<#if index==0> active</#if>" href="${ctx}/admin/introductionType">
                <i class="app-menu__icon fa fa-list"></i><span class="app-menu__label">功能分类</span>
            </a>
        </li>
        <li>
            <a class="app-menu__item<#if index==1> active</#if>" href="${ctx}/admin/introduction">
                <i class="app-menu__icon fa fa-list"></i><span class="app-menu__label">功能列表</span>
            </a>
        </li>
        <li>
            <a class="app-menu__item<#if index==2> active</#if>" href="${ctx}/admin/brand">
                <i class="app-menu__icon fa fa-list"></i><span class="app-menu__label">品牌管理</span>
            </a>
        </li>
        <li>
            <a class="app-menu__item<#if index==3> active</#if>" href="${ctx}/admin/goods">
                <i class="app-menu__icon fa fa-list"></i><span class="app-menu__label">商品管理</span>
            </a>
        </li>
        <li>
            <a class="app-menu__item<#if index==4> active</#if>" href="${ctx}/adImg">
                <i class="app-menu__icon fa fa-list"></i><span class="app-menu__label">广告管理</span>
            </a>
        </li>
        <li>
            <a class="app-menu__item<#if index==5> active</#if>" href="${ctx}/payorder">
                <i class="app-menu__icon fa fa-list"></i><span class="app-menu__label">订单管理</span>
            </a>
        </li>
    </ul>
</aside>
</#macro>

<#macro content_title title>
<div class="app-title">
    <div>
        <h1><i class="fa fa-list"></i> ${title}</h1>
    <#--<p>A free and modular admin template</p>-->
    </div>
    <ul class="app-breadcrumb breadcrumb">
        <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
        <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
    </ul>
</div>
</#macro>

<#macro content title>
<main class="app-content ${title}">
    <@global.content_title title></@global.content_title>
    <#nested/>
</main>
</#macro>