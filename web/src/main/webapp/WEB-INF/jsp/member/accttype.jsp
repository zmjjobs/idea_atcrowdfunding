<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>尚筹网-会员实名认证申请-账户选择</title>
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
.seltype {
    position: absolute;
    margin-top: 70px;
    margin-left: 10px;
    color: red;
}
	</style>
  </head>
  <body>
 	<%@include file="/WEB-INF/jsp/common/memberHeader.jsp" %>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 账户类型选择</h1>
      </div>
	  <div style="padding-top:10px;">
		<div class="row">
      <div class="col-xs-6 col-md-3">
      
      <h2>商业公司</h2>
        <a href="#" class="thumbnail" accttype="0">
          
          <img alt="100%x180" src="img/services-box1.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>个体工商户</h2>
        <a href="#" class="thumbnail" accttype="1">
          <img alt="100%x180" src="img/services-box2.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>个人经营</h2>
        <a href="#" class="thumbnail" accttype="2">
          <img alt="100%x180" src="img/services-box3.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>政府及非营利组织</h2>
        <a href="#" class="thumbnail" accttype="3">
          <img alt="100%x180" src="img/services-box4.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
    </div>
	<button id="accttypeBtn" type="button" class="btn btn-danger btn-lg btn-block">认证申请</button>
    </div> <!-- /container -->
      <!-- /END THE FEATURETTES -->
      <%@include file="/WEB-INF/jsp/common/memberBottom.jsp" %>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
     <script>
     	var accttpe = 0;
     	$(".thumbnail").click(function(){
     		$('.seltype').remove();
     		$(this).prepend('<div class="glyphicon glyphicon-ok seltype"></div>');
     		accttype = $(this).attr("accttype");
     	});
     	
	    $.each($(".thumbnail img"),function(i,n){
	    	$(this).attr("src","${APP_PATH}/"+$(this).attr("src"));
	    });
	    
	    /* 提交认证申请后，发起的异步请求 */
	    $("#accttypeBtn").click(function(){
	    	if($('.seltype').length == 1){
	    		$.ajax({
	    			type : "post",
	    			url : "${APP_PATH}/member/updateAccttype.do",
	    			data : {
	    				"accttype" : accttype
	    			},
	    			success : function(result){
	    				if(result.success){
							window.location.href="${APP_PATH}/member/basicinfo.htm";   					
	    				}else{
	    					layer.msg("更新账户类型失败！",{time:1000,icon:5,shift:6});
	    				}
	    			},
	    			error : function(){
	    				layer.msg("更新账户类型失败！",{time:1000,icon:5,shift:6});
	    			}
	    		});
	    	}else{
		    	layer.msg("至少选择一个账户类型！",{time:2000,icon:5,shift:6});
	    	}
	    });
	</script>
  </body>
</html>