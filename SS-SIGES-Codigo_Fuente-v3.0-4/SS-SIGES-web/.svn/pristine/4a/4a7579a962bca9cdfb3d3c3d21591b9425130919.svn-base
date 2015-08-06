

function initialize2(lat, lng, titulo, texto) {

    var mapOptions;
    if (lat == 0 || lng == 0) {
        mapOptions = {
            zoom: 6,
            center: new google.maps.LatLng(-32.376537, -56.192591)
        };
    } else {
        mapOptions = {
            zoom: 7,
            center: new google.maps.LatLng(lat, lng)
        };
    }



    var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);


    google.maps.event.addListener(map, 'click', function (e) {
        changeMarkerPosition(e.latLng, map, marker);

    });

    var myLatlng = new google.maps.LatLng(lat, lng);
    var contentString = texto;

    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });

    var marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title: titulo
    });

    var latitud = document.getElementById("frmFichaMapaUbicaPopup:proyLatAux_input");
    latitud.setAttribute("value", lat);
    var longitud = document.getElementById("frmFichaMapaUbicaPopup:proyLngAux_input");
    longitud.setAttribute("value", lng);
    //invoca geocoder para obtener departamento

    if (lat != 0 && lng != 0) {
        geocoder = new google.maps.Geocoder();
        geocoder.geocode({'latLng': myLatlng}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                for (var i in results[0].address_components) {
                    if (results[0].address_components[i].types && "administrative_area_level_1" === results[0].address_components[i].types[0]) {
                        var deptoInput = document.getElementById("frmFichaMapaUbicaPopup:deptoAux_input");
                        deptoInput.setAttribute("value", results[0].address_components[i].short_name);

                    }
                }
            } else {
                alert("No se pudo obtener acceso a Google - Geocoder: " + status);
            }
        });
    }

}



function placeMarker(position, map) {
    var marker = new google.maps.Marker({
        position: position,
        map: map
    });
    map.panTo(position);
}

function changeMarkerPosition(position, map, marker) {

    marker.setPosition(position);
    map.panTo(position);
    var lat = position.lat();
    var lng = position.lng();
    var latitud = document.getElementById("frmFichaMapaUbicaPopup:proyLatAux_input");
    latitud.setAttribute("value", lat);
    var longitud = document.getElementById("frmFichaMapaUbicaPopup:proyLngAux_input");
    longitud.setAttribute("value", lng);

    //invoca geocoder para obtener departamento
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'latLng': position}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            for (var i in results[0].address_components) {
                if (results[0].address_components[i].types && "administrative_area_level_1" === results[0].address_components[i].types[0]) {
                    var deptoInput = document.getElementById("frmFichaMapaUbicaPopup:deptoAux_input");
                    deptoInput.setAttribute("value", results[0].address_components[i].short_name);
                }
            }
        } else {
            alert("No se pudo obtener acceso a Google - Geocoder: " + status);
        }
    });
}





        