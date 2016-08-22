<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="header.jsp" />

<body page="categories">
	<div class="top-panel-categories">
		<div class="row">
			<div class="col-xs-11"></div>
			<div class="col-xs-1">
				<!-- add button -->
				<c:url var="defaultUrl" value="/categories" />
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
							<li><c:url var="editUrl" value="/categories/edit/${root.id}" />
								<c:url var="deleteUrl" value="/categories/delete/${root.id}" />
								<span> <cf:form method="POST" action="${deleteUrl}">
										<i class="glyphicon glyphicon-menu-up"></i>
										<label class="repellable-left">${root.title} <a
											class="glyphicon glyphicon-pencil repellable-left"
											href="${editUrl}"></a>
											<button class="glyphicon glyphicon-trash" type="submit"></button>
											<br>
											<div class="xxs">${root.description}</div>
										</label>
									</cf:form>
							</span>
								<ul>
									<c:forEach var="link" items="${root.links}">
										<li><a class="label label-success repellable-left"
											href="${link.linkAddress}"> <i
												class="glyphicon glyphicon-leaf"></i>${link.title}
										</a></li>
									</c:forEach>
								</ul> <!-- build category tree recursively --> <c:set var="nodes"
									value="${root.children}" scope="request" /> <jsp:include
									page="child.jsp" /></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- category tree ends -->
			<div class="col-md-4">
				<!-- update form -->
				<c:if test="${update == true}">
					<jsp:include page="updateCategory.jsp" />
				</c:if>
				<!-- update form ends -->
				<!-- add form -->
				<c:if test="${add == true}">
					<jsp:include page="addCategory.jsp" />
				</c:if>
				<!-- add form ends -->
			</div>
		</div>
	</div>
</body>

</html>