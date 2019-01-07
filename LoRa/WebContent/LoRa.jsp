<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<script src="http://maps.googleapis.com/maps/api/js"></script>
<% 
	Double lat=Double.parseDouble(request.getParameter("Latitude"));
	Double log=Double.parseDouble(request.getParameter("Longitude"));
	System.out.println(lat);
	System.out.println(log);
%>

<script>

function initialize() {

  var LatLng = new google.maps.LatLng(<%=lat%>, <%=log%>);
  
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

</head>

<body>

<div id="googleMap" style="width:500px;height:380px;"></div>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC6nuIpyM98yrp10bQgE5hsHokwsMTs-Ww&callback=initMap">
    </script>
</body>

</html>
