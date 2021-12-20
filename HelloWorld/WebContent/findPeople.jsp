<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사람 찾기</title>
<!-- 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<script src="./js/find_people.js"></script>
<!-- <script>
	$(function(){
		$("#divSearchRet").hide();	
	});
</script> -->
<style>
/* input 크기, 폰트 지정 */
.input {
	width: 250px;
	height: 30px;
	text-align: center;
	font-family: 'Nanum Myeongjo', serif;
}

/* 전체 div relative 설정 */
.center {
	position: relative;
}

/* 버튼류 설정값 */
.button {
	width: 100px;
	height: 35px;
	background-color: #f8585b;
	border: none;
	color: #fff;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	cursor: pointer;
	font-family: 'Poor Story', cursive;
}

.center>div {
	left: 50%;
	transform: translate(-50%);
	position: absolute;
}

.label {
	font-family: 'Poor Story', cursive;
	margin-bottom: 5px;
	font-size: 1.3em;
}

#mfont {
	font-family: 'Poor Story', cursive;
	font-size: 3em;
}

#validation {
	position: relative;;
	top: 300px;
}

/* 검색결과 테이블 관련 */

.hide {
	visibility: hide;
}

table {
	font-family: 'Poor Story', cursive;
}
#align {  /* 버튼을 감싸고 있는 p */
		position: relative;
		left: -25px;
		margin-top: 40px;
}


</style>
</head>
<body>
	<div class="center">
		<div>
			<h1 id="mfont">사람 찾기</h1>
			<form name="frm" method="post">
				<p class="label">닉네임으로 검색</p>
				<input type="text" name="nickname" class="input" placeholder="한글, 영어(대/소), 숫자 가능 (2~10)"> 
				<input type="hidden" name="type" value="findPeople">
				<p id="align">
				<input type="button" value="검색" class="button" onclick="findPeople_send()"> 
				<input type="reset" value="초기화" class="button">
				<input type="button" value="뒤로가기" class="button" onclick="history.back()">
				</p>
			</form>

			<div id="divSearchRet">
				<hr>
				<table>
					<thead>
						<tr>
							<th>닉네임</th>
							<th>이름</th>
							<th>생년월일</th>
							<th>성별</th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
	$("#divSearchRet").hide();
</script>
</html>