<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>尚筹网-权限管理-用户维护-分配角色</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<jsp:include page="/WEB-INF/jsp/common/userinfo.jsp"/>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">分配角色</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline">
				  <div class="form-group">
					<label for="leftRoleList">未分配角色列表</label><br>
					<select id="leftRoleList" class="form-control" multiple size="10" style="width:100px;overflow-y:auto;">
						<c:forEach items="${unassignList }" var="role">
	                        <option value="${role.id }">${role.name }</option>
						</c:forEach>
                    </select>
				  </div>
				  <div class="form-group">
                        <ul>
                            <li id="leftToRightBtn" class="btn btn-default glyphicon" style="width: 130px;">Add&gt;&gt;</li>
                            <br>
                            <li id="leftAllToRightBtn" class="btn btn-default glyphicon" style="margin-top:15px;width: 130px;">AddAll&gt;&gt;</li>
                            <br>
                            <li id="rightToLeftBtn" class="btn btn-default glyphicon" style="margin-top:15px;width: 130px;">&lt;&lt;Remove</li>
                            <br>
                            <li id="rightAllToLeftBtn" class="btn btn-default glyphicon" style="margin-top:15px;width: 130px;">&lt;&lt;RemoveAll</li>
                        </ul>
				  </div>
				  <div class="form-group" style="margin-left:40px;">
					<label for="rightRoleList">已分配角色列表</label><br>
					<select id="rightRoleList" class="form-control" multiple size="10" style="width:100px;overflow-y:auto;">
                        <c:forEach items="${assignList }" var="role">
	                        <option value="${role.id }">${role.name }</option>
						</c:forEach>
                    </select>
				  </div>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    
			    //如果左边（未分配）为空时，禁用添加按钮；反之启用添加按钮
				   function openOrColseLeftBtn(roleList){
					   if(roleList.length == 0){
				    		$("#leftToRightBtn").attr({"disabled":"disabled"});
				    		$("#leftAllToRightBtn").attr({"disabled":"disabled"});
				    	}else{
				    		$("#leftToRightBtn").removeAttr("disabled");
				    		$("#leftAllToRightBtn").removeAttr("disabled");
				    	}
				   }
				   openOrColseLeftBtn($("#leftRoleList option"));
				   
				    //如果右边（未分配）为空时，禁用移除按钮；反之，启用移除按钮
				   function openOrColseRightBtn(roleList){
					  // if($("#rightRoleList option").length == 0){
						if(roleList.length == 0){
				    		$("#rightToLeftBtn").attr({"disabled":"disabled"});
				    		$("#rightAllToLeftBtn").attr({"disabled":"disabled"});
				    	}else{
				    		$("#rightToLeftBtn").removeAttr("disabled");
				    		$("#rightAllToLeftBtn").removeAttr("disabled");
				    	}
				   }
				   openOrColseRightBtn($("#rightRoleList option"));
			    
			    //从左边（未分配）移动到右边（已分配）
			    function leftToRight(leftRoleSel){
			    	var loadingIndex = -1;
			    	var dataObj = {"userId":"${user.id}"};
			    	$.each(leftRoleSel,function(i,n){
			    		dataObj["ids["+i+"]"] = n.value ;
			    	});
			    	
			    	$.ajax({
			    		type:"post",
			    		url:"${APP_PATH}/user/assign.do",
			    		data:dataObj,
			    		beforeSend:function(){
			    			loadingIndex = layer.msg('正在分配角色...', {icon: 16});
			    			return true;
			    		},
			    		success:function(result){
			    			layer.close(loadingIndex);
			    			if(result.success){
			    				layer.msg('分配角色成功!', {time:2000, icon: 16},function(){
				    				$("#rightRoleList").append(leftRoleSel);
				    				openOrColseLeftBtn($("#leftRoleList option"));
							    	openOrColseRightBtn($("#rightRoleList option"));
				    			});
			    			}else{
			    				layer.msg("分配角色失败", {time:2000, icon:5, shift:6});
			    			}
			    			
			    		},
			    		error:function(){
			    			layer.msg("分配角色失败", {time:2000, icon:5, shift:6});
			    		}
			    	});
			    	
			    }
			   
			   
			   //点击Add时
			   $("#leftToRightBtn").click(function(){
			    	var leftRoleSel  = $("#leftRoleList :selected");
			    	leftToRight(leftRoleSel);
			    });
			    
			    
			    //点击AddAll时
			    $("#leftAllToRightBtn").click(function(){
			    	//左边所有的元素
				   	var leftRoleSel  = $("#leftRoleList option");
			    	leftToRight(leftRoleSel);
			    });
			    
			    
			  	//从右边（已分配）移动到左边（未分配）
			    function rightToLeft(rightRoleSel){
			    	var loadingIndex = -1;
			    	var dataObj = {"userId":"${user.id}"};
			    	$.each(rightRoleSel,function(i,n){
			    		dataObj["ids["+i+"]"] = n.value ;
			    	});
			    	
			    	$.ajax({
			    		type:"post",
			    		url:"${APP_PATH}/user/unassign.do",
			    		data:dataObj,
			    		beforeSend:function(){
			    			loadingIndex = layer.msg('正在取消角色...', {icon: 16});
			    			return true;
			    		},
			    		success:function(result){
			    			layer.close(loadingIndex);
			    			if(result.success){
			    				layer.msg('取消角色成功!', {time:2000, icon: 16},function(){
				    				$("#leftRoleList").append(rightRoleSel);
				    				openOrColseLeftBtn($("#leftRoleList option"));
							    	openOrColseRightBtn($("#rightRoleList option"));
				    			});
			    			}else{
			    				layer.msg("取消角色失败", {time:2000, icon:5, shift:6});
			    			}
			    			
			    		},
			    		error:function(){
			    			layer.msg("分取消角色失败", {time:2000, icon:5, shift:6});
			    		}
			    	});
			    }
			  	
			  	//点击Remove时
			    $("#rightToLeftBtn").click(function(){
			    	var rightRoleSel  = $("#rightRoleList :selected");
			    	rightToLeft(rightRoleSel);
			    	
			    });
			    
			   //点击RemoveAll时
		       $("#rightAllToLeftBtn").click(function(){
		    	    var rightRoleSel  = $("#rightRoleList option");
		    	    rightToLeft(rightRoleSel);
		       });
			   
		       $("#leftRoleList").delegate("option","dblclick",function(){
			    	leftRoles = new Array();
			    	leftRoles[0]=this;
			    	leftToRight(leftRoles);
			    });
		       
		       $("#rightRoleList").delegate("option","dblclick",function(){
			    	rightRoles = new Array();
			    	rightRoles[0]=this;
			    	rightToLeft(rightRoles);
			    });
		       
		       
            });
        </script>
  </body>
</html>
    