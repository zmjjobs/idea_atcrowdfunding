<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>尚筹网-会员实名认证申请-申请确认</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
	<style>
#footer {
    padding: 15px 0;
    background: #fff;
    border-top: 1px solid #ddd;
    text-align: center;
}
	</style>
  </head>
  <body>
 	<%@include file="/WEB-INF/jsp/common/memberHeader.jsp"%>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 申请</h1>
      </div>

		<ul class="nav nav-tabs" role="tablist">
		  <li role="presentation" ><a href="#"><span class="badge">1</span> 基本信息</a></li>
		  <li role="presentation" ><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
		  <li role="presentation" ><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
		  <li role="presentation" class="active"><a href="#"><span class="badge">4</span> 申请确认</a></li>
		</ul>
        
		<form role="form" style="margin-top:20px;">
		  <div class="form-group">
			<label for="fauthcode">验证码</label>
			<input type="text"  id="fauthcode" class="form-control" placeholder="请输入你邮箱中接收到的验证码">
		  </div>
          <button type="button" id="resendAuthcodeBtn" class="btn btn-primary">重新发送验证码</button>
		  <button type="button"  id="finishBtn"  class="btn btn-success">申请完成</button>
		</form>
		<hr>
    </div> 
    <%@include file="/WEB-INF/jsp/common/memberBottom.jsp"%>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script>
        $('#myTab a').click(function (e) {
          e.preventDefault();
          $(this).tab('show');
        });
        
        $("#finishBtn").click(function(){
        	var authcode = $("#fauthcode").val();
        	if(authcode == ""){
        		layer.msg("验证码不能为空！",{time:1500,icon:5,shift:6},function(){
        			$("#fauthcode").focus();
        		});
        		return false;
        	}
        	$.ajax({
        		url : "${APP_PATH}/member/finishApply.do",
        		data : {
        			"authcode" : authcode
        		},
        		success : function(result){
        			if(result.success){
        				window.location.href="${APP_PATH}/member.htm";
        			}else{
        				var message = result.errorMessage;
        				if(message == null){
        					layer.msg("验证失败", {time:1000, icon:5, shift:6});
        				}else{
        					layer.msg(message, {time:1000, icon:5, shift:6});
        				}
        			}
        		},
        		error : function(){
        			layer.msg("验证异常！",{time:1000,icon:5,shift:6});
        		}
        	});
        });
        
        $("#resendAuthcodeBtn").click(function(){
        	$.ajax({
        		url : "${APP_PATH}/member/resendAuthcode.do",
        		success : function(result){
        			if(result.success){
        				layer.msg("验证码已发送至您的邮箱！请您注意查收！", {time:2000, icon:6});
        			}else{
        				layer.msg("服务器繁忙，请稍后重试！",{time:1000,icon:5,shift:6});
        			}
        		},
        		error : function(){
        			layer.msg("验证异常！",{time:1000,icon:5,shift:6});
        		}
        	});
        });
	</script>
  </body>
</html>