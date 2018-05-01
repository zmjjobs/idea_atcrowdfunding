<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>尚筹网 - 用户维护</title>
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
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <jsp:include page="/common/userinfo.jsp"/>
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
                <ul style="padding-left:0px;" class="list-group">
                    <li class="list-group-item tree-closed" >
                        <a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a>
                    </li>
                    <li class="list-group-item">
                        <span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span>
                        <ul style="margin-top:10px;">
                            <li style="height:30px;">
                                <a href="${APP_PATH}/user/toIndex.htm" style="color:red;"><i class="glyphicon glyphicon-user"></i> 用户维护</a>
                            </li>
                            <li style="height:30px;">
                                <a href="role.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a>
                            </li>
                            <li style="height:30px;">
                                <a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 许可维护</a>
                            </li>
                        </ul>
                    </li>
                    <li class="list-group-item tree-closed">
                        <span><i class="glyphicon glyphicon-ok"></i> 业务审核 <span class="badge" style="float:right">3</span></span>
                        <ul style="margin-top:10px;display:none;">
                            <li style="height:30px;">
                                <a href="auth_cert.html"><i class="glyphicon glyphicon-check"></i> 实名认证审核</a>
                            </li>
                            <li style="height:30px;">
                                <a href="auth_adv.html"><i class="glyphicon glyphicon-check"></i> 广告审核</a>
                            </li>
                            <li style="height:30px;">
                                <a href="auth_project.html"><i class="glyphicon glyphicon-check"></i> 项目审核</a>
                            </li>
                        </ul>
                    </li>
                    <li class="list-group-item tree-closed">
                        <span><i class="glyphicon glyphicon-th-large"></i> 业务管理 <span class="badge" style="float:right">7</span></span>
                        <ul style="margin-top:10px;display:none;">
                            <li style="height:30px;">
                                <a href="cert.html"><i class="glyphicon glyphicon-picture"></i> 资质维护</a>
                            </li>
                            <li style="height:30px;">
                                <a href="type.html"><i class="glyphicon glyphicon-equalizer"></i> 分类管理</a>
                            </li>
                            <li style="height:30px;">
                                <a href="process.html"><i class="glyphicon glyphicon-random"></i> 流程管理</a>
                            </li>
                            <li style="height:30px;">
                                <a href="advertisement.html"><i class="glyphicon glyphicon-hdd"></i> 广告管理</a>
                            </li>
                            <li style="height:30px;">
                                <a href="message.html"><i class="glyphicon glyphicon-comment"></i> 消息模板</a>
                            </li>
                            <li style="height:30px;">
                                <a href="project_type.html"><i class="glyphicon glyphicon-list"></i> 项目分类</a>
                            </li>
                            <li style="height:30px;">
                                <a href="tag.html"><i class="glyphicon glyphicon-tags"></i> 项目标签</a>
                            </li>
                        </ul>
                    </li>
                    <li class="list-group-item tree-closed" >
                        <a href="param.html"><i class="glyphicon glyphicon-list-alt"></i> 参数管理</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="queryText" class="form-control has-success" type="text" placeholder="模糊查询账户">
                            </div>
                        </div>
                        <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/user/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <button id="batchDeleteBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i>批量删除</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="parentCheckbox" type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <%--异步插入分页数据--%>
                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                       <%--异步插入分页条--%>
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
        <c:if test="${not empty param.pageNum}">
            queryPageUser(${param.pageNum});
        </c:if>
        <c:if test="${empty param.pageNum}">
            queryPageUser(1);
        </c:if>

    });
    $("tbody .btn-success").click(function(){
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function(){
        window.location.href = "edit.html";
    });

    var dataObj = {
        pageNum : 1,
        pageSize : 10
    };
    $("#queryBtn").click(function(){
        var queryText = $("#queryText").val();
        if($.trim(queryText) == ""){
            layer.msg("查询条件不能为空，请输入查询条件！"),{time:1000,icon:5,shift:6},function () {
                $("#queryText").focus();
            }
            return;
        }
        dataObj.queryText = $.trim(queryText);
        queryPageUser(1);
    });

    function queryPageUser(pageNum){
        dataObj.pageNum = pageNum;
        $.ajax({
            type : "POST",
            url : "${APP_PATH}/user/doIndex.do",
            data : dataObj,
            beforeSend : function () {
                return true;
            },
            success : function(result){
                if(result.success){
                    var page = result.page;
                    var data = page.data;
                    var content = '';
                    $.each(data,function(index,user){
                        content += '<tr>';
                        content += '    <td> '+(index+1)+'</td>';
                        content += '    <td><input class="childCheckbox" type="checkbox" value="'+user.id+'"></td>';
                        content += '    <td>'+user.loginacct+'</td>';
                        content += '    <td>'+user.username+'</td>';
                        content += '    <td>'+user.email+'</td>';
                        content += '    <td>';
                        content += '        <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        content += '        <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'${APP_PATH}/user/toEdit.htm?pageNum='+pageNum+'&id=' + user.id + '\'"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        content+='	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteUser('+user.id+',\''+user.loginacct+'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
                        content += '    </td>';
                        content += '</tr>';
                    });
                    $("tbody").html(content);

                    var contentNavigater = '';
                    if(page.pageNum == 1){
                        contentNavigater += '<li class="disabled"><a href="#">上一页</a></li>';
                    }else{
                        contentNavigater += '<li><a onclick="queryPageUser('+(page.pageNum - 1)+')">上一页</a></li>';
                    }

                    for(var i = 1;i <= page.totalPage;i++){
                        if(pageNum == i){
                            contentNavigater += '<li class="active"><a onclick="queryPageUser('+i+')">'+i+'</a></li>';
                        }else{
                            contentNavigater += '<li><a onclick="queryPageUser('+i+')">'+i+'</a></li>';
                        }
                    }

                    if(page.pageNum == page.totalPage){
                        contentNavigater += '<li class="disabled"><a href="#">下一页</a></li>';
                    }else{
                        contentNavigater += '<li><a onclick="queryPageUser('+(page.pageNum + 1)+')">下一页</a></li>';
                    }
                    $(".pagination").html(contentNavigater);
                }else{
                    layer.msg(result.message,{time:1000,icon:5,shift:6});
                }
            },
            error : function(){
                layer.msg("分页查询失败！",{time:1000,icon:5,shift:6});
            }
        });
    }


    function deleteUser(id,loginacct){

        layer.confirm("确认删除["+loginacct+"]用户吗?",  {icon: 3, title:'提示'}, function(cindex){
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
                        layer.msg("用户删除成功", {time:1000, icon:6}, function(){
                            queryPageUser(1);
                        });
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
    }

    $("#parentCheckbox").click(function () {
        var parentCheckboxStatus = this.checked;
        $(".childCheckbox").each(function (i,n) {
            n.checked = parentCheckboxStatus;
        });
    });

    $("#batchDeleteBtn").click(function (){
        var checkboxArr = $(".childCheckbox:checked");
        var length = checkboxArr.length;
        var dataObj = {};

        $.each(checkboxArr,function(i,n){
            dataObj['userList['+i+'].id'] = n.value;
        });

        if(length>0){
            layer.confirm("删除这些用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
                var loadingIndex = -1 ;
                $.ajax({
                    type:"post",
                    url:"${APP_PATH}/user/doBatchDeleteUsers.do",
                    data:dataObj,
                    beforeSend:function(){
                        loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
                        return true ;
                    },
                    success:function(result){
                        layer.close(loadingIndex);
                        if(result.success){
                            queryPageUser(1);
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
</body>
</html>

