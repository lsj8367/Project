<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>니책내책 메인페이지</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400|Raleway:300);
html {
  padding-top: 50px;
  font-family: 'Open Sans', Helvetica, arial, sans-serif;
  text-align: center;
  background-color: #eeeeee;
}
html *,
html *:before,
html *:after {
  box-sizing: border-box;
  -webkit-transition: 0.5s ease-in-out;
  transition: 0.5s ease-in-out;
}
html i, html em,
html b, html strong,
html span {
  -webkit-transition: none;
  transition: none;
}

*:before,
*:after {
  z-index: -1;
}


h1,
h4 {
  font-family: 'Raleway', 'Open Sans', sans-serif;
  margin: 0;
  line-height: 1;
}

a {
  text-decoration: none;
  line-height: 80px;
  color: black;
}

.centerer {
  width: 100%;
  max-width: 600px;
  margin: auto;
  padding: 0 1rem;
}

@media (min-width: 600px) {
  .wrap {
    width: 50%;
    float: left;
  }
}
[class^="btn-"] {
  position: relative;
  display: block;
  overflow: hidden;
  width: 100%;
  height: 80px;
  max-width: 250px;
  margin: 1rem auto;
  text-transform: uppercase;
  border: 1px solid currentColor;
}

.btn-0 {
  color: #3f927d;
}
.btn-0:before {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: #114c3d;
  -webkit-transform: translateX(-100%);
          transform: translateX(-100%);
}
.btn-0:hover {
  color: #c7dfd9;
}
.btn-0:hover:before {
  -webkit-transform: translateX(0);
          transform: translateX(0);
}

.btn-1 {
  color: #37aacf;
}
.btn-1:before {
  content: '';
  position: absolute;
  top: 0;
  right: -50px;
  bottom: 0;
  left: 0;
  border-right: 50px solid transparent;
  border-bottom: 80px solid #0c5d77;
  -webkit-transform: translateX(-100%);
          transform: translateX(-100%);
}
.btn-1:hover {
  color: #c4e6f1;
}
.btn-1:hover:before {
  -webkit-transform: translateX(0);
          transform: translateX(0);
}

.btn-1-2 {
  color: #4d3fa3;
}
.btn-1-2:before, .btn-1-2:after {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  border-bottom: 80px solid #1b1158;
}
.btn-1-2:before {
  right: -50px;
  border-right: 50px solid transparent;
  -webkit-transform: translateX(-100%);
          transform: translateX(-100%);
}
.btn-1-2:after {
  left: -50px;
  border-left: 50px solid transparent;
  -webkit-transform: translateX(100%);
          transform: translateX(100%);
}
.btn-1-2:hover {
  color: #cbc7e4;
}
.btn-1-2:hover:before {
  -webkit-transform: translateX(-40%);
          transform: translateX(-40%);
}
.btn-1-2:hover:after {
  -webkit-transform: translateX(40%);
          transform: translateX(40%);
}

</style>

  <script>
  window.console = window.console || function(t) {};
</script>

  
  
  <script>
  if (document.location.search.match(/type=embed/gi)) {
    window.parent.postMessage("resize", "*");
  }

  
  
  </script>


</head>
<body>
<div class="centerer">
  <img src="resources/static/images/mbook.png" width="200px;" height="110px;">
 	<br><br><br>
  <div class="wrap" style="margin: auto; float: none;">
    <a class="btn-0" href="buymain">Buy</a>
    <a class="btn-1" href="rentlist1?book=rentmain">Rent</a>

  </div>
</div>


<script src="https://static.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://s.codepen.io/assets/libs/modernizr.js'></script>
</body>
</html>