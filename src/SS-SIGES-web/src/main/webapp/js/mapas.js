

function initialize2(mapElem, latitudElem, longitudElem, deptoElem, lat, lng, titulo, texto) {

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

    map = new google.maps.Map(document.getElementById(mapElem), mapOptions);
    var myLatlng = new google.maps.LatLng(lat, lng);
    var contentString = texto;

    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });

    marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title: titulo
    });

    google.maps.event.addListener(map, 'click', function (e) {
        changeMarkerPosition(latitudElem, longitudElem, deptoElem, e.latLng, map, marker);

    });
}

function placeMarker(position, map) {
    var marker = new google.maps.Marker({
        position: position,
        map: map
    });
    map.panTo(position);
}


function obtenerDepartamentoByLatLong(latitud, longitud) {
    var myLatlng = new google.maps.LatLng(latitud.value, longitud.value);
    var latlng = {lat: parseFloat(latitud.value), lng: parseFloat(longitud.value)};

    //mueve el mapa a las coordenadas ingresadas
    marker.setPosition(myLatlng);
    map.panTo(myLatlng);

    //invoca geocoder para obtener departamento
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'latLng': latlng}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            for (var i in results[0].address_components) {
                if (results[0].address_components[i].types && "administrative_area_level_1" === results[0].address_components[i].types[0]) {
                    return results[0].address_components[i].short_name;
                }
            }
        } else {
            //alert("No se pudo obtener acceso a Google - Geocoder: " + status);
        }
    });

    return null;
}

function obtenerDepartamento(latitudElem, longitudElem, deptoElem) {
//    var latitud = document.getElementById("frmFichaMapaUbicaPopup:proyLatAux_input");
//    var longitud = document.getElementById("frmFichaMapaUbicaPopup:proyLngAux_input");
//    var deptoInput = document.getElementById("frmFichaMapaUbicaPopup:deptoAux_input");
    var latitud = document.getElementById(latitudElem);
    var longitud = document.getElementById(longitudElem);
    var deptoInput = document.getElementById(deptoElem);
    deptoValue = obtenerDepartamentoByLatLong(latitud, longitud);
    if (deptoValue != null) {
        deptoInput.setAttribute("value", deptoValue);
        deptoInput.value = deptoValue;
    }
    return true;
}

function changeMarkerPosition(latitudElem, longitudElem, deptoElem, position, map, marker) {
    marker.setPosition(position);
    map.panTo(position);
    var lat = position.lat();
    var lng = position.lng();
//  var latitud = document.getElementById("frmFichaMapaUbicaPopup:proyLatAux_input");
//  var longitud = document.getElementById("frmFichaMapaUbicaPopup:proyLngAux_input");
    var latitud = document.getElementById(latitudElem);
    var longitud = document.getElementById(longitudElem);
    var deptoInput = document.getElementById(deptoElem);
    latitud.setAttribute("value", lat);
    latitud.value = lat;
    longitud.setAttribute("value", lng);
    longitud.value = lng;

    //invoca geocoder para obtener departamento
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'latLng': position}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            for (var i in results[0].address_components) {
                if (results[0].address_components[i].types && "administrative_area_level_1" === results[0].address_components[i].types[0]) {
//                    var deptoInput = document.getElementById("frmFichaMapaUbicaPopup:deptoAux_input");
//                    var deptoInput = deptoElem;
                    deptoInput.setAttribute("value", results[0].address_components[i].short_name);
                    deptoInput.value = results[0].address_components[i].short_name;
                }
            }
        } else {
            //alert("No se pudo obtener acceso a Google - Geocoder: " + status);
        }
    });
}





        