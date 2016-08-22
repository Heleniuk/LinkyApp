<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="header.jsp"/>

<body page="links">	
	<div class="top-panel-links">
		<div class="row">
			<div class="col-md-11"></div>
			<div class="col-xs-1">
				<div class="row">
					<c:url var="addUrl" value="/links/add"/>	
					<c:url var="searchUrl" value="/links/search"/>
					<c:url var="defaultUrl" value="/links"/>
					<!-- add button -->		
					<a href="${addUrl}">
						<button class="btn btn-md btn-default plus" type="button">
							<span class="glyphicon glyphicon-plus" aria-hidden="false"></span>
						</button>
					</a>			
					<!-- search button -->
					<a href="${defaultUrl}">
						<button class="btn btn-md btn-default search" type="button">
							<span class="glyphicon glyphicon-search" aria-hidden="false"></span>
						</button>
					</a>
				</div>
			</div>	
		</div>	
	</div>
	<div class="bottom-panel">	
		<div class="row">
			<!-- search results go here -->
			<div class="col-md-8">
				<div class="container">
					<div class="row">				
						<c:forEach var="link" items="${searchResults}">
							<div class="col-sm-4">															
								<c:url var="editUrl" value="/links/edit/${link.id}"/>
								<c:url var="deleteUrl" value="/links/delete/${link.id}"/>
								<cf:form method="POST" action="${deleteUrl}">
									<div class="title title-cell">${link.title}	
										<a class="glyphicon glyphicon-pencil repellable-left" href="${editUrl}"></a>
										<button class="glyphicon glyphicon-trash" type="submit"></button>	
									</div>
								</cf:form>											
								<div class="categories-cell row">
									<div class="col-md-12">
										<c:forEach var="category" items="${link.categories}">
											<span class="label label-info">${category.title}</span>
										</c:forEach>
									</div>
								</div>
								<p>								
								<div class="desc-cell">${link.description}</div>
								<p>
								<input class="rating" value="${link.rating}" data-min="0" data-max="5" data-step="1" data-size="xxs"
								data-display-only="true">								
								<p>
								<a class="btn btn-sm btn-default" href="${link.linkAddress}" role="button">View link</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<!-- Update form -->
				<c:if test="${update == true}">
					<jsp:include page="updateLink.jsp"/>
				</c:if>	
				<!-- end of update form -->
				<!-- add form -->
				<c:if test="${add == true}">
					<jsp:include page="addLink.jsp"/>
				</c:if>	
				<!-- end of add form -->
				<!-- search form -->
				<c:if test="${search == true}">
					<jsp:include page="searchLink.jsp"/>
				</c:if>	
				<!-- end of search form -->
			</div>
		</div>
		<!-- column ends -->
	<!-- row ends -->
	</div>
</body>

</html>