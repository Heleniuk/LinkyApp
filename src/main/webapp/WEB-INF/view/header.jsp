<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link href="<c:url value="/resources/css/links.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-treeview.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/star-rating.css"/>" media="all"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/star-rating.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/links.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap-treeview.js"/>"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LinkyApp</title>
</head>

<body>
	<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand">LinkyApp</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li id="links"><a href="/LinkyApp/links">Links</a></li>
				<li id="categories"><a href="/LinkyApp/categories">Categories</a></li>
			</ul>
		</div>
	</div>
	</nav>
</body>

</html>