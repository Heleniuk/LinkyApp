<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<ul>
		<c:forEach var="child" items="${nodes}">
			<li><span> <c:url var="deleteUrl"
						value="/categories/delete/${child.id}" /> <c:url var="editUrl"
						value="/categories/edit/${child.id}" /> <cf:form method="POST"
						action="${deleteUrl}">
						<i class="glyphicon glyphicon-menu-up"></i>
						<label class="repellable-left">${child.title} <a
							class="glyphicon glyphicon-pencil repellable-left"
							href="${editUrl}"></a>
							<button class="glyphicon glyphicon-trash" type="submit"></button>
							<br>
							<div class="xxs">${child.description}</div>
						</label>
					</cf:form>
			</span>
				<ul>
					<c:forEach var="link" items="${child.links}">
						<li><a class="label label-success" href="${link.linkAddress}">
								<i class="glyphicon glyphicon-leaf"></i> ${link.title}
						</a></li>
					</c:forEach>
				</ul> <c:if test="${child.children != null}">
					<c:set var="nodes" value="${child.children}" scope="request" />
					<jsp:include page="child.jsp" />
				</c:if></li>
		</c:forEach>
	</ul>
</body>

</html>
