<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function FirstPenalty(){
		document.frm.submit();
	}
</script>
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

.newbooktable td input, .newbooktable td textarea, .newbooktable td select{
	width: 100%; border: 0; font-family: 'TmoneyRoundWindRegular'; text-align: center;
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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@include file="admin_header.jsp"%>
<body>
			<table class="newbooktable" style="margin:auto; width:90%; text-align: center;">
				<tr><th>대여자아이디</th><th>대여자</th><th>연체도서명</th><th>차감포인트</th><th>연체일수</th></tr>
				<c:choose>
					<c:when test="${fn:length(longd) eq 0}">
						<tr>
							<td colspan='4'>장기연체도서가 존재하지 않습니다.<td>
						</tr>
						<tr>
							<td colspan='5'>
								<button type="button" class="btn btn-outline-secondary" onclick = "location.href='delaycount'">연체도서수로 이동</button>
							</td>
						</tr>
					</c:when>
					<c:otherwise> 
						<form action="delay" method = "post" name="frm">
							<c:forEach var="ld" items="${longd }">
								<tr>
									<td>
										<input type="hidden" name = "rent_no" value = "${ld.rent_no}">
										${ld.user_id }
										<input type="hidden" name = "user_id" value = "${ld.user_id}">
									</td>
									<td>${ld.user_name }</td>
									<td>${ld.ob_name }</td>
									<td>
										${ld.delpoint }
										<input type="hidden" name = "delpoint" value = "${ld.delpoint}">
									</td>
									<td>${ld.delaydate }</td>
								</tr>
							</c:forEach>
						</form>
						<tr>
							<td colspan='5'><input type="button" value="패널티 적용" onclick="javascript:FirstPenalty()"></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
</body>
<%@include file="admin_footer.jsp"%>
</html>