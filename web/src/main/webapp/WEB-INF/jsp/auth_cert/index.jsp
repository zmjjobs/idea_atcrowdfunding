<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>尚筹网-业务审核-实名认证审核</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/main.css">
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
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台-实名认证审核</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
						<jsp:include page="/WEB-INF/jsp/common/userinfo.jsp" />
					</li>
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
					<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="queryText" class="form-control has-success"
										type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<button id="queryBtn" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" class="btn btn-primary"
							style="float: right;"
							onclick="window.location.href='${APP_PATH}/user/toAdd.htm'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">

							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">序号</th>
										<th>流程名称</th>
										<th>流程版本</th>
										<th>任务名称</th>
										<th>申请会员</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody></tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<ul class="pagination">

											</ul>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
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
			   
			    var currpageno = "${param.pageno}";
			    if(currpageno == "" || currpageno == null){
			    	queryPage(1);
			    }else{
			    	queryPage(currpageno);
			    }
			   
            });
         
            
            function pageChange(pageno){
            	queryPage(pageno);
            }
            
            
            var dataObj = {
        			"pageno" : 1,
        			"pagesize" : 5 
        		};
            
            $("#queryBtn").click(function(){
            	
            	var queryText = $("#queryText").val();
            	if($.trim(queryText)==""){ //判断文本框的值是否为空:应该使用双引号而不是null ;
            		layer.msg("查询条件值不允许为空,请输入查询条件!", {time:2000, icon:5, shift:6}, function(){
            			$("#queryText").focus();
            		}); 		
            		return ;
            	}
            	
            	dataObj.queryText = $.trim(queryText) ;
            	
            	queryPage(1);
            });
            
            
            function queryPage(pageno){
            	dataObj.pageno = pageno ;
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH }/user/doindex.do",
            		data : dataObj,
            		beforeSend : function(){
            			return true ;
            		},
            		success : function(result){
            			if(result.success){
            				var page = result.page ;
            				var data = page.data ; //userList
            				
            				var content = '';
            				
            				$.each(data,function(index,user){
            					content+='<tr>';
                				content+='  <td>'+(index+1)+'</td>';
                				content+='  <td><input class="checkboxClass" type="checkbox" value="'+user.id+'"></td>';
                				content+='  <td>'+user.loginacct+'</td>';
                				content+='  <td>'+user.username+'</td>';
                				content+='  <td>'+user.email+'</td>';
                				content+='  <td>';
                				content+='	  <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/user/toAssignRole.htm?id='+user.id+'\'"><i class=" glyphicon glyphicon-check"></i></button>';
                				content+='	  <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'${APP_PATH}/user/edit.htm?pageno='+page.pageno+'&id='+user.id+'\'"><i class=" glyphicon glyphicon-pencil"></i></button>';
                				content+='	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteUser('+user.id+',\''+user.loginacct+'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
                				content+='  </td>';
                				content+='</tr>';	
            				});
            				
            				
            				//$("tbody").append(content); //append()是在原有内容基础上追加内容,可能出现重复数据;
            				$("tbody").html(content); // innerHTML
            				
            				var contentNavigater = '' ;
            				
            				if(page.pageno == 1){
            					contentNavigater += '<li class="disabled"><a href="#">上一页</a></li>';
            				}else{
            					contentNavigater += '<li><a href="#" onclick="pageChange('+(page.pageno-1)+')">上一页</a></li>';
            				}
            				
            				
            				for(var i=1 ; i<=page.totalno ; i++){
            					if(page.pageno==i){
            						contentNavigater += '<li class="active"><a href="#" onclick="pageChange('+i+')">'+i+'</a></li>';
            					}else{
            						contentNavigater += '<li><a href="#" onclick="pageChange('+i+')">'+i+'</a></li>';
            					}            					
            				}
            				
            				if(page.pageno == page.totalno){
            					contentNavigater += '<li class="disabled"><a href="#">下一页</a></li>';
            				}else{
            					contentNavigater += '<li><a href="#" onclick="pageChange('+(page.pageno+1)+')">下一页</a></li>';
            				}
            				
            				
            				$(".pagination").html(contentNavigater);
            			}else{
            				layer.msg(result.errorMessage, {time:1000, icon:5, shift:6});
            			}
            		},
            		error : function(){
            			layer.msg("分页查询失败!", {time:1000, icon:5, shift:6});
            		}
            	});
            	return ;
            }
            
            
            function deleteUser(id,loginacct){//根据ID进行删除
            	
            	layer.confirm("删除["+loginacct+"]用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
            		var loadingIndex = -1 ;
            		$.ajax({
            			type:"post",
            			url:"${APP_PATH}/user/doDelete.do",
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
            					layer.msg("用户删除成功！",{time:1000, icon:6},function(){
            						queryPage(1);
            					});
            				}else{
            					layer.msg("用户删除失败!", {time:1000, icon:5, shift:6});
            				}
            			},
            			error:function(){
            				layer.msg("用户删除失败!", {time:1000, icon:5, shift:6});
            			}
            			
            		});
    			    layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            	
            }
            
            
            $("#checkboxAll").click(function(){
            	//所有的子选框随着父选框全选或不选
            	var checkboxAllStatus = this.checked ;
            	$(".checkboxClass").each(function(i,n){
            		n.checked = checkboxAllStatus;
            	});
            });
            
            //需要父跟子动，暂时没有实现
          /*   $(".checkboxClass").click(function(){
            	var count = $(".checkboxClass").length;
            	var checkBoxChecked = $(".checkboxClass:checked");
            	var length = checkBoxChecked.length;
            	if(length==count){
            		$("#checkboxAll").attr("checked",true);
            	}else{
            		$("#checkboxAll").attr("checked",false);
            	}
            }); */
            
            $("#deleteBatchBtn").click(function(){
            	var checkBoxChecked = $(".checkboxClass:checked");
            	var length = checkBoxChecked.length;
            	
            	//拼串：每个键值对为id=xx,每个元素后面加一个&
            	/* var paramStr = "";
            	$.each(checkBoxChecked,function(i,n){
            		if(i!=0){
            			paramStr += "&";
            		}
            		paramStr += "id="+n.value;
            	}); */
            	
            	var dataObj = {};
            	$.each(checkBoxChecked,function(i,n){
            		//对应自定义的Datas类里面的datas属性
            		dataObj['datas['+i+'].id'] = n.value;
            		dataObj['datas['+i+'].username']='aaa';
            		
            		//对应自定义的Datas类里面的ids属性
            		//dataObj['ids['+i+']'] = n.value;
            	});
            	if(length>0){
            		layer.confirm("删除这些用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
                		var loadingIndex = -1 ;
	            		$.ajax({
	            			type:"post",
	            			//url:"${APP_PATH}/user/doDeleteBatch.do",
	            			//url:"${APP_PATH}/user/deleteBatchByUserids.do",
	            			url:"${APP_PATH}/user/deleteBatchByUsers.do",
	            			data:dataObj,
	            			beforeSend:function(){
	            				loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
	            				return true ;
	            			},
	            			success:function(result){
	            				layer.close(loadingIndex);
	            				if(result.success){
	            					queryPage(1);
	            				}else{
	            					layer.msg("删除失败!", {time:1000, icon:5, shift:6});
	            				}
	            			},
	            			error:function(){
	            				layer.msg("删除失败!", {time:1000, icon:5, shift:6});
	            			}
	            			
	            		});

	    			    layer.close(cindex);
	    			}, function(cindex){
	    			    layer.close(cindex);
	    			});
            	}else{
            		layer.msg("请选择要删除的用户!", {time:1000, icon:6});
            	}
            	
            });
        </script>
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
</body>
</html>
