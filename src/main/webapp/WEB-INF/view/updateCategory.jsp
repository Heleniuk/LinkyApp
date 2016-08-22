<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<c:url var="updateUrl" value="/categories/update" />
	<cf:form action="${updateUrl}" method="POST"
		modelAttribute="categoryFormModelToUpdate">
		<cf:hidden path="id" value="${categoryFormModelToUpdate.id}" />
		<cf:errors path="title" cssClass="error" />
		<cf:input path="title" class="form-control repellable-bottom"
			placeholder="Category Title"
			value="${categoryFormModelToUpdate.title}" />
		<cf:select class="selectpicker repellable-bottom" data-width="100%"
			data-live-search="true" path="parentId" title="Parent category">
			<cf:options items="${parentCategories}" itemLabel="title"
				itemValue="id" />
		</cf:select>
		<cf:errors path="description" cssClass="error" />
		<cf:textarea path="description" class="form-control repellable-bottom"
			placeholder="Category Description"
			value="${categoryFormModelToUpdate.description}" />
		<button class="btn btn-md btn-warning submittable" type="submit">Update
			category</button>
	</cf:form>
</body>

</html>