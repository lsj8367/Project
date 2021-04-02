var clockTarget = document.getElementById("clock");
var liveTime;
var futureTime;

// 서버 시작하자마자 딱 1번수행
const day = new Date();
const dayYear = day.getFullYear();
const dayMonth = day.getMonth() + 1;
const dayDate = day.getDate();
const dayHours = day.getHours();
var num = dayHours + 1;

//처음한번만 지금시간의 다음시간으로 할당
var nextDate = new Date(`${dayYear}/${dayMonth}/${dayDate} ${num}:30:00`); //다음시간 정각

function clock() {
	var live = new Date();
	// date Object를 받아오고
	var liveYear = live.getFullYear();
	// 년

	var liveMonth = live.getMonth();
	// 월 받음

	var liveDate = live.getDate();
	// 일 받음

	var liveDay = live.getDay();
	// 요일

	var liveWeek = [ '일', '월', '화', '수', '목', '금', '토' ];
	// 요일은 숫자형태라서 배열로 만들어놓음

	var liveHours = live.getHours();
	// 시간

	var liveMinutes = live.getMinutes();
	// 분

	nowTime = moment(live).format('YYYYMMDDHHmmss'); // 매 정각시간 비교			110000
	futureTime = moment(nextDate).format('YYYYMMDDHHmmss'); //현재시간 +1시간의 00시 110000;
	//현재시간에서 변수를 쓰기위해 여기서 refresh 실행
	if(nowTime === futureTime){
		refreshAll(); //전체 새로고침
		if(num === 24){
			num = 0; //00시로 초기화
			nextDate = new Date(`${liveYear}/${liveMonth}/${liveDate} 0${num}:30:00`);
		}else{
			nextDate = new Date(`${liveYear}/${liveMonth}/${liveDate} ${num < 10 ? `0${num}`:num}:30:00`);			
			num++;
		}
	}
	
	clockTarget.innerHTML = `<b>${liveYear}년 ${liveMonth+1}월 ${liveDate}일 ${liveWeek[liveDay]}요일` +
		    ` ${liveHours < 10 ? `0${liveHours}`:liveHours}시 ${liveMinutes < 10 ? `0${liveMinutes}`:liveMinutes}분</b>`;
	// 월은 0부터 1월이기때문에 +1일을 해주고
}

function init() {
	clock();
	setInterval(clock, 1000);
}	

init();



