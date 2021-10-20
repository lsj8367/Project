<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
</style>
</head>
<%@include file="admin_header.jsp"%>
<body>
	<form method="post" action="standbyok">
	<table class="newbooktable" style="width: 90%; margin-left: auto; margin-right: auto;">
		<tr><th>도서번호</th><th>도서이미지</th><th>도서명</th><th>저자</th><th>역자</th><th>출판사(출판일)</th><th>상태</th><th>기증자</th></tr>
		<c:forEach var="sl" items="${slist }">
			<tr>
				<td>
					${sl.obNo}
					<input type = "hidden" name="ob_no" value="${sl.obNo}">
				</td>
				<td><img src="${sl.obImage }" ></td>
				<td>${sl.obName}</td>
				<td>${sl.obAuthor}</td>
				<td>${sl.obInter}</td>
				<td>
				${sl.obComp}<br>
				(${sl.obBdate})
				</td>
				<td>
					<select name="ob_state">
						<option value="0" selected>승인대기
						<option value="1">최상
						<option value="2">상
						<option value="3">중
						<option value="4">하
						<option value="5">최하
					</select>
				</td>
				<td>${sl.obDonor}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan='8' style="text-align: center;">
				<button type="submit" class="btn btn-outline-secondary">완료</button>
			</td>
		</tr>
	</table>
	</form>
</body>
<%@include file="admin_footer.jsp"%>
</html>