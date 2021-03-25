// 초기 실행을 geoLocation에서 시작
function refreshAll(){
	selist();
	reLong3H(place); //3시간만 먼저 로딩해서 보여줌
	reMarker();
	reShort(place);
	reObsWeather(place);
	PastWeather(place);
}
function selist(){
	place = $("#selist").val();
}
var titleList = ['시간','날씨', '체감온도(°C)', '강수량(mm)', '강수확률(%)', '습도(%)', '풍향', '풍속(m/s)'];

function reObsWeather(place){ //현재시간 날씨 새로고침
	$.ajax({
		url: `/ObsWeather/${place}`,
		type: "GET",
		dataType: "json",
		async: false,
		success: function(data){
			$("#accuHighLow").empty();
			$("#accuRain").empty();
			$("#accuHighLow").append(data.getTodayAccu.minTemp + " / " + data.getTodayAccu.maxTemp + "&nbsp;&nbsp;");
			if(data.getTodayAccu.sumRain === 0){
				ss = '<b style="color: blue;">-</b>';
			}else{
				ss = `<b style="color: blue;">${data.getTodayAccu.sumRain}</b>`;
			}
			$("#accuRain").append(ss);
			
			
			var data = data;
			hourInfo(data);
		},
		error: function(){
			console.error("reObsWeather error");
		}
	});
};

function reShort(place){ //1시간 날씨 새로고침
	$.ajax({
		url: `/ShortForecast/${place}`,
		type: "GET",
		dataType: "json",
		async: false,
		success: function(data){
			hourInfo1(data);
			hourInfo2(data);
			hourInfo3(data);
		},
		error: function(){
			console.error("reShort error");
		}
	});
};

function Wind(windStr){
	if(windStr == 'N'){
		windStr = '북';
	}else if (windStr == 'NNE') {
		windStr = '북북동';
	}else if (windStr == 'NE') {
		windStr = '북북동';
	}else if (windStr == 'ENE') {
		windStr = '동북동';
	}else if (windStr == 'E') {
		windStr = '동';
	}else if (windStr == 'ESE') {
		windStr = '동남동';
	}else if (windStr == 'SE') {
		windStr = '남동';
	}else if (windStr == 'SSE') {
		windStr = '남남동';
	}else if (windStr == 'S') {
		windStr = '남';
	}else if (windStr == 'SSW') {
		windStr = '남남서';
	}else if (windStr == 'SW') {
		windStr = '남서';
	}else if (windStr == 'WSW') {
		windStr = '서남서';
	}else if (windStr == 'W') {
		windStr = '서';
	}else if (windStr == 'WNW') {
		windStr = '서북서';
	}else if (windStr == 'NW') {
		windStr = '북서';
	}else if (windStr == 'NNW') {
		windStr = '북북서';
	}
	return windStr;
}


function reLong3H(place){ //3시간 날씨 새로고침
	$.ajax({
		url: `/LongForecast3H/${place}`,
		type: "GET",
		dataType: "json",
		async: false,
		success: function(data){
			list3H = [];
			categories = [];
			var st1;
			for(var i = 0; i < data.list.length; i++){
				list3H.push(data.list[i].temp3h);
			}
			for(var i = 0; i < data.date.length; i++){
				st1 = data.date[i].substr(8, data.date.length) + "시";
				categories.push(st1);
			}
			a(list3H, '3시간', categories); // 로드됐을때 3시간 보여줌
			var ylay = data.list.length; //20칸
			
			var lfTable = "<ul class='innerLft'>";
			for(i = 0; i < titleList.length; i++){
				lfTable += "<li>" + titleList[i] +"</li>";
			}
			lfTable += "</ul>";
			
			for(i = 0; i < ylay; i++){
				lfTable += "<ul class='innerLft3H'>";
				lfTable += "<li>" + data.date[i].substr(8, data.date.length) + "시" + "</li>"; //시간
				lfTable += "<li>" + `<img src="/resources/img/${data.list[i].status}.png">` +"</li>"; //날씨
				lfTable += "<li>" + `${data.list[i].temp3h}` +"</li>"; //기온
				lfTable += "<li style='color: blue;'>" + `${data.list[i].rain3h == 0 ? '-' : `${data.list[i].rain3h}`}` +"</li>"; //강수량
				lfTable += "<li>" + `${data.list[i].rainProb3h == 0 ? '-' : `${data.list[i].rainProb3h}`}` +"</li>"; //강수확률
				lfTable += "<li>" + `${data.list[i].hum3h}`+"</li>"; // 습도
				lfTable += "<li>" + `${Wind(data.list[i].windStr)}`+"</li>"; // 풍향
				lfTable += "<li>" + `${data.list[i].windSpeed == 0 ? '-' : `${data.list[i].windSpeed}`}` +"</li>"; // 풍속
				lfTable += "</ul>";				
			}
			$("#lft").empty();
			$("#lft").append(lfTable);
		},
		error: function(){
			console.error("reLong3H error");
		}
	});
};

