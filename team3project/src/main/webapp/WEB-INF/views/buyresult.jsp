<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ include file="oldheader.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
  text-align: left;
}

.newbooktable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
  background-color: #37aacf;
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

<body>
<br>

<table class="newbooktable">
   <tr><th colspan="2" style="text-align: center;">주문이 완료되었습니다</th></tr>
   <tr>
      <td>주문번호</td>
      <td>${buylist.orderlist_no }</td>
   </tr>
   <tr>
      <td>성함</td>
      <td>${buylist.order_person }</td>
   </tr>
   <tr>
      <td>회원 여부</td>
      <c:choose>
         <c:when test="${buylist.order_id eq '' }">
            <td>비회원</td>
         </c:when>
         <c:otherwise>
            <td>정회원</td>
         </c:otherwise>
      </c:choose>
   </tr>
   <tr>
      <td>주문일</td>
      <fmt:parseDate var="dateString" value="${buylist.order_date}" pattern="yyyy-MM-dd HH:mm:ss.S" />
      <td><fmt:formatDate value="${dateString}" pattern="yyyy년 MM월 dd일" /></td>
   </tr>
   <tr>
      <td>결제 유형</td>
      <c:choose>
         <c:when test="${buylist.order_paytype eq 0}">
            <td>무통장입금</td>
         </c:when>
         <c:otherwise>
            <td>${card_comp}카드  - 카드번호 : ${card_no }</td>
         </c:otherwise>
      </c:choose>
   </tr>
   <tr>
      <td>금액</td>
      <td>${buylist.order_sum } 원</td>
   </tr>
   <tr>
      <td>주소지</td>
      <td>${buylist.order_address}</td>
   </tr>
   <tr>
      <td colspan="2" style="text-align: center;">
         <input type="submit" class="btn btn-primary btn-lg" value="메인으로" onclick="location.href='rentlist1?book=rentmain'">      
      </td>
   </tr>
</table>

   
   
   
   
</body>
</html>