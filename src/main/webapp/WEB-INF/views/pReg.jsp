<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매출 전표 등록</title>
</head>
<body>

	<br>
	<div class="jumbotron jumbotron-fluid">
	  <div class="container">
	    <h1 class="display-4">매출 전표 등록</h1>
	  </div>
	</div>
	
	<c:if test="${ errorMessage ne null }">${ errorMessage }</c:if>
	
	<!-- 모델
		class PRegSaveModel {
			@NotNull(message = "cannot be null")
			@Length(max = 6, message = "길이는 6자리를 넘길 수 없습니다.")
			private String saleno;
			
			private String scode;
			private String pcode;
			private Date saledate;
			private int amount;
		}
	 -->
			
	<form id="form" action="/pizza/pRegSave" method="post">
	
		<div class="form-group">
			<label >매출전표번호 :</label>
			<input type="text" name ="saleno" value="${saleno}" class="form-control col-1" readonly/>
		</div>
		<div class="form-group">
			<label >지점코드 :</label>
			<select id="scode" name="scode" class="form-control col-3"> <!-- id는 select라는 태그의 고유식별코드가 되는것 //name은 다른곳에서 쓸때의 값을 담음-->
				<option value="" selected>지점 선택</option>
				<c:forEach items="${scodelist}" var="pizzaVo"><!-- items = 반복가능한객체를 가져옴 // var=그 객체중의 1개를 넣어줌 -->
					<option value="${pizzaVo.scode }">${pizzaVo.sname }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label>피자 판매 일자 :</label>
			<input id="saledate" type="date" name ="saledate" class="form-control col-3"/>
		</div> 
		<div class="form-group"> 
			<label>피자코드 :</label>
			<select id="pcode" name="pcode" class="form-control col-3">
				<option value="" selected>피자선택</option>
				<c:forEach items="${pcodelist}" var="pizzaVo" >
					<option value="${pizzaVo.pcode }">${pizzaVo.pname }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label>판매수량 :</label>
			<input id="amount" type="number" name ="amount" class="form-control col-3" min="1"/>
		</div>
		<div class="form-group">
			<input type="button" id="submitForm" class="btn btn-primary" value="등록"/>
			<input type="reset" class="btn btn-secondary" value="다시쓰기"/>
		</div>
	</form>

	<!-- <script type="text/javascript">
		$("form").on("submit", function(e) {
			e.preventDefault();
			const saledate = $("input[name=saledate]").val();
		});
	</script> -->
	<script type="text/javascript">
 		$("#submitForm").on("click", function(event) {
			
			if(!$("select[name=scode]").val()){
				alert('지점을 선택해 주세요');
				return;
			}
 			
 			var saledate = $("#saledate").val();
			if(saledate === undefined || saledate === ""){
				alert('날짜를 선택해 주세요');
				return;
			}
 			
			var pcode = $("select[name=pcode]").val();
			if(pcode === undefined || pcode === ""){
				alert('피자를 선택해 주세요');
				return;
			}
			
			var amount = $("#amount").val();
			if(amount === undefined || amount === "" || amount <= 0) {
				alert('판매수량을 입력해 주세요.');
				return; // 이 함수를 종료한다.
			}

			// 검사완료 후, 제출
			$("#form").submit();
		}); 
		
/* 		function check() {
			var amount = $("#amount").val();
			if(amount === undefined || amount === "" || amount <= 0){
				alert('판매수량을 입력해 주세요.');
				return false;
			}
			var pcode = $("select[name=pcode]").val();
			if(pcode === undefined || pcode === "" ){
				alert('피자를 선택해 주세요');
				return false;
			}
			return true;
		} */
	</script> 
	
</body>
</html>