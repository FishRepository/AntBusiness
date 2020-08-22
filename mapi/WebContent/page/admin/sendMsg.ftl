<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html lang="en">
<@global.head "Tomato Admin - 消息管理">
    <style type="text/css">
        .margin-left {
            margin-right: 5px;
        }
    </style>
</@global.head>
<body class="app sidebar-mini rtl">
<@global.navbar></@global.navbar>
<@global.sidebar 6></@global.sidebar>
<@global.content "消息管理">
    <div class="row">
        <div class="col-md-6">
            <div class="tile">
                <div class="tile-body">
                    <textarea class="form-control" rows="4" id="msg"></textarea>
                </div>
            </div>
            <div class="tile-footer">
                <button id="send" class="btn btn-primary" type="button"><i
                            class="fa fa-fw fa-lg fa-check-circle"></i>发送
                </button>
            </div>
        </div>
    </div>

</@global.content>


<@global.script>
<script type="text/javascript">
    $('#send').click(function () {
        const msg = $('#msg').val();
        $.ajax({
            url: '${ctx}/sendMsg/send',
            type: 'get',
            data: {
                msg: msg
            },
            success: function (result) {
                if (result.code === 0) {
                    swal("操作成功！");
                    $('#msg').val("");
                }else {
                    swal("操作失败！",'error');
                }
            }
        });
    });

</script>
</@global.script>
</body>
</html>