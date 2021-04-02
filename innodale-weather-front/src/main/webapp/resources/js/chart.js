// 여기는 동적으로 변경해야될 3, 6, 12, 24시간의 데이터
var time;

var a = function(arr, time, categories) {
	Highcharts.chart('container', {
		credits: { //워터마크 가리기
		    enabled: false
		  },
		colors : [
			"#ffffff"
			],
		chart : {
			backgroundColor : "#009ae1",
			type : 'scatter', // 점선 scatter
			lineWidth : 0,
		},
		// 그래프 제목
		title : {
			text : '',
		},
		xAxis : {
			lineColor : "#009ae1", //div 전체 배경색상과 맞추기
			categories : categories, // 동적으로 수정하면됨 x축
			title : {
				text : '',
			},
			labels: {
				enabled : false, //x축 값 안보이게
				overflow: 'justify'
	        }
		},
		yAxis : {
			gridLineWidth : 0, //grid 선 없앰
			title : {
				text : '',
			},
			labels: {
				enabled : false,
	        },
		},
		plotOptions : {
			scatter : { //점마다
				dataLabels : {
					enabled : true,
					format : '{y} °C', // 몇도인지 그래프 표시
					style: {
		                color: '#ffffff'
		            },
				},
				enableMouseTracking : true
			}
		},
		tooltip: {
	        headerFormat: '<b>{series.name}</b><br/>', //series의 name값
	        pointFormat: '{point.category}: {point.y}°C' //categories 배열 0번부터의 값 : plotOptions의 format 값
	    },
		series : [ {
			data : arr, // 기온
			showInLegend : false, //그래프 series명 숨김
			name: `${time}`,
		} ]
	})
};



$("#btn1").click(function() { // 3시간 버튼 클릭
	$("#lft").empty();
	time = $("#btn1").val();
	$("#container").width("1350px");
	reLong3H(place);
});

$("#btn2").click(function() { // 6시간 버튼 클릭
	$("#lft").empty();
	time = $("#btn2").val();
	$("#container").width("1000px");
	reLong6H(place);
	
})

$("#btn3").click(function() { // 12시간 버튼 클릭
	$("#lft").empty();
	time = $("#btn3").val();
	$("#container").width("700px");
	reLong12H(place);
	
});

$("#btn4").click(function() { // 24시간 버튼 클릭
	$("#lft").empty();
	$("#container").width("500px");
	time = $("#btn4").val();
	reLong24H(place);

});
