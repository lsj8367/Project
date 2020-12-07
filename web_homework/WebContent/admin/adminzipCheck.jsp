<%@page import="kr.del.member.ZipcodeDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="memberMgr" class="kr.del.member.MemberMgr" scope="page"/>

<%
request.setCharacterEncoding("utf-8");
String check = request.getParameter("check"); //이것이 n일때 맨아래의 if문이 돌아가게된다.
String p_area3 = request.getParameter("area3"); //검색의 동이름 밑에 name=area3 에서 값을 받아줌

ArrayList<ZipcodeDto> list = memberMgr.zipcodeRead(p_area3); //p_area3들고 zipcodeRead 메소드로 감

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<link type="text/css" rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
<script type="text/javascript">
window.onload = function() {
	document.getElementById("btnZipFind").onclick = dongCheck;
	
	document.getElementById("btnZipClose").onclick = function() {
		window.close();
	};
}

function dongCheck(){
	//alert("c");
	if(zipForm.area3.value === "") {
		alert("검색할 동이름을 입력하세요.");
		zipForm.area3.focus();
		return;
	}
	
	zipForm.submit(); //데이터를 넘겨줌
	
}

function send(zipcode, area1, area2, area3, area4){
	//alert(zipcode + " " + area1);
	//opener.document.updateForm.zipcode.value = "";
	opener.document.updateFormAdmin.zipcode.value = zipcode; //opener는 updateForm
	var juso = area1 + " " + area2 + " " + area3 + " " + area4; //value값 선언할때 문자열더하기 안하는게 좋음.
	//opener.document.updateForm.address.value = "";
	opener.document.updateFormAdmin.address.value = juso; //opener는 updateForm
	window.close();
}
</script>
</head>
<body>
<b>** 우편번호 찾기 **</b><br>
<form action="adminzipCheck.jsp" name="zipForm" method="post">
<table>
	<tr>
		<td>
		동 이름 입력 : <input type="text" name="area3">
		<input type="button" value="검색" id="btnZipFind">
		<input type="button" value="닫기" id="btnZipClose">		
		<input type="hidden" value="n" name="check"> <!-- 검색이 시작되면 check 값 n으로 바뀜 -->	
		</td>
	</tr>
</table>
</form>

<%
if(check.equals("n")){
	if(list.isEmpty()){ //리스트값이 없을때
%>
		<b>검색 결과가 없습니다!</b>
<%
	}else{
%>
	<table>
		<tr>
			<td style="text-align: center;color: red">
				<b>검색자료를 클릭하면 자동으로 주소가 입력됩니다.</b>	
			</td>
		</tr>
		<tr>
			<td>
<%
				for(int i=0; i < list.size(); i++){
					ZipcodeDto dto = list.get(i);
					String zipcode = dto.getZipcode(); //우편번호
					String area1 = dto.getArea1();
					String area2 = dto.getArea2();
					String area3 = dto.getArea3();
					String area4 = dto.getArea4();
					if(area4 == null) area4 = ""; //null값인것들 공백을 값으로 줌
%>
				<a href="javascript:send('<%=zipcode %>', '<%=area1 %>','<%=area2 %>','<%=area3 %>','<%=area4 %>')"> <!-- 하이퍼텍스트처리로 감싸기 -->
				<%=zipcode %> <%=area1 %> <%=area2 %> <%=area3 %> <%=area4 %>
				</a>
				<br>

<%					
				}
%>
			</td>
		</tr>
	</table>
<%	
	}
}
%>
</body>
</html>