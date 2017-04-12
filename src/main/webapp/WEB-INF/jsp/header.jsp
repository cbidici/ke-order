<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html ng-app="keorder">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="./resources/bootstrap/bootstrap.min.css?3.3.1">

<style type="text/css">
body {
	padding-top: 50px;
}

.starter-template {
	padding: 40px 15px;
	text-align: lef;
}
</style>

<script src="./resources/jquery/jquery.min.js?1.11.1"></script>
<script src="./resources/bootstrap/bootstrap.min.js?3.3.1"></script>
<script src="./resources/djs/constants.djs?0.0.1"></script>
<script src="./resources/angular/angular.min.js?1.3.14"></script>
<script src="./resources/js/ke-app.js?0.0.1"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// set active menu item css
		$(".navbar-nav > .active").removeClass("active");
		$("#${page}").addClass("active");
	});
</script>

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>Kahveci Efendi</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Menü</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Kahveci Efendi</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li id="index" class="active"><a href="./index.html">Anasayfa</a></li>
					<li id="order" ><a href="./order.html">Sipariş</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">