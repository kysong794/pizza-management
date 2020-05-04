<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매출 전표 수정</title>
</head>
	<body>
		
		<br>
		<div class="jumbotron jumbotron-fluid">
		  <div class="container">
		    <h1 class="display-4">매출 전표 수정</h1>
		  </div>
		</div>
		
		<c:if test="${ errorMessage ne null }">${ errorMessage }</c:if>

		<form id="form" action="/pizza/update" method="post">
		
			<div class="form-group">
				<label>매출전표번호 :</label>
				<input type="text" name ="saleno" value="${sale.saleno}" class="form-control col-1" readonly/>
			</div>
			<div  class="form-group">
				<label>지점코드 :</label>
				<select id="scode" name="scode" class="form-control col-3"> 
					<option value="">지점 선택</option>
					<c:forEach items="${scodelist}" var="scode">
						<c:if test="${ sale.scode eq scode.scode }">
							<option selected value="${scode.scode}">${scode.sname}</option>
						</c:if>
						<c:if test="${ sale.scode ne scode.scode }">
							<option value="${scode.scode}">${scode.sname}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>피자일자 :</label>
				<input type="date" name ="saledate" class="form-control col-3" value="${ sale.formatDate() }"/>
				<%-- <input type="date" name ="saledate" value='<fmt:formatDate value="${sale.saledate}" pattern="yyyy-MM-dd"/>'/> --%>
			</div> 
			<div class="form-group">
				<label>피자코드 :</label>
				<select id="pcode" name="pcode" class="form-control col-3">
					<option>피자선택</option>
					<c:forEach items="${pcodelist}" var="pcode">
						<c:if test="${ pcode.pcode eq sale.pcode }">
							<option selected value="${ pcode.pcode }">${pcode.pname }</option>
						</c:if>
						<c:if test="${pcode.pcode ne sale.pcode }">
							<option value="${ pcode.pcode }">${pcode.pname }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>판매수량 :</label>
				<input type="number" name ="amount" class="form-control col-3" value="${sale.amount }" min="0"/>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="수정"/>
				<input type="reset" class="btn btn-secondary" value="다시쓰기"/>
			</div>	
		</form>
	</body>
</html>