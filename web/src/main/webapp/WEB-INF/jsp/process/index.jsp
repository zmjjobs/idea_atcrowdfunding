<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>尚筹网-业务管理-流程管理</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/main.css">
<link rel="stylesheet" href="${APP_PATH}/css/pagination.css">
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
						- 流程管理</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;"><%@include
							file="/WEB-INF/jsp/common/userinfo.jsp"%>
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
					<%@include file="/WEB-INF/jsp/common/menu.jsp"%>
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
						<form class="form-inline" id="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="queryText" class="form-control has-success"
										type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" class="btn btn-warning"
								onclick="queryProcess()">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>

						<button id="uploadProcDefBtn" type="button" class="btn btn-primary"
							style="float: right;">
							<i class="glyphicon glyphicon-upload"></i> 上传流程定义文件
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<form id="processDefForm" method="post" enctype="multipart/form-data">
								<input style="display:none;" type="file" class="form-control" id="uploadProcessDefFile" name="procDefFile">
							</form>
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="60">序号</th>
										<th>流程ID</th>
										<th>流程名称</th>
										<th>流程KEY</th>
										<th>流程版本</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
								<tfoot>
									<tr>
										<td colspan="5" align="center">
											<!-- <ul class="pagination">
							
						</ul> -->
											<div id="pagination" class="pagination">
												<!-- 这里显示分页 -->
											</div>
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
	<script src="${APP_PATH}/jquery/pagination/jquery.pagination.js"></script>
	<script src="${APP_PATH }/jquery/jquery-form/jquery-form.min.js"></script>
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

			queryPage(0);
		});

		/* 上传流程定义文件 */
		$("#uploadProcDefBtn").click(function(){
			$("#uploadProcessDefFile").click();
		});
		
		/* 如果隐藏的输入框体发生改变，则异步提交隐藏表单 */
		$("#uploadProcessDefFile").change(function(){
			var options = {
        			url:"${APP_PATH}/process/deploy.do",
       				beforeSubmit : function(){
       					loadingIndex = layer.msg('流程正在部署中', {icon: 6});
               			return true ; //必须返回true,否则,请求终止
       				},
       				success : function(result){
            			layer.close(loadingIndex);
            			if(result.success){
            				layer.msg("流程部署成功", {time:1000, icon:6});
            				$("#processDefForm")[0].reset();
            				queryPage(0);
            			}else{
            				layer.msg("流程部署失败", {time:1000, icon:5, shift:6});
            			}	
            		},
            		error : function (){
            			layer.msg("流程部署失败,或因上传文件有误！", {time:3000, icon:5, shift:6});
            		}
       		};
       		$("#processDefForm").ajaxSubmit(options);
       		return;
		});
		
		function changePageno(pageno) {
			queryPage(pageno - 1);
		}

		/* 使用Ajax异步查询数据 */
		function queryPage(pageIndex) {
			var dataObj = {
				"pageno" : pageIndex + 1,
				"pagesize" : 3
			};
			if (condition) {
				dataObj.pagetext = $("#queryText").val(); //增加模糊查询条件
			}

			var loadingIndex = -1;
			$.ajax({
				url : "${APP_PATH}/process/pageQuery.do",
				type : "post",
				data : dataObj,
				beforeSend : function() {
					loadingIndex = layer.msg('数据查询中', {
						icon : 6
					});
					return true;
				},
				success : function(result) {
					//显示结果
					layer.close(loadingIndex);
					if (result.success) {
						//循环遍历,显示数据
						var pageObj = result.page;
						var list = pageObj.data;
						var content = "";
						$("tbody").html(content);
						$
								.each(
										list,
										function(i, n) {//这里的n代表流程定义对象procdef

											content += "<tr>";
											content += "	<td>"
													+ (i + 1) + "</td>";
											content += "	<td>" + n.id
													+ "</td>";
											content += "	<td>" + n.name
													+ "</td>";
											content += "	<td>" + n.key
													+ "</td>";
											content += "	<td>"
													+ n.version
													+ "</td>";

											content += "	<td>";
											content += "		<button type='button' onclick='window.location.href=\"${APP_PATH}/process/toShowImg.htm?pageno="
													+ pageObj.pageno
													+ "&id="
													+ n.id
													+ "\"' class='btn btn-success btn-xs'>";
											content += "			<i class=' glyphicon glyphicon-eye-open'></i>";
											content += "		</button>";
											content += "		<button type='button' class='btn btn-danger btn-xs' onclick='deleteProcDef(\""
													+ n.id
													+ "\",\""
													+ n.name + "\")'>";
											content += "			<i class=' glyphicon glyphicon-remove'></i>";
											content += "		</button>";
											content += "	</td>";
											content += "</tr>";

										});
						$("tbody").html(content);

						// 创建分页
						var num_entries = pageObj.totalsize;
						$("#pagination").pagination(num_entries, {
							num_edge_entries : 2, //边缘页数
							num_display_entries : 4, //主体页数
							callback : queryPage,
							items_per_page : pageObj.pagesize, //每页显示条数
							current_page : (pageObj.pageno - 1), //当前页,索引从0开始
							prev_text : "上一页",
							next_text : "下一页"
						});

					} else {

						layer.msg("流程定义查询数据失败", {
							time : 1000,
							icon : 5,
							shift : 6
						});

					}
				},
				error : function() {
					layer.msg("流程定义查询数据错误", {
						time : 1000,
						icon : 5,
						shift : 6
					});
				}

			});
		}

		var condition = false;
		//条件查询
		function queryProcess() {
			var queryText = $("#queryText");
			if ($.trim(queryText.val()) == "") {
				layer.msg("查询条件不能为空", {
					time : 1000,
					icon : 5,
					shift : 6
				}, function() {
					queryText.focus();
				});
				return;
			}
			condition = true;
			queryPage(0);
		}

		/* 删除流程定义 */
		function deleteProcDef(id, name) {
			layer.confirm("确认要删除《" + name + "》流程吗?", {
				icon : 3,
				title : '提示'
			}, function(cindex) {

				$.ajax({
					url : "${APP_PATH}/process/doDelete.do",
					type : "POST",
					data : {
						"id" : id
					},
					beforeSend : function(){
						loadingIndex = layer.msg("正在删除数据！",{time:1000,icon:6});
						return true;
					},
					success : function(result) {
						if (result.success) {
							layer.msg("删除" + name + "流程定义成功!", {
								time : 1000,
								icon : 6
							}, function() {
								queryPage(0);
							});
						} else {
							layer.msg("删除" + name + "流程定义失败!", {
								time : 1000,
								icon : 5,
								shift : 6
							});
						}
					},
					error : function() {
						layer.msg("删除" + name + "流程定义失败!", {
							time : 1000,
							icon : 5,
							shift : 6
						});
					}
				});

				layer.close(cindex);
			}, function(cindex) {
				layer.close(cindex);
			});

		}

		//选中父选框，孩子都选中，去掉父选框，孩子选框都不选中
		$(".table thead :checkbox").click(function() {
			var checked = this.checked;
			var checkboxList = $(".table tbody :checkbox");
			$.each(checkboxList, function(i, n) {
				n.checked = checked;
			});
		});

		//传递多个对象的方式
		$("#batchDelete").click(function() {
			var checkedList = $("table tbody input:checked");
			if (checkedList.length > 0) {
				layer.confirm("确定要删除这些广告吗?", {
					icon : 3,
					title : '提示'
				}, function(cindex) {

					var datas = {};

					$.each(checkedList, function(i, n) {

						datas["datas[" + i + "].id"] = n.value;
						datas["datas[" + i + "].name"] = "n.name";
					});

					$.ajax({
						url : "${APP_PATH}/process/batchDelete.do",
						type : "POST",
						data : datas,
						success : function(result) {
							if (result.success) {
								layer.msg("删除广告成功!", {
									time : 1000,
									icon : 6
								}, function() {
									queryPage(0);
								});
							} else {
								layer.msg("删除广告失败!", {
									time : 1000,
									icon : 5
								});
							}
						}
					});
					layer.close(cindex);
				}, function(cindex) {
					layer.close(cindex);
				});
			}
		});
	</script>
	<script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
</body>

</html>