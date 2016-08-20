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

<body page="categories">
	<jsp:include page="header.jsp"/>
	<div class="top-panel-categories">
		<div class="row">
			<div class="col-xs-11">
			</div>
			<div class="col-xs-1">
				<!-- add button -->
				<c:url var="defaultUrl" value="/categories/default"/>
				<a href="${defaultUrl}">
					<button class="btn btn-md btn-default" type="button">
						<span class="glyphicon glyphicon-plus"></span>
					</button>	
				</a>
			</div>	
		</div>	
	</div>
	<div class="bottom-panel">
		<div class="row">
			<!-- category tree starts here -->
			<div class="col-md-8">
				<div class="tree">
					<ul>
						<c:forEach var="root" items="${roots}">
							<li>																					
								<c:url var="editUrl" value="/categories/edit/${root.id}"/>	
								<c:url var="deleteUrl" value="/categories/delete/${root.id}"/>
								<span>
									<cf:form method="POST" action="${deleteUrl}">
										<i class="glyphicon glyphicon-menu-up"></i>	
										<label class="repellable-left">${root.title}
											<a class="glyphicon glyphicon-pencil repellable-left" href="${editUrl}"></a>
											<button class="glyphicon glyphicon-trash" type="submit"></button>				
											<br>
											<div class="xxs">${root.description}</div>
										</label>									
									</cf:form>								
								</span>	
								<ul>
									<c:forEach var="link" items="${root.links}">
										<li>							
											<a class ="label label-success repellable-left" href="${link.linkAddress}">
												<i class="glyphicon glyphicon-leaf"></i>${link.title}
											</a>			
										</li>
									</c:forEach>
								</ul>
								<!-- build category tree recursively -->
								<c:set var="nodes" value="${root.children}" scope="request"/>
									<jsp:include page="child.jsp"/>						
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>			
			<!-- category tree ends -->
			<div class="col-md-4">
				<!-- update form -->
				<c:if test="${update == true}">
					<jsp:include page="updateCategory.jsp"/>
				</c:if>	
				<!-- update form ends -->
				<!-- add form -->			
				<c:if test="${add == true}">
					<jsp:include page="addCategory.jsp"/>
				</c:if>
				<!-- add form ends -->
			</div>
		</div>
	</div>
</body>

</html>