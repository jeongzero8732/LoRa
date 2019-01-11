function initialize() {



  var LatLng = new google.maps.LatLng(37.506736, 126.783362);

  var mapProp = {

    center: LatLng, // 위치

    zoom:18, // 어느정도까지 세세하게 볼 것인지.

    mapTypeId:google.maps.MapTypeId.ROADMAP

  };

  var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);


  var marker = new google.maps.Marker({

	position: LatLng,

	map: map,

	

  });

}

google.maps.event.addDomListener(window, 'load', initialize);
