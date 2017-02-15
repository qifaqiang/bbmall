<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>小票打印</title>
</head>
<style>
body {
	padding: 30px 0;
}

.breakW {
	word-break: break-all;
	word-wrap: break-word;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
	table-layout: fixed;
	width: 180px;
	font-size: 12px;
	font-family: "Microsoft YaHei", 微软雅黑;
}

.tac {
	text-align: center;
}

.tar {
	text-align: right;
}

.tal {
	text-align: left;
}

.ellipsisW {
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden
}

.ow {
	white-space: nowrap;
	overflow: hidden
}

.fw {
	font-weight: bold;
	font-size: 14px;
}
</style>
<body>
	<!--<object ID='wb' WIDTH=0 HEIGHT=0 CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>-->
	<table>
		<tbody>
			<tr>
				<td colspan="4" class="tac">鲜易达</td>
			</tr>
			<tr>
				<td colspan="4" class="tac">${orders.company.companyName}</td>
			</tr>
			<tr>
				<td colspan="1">时间：</td>
				<td colspan="3"><fmt:formatDate value="${orders.addtime}"
						type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<td colspan="1">编号：</td>
				<td colspan="3" class="breakW">${orders.ordersn}</td>
			</tr>
			<tr>
				<td colspan="4" class="ow">*************************************************</td>
			</tr>

			<tr>
				<th>商品</th>
				<th>数量</th>
				<th>单价</th>
				<th>金额</th>
			</tr>
			<tr>
				<td colspan="4" class="ow">********************************************************</td>
			</tr>
			<c:forEach items="${orders.od }" var="ods">
				<tr>
					<td colspan="4" class="">${ods.prodName }[${ods.prodSpecName}]</td>
				</tr>
				<tr>
					<td></td>
					<td class="tac">${ods.prodCount }</td>
					<td class="tac">${ods.prodPrice }</td>
					<td class="tac ellipsisW">${ods.prodCount*ods.prodPrice }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" class="ow">************************************************</td>
			</tr>
			<tr>
				<td>合计：</td>
				<td colspan="1" class="tac">3项</td>
				<td colspan="2" class="tar ">${orders.orderPrice }元</td>

			</tr>

			<tr>
				<td>运费：</td>
				<td colspan="3" class="tar">${empty orders.shipPrice?'0.00':orders.shipPrice}元</td>
			</tr>
			<tr>
				<td>优惠：</td>
				<td colspan="3" class="tar">${orders.promotionPrice+orders.couponsPrice }元</td>
			</tr>


			<tr>
				<td>实收：</td>
				<td colspan="3" class="tar"><span>(已付款)</span>${orders.orderAccount }元</td>
			</tr>
			<tr>
				<td colspan="4">收货人信息：</td>
			</tr>
			<tr>
				<td colspan="4" class="fw">${orders.ul.addressName }&nbsp;
					&nbsp; ${orders.ul.consignee } &nbsp; &nbsp; ${orders.ul.mobile }</td>
			</tr>
			<tr>
				<td colspan="4" class="ow">*************************************************</td>
			</tr>

			<tr>
				<td colspan="4" class="tac">鲜易达....</td>
			</tr>



		</tbody>
	</table>

	<style type="text/css" media=print>
.noprint {
	display: none;
}
</style>
	<button onclick="window.print()" class='noprint'
		style="margin-left:35px;margin-top:60px; padding:10px 25px; border:none;background: #ececec;border: 1px solid silver;border-radius: 5px;">打印小票</button>
	<script>
		//    alert(document.body.offsetWidth)
	</script>
</body>
</html>