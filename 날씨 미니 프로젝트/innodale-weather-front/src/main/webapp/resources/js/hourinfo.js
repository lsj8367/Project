const hour = new Date();
const hours = hour.getHours();

//현재시간 데이터
function hourInfo(data){
	
	var temp = `${data.list[0].temp}`;
	var bodyTemp = `${data.list[0].bodyTemp}`;
	var rain = `${data.list[0].rain}`;
	var status = `${data.list2.status}`;
	var info = "<ul>";
	info += "<li class=\"ht\">"+hours+"시" +"</li>";
	info += `<li class="hl status"><img src="/resources/img/`+status+`.png"/></li>`;
	info += "<li class=\"hl\">"+"기온 : "+temp+"°C"+"</li>";
	info += "<li class=\"hl\">"+"체감온도 : "+bodyTemp+"°C"+"</li>";
	if(rain !=0){
		info += "<li class=\"hl\">"+"강수량 : <b style='color:blue;'>" +rain+"</b><small>mm</small></li>";
	} else {
		info += "<li class=\"hl\">"+"강수량 : "+"<b style='color:blue;'>-</b></li>";
	}
	info+"</ul>";
	
	$("#obsWeather").append(info);
}

//현재시간+1시간 데이터
function hourInfo1(data){
	hourClear();
	var temp = `${data[0].temp1h}`;
	var bodyTemp = `${data[0].bodytemp1h}`;
	var rain = `${data[0].rain1h}`;
	var status =`${data[0].status}`;
	var info = "<ul>";
	var br = "<br>";
	info += "<li class=\"ht\">"+(hours+1)+"시" +"</li>";
	info += `<li class="hl status"><img src="/resources/img/`+status+`.png"/></li>`;
	info += "<li class=\"hl\">"+"기온 : "+temp+"°C"+"</li>";
	info += "<li class=\"hl\">"+"체감온도 : "+bodyTemp+"°C"+"</li>";
	if(rain !=0){
		info += "<li class=\"hl\">"+"강수량 : <b style='color:blue;'>" +rain+"</b><small>mm</small></li>";
	} else {
		info += "<li class=\"hl\">"+"강수량 : "+"<b style='color:blue;'>-</b></li>";
	}
	info+="</ul>";
	
	$("#shortWeather1H").append(info);
}
//현재시간+2시간 데이터
function hourInfo2(data){
	var temp = `${data[1].temp1h}`;
	var bodyTemp = `${data[1].bodytemp1h}`;
	var rain = `${data[1].rain1h}`;
	var status =`${data[1].status}`;
	var info = "<ul>";
	var br = "<br>";
	info += "<li class=\"ht\">"+(hours+2)+"시" + br;
	info += `<li class="hl status"><img src="/resources/img/`+status+`.png"/></li>`;
	info += "<li class=\"hl\">"+"기온 : "+temp+"°C"+"</li>";
	info += "<li class=\"hl\">"+"체감온도 : "+bodyTemp+"°C"+"</li>";
	if(rain !=0){
		info += "<li class=\"hl\">"+"강수량 : <b style='color:blue;'>" +rain+"</b><small>mm</small></li>";
	} else {
		info += "<li class=\"hl\">"+"강수량 : "+"<b style='color:blue;'>-</b></li>";
	}
	info+="</ul>";
	
	$("#shortWeather2H").append(info);
}
//현재시간+3시간 데이터
function hourInfo3(data){
	var temp = `${data[2].temp1h}`;
	var bodyTemp = `${data[2].bodytemp1h}`;
	var rain = `${data[2].rain1h}`;
	var status =`${data[2].status}`;
	var info = "<ul>";
	var br = "<br>";
	info += "<li class=\"ht\">"+ (hours +3)+"시" + "</li>";
	info += `<li class="hl status"><img src="/resources/img/`+status+`.png"/></li>`;
	info += "<li class=\"hl\">"+"기온 : "+temp+"°C"+"</li>";
	info += "<li class=\"hl\">"+"체감온도 : "+bodyTemp+"°C"+ "</li>";
	if(rain !=0){
		info += "<li class=\"hl\">"+"강수량 : <b style='color:blue;'>" +rain+"</b><small>mm</small></li>";
	} else {
		info += "<li class=\"hl\">"+"강수량 : "+"<b style='color:blue;'>-</b></li>";
	}
	info +"</ul>";
	
	$("#shortWeather3H").append(info);
}

function hourClear(){
	$("#shortWeather1H").empty();
	$("#shortWeather2H").empty();
	$("#shortWeather3H").empty();
	$("#obsWeather").empty();
}