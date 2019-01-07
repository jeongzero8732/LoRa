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


<script src="http://maps.googleapis.com/maps/api/js"></script>
<%-- <% 
	Double lat=Double.parseDouble(request.getParameter("Latitude"));
	Double log=Double.parseDouble(request.getParameter("Longitude"));
	System.out.println(lat);
	System.out.println(log);
%> --%>

<script style="margin-left:200px;">

function initialize() {

  var LatLng = new google.maps.LatLng(37.5033089, 126.74576159999992);
  
  var mapProp = {

    center: LatLng, // 위치

    zoom:17, // 어느정도까지 세세하게 볼 것인지.

    mapTypeId:google.maps.MapTypeId.ROADMAP

  };

  var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);

  var marker = new google.maps.Marker({

	position: LatLng,

	map: map,

  });

}

google.maps.event.addDomListener(window, 'load', initialize);

</script>
<div id="googleMap" style="width:500px;height:380px;margin-left:360px;"></div>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC6nuIpyM98yrp10bQgE5hsHokwsMTs-Ww&callback=initMap">
    </script>
</body>
</html>