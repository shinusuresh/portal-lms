<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:if test="${empty divNeeded}">
	<div class="col-lg-12">
</c:if>

<c:choose>
	<c:when test="${not empty notification }">
		<c:choose>
			<c:when test="${'SUCCESS' eq notification.status }">
				<div class="alert alert-info alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">×</button>
					<c:out value="${notification.message}" />
					<!-- <a href="#" class="alert-link">Alert Link</a>. -->
				</div>
			</c:when>
			<c:when test="${'ERROR' eq notification.status }">
				<div class="alert alert-danger alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">×</button>
					Error :
					<c:out value="${notification.message}" />
				</div>
			</c:when>
			<c:when test="${'WARNING' eq notification.status }">
				<div class="alert alert-warning alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">×</button>
					Warning :
					<c:out value="${notification.message}" />
				</div>
			</c:when>
		</c:choose>
	</c:when>

</c:choose>
<c:if test="${empty divNeeded}">
	</div>
</c:if>