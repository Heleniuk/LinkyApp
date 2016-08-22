<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<c:url var="addUrl" value="/links/add" />
	<cf:form method="POST" action="${addUrl}"
		modelAttribute="linkFormModel">
		<div id="addLinkForm" class="repellable-bottom">
			<cf:errors path="linkAddress" cssClass="error" />
			<cf:input path="linkAddress" class="form-control repellable-bottom"
				placeholder="Link address" />
			<cf:errors path="title" cssClass="error" />
			<cf:input path="title" class="form-control repellable-bottom"
				placeholder="Link Title" />
			<cf:errors path="description" cssClass="error" />
			<cf:textarea path="description"
				class="form-control repellable-bottom"
				placeholder="Link Description" />
			<cf:errors path="categories" cssClass="error" />
			<cf:select path="categories" class="selectpicker repellable-bottom"
				data-width="100%" data-live-search="true" multiple="true"
				title="Category">
				<cf:options items="${categories}" itemLabel="title" itemValue="id" />
			</cf:select>
			<label for="input-7-xs" class="control-label repellable-bottom">Rate
				this link </label>
			<cf:errors path="rating" cssClass="error" />
			<cf:input id="input-7-xs" path="rating"
				class="rating rating-loading repellable-bottom" value="0"
				data-min="0" data-max="5" data-step="1" data-size="xs" />
			<button class="btn btn-md btn-success submittable repellable-bottom"
				type="submit">Add link</button>
		</div>
	</cf:form>
</body>

</html>