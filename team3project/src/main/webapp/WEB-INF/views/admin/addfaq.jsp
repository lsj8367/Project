<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
}

.newbooktable td input, .newbooktable td textarea, .newbooktable td select{
	width: 100%; border: 0; font-family: 'TmoneyRoundWindRegular';
}

.newbooktable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
  background:gray;
  color: white;
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
</head>
<%@include file="admin_header.jsp"%>
<body>
<form name="frm" method="post" action="faqadd">
<table class="newbooktable" style="text-align: center; margin: auto; width: 90%">
	<tr>
		<th colspan='2'>FAQ 등록</th>
	</tr>
	<tr>
		<th>FAQ제목</th>
		<td><input type = "text" name = "faq_title" style="width: 100%"></td>
	</tr>
	<tr>
		<th>FAQ내용</th>
		<td><textarea name="faq_content" style="width: 100%; height: 200px" ></textarea></td>
	</tr>
	<tr>
		<th>분류</th>
		<td style="text-align: left;">
		<select name="faq_type" style="width: 20%;">
			<option value="1" selected>상품
			<option value="2">주문
			<option value="3">배송
			<option value="4">대여
		</select>
		</td>
	</tr>
	<tr>
		<td colspan='2'>
			<button type="submit" class="btn btn-outline-secondary">등록</button>&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-outline-secondary"  onClick="location.href='admin'">취소</button>
		</td>
	</tr>
</table>
</form>
<div id="footer_wrap">
<%@include file="admin_footer.jsp"%>
</div>
</body>
</html>