<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Insert title here</title>
<style>
.content {
	display: none;
}
</style>
<script type="text/javascript">
var old_view ="";

function swapimg(){
	var table = document.getElementById("mytable");
	var cells = table.getElementByTagName("td");
	for (var i = 0; i < ${fn:length(rl)}; i + 2) {
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
function monthview(){
	frm.action = "monthbestreview";
	frm.submit();
}

function givePoint(){
	frm.action="givepoint";
	frm.submit();
}
</script>
</head>
	<%@include file="admin_header.jsp"%>
<body>
	
	<form name="frm" method="post">
		<table id="mytable" class="newbooktable"
			style="text-align: center; width: 90%; margin: auto;" border='1'>
			<tr>
				<td colspan='8'>
				<select name="sql">
						<option value="" selected>전체보기
							<c:forEach var="r" items="${rm }">
								<option
									value="and month(review_date) like concat('%',${r.review_month },'%')">${r.review_month }월
							</c:forEach>
				</select>
				<button type="submit" class="btn btn-outline-secondary" onclick="javascript:monthview()">보기</button>
				</td>
			</tr>
			<tr>
				<th>순위</th>
				<th>작성월</th>
				<th>번호</th>
				<th>작성자id</th>
				<th>책번호</th>
				<th>책이름</th>
				<th>작성일자</th>
				<th>보기</th>
			</tr>
			<c:forEach var="rl" items="${rl }" varStatus="status">
				<tr>
					<td>${rl.rn }</td>
					<td>${rl.review_month }월</td>
					<td>${rl.review_no }</td>
					<td>${rl.review_id }</td>
					<td>${rl.review_bookno }</td>
					<td>${rl.review_name }</td>
					<td>${rl.review_date }</td>
					<td onclick="MenuClick('${status.count}',this);"><span
						class="imgsp">▼</span></td>
				</tr>
				<tr id="${status.count }" class="content">
					<td style="width: 10%">내용</td>
					<td colspan='7'>평점 : <c:forEach var="i" begin="1"
							end="${rl.review_rate }">★</c:forEach> <c:forEach var="j"
							begin="1" end="${5 - rl.review_rate }">☆</c:forEach> <br>
						공감수 : ${rl.review_gonggam } <br>
					<br> ${rl.review_context } <jsp:useBean id="now"
							class="java.util.Date" /> <fmt:formatDate value="${now}"
							pattern="MM" var="today" /> <c:if
							test="${today eq rl.review_month }">
							<input type="hidden" value="${rl.rn }" name="rn">
							<input type="hidden" value="${rl.review_id }" name="review_id">
							<p />
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan='8'>
					<button type="button" class="btn btn-outline-secondary" onclick="givePoint()">포인트 지급</button>
				</td>
			</tr>
		</table>
	</form>
</body>
	<%@include file="admin_footer.jsp"%>
</html>