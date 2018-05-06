<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<@global.head "Tomato Admin - 商品管理"></@global.head>
<body class="app sidebar-mini rtl">
<@global.navbar></@global.navbar>
<@global.sidebar 3></@global.sidebar>
<@global.content "商品管理">
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <label class="control-label">请选择品牌&nbsp;：</label>
                            <select id="queryBrandId" class="form-control">
                                <option value="0">请选择</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <button id="addNew" class="btn btn-primary" type="button">
                                <i class="fa fa-fw fa-lg fa-check-circle"></i>新增
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
                            <th>ID
                            <th>品牌</th>
                            <th>商品名称</th>
                            <th>价格</th>
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
                        <input name="goodsId" type="text" hidden>
                        <div class="form-group row">
                            <label class="control-label col-md-3">请选择品牌&nbsp;：</label>
                            <div class="col-md-8">
                                <select name="brandId" class="form-control">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">名称&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="goodsName" class="form-control" type="text" placeholder="请输入名称">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">价格&nbsp;：</label>
                            <div class="col-md-8">
                                <input name="goodsPrice" class="form-control" type="text" placeholder="请输入价格">
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
    // 品牌查询筛选
    $('#queryBrandId').change(function () {
        reloadDataTable();
    });

    $(function () {
        // 初始化品牌下拉框
        $.ajax({
            url: '${ctx}/admin/listBrand',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var $queryBrandId = $('#queryBrandId');
                var $brandId = $('[name="brandId"]');
                $queryBrandId.find('option:gt(0)').remove();
                $brandId.find('option:gt(0)').remove();
                var brands = result.data;
                $.each(brands, function (i, brand) {
                    $queryBrandId.append('<option value="' + brand.brandId + '">' + brand.brandName + '</option>');
                    $brandId.append('<option value="' + brand.brandId + '">' + brand.brandName + '</option>');
                });
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
                url: '${ctx}/admin/listGoods',
                type: "get",
                data: {
                    brandId: function () {
                        return $('#queryBrandId').val();
                    }
                }
            },
            'columns': [
                {'data': 'goodsId'},
                {'data': 'brandName'},
                {'data': 'goodsName'},
                {'data': 'goodsPrice'},
                {
                    'data':
                            function (row) {
                                return "<a class=\'btn btn-primary btn-sm\' href=\'javascript:editItem(" + row.goodsId + ")\'><i class=\'fa fa-pencil-square-o\'></i>编辑</a>" +
                                        '&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;' +
                                        "<a class=\'btn btn-info btn-sm\' href=\'javascript:removeItem(" + row.goodsId + ")\'><i class='fa fa-eye\'></i>删除</a>";
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
            url: '${ctx}/admin/getGoodsById',
            type: 'get',
            data: {
                id: id
            },
            success: function (result) {
                $('#form-data').fill(result);
                $('.modal').modal('show');
            }
        });
    }

    // 保存栏目
    function saveItem() {
        var $form = $('#form-data');
        var url = '${ctx}/admin/saveGoods';
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
                        url: '${ctx}/admin/removeGoodsById',
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
</script>
</@global.script>
</body>
</html>