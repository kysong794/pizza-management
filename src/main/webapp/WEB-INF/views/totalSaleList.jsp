<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통합 매출 조회</title>
</head>
<body>

	<br>
	<div class="jumbotron jumbotron-fluid">
	  <div class="container">
	    <h1 class="display-4">통합 매출 조회</h1>
	  </div>
	</div>
	
	<form id="form" action="/pizza/delete" method="post">	
		<table class="table">
			<tr>
				<th>매출전표번호  </th>
				<th>지점  </th>
				<th>판매일자  </th>
				<th>피자코드  </th>
				<th>피자명  </th>
				<th>판매수량  </th>
				<th>매출액  </th>
				<th> </th>
				<th> </th>
			</tr>
			<c:forEach items="${totalSaleList}" var="pizzaVo">
				<tr>
					<th>${pizzaVo.saleno }</th>
					<th>${pizzaVo.scode }-${pizzaVo.sname }</th>
					<!-- Vo에 타입이 Date 일때 jsp에서 가공할때. //pattern이 앞 value가 뒤에 와야한다 안그럼 오류남-->
					<th>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${pizzaVo.saledate }"/>
					</th>
					<th>${pizzaVo.pcode }</th>
					<th>${pizzaVo.pname }</th>
					<th>${pizzaVo.amount }</th>
					<th>
						<fmt:formatNumber value="${pizzaVo.sumcost }" pattern="#,###"/>원
					</th>
					<th>
						<a class="btn btn-info" href="/pizza/update?saleno=${pizzaVo.saleno}">수정</a>
					</th>
					<th>
						<%-- <input type="hidden" name="saleno" value="${ pizzaVo.saleno }"/> --%>
						<button class="btn btn-secondary" type="button"
								onclick="javascript:deletePizza(${ pizzaVo.saleno })">삭제</button>
					</th>
	
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="saleno" />
	</form>

<script>
	/*
		Library(도서관)
		내가 원하는 기능이 있으면 이를 참조해서 사용할 수 있도록 한 코드(들)
		ex) 제이쿼리는 자바스크립트의 라이브러리입니다(O)
	*/
	/*  jQuery 자바스크립트의 라이브러리(javascript사용을 도와줌 (확장팩같은 느낌))
		$() -> jQuery (선택자 : DOM엘리먼트(태그)를 선택할 때 도와주는 기능)
		선택하는 방법 여러가지가 있음.
		1. 태그 이름으로 (태그의 정보를 전부 가지고) 오는 방법
			<input/> -> $('input')
		2. 이름(name 애트리뷰트)로 가지고 오는 방법 -> 태그 이름을 반드시 적어 주어야 함. 대괄호 안에 애트리뷰트 이름으로 상세히 선택 가능
			<input name="username"/> -> $('input[name=username]')
		3. 클래스 이름으로 가지고 오는 방법(*) -> 마침표 '.'를 클래스 이름 앞에 붙여서 가지고 올 수 있음
			<input class="btn btn-primary"/> -> $('.btn'), $('.btn-primary') $('.btn btn-primary')
		4. id 속성으로 가지고 오는 방법(*) -> 샾 기호 '#'를 id값 앞에 붙여서 가지고 올 수 있음
			<input id="username"/> -> $('#username')
			
		범위 크기  1 -> 2 -> 3 -> 4
		1번이 가장 큰 범위를 가지고있음
		4번은 id 값이기때문에 딱 1개만 가져올수있음
	*/

	// 매출 정보 삭제 버튼
	// pk saleno를 받아서 form 안의 input 태그의 value에 saleno를 넣어준다 ->
	// form을 제출
	function deletePizza(saleno) {
		var result = confirm('정말로 삭제 하시겠습니까?');

		if(result==true){
			// input 태그의 value에 saleno를 세팅
			$("input[name=saleno]").val(saleno);
			// 동기
			$("#form").submit();
			
		}else{
			
		}
		// 비동기
		// $.ajax({...});
	}
</script>
	
</body>
</html>