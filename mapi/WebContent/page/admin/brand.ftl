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
                            <th>是否推荐</th>
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
                        <input name="brandId" type="text" hidden>
                        <div class="form-group row">
                            <label class="control-label col-md-3">品牌名称&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="brandName" class="form-control" type="text" placeholder="请输入名称">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">品牌简介&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="brandInfo" class="form-control" type="text" placeholder="请输入名称">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">副标题&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="title" class="form-control" type="text" placeholder="请输入名称">
                            </div>
                        </div>
                        <div class="form-group row" style="" id="uploadLogoImgDiv">
                            <input name="logoUrl" type="text" hidden id="logoUrl">
                            <label class="control-label col-md-3">上传logo&nbsp;：</label>
                            <div class="col-md-8">
                                <input id="uploadLogoImg" name="file" type="file" multiple>
                            </div>
                        </div>
                        <div class="form-group row" style="" id="uploadImagesDiv">
                            <input name="images" type="text" hidden id="images">
                            <label class="control-label col-md-3">上传品牌图&nbsp;：</label>
                            <div class="col-md-8">
                                <input id="uploadImages" name="file" type="file" multiple>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">搜索名称&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="brandShortname" class="form-control" type="text" placeholder="搜索词，多个逗号隔开">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">下载码&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="brandDownloadcode" class="form-control" type="text" placeholder="请输入下载码">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">番茄币&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="brandPrice" class="form-control" type="text" placeholder="请输入下载价">
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
    $(function(){
        initFileInput("uploadLogoImg", "${ctx}/adImg/uploadImg", "logo");
        initFileInput("uploadImages", "${ctx}/adImg/uploadImg", "images");
    });

    //初始化fileinput控件（第一次初始化）
    //path = [
    // "http://lorempixel.com/800/460/nature/1",
    // "http://lorempixel.com/800/460/nature/2",
    // ]
    function initFileInput(ctrlName, uploadUrl, type, path,config) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh',                                         //设置语言
            uploadUrl: uploadUrl,                                   //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],    //接收的文件后缀
            showUpload: true,                                       //是否显示上传按钮
            showRemove : true,                                      //显示移除按钮
            showCaption: false,                                     //是否显示标题
            browseClass: "btn btn-primary",                         //按钮样式
            uploadAsync: true,                                      //默认异步上传
            dropZoneEnabled: false,                                 //是否显示拖拽区域
            maxFileCount: 6,                                       //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            overwriteInitial: false,
            initialPreview:path,
            initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
            initialPreviewFileType: 'image', // image is the default and can be overridden in config below
            initialPreviewConfig:config
        }).on('fileuploaded', function(event, data){
            //导入文件上传完成之后的事件
            var result = data.response;
            if(result.code === 0){
                if(type === 'logo'){
                    $('#logoUrl').val(result.data.urlPath);
                }else {
                    var imgaes = $('#images').val();
                    if(imgaes){
                        imgaes += "##"+result.data.urlPath;
                    }else{
                        imgaes = result.data.urlPath;
                    }
                    $('#images').val(imgaes);
                }
            }else {
                $(event.target)
                    .fileinput('clear')
                    .fileinput('unlock');
                $(event.target)
                    .parent()
                    .siblings('.fileinput-remove')
                    .hide();
                swal(result.msg);
            }
        });
    }

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
                {'data': function (row) {
                        return row.isHot>0?'是':"否";
                    }
                },
                {
                    'data':
                        function (row) {
                            var opHtml = "<a class=\'btn btn-primary btn-sm\' href=\'javascript:editItem(" + row.brandId + ")\'><i class=\'fa fa-pencil-square-o\'></i>编辑</a>" +
                                    '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                    "<a class=\'btn btn-info btn-sm\' href=\'javascript:removeItem(" + row.brandId + ")\'><i class='fa fa-eye\'></i>删除</a>";
                            if(row.isHot===0){
                                opHtml+='&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                "<a class=\'btn btn-info btn-sm\' href=\'javascript:setHot(" + row.brandId+","+1 + ")\'><i class='fa fa-eye\'></i>置顶</a>";
                            }else{
                                opHtml+='&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                    "<a class=\'btn btn-info btn-sm\' href=\'javascript:setHot(" + row.brandId+","+2 + ")\'><i class='fa fa-eye\'></i>取消置顶</a>";
                            }
                            return opHtml;
                        }
                }
            ]
        });
    });

    $('#addNew').click(function () {
        addAgent({agentlevel_name: '零售价'});
        $('#myModal').modal('show');
    });

    function setHot(id,type){
        $.ajax({
            url: '${ctx}/admin/setBrandHot',
            type: 'get',
            data: {
                id: id,
                type: type
            },
            success: function (result) {
                if (result.success) {
                    swal("操作成功");
                    reloadDataTable();
                }else{
                    swal("操作失败",'error');
                    reloadDataTable();
                }
            }
        });
    }

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
                var logo = result.logoUrl;
                var brandImages = result.imageList;
                if (agents) {
                    $.each(agents, function (i, agent) {
                        addAgent(agent);
                    });
                }
                $('#form-data').fill(result);
                $('#images').val('');
                if(logo){
                    $("#uploadLogoImg").fileinput('destroy');
                    console.log(result.brandId);
                    initFileInput("uploadLogoImg", "${ctx}/adImg/uploadImg", "logo", logo);
                }
                if(brandImages){
                    $("#uploadImages").fileinput('destroy');
                    var path = [];
                    var config = [];
                    var configItem = {};
                    for(var i=0;i<brandImages.length;i++){
                        path[i] = brandImages[i].brandimages_url;
                        configItem = {};
                        configItem['key']=i+1;
                        configItem['caption']='picture-'+(i+1);
                        configItem['url']="${ctx}/brandMgr/deleteImg.do?brandimages_id="+brandImages[i].brandimages_id;
                        configItem['size']=12345;
                        config.push(configItem);
                    }
                    initFileInput("uploadImages", "${ctx}/adImg/uploadImg", "images", path, config);
                }
                $('#myModal').modal('show');
            }
        });
    }

    // 保存栏目
    function saveItem() {
        var $form = $('#form-data');
        var url = '${ctx}/admin/saveBrand';
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
    $('#myModal').on('hidden.bs.modal', function () {
        var $form = $('#form-data');
        $form.clear();
        //初始化图片上传
        $("#uploadLogoImg").fileinput('destroy');
        $("#uploadImages").fileinput('destroy');
        initFileInput("uploadLogoImg", "${ctx}/adImg/uploadImg", "logo");
        initFileInput("uploadImages", "${ctx}/adImg/uploadImg", "images");
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
        var id = agent ? agent.agentlevel_id : '';
        var agentName = agent ? agent.agentlevel_name : '';
        var agentDefault = agent ? agent.agentlevel_default : '';
        var $form = $('#form-data');
        var $row = $('<div class="form-group row js-agent">' +
                '<input class="js-id" name="agents[' + $form.find('.js-agent').length + '].agentlevel_id" type="text" value="' + (id ? id : '') + '" hidden>' +
                '<input name="agents[' + $form.find('.js-agent').length + '].agentlevel_name" class="form-control col-md-6 margin-left js-name" type="text" placeholder="请输入代理名称" value="' + agentName + '">' +
                '<label><input class="js-default" name="agents[' + $form.find('.js-agent').length + '].agentlevel_default" type="checkbox" value="1" ' + (agentDefault ? 'checked' : '') + '/>用币下载</label>' +
                '<button class="btn btn-primary margin-left" type="button" onclick="removeRow()">删除</button>' +
                '</div>');
        // if (agentName === '零售价') {
        //     $row.find('input').prop('disabled', true);
        //     $row.find('button').prop('disabled', true);
        // }
        $form.append($row);
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
            $(item).find('.js-id').attr('name', 'agents[' + i + '].agentlevel_id');
            $(item).find('.js-name').attr('name', 'agents[' + i + '].agentlevel_name');
            $(item).find('.js-default').attr('name', 'agents[' + i + '].agentlevel_default');
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
<style>
    .modal-dialog {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }

    .modal-content {
        /*overflow-y: scroll; */
        position: absolute;
        top: 0;
        bottom: 0;
        width: 100%;
    }

    .modal-body {
        overflow-y: scroll;
        position: absolute;
        top: 55px;
        bottom: 65px;
        width: 100%;
    }

    .modal-header .close {margin-right: 15px;}

    .modal-footer {
        position: absolute;
        width: 100%;
        bottom: 0;
    }
</style>
</body>
</html>