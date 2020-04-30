<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지점별 매출 현황</title>
</head>
<body>
	<table class="table">
		<tr>
			<th>지점 코드</th>
			<th>지점 명</th>
			<th>총 매출액</th>
		</tr>
		<c:forEach items="${shopSaleList }" var="pizzaVo">
			<tr>
				<th>${pizzaVo.scode }</th>
				<th>${pizzaVo.sname }</th>
				<th>
					<fmt:formatNumber value="${pizzaVo.sumcost }" pattern="#,###"/>원<!-- jsp에서 천자리에 " , " 찍어주기 -->
				</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>