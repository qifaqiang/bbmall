<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="top.jsp"></jsp:include>

<div class="container">
	<div class="index_header">
		<a href="${tddInit.shopping_url }" class="index_logo"><img
			src="${tddInit.image_url }/${tddInit.site_logo}" /></a>

		<h2>收银台</h2>
		<div class="clear"></div>
	</div>
	<div class="Cashier">
		<div class="IssueOrder">
		    <i></i>
			<c:if test="${!empty errorInfo }">${ errorInfo}</c:if>
			<c:if test="${empty errorInfo }">该订单有问题，请重新下单，或者联系管理员!</c:if>
		</div>
	</div>
