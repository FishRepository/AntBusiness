<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<@global.head "Tomato Admin - 广告管理">
    <style type="text/css">
        .margin-left {
            margin-left: 15px;
        }
    </style>
</@global.head>
<body class="app sidebar-mini rtl">
<@global.navbar></@global.navbar>
<@global.sidebar 4></@global.sidebar>
<@global.content "广告管理">
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
                            <th>广告图</th>
                            <th>链接地址</th>
                            <th>描述</th>
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
    $(function(){
        <#--initFileInput("uploadImg", "${ctx}/adImg/uploadImg");-->
        initFileInput("uploadImg", "${ctx}/images/uploadimages");
    });

    //初始化fileinput控件（第一次初始化）
    function initFileInput(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh',                                         //设置语言
            uploadUrl: uploadUrl,                                   //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png', 'pdf'],    //接收的文件后缀
            showUpload: true,                                       //是否显示上传按钮
            showRemove : true,                                      //显示移除按钮
            showCaption: false,                                     //是否显示标题
            browseClass: "btn btn-primary",                         //按钮样式
            uploadAsync: true,                                      //默认异步上传
            dropZoneEnabled: false,                               //是否显示拖拽区域
            //minImageWidth: 50,                                    //图片的最小宽度
            //minImageHeight: 50,                                   //图片的最小高度
            //maxImageWidth: 1000,                                  //图片的最大宽度
            //maxImageHeight: 1000,                                 //图片的最大高度
            //maxFileSize: 0,                                       //单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 1,                                       //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        });

        //导入文件上传完成之后的事件
        control.on('fileuploaded', function(event, data){
            console.log(data);
            var result = data.response;
            if(result.code === 0){
                $('#imgUrl').val(result.data.urlPath);
            }else {
                swal("操作失败！");
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
                url: '${ctx}/adImg/list',
                type: "get"
            },
            'columns': [
                {'data': 'id'},
                {'data':
                        function (row) {
                            return "<img src=\'"+row.imgUrl+"\' />";
                        }
                },
                {'data': 'linkUrl'},
                {'data': 'description'},
                {
                    'data':
                        function (row) {
                            return "<a class=\'btn btn-primary btn-sm\' href=\'javascript:editItem(" + row.id + ")\'><i class=\'fa fa-pencil-square-o\'></i>编辑</a>" +
                                '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                "<a class=\'btn btn-info btn-sm\' href=\'javascript:removeItem(" + row.id + ")\'><i class='fa fa-eye\'></i>删除</a>";
                        }
                }
            ]
        });
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