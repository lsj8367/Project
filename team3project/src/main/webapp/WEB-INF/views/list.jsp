<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책목록</title>
<style type="text/css">
html, body { height:100%; overflow:hidden }
#footer_wrap {width:100%;height:118px;clear:both;margin-top:30px; position: sticky;}
div#s_left {
	width: 30%;
	height: 100%;
	float: left;
}

#contents{
	width: 70%;
	height: 100%;
	float: center;
	margin-left: 280px;
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
  text-align: center;
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

<div id="contents">
<table class="newbooklisttable" border="1" style="width: 80%; float: none; margin: auto;">

	<tr style="text-align: center; background-color: #114c3d; color: white;">
		<th>책</th><th>제목</th><th>가격</th><th>출판년도</th><th>출판사</th>
	</tr>
	<!-- 반복!! -->
	<c:choose>
	<c:when test="${f:length(newbooklist) eq 0}">
		<tr style="text-align: center;"><td colspan="5">목록이 없습니다.</td></tr>
	</c:when>
	<c:otherwise>
		<c:forEach var="n" items="${newbooklist}">
			<tr style="text-align: center;">
				<td style="width: 20%;"><a href="newbook?book_no=1"><img width="100" src="${n.nbImage}"/></a></td>
				<td style="width: 35%;"><a href="newbook?book_no=1">${n.nbName}</a></td>
				<td style="width: 15%;">${n.nbPrice}</td>
				<td style="width: 15%;">
					<fmt:parseDate var="dateString" value="${n.nbBdate}" pattern="yyyy-MM-dd HH:mm:ss.S" />
	      			<fmt:formatDate value="${dateString}" pattern="yyyy년 MM월 dd일" />
				</td>
				<td style="width: 15%;">${n.nbComp}</td>
			</tr>
		</c:forEach>
	</c:otherwise>
	</c:choose>
</table>
<div id="footer_wrap">
<%@include file="footer.jsp" %>
</div>
</div>
</body>
</html>