<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품별 매출 현황</title>
</head>
<body>
	<table>
		<tr>
			<th>피자코드</th>
			<th>피자 명</th>
			<th>총 매출액</th>
		</tr>
		<c:forEach items="${pcodeSaleList }" var="pizzaVo">
			<tr>
				<th>${pizzaVo.pcode }</th>
				<th>${pizzaVo.pname }</th>
				<th>
					<fmt:formatNumber value="${pizzaVo.sumcost }" pattern="#,###"/>원<!-- jsp에서 천자리에 " , " 찍어주기 -->
				</th>
				<%-- <th>${pizzaVo.sumcost }원</th> <!-- 1천자리마다 " , " 찍어주기 DB에서 처리 했음 --> --%>
			</tr>	
		</c:forEach>	
	</table>

</body>
</html>