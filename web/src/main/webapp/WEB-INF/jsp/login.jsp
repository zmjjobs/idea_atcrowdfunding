<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <title>尚筹网-登录</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form class="form-signin" actioon="${APP_PATH}/doLogin.do" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="formLoginacct" name="loginacct" placeholder="请输入登录账号" autofocus value="admin">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="formUserpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;" value="123">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select id="formUserType" name="userType" class="form-control" >
                <option value="member">会员</option>
                <option value="user" selected>管理</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="doLogin()" > 登录</a>
    </form>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script>
    function doLogin() {
        var loginacct = $("#formLoginacct").val();
        var userpswd = $("#formUserpswd").val();
        var userType = $("#formUserType").val();
        if($.trim(loginacct) == ""){
            layer.msg("登录用户名不能为空！"),{time:1000,icon:5,shift:6},function () {
                $("#formLoginacct").focus();
            }
            return;
        }
        if($.trim(userpswd) == ""){
            layer.msg("登录密码不能为空！"),{time:1000,icon:5,shift:6},function () {
                $("#formUserpswd").focus();
            }
            return;
        }
        $.ajax({
            type : "POST",
            url : "${APP_PATH}/doLogin.do",
            data : {
                loginacct : loginacct,
                userpswd : userpswd
            },
            beforeSend : function(){
                return true;
            },
            success : function(result){
                if(result.success){
                    if(userType == 'member'){
                        window.location.href = "${APP_PATH}/index.jsp";
                    }else if(userType == 'user'){
                        window.location.href = "${APP_PATH}/toMain.htm";
                    }else{
                        window.location.href = "${APP_PATH}/index.jsp";
                    }
                }else{
                    alert(result.message);
                }

            },
            error : function(){
                alert("登录失败！");
            }
        });
    }
</script>
</body>
</html>