function reLong6H(place){ //6시간 날씨 새로고침
	$.ajax({
		url: `/LongForecast6H/${place}`,
		type: "GET",
		dataType: "json",
		async: false,
		success: function(data){
			list6H = [];
			categories = [];
			var st1;
			for(var i = 0; i < data.list.length; i++){
				list6H.push(data.list[i].temp6h);
			}
			for(var i = 0; i < data.date.length; i++){
				st1 = data.date[i].substr(8, data.date.length) + "시";
				categories.push(st1);
			}
			a(list6H, time, categories);
			var ylay = data.list.length; //20칸
			
			var lfTable = "<ul class='innerLft'>";
			for(i = 0; i < titleList.length; i++){
				lfTable += "<li>" + titleList[i] +"</li>";
			}
			lfTable += "</ul>";
			
			for(i = 0; i < ylay; i++){
				lfTable += "<ul class='innerLft6H'>";
				lfTable += "<li>" + data.date[i].substr(8, data.date.length) + "시" + "</li>"; //시간
				lfTable += "<li>" + `<img src="/resources/img/${data.list[i].status}.png">` +"</li>"; //날씨
				lfTable += "<li>" + `${data.list[i].temp6h}` +"</li>"; //기온
				lfTable += "<li style='color: blue;'>" + `${data.list[i].rain6h == 0 ? '-' : `${data.list[i].rain6h}`}` +"</li>"; //강수량
				lfTable += "<li>" + `${data.list[i].rainProb6h == 0 ? '-' : `${data.list[i].rainProb6h}`}` +"</li>"; //강수확률
				lfTable += "<li>" + `${data.list[i].hum6h}`+"</li>"; // 습도
				lfTable += "<li>" + `${Wind(data.list[i].windStr)}`+"</li>"; // 풍향
				lfTable += "<li>" + `${data.list[i].windSpeed == 0 ? '-' : `${data.list[i].windSpeed}`}` +"</li>"; // 풍속
				lfTable += "</ul>";				
			}
			
			$("#lft").append(lfTable);
		},
		error: function(){
			console.error("reLong6H Error");
		}
	});
};

