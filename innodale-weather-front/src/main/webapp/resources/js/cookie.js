//쿠키 설정
function setCookie(cname, cvalue, exdays) {

	var d = new Date();
	//30일동안 유효기간 설정
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
} 

//쿠키 정보 가져옴
function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}
//관심 지역 추가여부 확인 
function checkCookie() {
	if (confirm("관심 지역 등록 하시겠습니까?") == true) {
		var user = document.getElementsByTagName('option')[0].value;
		addCookie(user);
	} else
		return;//confirm 종료
}

//쿠키 추가등록(관심지역) 3개 지역까지 등록 가능
function addCookie() {
	var dc = document.cookie;
	//쿠키 정보 가져와서 "=" 갯수 확인하여 3개보다 많으면 등록 못함
	var results = (dc.match(/=/g)||[]).length;
	if (results < 3) {
		var user = document.getElementsByTagName('option')[0].value;
		
		var listCookie = document.cookie.split( "; " );
		var strCookie = "";
	    
		for( var i = 0; i < listCookie.length; ++i ){
			var iPos = listCookie[i].indexOf("=");
			var strName = listCookie[i].substring(0,iPos);
			var strValue = listCookie[i].substring(iPos+1);
			strValue = decodeURIComponent( strValue );
			if(strValue != user){
				setCookie(user, user, 30);
				alert("등록 되었습니다");
				
			}else{
				alert("이미 등록된 주소 입니다.");
				return false;
			}
		}
		
		showCookie();
	}else{
		alert("관심지역은 3개까지 허용입니다.");
		return;
		
	}

}

//설정에서 관심지역 목록 보여줌
function showCookie()
{
	var divCookie = document.getElementById("cookie_list");

	if( document.cookie == null || document.cookie == "" )
	{
		divCookie.innerHTML = "관심 지역을 설정해 주세요";
		return;
	}
    
	var listCookie = document.cookie.split( "; " );
	var strCookie = "";
    
	for( var i = 0; i < listCookie.length; ++i )
	{
        
        
		var iPos = listCookie[i].indexOf("=");
		var strName = listCookie[i].substring(0,iPos);
		var strValue = listCookie[i].substring(iPos+1);
		strValue = decodeURIComponent( strValue );
		strCookie += "<li><input type=\"checkbox\" name=\"chk_addr\" value=\""+strValue+"\"id=\""+ "c"+i +"\">"+ strValue + "</il>";
	}
	
	divCookie.innerHTML = strCookie;

}

//select 박스에 관심지역 입력
addrList();
function addrList(){
	
	var listCookie = document.cookie.split( "; " );
	var strCookie = "";
    
	for( var i = 0; i < listCookie.length; ++i )
	{
		var iPos = listCookie[i].indexOf("=");
		var strName = listCookie[i].substring(0,iPos);
		var strValue = listCookie[i].substring(iPos+1);
		strValue = decodeURIComponent( strValue );
		strCookie += "<option value='" + strValue + "'>"+ strValue + "</option>";
	}

	$('#selist').append(strCookie);
	
}

showCookie();
//설정 창에서 관심지역 선택후 삭제
function chk_addr() {

	var cnt = $('input:checkbox[name="chk_addr"]').length
	var chk_cnt = $('input:checkbox[name="chk_addr"]:checked').length
	
	if (chk_cnt < 1) {

	} else {

		if (confirm("관심 지역 삭제 하시겠습니까?") == true) {

			for (var i = 0; i < cnt; i++) {
				if (document.getElementsByName("chk_addr")[i].checked == true) {
					var addr = document.getElementsByName("chk_addr")[i].value;
					setCookie(addr, addr, 0);
				}

			}
			showCookie();
			addrList();
		}else{}

	}
}

//주소 변경시 refresh
$('#selist').change(function(){
	refreshAll();
});