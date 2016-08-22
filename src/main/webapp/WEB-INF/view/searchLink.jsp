<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<c:url var="searchUrl" value="/links/search" />
	<cf:form id="searchLinkForm" method="GET" action="${searchUrl}"
		modelAttribute="linkFormModel">
		<cf:input path="linkAddress" class="form-control repellable-bottom"
			placeholder="Link address contains" required="" />
		<cf:input path="title" class="form-control repellable-bottom"
			placeholder="Title contains" />
		<cf:textarea path="description" class="form-control repellable-bottom"
			placeholder="Description contains" />
		<cf:select path="categories" class="selectpicker repellable-bottom"
			data-width="100%" data-live-search="true" multiple="true"
			title="Category">
			<cf:options items="${categories}" itemLabel="title" itemValue="id" />
		</cf:select>
		<cf:hidden path="rating" />
		<button class="btn btn-md btn-primary submittable" type="submit">Search</button>
	</cf:form>
</body>

</html>