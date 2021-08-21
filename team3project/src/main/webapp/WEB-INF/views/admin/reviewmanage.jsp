<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style type="text/css">
@font-face {
    font-family: 'TmoneyRoundWindRegular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

body {
  padding-top: 200px;
  font-family: 'TmoneyRoundWindRegular';
}

//테이블 적용
.newbooktable {
  font-family: 'TmoneyRoundWindRegular';
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin-left:auto;
  margin-right:auto;
  float : none;
  padding: 0;
  width: 50%;
  table-layout: fixed;
  text-align: center;
}

.newbooktable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.newbooktable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.newbooktable th,
.newbooktable td {
  padding: .625em;
  text-align: center;
  font-family: 'TmoneyRoundWindRegular';
}

.newbooktable td input, .newbooktable td textarea {
	width: 100%; border: 0; font-family: 'TmoneyRoundWindRegular'; text-align: center;
}

.newbooktable td select{
	border: 0; font-family: 'TmoneyRoundWindRegular'; text-align: center;
}

.newbooktable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
  background:gray;
  color: white;
  font-family: 'TmoneyRoundWindRegular';
}
  .divtag{
  	text-align: center;
  	margin: auto;
  	width: 100%;
  	font-family: 'TmoneyRoundWindRegular';
  }

@media screen and (max-width: 600px) {
  .newbooktable {
    border: 0;
  }

  .newbooktable caption {
    font-size: 1.3em;
  }
  
  .newbooktable th {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
    font-family:  'TmoneyRoundWindRegular';
  }
  
  .newbooktable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
    text-align: center;
  }
  
  .newbooktable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: center;
  }
  
  .newbooktable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .newbooktable td:last-child {
    border-bottom: 0;
  }
  
  
}

.content {
	display:none;
}
</style>
<script type="text/javascript">
var old_view ="";

function swapimg(){
	var table = document.getElementById("mytable");
	var cells = table.getElementByTagName("td");
	for (var i = 0; i < ${fn:length(rv)}; i + 2) {
		cells.item(i).childNodes[1].innerHTML = "▼";	
	}	
}

function MenuClick(sid,myimg){
	var mView = window.document.getElementById(sid);
	if(old_view != mView){
		if(old_view != ""){
			old_view.style.display = "none";
			swapimg();
		}
		mView.style.display = "table-row";
		old_view = mView;
		
		myimg.childNodes[1].innerHTML = "▲";
	}else{
		mView.style.display = "none";
		old_view = "";
		myimg.childNodes[1].innerHTML = "▼";
	}
	
}
</script>
</head>
<%@include file="admin_header.jsp"%>
<body>
<table id="mytable" style="text-align: center; width: 90%; margin:auto;" class="newbooktable"> 
 <tr><th>번호</th><th>작성자</th><th>책이름</th><th>작성일</th><th>공감수</th><th>보기</th></tr>
  
   <c:forEach var="rv" items="${rv }" varStatus="status">
	<tr>
		<td>${rv.review_no }</td>
		<td>${rv.review_id }</td>
		<td>${rv.review_name }</td>
		<td>${rv.review_date }</td>
		<td>${rv.review_gonggam }</td>
   		<td onclick = "MenuClick('${status.count}',this);"><span class="imgsp">▼</span></td>
	</tr>
   <tr id="${status.count }" class="content"> 
   <td style="width: 10%"><b>내용</b></td>
   <td colspan='5'>
   		<c:forEach var="i" begin="1" end="${rv.review_rate }">★</c:forEach>
   		<c:forEach var="j" begin="1" end="${5 - rv.review_rate }">☆</c:forEach>
   		<br><br>
   		${rv.review_context }
   </td> 
    </tr> 
</c:forEach>
    </table> 
</body>
<%@include file="admin_footer.jsp"%>
</html>