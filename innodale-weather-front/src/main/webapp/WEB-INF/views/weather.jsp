<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
request.setCharacterEncoding("UTF-8"); 
response.setContentType("text/html; charset=utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>실시간 날씨 정보 시스템</title>
<!-- jQuery -->
<script src="/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
window.onload = function(){
	navigator.geolocation.getCurrentPosition(success, error, options);
}
</script>

<!-- 버튼 css -->
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.min.css">

<!-- Daum 주소 api-->
<script
	src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js/address.js"></script>
<!-- 그래프 차트 api -->
<script src="https://code.highcharts.com/highcharts.js"></script>

<!-- 레이아웃 css-->
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">

<!-- 지도-->
<script type="text/javascript" src="//map.vworld.kr/js/vworldMapInit.js.do?version=2.0&apiKey=2D536B8A-D7EC-3B05-BE1A-068D84D6F091"></script>
<script type="text/javascript" src="http://apis.vworld.kr/coord2jibun.do?x=126.961862449327&y=37.3952998720615&output=xml&epsg=epsg:4326&apiKey=2D536B8A-D7EC-3B05-BE1A-068D84D6F091"></script>

<!-- 날짜포맷 -->
<script src="/resources/js/moment.js"></script>

<!-- 1시간대 별 데이터 -->
<script src="/resources/js/hourinfo.js"></script>
</head>
<body style="color:white;"></body>
	<div id="header">

			<select class="form-control" id="selist" style="width:200px;" onchange="selist()">
			</select>
			<button class="btn btn-primary" id="addr" onclick="execDaumPostcode()">찾기</button>
			<button class="btn btn-primary" onclick="checkCookie()">관심지역 등록</button>
			<button class="btn btn-primary" onclick="modal(); showCookie();">설정</button>
			<button class="btn btn-primary" id="btnTest">현재위치</button>
			<script src="/resources/js/geoLocation.js"></script>

	</div>
	<br>
<div id="main">
	<div id="nav">
		<div style="text-align: center;">
			<h2 id="clock">00:00</h2>
			<!-- 현재시간 -->
			<script src="/resources/js/clock.js"></script>
			<br>
			<ul id="todayAccu">
				<li class="todayLi" style="font-size: 20px">최저/최고 기온(<small>°C</small>)&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li class="todayLi" id="accuHighLow" style="font-size: 20px"></li>
				<li class="todayLi" style="font-size: 20px">누적강수량(<small>mm</small>)&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li class="todayLi" id="accuRain" style="font-size: 20px"></li>
			</ul>
		</div>
		<br> <br>

		<div class="hourParent">
			<div class="hour" id="obsWeather">
			</div>

			<div class="hour" id="shortWeather1H">
			</div>

			<div class="hour" id="shortWeather2H">
			</div>

			<div class="hour" id="shortWeather3H">
			</div>
		</div>
		<br>
		<div id="info">
			<div class="infobtn">
				<button class="btn btn-warning" type="button" value="3시간" id="btn1">3시간</button>
				<button class="btn btn-warning" type="button" value="6시간" id="btn2">6시간</button>
				<button class="btn btn-warning" type="button" value="12시간" id="btn3">12시간</button>
				<button class="btn btn-warning" type="button" value="24시간" id="btn4">24시간</button>
			</div><br><br>
			
			<div id ="scroll">
				<div id="reLong">
					<!-- 그래프 보여주는곳-->
					<div id="container" style="margin-left:70px;height: 100px"></div>
					<script src="/resources/js/chart.js"></script>
				</div>
				<div id="lft">
				</div>
			</div>
		</div>
	</div>
	<div id="content">
		<div id="map">
			<h2>전국날씨</h2><br>
			<!-- 지도 보여주는곳-->
			<div id="vmap" style="color: black;"></div>
			<script src="/resources/js/vworld.js"></script>

		</div>

		<br>
		<div id="pastweater">
			<h2>과거날씨</h2>
			<br>
			<table id="calendar">
				<tr>
					<td>
						<button class="btn btn-light" id="prev">&lt;</button>
					</td>
					<td colspan="5" id="calendarYM"></td>
					<td>
						<button class="btn btn-light" id="next">></button>
					</td>
				</tr>

				<tr>
					<td style="color: crimson;">일</td>
					<td>월</td>
					<td>화</td>
					<td>수</td>
					<td>목</td>
					<td>금</td>
					<td style="color: blue;">토</td>
				</tr>

			</table>
		</div>
	</div>
</div>

<div class="clear"></div>

<!-- modal test -->
<div id="modal" class="searchModal">
	<div class="search-modal-content">
		<div class="page-header">
			<h1>관심지역 설정</h1>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="row">
					<div class="col-sm-12 ">
						<div id="cookie_list"
							style="border: 1px solid #000000; padding: 10px; margin-top: 10px;"></div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div style="cursor: pointer; background-color: #DDDDDD; text-align: center; color: black;">
			<div class="pop_bt modalCloseBtn" onClick="chk_addr();" style="font-size: 13pt;">
				삭제
			</div>
			<div class="pop_bt modalCloseBtn" style="font-size: 13pt; background-color: yellow;" onclick="closeModal();">
				창닫기
			</div>
		</div>
	</div>
</div>

<!-- 쿠키 -->
<script src="/resources/js/cookie.js"></script>
<script src="/resources/js/modal.js"></script>

<!-- 달력 보이는곳-->
<script src="/resources/js/date.js"></script>
<!-- div 새로고침 -->
<script src="/resources/js/refresh.js"></script>

<%@ include file="footer.jsp"%>
</body>

</html>