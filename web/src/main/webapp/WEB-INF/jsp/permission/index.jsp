<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>尚筹网-权限管理-许可维护</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/main.css">
<link rel="stylesheet" href="${APP_PATH }/ztree/zTreeStyle.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 许可维护</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;"><jsp:include
							page="/WEB-INF/jsp/common/userinfo.jsp" /></li>
					<li style="margin-left: 10px; padding-top: 8px;">
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
					<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script src="${APP_PATH }/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});

		});
	</script>

	<SCRIPT type="text/javascript">
		var setting = {
			view : {
				selectedMulti:false,
				//treeNode前台是JSON对象，后台是Permission对象
				addDiyDom : function(treeId, treeNode) {
					var icoObj = $("#" + treeNode.tId + "_ico");
					if (treeNode.icon) {
						icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background", "");
					}
				},
				addHoverDom: function(treeId, treeNode){  
					var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
					aObj.attr("href", "javascript:;");
					if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
					var s = '<span id="btnGroup'+treeNode.tId+'">';
					if ( treeNode.level == 0 ) {
						s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?pId='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
					} else if ( treeNode.level == 1 ) {
						s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" title="修改权限信息" onclick="window.location.href=\'${APP_PATH}/permission/toEdit.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
						if (treeNode.children.length == 0) {
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermissionById('+treeNode.id+',\''+treeNode.name+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
						}
						s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#"  onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?pId='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
					} else if ( treeNode.level == 2 ) {
						s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" title="修改权限信息" onclick="window.location.href=\'${APP_PATH}/permission/toEdit.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
						s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermissionById('+treeNode.id+',\''+treeNode.name+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
					}
	
					s += '</span>';
					aObj.after(s);
				},
				removeHoverDom: function(treeId, treeNode){
					$("#btnGroup"+treeNode.tId).remove();
				}
			},
			async : {
				enable : true,
				//url : "${APP_PATH}/permission/loadData.do",
				url : "${APP_PATH}/permission/asyncLoadData.do",
				autoParam : ["id"]
			}
		};
		/* var loadingIndex = -1;
		$.ajax({
			type : "POST",
			url : "${APP_PATH}/permission/loadData.do",
			beforeSend : function() {
				loadingIndex = layer.load(2, {
					time : 10000
				});
				return true;
			},
			success : function(result) {
				layer.close(loadingIndex);
				if (result.success) {
					//$.fn.zTree.init($("#treeDemo"), setting, result.data);
				} else {
					layer.msg("加载数据失败！", {
						time : 2000,
						icon : 5,
						shift : 6
					});
				}
			},
			error : function() {
				layer.msg("加载数据失败！", {
					time : 2000,
					icon : 5,
					shift : 6
				});
			}
		}); */
		$.fn.zTree.init($("#treeDemo"), setting);
		function  deletePermissionById(id,name){
			layer.confirm("删除["+name+"]用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
        		var loadingIndex = -1 ;
        		$.ajax({
        			type:"post",
        			url:"${APP_PATH}/permission/doDelete.do",
        			data:{
        				"id":id
        			},
        			beforeSend:function(){
        				loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
        				return true ;
        			},
        			success:function(result){
        				layer.close(loadingIndex);
        				if(result.success){
        					layer.msg("许可删除成功！",{time:1000, icon:6},function(){
        						//读取当前树对象
        						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        						//刷新当前树对象的数据
        						treeObj.reAsyncChildNodes(null,"refresh");
        						/* var nodes = treeObj.getSelectedNodes();
        						if(nodes.length>0){
        							treeObj.reAsyncChildNodes(nodes[0],"refresh");
        						} */
        						
        						
        						
        					});
        				}else{
        					layer.msg("许可删除失败!", {time:1000, icon:5, shift:6});
        				}
        			},
        			error:function(){
        				layer.msg("许可删除失败!", {time:1000, icon:5, shift:6});
        			}
        			
        		});
			    layer.close(cindex);
			}, function(cindex){
			    layer.close(cindex);
			});
		}
		
	</SCRIPT>

	<script type="text/javascript" src="${APP_PATH}/script/menu.js"></script>
</body>
</html>
