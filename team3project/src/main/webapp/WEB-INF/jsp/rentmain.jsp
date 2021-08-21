<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
@font-face {
	font-family: 'TmoneyRoundWindRegular';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	font-family: 'TmoneyRoundWindRegular';
}


#container {
        width: 100%;
        margin: auto;
        padding: 20px;
      }
      #header {
        padding: 0;
        margin: auto;
        margin-bottom: 20px;
      }
      #content {
        width: 60%;
        padding: 20px;
        margin-bottom: 20px;
        float: left;
      }
      #leftbar {
   	  	 width: 260px;
        padding: 20px;
        margin-bottom: 20px;
        float: left;
      }
      #sidebar {
        width: 260px;
        padding: 20px;
        margin-bottom: 20px;
        float: right;
      }
      #footer {
        clear: both;
        padding: 0;
      }
      @media ( max-width: 480px ) {
        #container {
          width: auto;
        }
        #content {
          float: none;
          width: auto;
        }
        #sidebar {
          float: none;
          width: auto;
        }
        #leftbar {
          float: none;
          width: auto;
        }
      }


div#s_left {
	width: 30%;
	height: 100%;
	float: left;
}

#contents {
	width: 70%;
	height: 100%;
	float: left;
}
.newbooktable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: auto;
  float : none;
  padding: 0;
  width: 30%;
  table-layout: fixed;
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
}

.newbooktable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
  background-color: #37aacf;
  text-align: center;
}

@media screen and (max-width: 600px) {
  .newbooktable {
    border: 0;
  }

  .newbooktable caption {
    font-size: 1.3em;
  }
  
  .newbooktable thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  .newbooktable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  .newbooktable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
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
<%@include file="oldheader.jsp"%>
<body style="overflow: auto; margin: 0; padding: 0; height: 100%">
<div id="container">
      
      <div id="leftbar">
		<%@ include file="menutab.jsp"%>
		</div>
      <div id="content">
		<a href="rentlist1?book=high">최상급 전체 보기</a>
				<table class="newbooktable" style="text-align:center; width: 100%; margin: auto; float: left;">
					<tr style="text-align: center;">
						<th>책</th>
						<th>제목</th>
						<th>가격</th>
						<th>출판년도</th>
						<th>출판사</th>
					</tr>

					<!-- 반복!! -->
					<!-- 데이터 가져오는곳 -->
					<c:choose>
						<c:when test="${f:length(oldbooklist) eq 0}">
							<tr style="text-align: center;">
								<td colspan="5">목록이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="o" items="${oldbooklist}">


								<tr style="text-align: center;">
									<td style="width: 20%;"><a
										href="oldbook?book_no=${o.ob_no }"><img width="100"
											src="./${o.ob_image}" /></a></td>
									<td style="width: 35%;"><a
										href="oldbook?book_no=${o.ob_no }">${o.ob_name}</a></td>
									<td style="width: 15%;">${o.ob_price} 원</td>
									<fmt:parseDate var="dateString" value="${o.ob_bdate}"
										pattern="yyyy-MM-dd HH:mm:ss.S" />
									<td style="width: 15%;"><fmt:formatDate
											value="${dateString}" pattern="yyyy년MM월dd일" /></td>
									<td style="width: 15%;">${o.ob_comp}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<a href="rentlist1?book=low">2~3등급 전체 보기</a><br>
		<table class="newbooktable" style="width: 100%; margin: auto; float: none;">

			<tr style="text-align: center;">
				<th>책</th>
				<th>제목</th>
				<th>가격</th>
				<th>출판년도</th>
				<th>출판사</th>
				<th>대여여부</th>
			</tr>

			<!-- 반복!! -->
			<!-- 데이터 가져오는곳 -->
			<c:choose>
				<c:when test="${f:length(oldbooklow) eq 0}">
					<tr style="text-align: center;">
						<td colspan="6">목록이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="l" items="${oldbooklow}">
						<tr style="text-align: center;">
							<td><a href="oldrental?book_no=${l.ob_no }"><img
									width="100" src="./${l.ob_image}" /></a></td>
							<td><a href="oldrental?book_no=${l.ob_no }">${l.ob_name}</a></td>
							<td>${l.ob_price} 원</td>
							<fmt:parseDate var="daterental" value="${l.ob_bdate}"
								pattern="yyyy-MM-dd HH:mm:ss.S" />
							<td style="width: 15%;"><fmt:formatDate
									value="${daterental}" pattern="yyyy년MM월dd일" /></td>
							<td>${l.ob_comp}</td>
							<c:choose>
								<c:when test="${l.ob_donor}">
								<c:if test="${l.ob_donor eq '0'}">
									<td><c:out value="대여가능" /></td>
								</c:if>
								<c:if test="${l.ob_donor eq '1'}">
									<td><c:out value="대여중" /></td>
								</c:if>
								</c:when>
								
								<c:otherwise>
									<td><c:out value="대여가능"></c:out>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
</div> <!-- contents 끝 -->	
	
<div id="sidebar">	
		<table class="newbooktable" style="margin: auto; float: none; width: 100%">
			<tr>
				<c:choose>
					<c:when test="${sessionScope.id eq null}">
						<td colspan="2"><a href="give">내 책 기증하기</a></td>
					</c:when>

					<c:otherwise>
						<td colspan="2">
							<a href="give">내 책 기증하기</a><br>
							<a href="mypage">대여목록 보기</a>
						</td>				
					</c:otherwise>
				</c:choose>
			
			</tr>
			<tr>
				<c:choose>
				<c:when test="${sessionScope.id eq null}">
					<td colspan="2">방문자님 환영합니다.</td>
				</c:when>
				<c:otherwise>
					<td colspan="2">${sessionScope.name}님 환영합니다.<br>보유포인트는 ${sessionScope.point}point</td>
				</c:otherwise>
				</c:choose>
			</tr>

			<tr>
				<td colspan="2">↓이달의 베스트 대여도서 ↓</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><a href="oldrental?book_no=${best.ob_no }"> <img width="100" src="./${best.ob_image}"></a></td>
				<!-- ${best.ob_name} -->
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">이달의 다독왕은 <br>
					${readbest.user_name} 님 입니다
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><a href="center">고객 센터</a></td>
			</tr>
		</table>
	</div>
      <div id="footer">
         	<%@include file="footer.jsp"%>
      </div>
</div>