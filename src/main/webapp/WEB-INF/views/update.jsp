<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
	<form id="form" action="/pizza/update" method="post">	
	
		<div>
			<label>매출전표번호 :</label>
			<input type="text" name ="saleno" value="${saleno}" readonly/>
		</div>
		<div>
			<label>지점코드 :</label>
			<select id="scode" name="scode"> 
				<option selected>지점 선택</option>
				<c:forEach items="${scodelist}" var="pizzaVo">
					<option value="${pizzaVo.scode }">${pizzaVo.sname }</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label>피자일자 :</label>
			<input type="date" name ="saledate"/>
		</div> 
		<div>
			<label>피자코드 :</label>
			<select id="pcode" name="pcode">
				<option selected>피자선택</option>
				<c:forEach items="${pcodelist}" var="pizzaVo">
					<option value="${pizzaVo.pcode }">${pizzaVo.pname }</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label>판매수량 :</label>
			<input type="number" name ="amount"/>
		</div>
		<div>
			<input type="submit" value="수정"/>
			<input type="reset" value="다시쓰기"/>
		</div>	
	</form>
	
</body>
</html>