<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
.newbooktable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
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
<%@include file="header.jsp"%>
<body style="overflow: auto; margin: 0; padding: 0; height: 100%">

<table class="newbooktable" border="1" style="width: 50%; float: none; margin: auto;">
	<tr>
		<td style="text-align: center; background-color: #114c3d; color: white;"> 
			비회원 주문 정보
		</td>
	</tr>
	<tr>
		<td> 
			주문번호 : ${orderDto.order_no}
		</td>
	</tr>
	<tr>
		<td>  
			주문비밀번호 : ${orderDto.order_passwd}
		</td>
	</tr>
	<tr>
		<td> 
			주문한 책 이름 : ${newbook.nbName}
		</td>
	</tr>
	<tr>
		<td> 
			주문한 책 개수 : ${orderDto.order_scount}
		</td>
	</tr>
	<tr>
		<td> 
			결제금액 : ${orderDto.order_sum}
		</td>
	</tr>
	<tr>
		<td> 
			배송지 : ${orderDto.order_address}
		</td>
	</tr>
	<tr>
		<td style="text-align: center;"> 
			<button type="button" class="btn btn-outline-success" onclick="location.href='main'">메인화면</button>
		</td>
	</tr>	
</table>
<div id="footer_wrap">
<%@include file="footer.jsp" %>
</div>
</body>
</html>