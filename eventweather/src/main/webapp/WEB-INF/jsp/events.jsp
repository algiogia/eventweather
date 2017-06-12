<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EventWeather</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

</head>
<body>
	<div class="jumbotron">
		<h1>EventWeather</h1>
		<p>Events and weather forecast</p>
	</div>
	<ul class="nav nav-tabs" role="tablist">
		<c:forEach items="${events}" var="category" varStatus="status">
			<li role="presentation" ${status.first ? 'class="active"' : ''}>
				<a href="#${category.key}" aria-controls="${category.key}" role="tab" data-toggle="tab">${category.key}</a>
			</li>
		</c:forEach>
	</ul>
	<div class="tab-content">
		<c:forEach items="${events}" var="category" varStatus="categoryStatus">
			<div role="tabpanel" class="tab-pane${categoryStatus.first ? ' active' : ''}" id="${category.key}">
				<c:forEach items="${category.value}" var="event">
					<div class="panel panel-default">
						<div class="panel-heading">${event.title}</div>
						<div class="panel-body">${event.description}</div>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</body>
</html>