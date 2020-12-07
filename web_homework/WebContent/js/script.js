function zipCheck(){ //회원 가입에서의 우편번호찾기
	url = "zipcheck.jsp?check=y"; 
	window.open(url,"post",
		"toolbar=no,width=350,height=300,top=200,left=300,status=yes,scrollbars=yes,menubar=no");
}

function updatezipCheck(){ //회원정보 수정에서의 우편번호 찾기
	url = "updatezipcheck.jsp?check=y"; 
	window.open(url,"post",
	"toolbar=no,width=500,height=400,top=300,left=400,status=yes,scrollbars=yes,menubar=no");
}



function idCheck(){
	if(regForm.id.value === ""){
		alert("id를 입력하시오");
		regForm.id.focus();
	}else{
		url = "idcheck.jsp?id=" + regForm.id.value;
		window.open(url,"","width=300,height=150,left=150,top=150");
	}
}

function inputCheck(){
	if(regForm.id.value == ""){
		alert("아이디 입력!");
		regForm.id.focus();
		return;
	}
	
	if(regForm.passwd.value == ""){
		alert("비밀번호 입력!");
		regForm.passwd.focus();
		return;
	}
	if(regForm.passwd.value !== regForm.repasswd.value){
		alert("비밀번호 불일치!");
		regForm.passwd.focus();
		return;
	}
	
	//...
	
	if(regForm.job.value === "0"){
		alert("직업을 선택하시오");
		regForm.job.focus();
		return;
	}
	
	regForm.submit();
}


//쇼핑몰 로그인 후 자신의 정보 수정 시 사용
function memberUpdate(){ 
	//입력자료 오류검사 생략
	document.updateForm.submit();
}
function memberUpdateCancel(){
	location.href="../guest/guest_index.jsp";
}
function memberDelete(){
	alert("회원탈퇴는 곧 죽음!!!");
}

function adminzipCheck(){ //관리자의 회원정보 수정에서의 우편번호 찾기
	url = "adminzipCheck.jsp?check=y";
	window.open(url,"post",
		"toolbar=no,width=350,height=300,top=200,left=300,status=yes,scrollbars=yes,menubar=no");
}

//관리자 관련 
function funcAdminLogin(){
	if(adminLoginform.adminid.value === ""){
		alert("id 를 입력하시오");
		adminLoginform.adminid.focus();
		return;
	}
	if(adminLoginform.adminpasswd.value === ""){
		alert("password를 입력하시오");
		adminLoginform.adminpasswd.focus();
		return;
	}
	adminLoginform.submit();
}
function funcAdminHome(){
	location.href = "../index.jsp";
}

//관리자 입장에서 각 회원 수정
function memUpdate(id) {
	//alert(id);
	document.updateFrm.id.value = id; //hidden으로 숨어있는 form태그에 id값을 넣어줌
	document.updateFrm.submit();
}

function memberUpdateAdmin(){
	document.updateFormAdmin.submit();
}
function memberUpdateCancelAdmin(){
	location.href = "membermanager.jsp";
}

//관리자에서 상품처리
function productDetail(no){
	document.detailForm.no.value = no;
	document.detailForm.submit();
}
function productUpdate(no){
	if(confirm("정말 수정할까요?")){
		document.updateForm.no.value = no;
		document.updateForm.submit();
	}
}
function productDelete(no){
	if(confirm("정말 삭제할까요?")){
		document.delForm.no.value = no;
		document.delForm.submit();		
	}
}

//사용자에서 상품 처리
function productDetail_guest(no){ 
	document.detailFrm.no.value = no;
	document.detailFrm.submit();
}

//카트 처리용
function cartUpdate(form){
	form.flag.value = "update";
	form.submit();
}
function cartDelete(form){
	form.flag.value = "del";
	form.submit();
}

//관리자에서 주문 처리
function orderDetail(no){
	document.detailFrm.no.value = no;
	document.detailFrm.submit();
}

function orderUpdate(form){
	document.detailFrm.flag.value = "update";
	form.submit();
}

function orderDelete(form){
	document.detailFrm.flag.value = "delete";
	form.submit();
}




