<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
html, body { height:100%; overflow:hidden }
div#s_left{
	width:25%;
	height: 99%;
	background-color: yellow;
	float:left;
}
#contents{
	width: 50%;
	height: 99%;
	background-color: green;
	float: left;
}
#s_right{
	width: 25%;
	height: 99%;
	background-color: red;
	float:left;
}
</style>
</head>
<body>
<div id="s_left">왼쪽</div>
<div id="contents">본문</div>
<div id="s_right">오른쪽</div>

</body>
</html>