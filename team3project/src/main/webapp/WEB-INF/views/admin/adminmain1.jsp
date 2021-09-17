<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<style type="text/css">   
    @font-face {
    font-family: 'TmoneyRoundWindRegular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

body{
	font-family: "TmoneyRoundWindRegular";
}
</style> 
    
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/aos.css">
    <link rel="stylesheet" href="css/style.css">
  </head>
  <%@include file="admin_header.jsp"%>
  <body>
    
    
    <!-- END nav -->
    
    <section class="home-slider ftco-degree-bg">
      <div class="slider-item">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center justify-content-center">
            <div class="col-md-10 ftco-animate text-center">
              <h1 class="mb-4">Manage 
                <strong class="typewrite" data-period="4000" data-type='[ "NewBook.", "OldBook.", "User.", "Order." ]'>
                  <span class="wrap"></span>
                </strong>
              </h1>
              <p>MYB(니북내북) 사이트는 <br>새책을 판매하기도 기부받은 책을 대여하기도 하면서<br> 책의 선순환을  앞장서 권장하는 사이트입니다.<br> 
              많은 사람들의 참여를 바라며 <br>나에게는 집 안 공간차지만 하는 쓸모없는 책이<br> 다른 누군가에게는 도움이 될 수 있는<br> 소중한 도서가 될 수 있습니다.<br> 우리 사이트의 관리자로서<br> 책의 선순환을 확산시키는데에<br> 노력해주시길 부탁드리겠습니다.</p>
              
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->

    <section class="ftco-section-featured ftco-animate">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="carousel owl-carousel" style="width : 70%; margin: auto;">
              <div class="item">
                <a href="">
                  <img src="images/bookstore.jpg" class="img-fluid" alt="">
                </a>
              </div>
              <div class="item">
                <a href="">
                  <img src="images/library.jpg" class="img-fluid" alt="">
                </a>
              </div>
              <div class="item">
                <a href="">
                  <img src="images/oldbookstore.jpg" class="img-fluid" alt="">
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>


  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/jquery.timepicker.min.js"></script>
  <script src="js/adminmain1.js"></script>
</body>
</html>