<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div style="background-color:gray; padding:20px; text-align:center;">
	<H1 style="padding-left:230px;">안전지킴이</H1>
</div>
<form action="/LoRa/LoRa.jsp" id="LoRa">
<!-- 위도 : <input name="Latitude" type="text"><br><br>
경도 : <input name="Longitude" type="text"> <input type="submit" value="위치확인"><br><br> -->
	<div style="margin-left:450px;">
	<div style="float:left;display:inline;">
		<button style="padding-right:30px;padding-left:30px;">위치확인</button>
	</div>
	<div style="float:left;display:inline;">
		<button style="padding-right:30px;padding-left:30px;">운동량</button>
	</div><div style="float:left;display:inline;">
		<button style="padding-right:30px;padding-left:30px;">심박수</button>
	</div>
	</div>
<!-- 운동량 : <input name="excersize" type="text"><br><br>
심박수: <input name="purse" type="text"><br><br> -->
</form>
<br><br><br>
<div style="margin-left:540px;">
측정된 심박수 <br>
<input type="text" value="82"><br><br><br>

정상 심박수 범위<br>
<input type="text" value="40~100bpm"><br><br>

</div>
</body>
</html>