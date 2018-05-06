<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>尚筹网-登录页面</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/login.css">
<style type="text/css">
h4 {
	color: red;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" href="index.html" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">
		<form class="form-signin" action="${APP_PATH }/dologin.do"
			method="post">
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-log-in"></i> 用户登录
			</h2>
			<div class="form-group has-success has-feedback">

				<input type="text" class="form-control" id="floginacct"
					name="loginacct" value="${param.loginacct }" placeholder="请输入登录账号"
					autofocus>
				<h4>${errorMessage}</h4>
				<!-- <input type="text" id="error_input"/> -->
				<span class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">

				<input type="password" class="form-control" id="fuserpswd"
					name="userpswd" placeholder="请输入登录密码" style="margin-top: 10px;">
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<select id="fusertype" name="usertype" class="form-control">
					<option value="member">会员</option>
					<option value="user" >管理</option>
				</select>
			</div>
			<div class="checkbox">
				<label> <input id="rememberMe" type="checkbox"
					value="remember-me"> 记住我
				</label> <br> <label> 忘记密码 </label> <label style="float: right">
					<a href="reg.html">我要注册</a>
				</label>
			</div>
			<a id="login_a" class="btn btn-lg btn-success btn-block" onclick="dologin()">
				登录</a>
		</form>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script>
	
		/* 13代表回车键 -回车即可登录 */
		$("body").bind("keyup",function(event) {  
		    if(event.keyCode==13){  
		    	dologin();
		    }     
		});   
		
		function dologin() {
    	
    		var floginacct = $("#floginacct");

    		if($.trim(floginacct.val())==""){ //去掉前后两端空格
    		
    			layer.msg("登录用户名称不允许为空!", {time:2000, icon:5, shift:6}, function(){
    				floginacct.focus();
    			//return ; //停止当前回调函数,并不是登录事件
    			});    		
    		
    			return ;
    		}
    	
    	
    		var fuserpswd = $("#fuserpswd");
    		if($.trim(fuserpswd.val())==""){
    			layer.msg("登录用户密码不允许为空!", {time:2000, icon:5, shift:6}, function(){
    				fuserpswd.focus();
    			});  
    			return ;
    		}
    	
    	
    		var urlStr = "";
    	
    		var fusertype = $("#fusertype").val();
    	
    		var flag = $("#rememberMe")[0].checked; //是否选中【记住我】
    	
    		if(fusertype == "member"){
    			urlStr = "${APP_PATH }/doMemberLogin.do";
    		}else if(fusertype == "user"){
    			urlStr = "${APP_PATH }/doUserLogin.do";
    		}else{
    			return ;
    		}
    	
    	
    		var loadingIndex = -1;
    		$.ajax({
    			type : "POST",
    			url : urlStr,
    			data : {
    				"loginacct":floginacct.val(),
    				"userpswd":fuserpswd.val(),
    				"flag":flag?"1":"0"
    			},
    			beforeSend : function(){
    				//一般是完成提交请求前的准备工作:例如表单数据校验.
    				//loadingIndex = layer.msg('处理中', {icon: 16});
    				loadingIndex = layer.load(2, {time: 10*1000});
    				return true ; //继续发起ajax请求.
    			},
    			success : function(result){ //将服务器端返回的JSON格式字符串转换为JSON,然后通过JS进行解析.
    				layer.close(loadingIndex);
    				//表示服务器端成功处理请求,并返回结果的处理
    			
   					if(result.success){
   						if(fusertype=="member"){
   	    					window.location.href="${APP_PATH}/member.htm";
   	    				}else if(fusertype=="user"){
   							window.location.href="${APP_PATH}/main.htm";
   	    				}
   					}else{
   						layer.msg(result.errorMessage, {time:2000, icon:5, shift:6});  
   					}    				
    			   		
    			},
    			error : function(){
    				//表示服务器端处理请求失败,执行相关操作    			
    				layer.msg("登录失败", {time:2000, icon:5, shift:6});
    			}
    		});

	    }
    </script>
  </body>
</html>