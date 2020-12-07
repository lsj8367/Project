<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책목록</title>
<style type="text/css">
html, body { height:100%; overflow:hidden }
#footer_wrap {width:100%;height:118px;clear:both;margin-top:30px; position: sticky;}
#contents{
	width: 70%;
	height: 100%;
	float: left;
}


html, body {

    margin:0;

    padding:0;
}

#footer {

    position:absolute;

    bottom:0;

    width:100%;

    height:100px;   
}



//테이블 적용
.newbooklisttable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
}

.newbooklisttable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.newbooklisttable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.newbooklisttable th,
.newbooklisttable td {
  padding: .625em;
  text-align: left;
}

.newbooklisttable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  .newbooklisttable {
    border: 0;
  }

  .newbooklisttable caption {
    font-size: 1.3em;
  }
  
  .newbooklisttable thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  .newbooklisttable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  .newbooklisttable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  .newbooklisttable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .newbooklisttable td:last-child {
    border-bottom: 0;
  }
}
</style>
</head>
<%@include file="header.jsp" %>
<body style="overflow: auto; margin: 0; padding: 0; height: 100%">

<br>
<br>
<!-- 아직 NewbookController를 분류해서 안 만들고 상단 메뉴바를 누르면 책목록을 볼 수 있게만 만듬.. -->
<table class="newbooklisttable" border="1" style="width: 80%; float: none; margin: auto;">

	<tr style="text-align: center; background-color: #114c3d; color: white;">
		<th>책</th><th>제목</th><th>가격</th><th>출판년도</th><th>출판사</th>
	</tr>
	<!-- 반복!! -->
	<tr style="text-align: center;">
		<td style="width: 20%;"><a href="newbook?book_no=1">책 이미지</a></td>
		<td style="width: 35%;"><a href="newbook?book_no=1">책 제목</a></td>
		<td style="width: 15%;">책 가격</td>
		<td style="width: 15%;">책 출판년도</td>
		<td style="width: 15%;">책 출판사</td>
	</tr>
</table>





<!-- footer 크기만큼 주면 안 가려짐 -->
<div style="height: 100px;"></div>
<div id="footer">
<%@include file="footer.jsp" %>
</div>
</body>
</html>