function reLong12H(place){ //12시간 날씨 새로고침
	$.ajax({
		url: `/LongForecast12H/${place}`,
		type: "GET",
		dataType: "json",
		async: false,
		success: function(data){
			list12H = [];
			categories = [];
			var st1;
			for(var i = 0; i < data.list.length; i++){
				list12H.push(data.list[i].temp12h);
			}
			for(var i = 0; i < data.date.length; i++){
				st1 = data.date[i].substr(8, data.date.length) + "시";
				categories.push(st1);
			}
			a(list12H, time, categories);
			var ylay = data.list.length; //20칸
			
			var lfTable = "<ul class='innerLft'>";
			for(i = 0; i < titleList.length; i++){
				lfTable += "<li>" + titleList[i] +"</li>";
			}
			lfTable += "</ul>";
			
			for(i = 0; i < ylay; i++){
				lfTable += "<ul class='innerLft12H'>";
				lfTable += "<li>" + data.date[i].substr(8, data.date.length) + "시" + "</li>"; //시간
				lfTable += "<li>" + `<img src="/resources/img/${data.list[i].status}.png">` +"</li>"; //날씨
				lfTable += "<li>" + `${data.list[i].temp12h}` +"</li>"; //기온
				lfTable += "<li style='color: blue;'>" + `${data.list[i].rain12h == 0 ? '-' : `${data.list[i].rain12h}`}` +"</li>"; //강수량
				lfTable += "<li>" + `${data.list[i].rainProb12h == 0 ? '-' : `${data.list[i].rainProb12h}`}` +"</li>"; //강수확률
				lfTable += "<li>" + `${data.list[i].hum12h}`+"</li>"; // 습도
				lfTable += "<li>" + `${Wind(data.list[i].windStr)}`+"</li>"; // 풍향
				lfTable += "<li>" + `${data.list[i].windSpeed == 0 ? '-' : `${data.list[i].windSpeed}`}` +"</li>"; // 풍속
				lfTable += "</ul>";				
			}
			
			$("#lft").append(lfTable);
		},
		error: function(){
			console.error("reLong12H error");
		}
	});
};

function reLong24H(place){ //24시간 날씨 새로고침
	$.ajax({
		url: `/LongForecast24H/${place}`,
		type: "GET",
		dataType: "json",
		async: false,
		success: function(data){
			list24H = [];
			categories = [];
			var st1;
			for(var i = 0; i < data.list.length; i++){
				list24H.push(data.list[i].temp24h);
			}
			for(var i = 0; i < data.date.length; i++){
				st1 = data.date[i].substr(6,2) + "일" + data.date[i].substr(8, data.date.length) + "시";
				categories.push(st1);
			}
			a(list24H, time, categories);
			var ylay = data.list.length; //20칸
			
			var lfTable = "<ul class='innerLft'>";
			for(i = 0; i < titleList.length; i++){
				lfTable += "<li>" + titleList[i] +"</li>";
			}
			lfTable += "</ul>";
			
			for(i = 0; i < ylay; i++){
				lfTable += "<ul class='innerLft24H'>";
				lfTable += "<li>" + data.date[i].substr(8, data.date.length) + "시" + "</li>"; //시간
				lfTable += "<li>" + `<img src="/resources/img/${data.list[i].status}.png">` +"</li>"; //날씨
				lfTable += "<li>" + `${data.list[i].temp24h}°C` +"</li>"; //기온
				lfTable += "<li style='color: blue;'>" + `${data.list[i].rain24h == 0 ? '-' : `${data.list[i].rain24h}`}` +"</li>"; //강수량
				lfTable += "<li>" + `${data.list[i].rainProb24h == 0 ? '-' : `${data.list[i].rainProb24h}`}` +"</li>"; //강수확률
				lfTable += "<li>" + `${data.list[i].hum24h}`+"</li>"; // 습도
				lfTable += "<li>" + `${Wind(data.list[i].windStr)}`+"</li>"; // 풍향
				lfTable += "<li>" + `${data.list[i].windSpeed == 0 ? '-' : `${data.list[i].windSpeed}`}` +"</li>"; // 풍속
				lfTable += "</ul>";				
			}
			
			$("#lft").append(lfTable);

		},
		error: function(){
			console.error("reLong24H error");
		}
	});
};

function reMarker(){
	$.ajax({
		url: `/reMarker`,
		type: "GET",
		dataType: "json",
		async: false,
		success: function(data){
			var data = data;
			removeAllMarker(); //지도 마커 다 지우기
			addMarkerLayer(data); //지도에 마커 다시 표시하기
		},
		error: function(){
			console.error("reMarker error");
		}
		
	});
}

