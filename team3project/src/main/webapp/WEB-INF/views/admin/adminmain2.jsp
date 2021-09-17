<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="font/icomoon/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/aos2.css">
<link rel="stylesheet" href="css/style2.css">

</head>
<body data-spy="scroll" data-target=".site-navbar-target"
	data-offset="300">


	<div class="site-section" id="features-section">
		<div class="container">

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
							<c:set var="total" value="${total + np.order_profit }" />
							<c:out value="${total }" />원
							<p>
								<a href="#">Learn More</a>
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
								<a href="#">Learn More</a>
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
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
								Perferendis quis molestiae vitae eligendi at.</p>
							<p>
								<a href="#">Learn More</a>
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
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
								Perferendis quis molestiae vitae eligendi at.</p>
							<p>
								<a href="#">Learn More</a>
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
							<table style="text-align: center; margin: auto;" border='1'>
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
							<table style="text-align: center; margin:auto;" border='1'> 
								<tr>
									<th>순위</th><th>id</th><th>주문자</th><th>구매횟수</th><th>구매책수</th>
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
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
								Perferendis quis molestiae vitae eligendi at.</p>
							<p>
								<a href="#">Learn More</a>
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
							<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
								Perferendis quis molestiae vitae eligendi at.</p>
							<p>
								<a href="#">Learn More</a>
							</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<table>
		<tr>
			<td>이달 순수익<br> #####원<br> 판매금액<br> #####원
			</td>
		</tr>
		<tr>
			<td>이달의 구매왕<br> 1등 누구 id<br> 2등 누구 id<br> 3등 누구
				id<br>
			</td>
			<td>이달의 판매왕<br> 1등 누구 id<br> 2등 누구 id<br> 3등 누구
				id<br>
			</td>
		</tr>
		<tr>
			<td>가장 많이 팔린 책<br> 책번호<br> 이름<br> 저자<br> 출판사<br>
				판매량<br>
			</td>
			<td>가장 많이 대여한 책<br> 책번호<br> 이름<br> 기증자<br>
				횟수
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<h1>TOP3</h1>
			</td>
		</tr>
		<tr>
			<td>이달의 베스트셀러<br> 1등 누구 id<br> 2등 누구 id<br> 3등 누구
				id<br>
			</td>
			<td>이달의 베스트대여도서<br> 1등 누구 id<br> 2등 누구 id<br> 3등
				누구 id<br>
			</td>
		</tr>
	</table>
</body>

<script src="js/jquery-ui.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/aos2.js"></script>
<script src="js/adminmain2.js"></script>
</html>