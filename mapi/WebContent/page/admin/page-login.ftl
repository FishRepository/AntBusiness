<#assign ctx=request.getContextPath()/>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/font-awesome.min.css">
    <title>Login -  Admin</title>
  </head>
  <body>
    <section class="material-half-bg">
      <div class="cover"></div>
    </section>
    <section class="login-content">
      <div class="logo">
        <h1>Tomato</h1>
      </div>
      <div class="login-box">
        <form class="login-form" action="#">
          <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>登录</h3>


          <div class="form-group username-div">
            <label class="control-label">用户名</label>
            <input id="username" class="form-control" type="text" placeholder="username" autofocus>
            <div class="form-control-feedback" style="display: none;color: #e04b59">账号或密码错误 &nbsp;&nbsp;!</div>
          </div>

          <div class="form-group password-div">
            <label class="control-label">密码</label>
            <input id="password" class="form-control" type="password" placeholder="Password">
          </div>
          <#--<div class="form-group">-->
            <#--<div class="utility">-->
              <#--<div class="animated-checkbox">-->
                <#--<label>-->
                  <#--<input type="checkbox"><span class="label-text">Stay Signed in</span>-->
                <#--</label>-->
              <#--</div>-->
              <#--<p class="semibold-text mb-2"><a href="#" data-toggle="flip">Forgot Password ?</a></p>-->
            <#--</div>-->
          <#--</div>-->
          <div class="form-group btn-container">
            <button class="btn btn-primary btn-block"><i class="fa fa-sign-in fa-lg fa-fw"></i>登录</button>
          </div>
        </form>
        <#--<form class="forget-form" action="index.ftl">-->
          <#--<h3 class="login-head"><i class="fa fa-lg fa-fw fa-lock"></i>Forgot Password ?</h3>-->
          <#--<div class="form-group">-->
            <#--<label class="control-label">EMAIL</label>-->
            <#--<input class="form-control" type="text" placeholder="Email">-->
          <#--</div>-->
          <#--<div class="form-group btn-container">-->
            <#--<button class="btn btn-primary btn-block"><i class="fa fa-unlock fa-lg fa-fw"></i>RESET</button>-->
          <#--</div>-->
          <#--<div class="form-group mt-3">-->
            <#--<p class="semibold-text mb-0"><a href="#" data-toggle="flip"><i class="fa fa-angle-left fa-fw"></i> Back to Login</a></p>-->
          <#--</div>-->
        <#--</form>-->
      </div>
    </section>
    <!-- Essential javascripts for application to work-->
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/popper.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="${ctx}/js/plugins/pace.min.js"></script>
    <script type="text/javascript">
      // Login Page Flipbox control
      $('.login-content [data-toggle="flip"]').click(function() {
      	$('.login-box').toggleClass('flipped');
      	return false;
      });
      $('.btn-primary').click(function () {
        var username = $('#username').val();
        var password = $('#password').val();
        $.ajax({
            url: "${ctx}/login/validate",
            data: {
                username: username,
                password:password
            },
            success: function (data) {
                if (data && data.success) {
                    window.location.href = "${ctx}/admin/index";
                }else{
                    $('.username-div').addClass('has-danger');
                    $('#username').addClass('is-invalid');
                    $('#password').addClass('is-invalid');
                    $('.password-div').addClass('has-danger');
                    $('.form-control-feedback').show();
                }
            }
        })
      });


    </script>
  </body>
</html>