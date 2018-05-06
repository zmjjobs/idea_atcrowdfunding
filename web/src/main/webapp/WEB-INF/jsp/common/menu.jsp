<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<ul style="padding-left:0px;" class="list-group">
	<c:if test="${not empty sessionScope.rootPermission }">
		<c:forEach items="${sessionScope.rootPermission.children }" var="permission">
			<c:if test="${empty permission.children}">
				<li class="list-group-item tree-closed" >
					<a href="${APP_PATH }/${permission.url }"><i class="${permission.icon}"></i> ${permission.name }</a> 
				</li>
			</c:if>
			<c:if test="${not empty permission.children}" >
				<li class="list-group-item tree-closed">
					<span><i class="${permission.icon}"></i> ${permission.name } <span class="badge" style="float:right">${fn:length(permission.children)} </span></span> 
					<ul style="margin-top:10px;display:none;">
						<c:forEach items="${permission.children }" var="innerPermission">
							<li style="height:30px;">					
								<a href="${APP_PATH }/${innerPermission.url}"><i class="${innerPermission.icon }"></i> ${innerPermission.name }</a> 
							</li>
						</c:forEach>
					</ul>
				</li>
			</c:if>
		</c:forEach>
	</c:if>
	<c:if test="${empty sessionScope.rootPermission.children }">
		暂未分配任何权限！
	</c:if>
</ul>