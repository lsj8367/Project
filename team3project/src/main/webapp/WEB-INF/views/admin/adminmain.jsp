<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body{
padding-top: 80px;
}

.m1{
	text-align: center; 
	margin: auto;
	width: 100%;
}

.m1 th{
	border-bottom: 2px solid;
}

.m1 tr{
	border-bottom: 1px solid;
}
@media screen and (max-width: 1060px) {
body{
padding-top:60px;
}
}
</style>

<title>Insert title here</title>
<link rel="stylesheet" href="resources/font/icomoon/style.css">

<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">

<link rel="stylesheet" href="resources/css/aos2.css">

<link rel="stylesheet" href="resources/css/style2.css">

</head>
<%@include file="admin_header.jsp"%>
<body data-spy="scroll" data-target=".site-navbar-target"
	data-offset="300">
<%@include file="adminmain1.jsp"%>

	<div class="site-section" id="features-section">
		<div class="container" style="font-size: 15px;">

			<div class="row align-items-stretch">
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up">

					<div class="unit-4 d-block">
						<div class="unit-4-icon mb-3">
							<span class="icon-wrap"><span
								class="text-primary icon-autorenew"></span></span>
						</div>
						<div>
							<h3>이달 순수익</h3>
							<c:set var="total" value="0"/>
							<c:set var="total" value="${total + rp.order_profit }" />
							<c:out value="${total }" />원
							<p>
								
							</p>
						</div>
					</div>

				</div>
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
					data-aos-delay="100">

					<div class="unit-4 d-block">
						<div class="unit-4-icon mb-3">
							<span class="icon-wrap"><span
								class="text-primary icon-store_mall_directory"></span></span>
						</div>
						<div>
							<h3>이달 판매금액</h3>
								 <c:set var="total" value="0"/>
								  
									<c:set var="total" value="${total + np.order_profit }" />
							
								   
									<c:set var="total" value="${total + op.order_profit }" />
								
									
									<c:out value="${total }" />원
								    
							<p>
								
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
					data-aos-delay="200">
					<div class="unit-4 d-block">
						<div class="unit-4-icon mb-3">
							<span class="icon-wrap"><span
								class="text-primary icon-shopping_basket"></span></span>
						</div>
						<div>
							<h3>가장 많이 팔린 책</h3>
							<table class="m1">
								<tr>
									<th>번호</th>
									<th>이름</th>
									<th>저자</th>
									<th>횟수</th>
								</tr>
								<c:forEach var="bs" items="${bsb }">
									<tr>
										<td>${bs.nb_no}</td>
										<td>${bs.nb_name}</td>
										<td>${bs.nb_author }</td>
										<td>${bs.nb_scount }</td>
									</tr>
								</c:forEach>
								</table>
							<p>
								
							</p>
						</div>
					</div>
				</div>


				<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up">
					<div class="unit-4 d-block">
						<div class="unit-4-icon mb-3">
							<span class="icon-wrap"><span
								class="text-primary icon-settings_backup_restore"></span></span>
						</div>
						<div>
							<h3>가장 많이 대여한 책</h3>
							<table class="m1">
								<tr>
									<th>번호</th>
									<th>이름</th>
									<th>기증자</th>
									<th>횟수</th>
								</tr>
								<c:forEach var="b" items="${brb }">
									<tr>
										<td>${b.ob_no}</td>
										<td>${b.ob_name}</td>
										<td>${b.ob_donor }</td>
										<td>${b.ob_scount }</td>
									</tr>
								</c:forEach>
								</table>
							<p>
								
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
					data-aos-delay="100">
					<div class="unit-4 d-block">
						<div class="unit-4-icon mb-3">
							<span class="icon-wrap"><span
								class="text-primary icon-sentiment_satisfied"></span></span>
						</div>
						<div>
							<h3>이달의 대여왕</h3>
							<table class="m1">
								<tr>
									<th>순위</th>
									<th>회원id</th>
									<th>회원명</th>
									<th>대여횟수</th>
								</tr>
								<c:forEach var="ru" items="${ru }">
									<tr>
										<td>${ru.rn }등</td>
										<td>${ru.user_id }</td>
										<td>${ru.user_name }</td>
										<td>${ru.count }</td>
									</tr>
								</c:forEach>
								</table>
								<p>
									<a href="monthrenter">Learn More</a>
								</p>
						</div>
					</div>


				</div>

				<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
					data-aos-delay="200">
					<div class="unit-4 d-block">
						<div class="unit-4-icon mb-3">
							<span class="icon-wrap"><span
								class="text-primary icon-power"></span></span>
						</div>
						<div>
							<h3>이달의 구매왕</h3>
							<table class="m1"> 
								<tr>
									<th>순위</th><th>id</th><th>주문자</th><th>횟수</th><th>도서수</th>
							 	</tr>
							 	<c:forEach var="bu" items="${bu }">
							 	<tr>
							 		<td>
							 			${bu.rn }등
							 		</td>
							 		<td>
							 		<c:choose>
							 			<c:when test="${empty bu.user_id}">
							 				비회원
							 			</c:when>
							 			<c:otherwise>
							 				${bu.user_id }
							 			</c:otherwise>
							 		</c:choose>
							 		</td>
							 		<td>${bu.order_person }</td>
							 		<td>${bu.count }</td>	
							   		<td>${bu.sum }</td>  
							 	</tr>
							 	</c:forEach>	
							</table> 
							<p>
								<a href="monthbuyer">Learn More</a>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
					data-aos-delay="200">
					<div class="unit-4 d-block">
						<div class="unit-4-icon mb-3">
							<span class="icon-wrap"><b style="color: red;">TOP3</b><span
								class="text-primary icon-power"></span></span>
						</div>
						<div>
							<h3>이달의 베스트 셀러</h3>
							<table class="m1"> 
							   
								<tr>
									<th>순위</th><th>번호</th><th>이름</th><th>판매량</th>
							 	</tr>
							 	<c:forEach var="ol" items="${om }" varStatus="status">
							 	<tr>
							 		<td>
							 			${ol.rn }위
							 		</td>
							 		<td>${ol.nb_no }</td>
							 		<td>${ol.nb_name }</td>
							 		<td>${ol.scount }
							   		</td>  
							 	</tr>
							  	
							 	</c:forEach>
							 	
							</table> 
							<p>
								<a href="bestsellerset">Learn More</a>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-lg-4 mb-4 mb-lg-4" data-aos="fade-up"
					data-aos-delay="200">
					<div class="unit-4 d-block">
						<div class="unit-4-icon mb-3">
							<span class="icon-wrap"><b style="color: red;">TOP3</b><span
								class="text-primary icon-power"></span></span>
						</div>
						<div>
							<h3>이달의 베스트 대여도서</h3>
							<table class="m1">
								<tr>
									<th>순위</th><th>번호</th><th>이름</th><th>대여량</th>
							 	</tr>
							 	<c:forEach var="rtl" items="${rtm }" varStatus="status">
							 	<tr>
							 		<td>
							 			${rtl.rn }위
							 		</td>
							 		<td>${rtl.rent_no }</td>
							 		<td>${rtl.ob_name }</td>	
							   		<td>${rtl.count }</td>  
							 	</tr>
							 	</c:forEach>
							 	
							</table>
							<p>
								<a href="bestrentbookset">Learn More</a>
							</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>

<script src="resources/js/jquery-ui.js"></script>

<script src="resources/js/bootstrap.min.js"></script>

<script src="resources/js/aos2.js"></script>

<script src="resources/js/adminmain2.js"></script>

<%@include file="admin_footer.jsp"%>
</html>