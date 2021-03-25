var options = {
	enableHighAccuracy : true,
	timeout : 5000,
	maximumAge : 0
};

function success(pos) {
	var crd = pos.coords;

	lat = crd.latitude; //위도
	lon = crd.longitude; //경도
	$.ajax({
		url : 'https://dapi.kakao.com/v2/local/geo/coord2address.json?x=' + lon +'&y=' + lat,
		type : 'GET',
		headers : {
			'Authorization' : 'KakaoAK eb7e41ee69dbef8df110511fbcbf68c6'
		},
		success : function(data) {
			exTest = data.documents[0].address.address_name;
			if( document.cookie == null || document.cookie == "" ){
				
				$('#selist').empty();
				var def = `<option value='${exTest}' selected='selected'>${exTest}</option>`;
				$('#selist').append(def);
				refreshAll();
			}else{
				refreshAll();
			}
		},
		error : function(e) {
			console.error(e);
		}
	});
};

function error(err) {
	console.warn('ERROR(' + err.code + '): ' + err.message);
};

$("#btnTest").click(function() {
	navigator.geolocation.getCurrentPosition(success, error, options);
})
