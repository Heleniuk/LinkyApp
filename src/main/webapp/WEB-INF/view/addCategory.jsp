<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<c:url var="addUrl" value="/categories/add" />
	<cf:form id="addCategoryForm" action="${addUrl}" method="POST"
		modelAttribute="categoryFormModel">
		<cf:errors path="title" cssClass="error" />
		<cf:input path="title" class="form-control repellable-bottom"
			placeholder="Category Title" />
		<cf:select class="selectpicker repellable-bottom" data-width="100%"
			data-live-search="true" path="parentId" title="Parent category">
			<cf:options items="${parentCategories}" itemLabel="title"
				itemValue="id" />
		</cf:select>
		<cf:errors path="description" cssClass="error" />
		<cf:textarea path="description" class="form-control repellable-bottom"
			placeholder="Category Description" />
		<button class="btn btn-md btn-success submittable repellable-bottom"
			type="submit">Add category</button>
	</cf:form>
</body>

</html>