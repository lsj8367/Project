let todayDate = new Date(); //오늘 날짜 todayDate
var todayYear = todayDate.getFullYear();
var todayMonth = todayDate.getMonth() + 1; //이번 월	
var countYear = 0;

$("#next").click(function(){
	nextCalendar(place);
})

$("#prev").click(function(){
	prevCalendar(place);
})

function prevCalendar(place) {//이전 달력
    //정확하게 1년전까지만
    if (countYear <= 11) { //여기는 동적으로 바꿔야함
    	todayDate = new Date(todayDate.getFullYear(), todayDate.getMonth() - 1, todayDate.getDate());
    	PastWeather(place);
    	countYear++;    		
    }else{
        alert("1년 단위만 보관합니다.");
    }
}

//다음 달력을 오늘을 저장하고 달력에 뿌려줌
function nextCalendar(place) {
    if (todayYear === todayDate.getFullYear() && todayMonth === todayDate.getMonth() + 1) { //조회하려고 하는 달이 이번년,달 일 경우
        todayDate = new Date();
        alert(`현재 ${todayDate.getMonth() + 1}월 이후로는 조회할 수 없습니다.`);
        countYear = 0;
    } else {
        todayDate = new Date(todayDate.getFullYear(), todayDate.getMonth() + 1, todayDate.getDate());
        PastWeather(place);
        countYear -= 1;
    }
}

function makeCalendar(data) {
//		alert(JSON.stringify(data));
	if(data.getPastDayAccu.length ==null || data.getPastDayAccu.length == 0){
		alert("조회하신 달의 데이터가 없습니다");
		return false;
	}
    var nMonth = new Date(todayDate.getFullYear(), todayDate.getMonth(), 1);// 이번달의 첫번째날
    if (todayYear === todayDate.getFullYear() && todayMonth === todayDate.getMonth() + 1) {//조회하려고 하는 달이 이번년,이번달인 경우
        var lastDate = new Date(); //오늘을 lastDate로 설정
    } else {
        var lastDate = new Date(todayDate.getFullYear(), todayDate.getMonth() + 1, 0);//이번달의 마지막날
    }

    var tblCalendar = document.getElementById("calendar");    //달력
    var tblCalendarYM = document.getElementById("calendarYM"); ///XXXX년도XX월 출력
    tblCalendarYM.innerHTML = todayDate.getFullYear() + "년" + (todayDate.getMonth() + 1) + "월";
    

    //기존에 테이블값 초기화
    while (tblCalendar.rows.length > 2) {
        tblCalendar.deleteRow(tblCalendar.rows.length - 1);
    }
    var row = null;
    row = tblCalendar.insertRow();
    var cnt = 0;
    
    // 1일이 시작되는 칸을 맞추어줌
    for (i = 0; i < nMonth.getDay(); i++) {
        cell = row.insertCell();
        cnt = cnt + 1;
    }
    
    //달력 출력
    var arr = new Array();
    
    for(var j = 0; j<data.getPastDayAccu.length; j++){
    	arr.push(data.getPastDayAccu[j].dd);
    }
    
    for (i = 1; i <= lastDate.getDate(); i++) {
        cell = row.insertCell();
        
        if(arr.indexOf(i) != -1){
        	
        	cell.innerHTML = i + `<br><img src="/resources/img/${data.getPastDayAccu[arr.indexOf(i)].status}.png"/>` +'<br>'+ data.getPastDayAccu[arr.indexOf(i)].maxTemp +'<br>'+ data.getPastDayAccu[arr.indexOf(i)].minTemp; +'<br>'
        }else{
        	cell.innerHTML = i + '<br><br>-';
        
        }

        cnt = cnt + 1;
        if (cnt % 7 == 0)    //1주=7일
            row = calendar.insertRow();

    }
}
function PastWeather(place){
	$.ajax({
		type: 'POST',
		url: `/PastWeather/${place}`,
		data:JSON.stringify({
			shortInitDt : moment(todayDate).format('YYYYMM'),
		}),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		async: false,
		success: function(data){
			makeCalendar(data);
		},
		error : function(){
			console.error('Calendar error');
		}
	})
}

function reMakeCal(){
	$("#calendar").load(window.location.href + '#calendar');
}

