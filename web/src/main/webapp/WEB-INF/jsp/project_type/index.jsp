<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 项目分类</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top: 8px;">
				<jsp:include page="/WEB-INF/jsp/common/userinfo.jsp" />
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
			<%@ include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据矩阵</h3>
			  </div>
			 <div class="panel-body">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th>名称</th>
                  <th >商业公司</th>
                  <th >个体工商户</th>
                  <th >个人经营</th>
                  <th >政府及非营利组织</th>
                </tr>
              </thead>
              <tbody>
                <tr>n
                  <td>营业执照副本</td>
                  <td><input type="checkbox">123</td>
                  <td><input type="checkbox">31231</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>税务登记证</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>组织机构代码证</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>单位登记证件 </td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>法定代表人证件</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>经营者证件 </td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>手执身份证照片</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
              </tbody>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
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
			    queryPageType(1);
            });
    		function pageChange(pageno) {
    			// 	window.location.href="${APP_PATH}/user/user.htm?pageno="+pageno;
    			queryPageType(pageno);
    		}
    		var dataObj = {
    			"pageno" : 1,
    			"pagesize" : 2
    		};
    		
    		$("#queryBtn").click(function() {
    			var queryText = $("#queryText").val();
    			if ($.trim(queryText) == "") {//判断文本框的值是否为空 应该用""不用null
    				layer.msg("查询条件不能为空，请输入查询条件", {time : 2000,icon : 5,shift : 6}, 
    				function() {
    					$("#queryText").focus();
    				});
    				return;
    			}

    			dataObj.queryText = $.trim(queryText);
    			queryPageType(1);
    		});
    		
    		
    		function queryPageType(pageno) {
    			dataObj.pageno = pageno;
    			$.ajax({
    				type : "post",
    				url : "${APP_PATH }/type/doindex.do",//这走业务请求
    				data : dataObj,
    				beforesent : function() {
    					return true;
    					},
    				success : function(result) {
    					if (result.success) {
    						var page = result.page;
    						var data = page.data;
    						var content = '';
    						$.each(data,function(index, type) {
    							content += '<tr>';
    							content += '	<td>'+ (index + 1)+'</td>';
    							content += '	<td><input class="checkboxClass" value="'+type.id+'" type="checkbox"></td>';
    							content += '	<td>'+type.name+'</td>';
    							content += '	<td>'+type.remark+'</td>';
    							content += '	<td>';
    							content += '		<button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'${APP_PATH}/type/edit.htm?pageno='+ page.pageno+ '&id='+ type.id+ '\'"><i class=" glyphicon glyphicon-pencil"></i></button>';
    							content += '		<button  type="button" class="btn btn-danger btn-xs" onclick="deleteType('+ type.id+ ',\''+ type.name+ '\')"><i class=" glyphicon glyphicon-remove"></i></button>';
    							content += '	</td>';
    							content += '</tr>';
    						});
    						$("tbody").html(content);
    						
							var contentNavigater = '';

							if (page.pageno == 1) {
								contentNavigater += '<li class="disabled"><a href="#">上一页</a></li>';
							} else {
								contentNavigater += '<li><a href= "#" onclick="pageChange('+ (page.pageno - 1)+ ')">上一页</a></li>';
							}

							for ( var i = 1; i <= page.totalno; i++) {
								if (page.pageno == i) {
									contentNavigater += '<li class="active"><a href="#" onclick="pageChange('+ i + ')">' + i + '</a></li>';
								} else {
									contentNavigater += '<li><a href="#" onclick="pageChange('+ i + ')">' + i + '</a></li>';
								}
							}
							if (page.pageno == page.totalno) {
								contentNavigater += '<li class="disabled"><a href="#">下一页</a></li>';
							} else {
								contentNavigater += '<li><a href="#" onclick="pageChange('+ (page.pageno + 1)+ ')">下一页</a></li>';
							}

							$(".pagination").html(contentNavigater);
						} else {
							layer.msg(result.errorMessage, {time:1000, icon:5, shift:6});
						}
					},
					error : function() {
						layer.msg("分页查询失败", {time : 2000,icon : 5,shift : 6});
					}
				});
    			
				return;
			};
			function deleteType(id, name) {
				layer.confirm("删除[" + name + "]用户,确认删除?", {
					icon : 3,
					title : '提示'
				}, function(cindex) {
					var loadingIndex = -1;
					$.ajax({
						type : "post",
						url : "${APP_PATH }/type/dodelete.do",
						data : {
							id : id,
						},
						beforeSend : function() {
							loadingIndex = layer.msg("正在删除数据!", {time : 2000,icon : 6});
							return true;
						},
						success : function(result) {
							layer.close(loadingIndex, {time : 2000,icon : 5,shift : 6});
							if (result.success) {
								queryPageType(1);
							} else {
								layer.msg("删除失败!+++++", {time : 1000,icon : 5,shift : 6});
							}
						},
						error : function() {
							layer.msg("删除失败!!!!!!!", {time : 1000,icon : 5,shift : 6});
						}
					});
					layer.close(cindex);
				}, function() {
					layer.close(cindex);
				});
				return;
			}
			$("#checkboxAll").click(function() {
				var checkboxAllStatus = this.checked;

				$(".checkboxClass").each(function(i, n) {
					n.checked = checkboxAllStatus;
				});
			});
			$("#deleteBatch").click(function() {
				var checkBoxChecked = $(".checkboxClass:checked");
				var length = checkBoxChecked.length;
				paramStr = "";
				$.each(checkBoxChecked, function(i, n) {
					if (i != 0) {
						paramStr += "&";
					}
					paramStr += "id=" + n.value;
				});

				if (length > 0) {
					layer.confirm("删除这些用户,确认删除?", {
						icon : 3,
						title : '提示'
					}, function(cindex) {
						var loadingIndex = -1;
						$.ajax({
							
							type : "post",
							url : "${APP_PATH }/type/dodeleteBatch.do",
							data : paramStr,
							beforeSend : function() {
								loadingIndex = layer.msg("正在删除数据!", {time : 1000,icon : 6});
								return true;
								
							},
							success : function(result) {
								layer.close(loadingIndex, {time : 2000,icon : 5,shift : 6});
								if (result.success) {
									queryPageType(1);
								}else {
									layer.msg("删除失败!+++++", {time : 1000,icon : 5,shift : 6});
								}
							},
							error : function() {
								layer.msg("删除失败!!!!!!!", {time : 1000,icon : 5,shift : 6});
							}
						});
						layer.close(cindex);
					}, 
					function(cindex) {
						layer.close(cindex);
					});
				} else {
					layer.msg("请选择要删除的用户", {time : 1000,icon : 6,shift : 6});
				}
			});
 
        </script>
        <script type="text/javascript" src="${APP_PATH}/script/menu.js"></script>
  </body>
</html>
