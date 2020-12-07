<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .menu a{cursor:pointer;}
    .menu .hide{display:none;}
</style>
<script>
$(document).ready(function(){
    // menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
    $(".menu>a").click(function(){
        var submenu = $(this).next("ul");
 
        // submenu 부드럽게 움직이기
        if( submenu.is(":visible") ){
              submenu.slideUp();
        }else{
                submenu.slideDown();
        }
    });
});
</script>

</head>
<body>

<div style="float: left;">
		<div>
			<ul style="list-style: none;">
				<li class="menu"><a>장르별보기</a>
					<ul class="hide" style="list-style: none;">
						<li><a href="rentmain?book=a" target="_self">미분류(default)</a></li>
						<li><a href="rentmain?book=b" target="_self">소설/시/희곡</a></li>
						<li><a href="rentmain?book=c" target="_self">에세이</a></li>
						<li><a href="rentmain?book=d" target="_self">어린이</a></li>
						<li><a href="rentmain?book=e" target="_self">유아</a></li>
						<li><a href="rentmain?book=f" target="_self">경제경영</a></li>
						<li><a href="rentmain?book=g" target="_self">인문학</a></li>
						<li><a href="rentmain?book=h" target="_self">경제경영</a></li>
						<li><a href="rentmain?book=i" target="_self">사회과학</a></li>
						<li><a href="rentmain?book=j" target="_self">수험서/자격증</a></li>
						<li><a href="rentmain?book=k" target="_self">대학교재</a></li>
						<li><a href="rentmain?book=l" target="_self">컴퓨터/모바일</a></li>
						<li><a href="rentmain?book=rentmain" target="_self">전체보기</a></li>
					</ul>
				</li>
			</ul>
		</div>
</div>

</body>
</html>