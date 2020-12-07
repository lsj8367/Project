<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function check(){

		frm.submit();
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
</head>
<%@include file="admin_header.jsp"%>
<body>
<form name="frm" method="post" action="bookregister" enctype="multipart/form-data">
<table class="newbooktable" style="text-align: center; width: 40%; margin: auto;">
	<tr>
		<th colspan='2'>도서 등록</th>
	</tr>
	<tr>
		<th>도서명</th>
		<td><input type = "text" name = "nb_name" style="width: 100%; border: 0;"></td>
	</tr>
	<tr>
		<th>저자</th>
		<td><input type = "text" name = "nb_author"></td>
	</tr>
	<tr>
		<th>역자</th>
		<td><input type = "text" name = "nb_inter"></td>
	</tr>
	<tr>
		<th>분야</th>
		<td>
		<select name="nb_genre">
			<option value="미분류" selected>미분류
			<option value="소설/시/희곡">소설/시/희곡
			<option value="에세이">에세이
			<option value="어린이">어린이
			<option value="유아">유아
			<option value="경제경영">경제경영
			<option value="인문학">인문학
			<option value="사회과학">사회과학
			<option value="수험서/자격증">수험서/자격증
			<option value="외국어">외국어
			<option value="대학교재">대학교재
			<option value="컴퓨터/모바일">컴퓨터/모바일
		</select>
		</td>
	</tr>
	<tr>
		<th>출판사</th>
		<td><input type = "text" name = "nb_comp"></td>
	</tr>
	<tr>
		<th>출판일자</th>
		<td><input type = "date" name = "nb_bdate"></td>
	</tr>
	<tr>
		<th>재고</th>
		<td><input type = "text" name = "nb_stock"></td>
	</tr>
	<tr>
		<th>금액</th>
		<td><input type = "text" name = "nb_price"></td>
	</tr>
	<tr>
		<th>줄거리</th>
		<td><textarea name="nb_plot" cols="50" rows="10" ></textarea></td>
	</tr>
	<tr>
		<th>사진</th>
		<td>
		<!--<input type = "file" name = "file" accept="image/*">-->
		<input type = "text" name = "nb_image">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" class="btn btn-outline-secondary" onClick="check()">등록</button>&nbsp;&nbsp;
			<button type="button" class="btn btn-outline-secondary" onClick="location.href='admin'">취소</button>
		</td>
	</tr>
</table>
</form>
</body>
<%@include file="admin_footer.jsp"%>
</html>