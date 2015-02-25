<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/environment.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>

</head>

<body>
	<script type="text/javascript">
		$('check').on('change', function() {
			var total;
			alert(total);
			$(':checkbox:checked').each(function() {
				total  = $(this).val();
			});
			
		});
	</script>
	<div id="wrapper">
		<%@include file="../../common/navbar/navbar.jspf"%>

		<div id="page-wrapper">
			<div class="row">
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<!-- Slot for message box -->
				<%@include file="../../common/messages/messagesnotifications.jsp"%>

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bell fa-fw"></i> Public Holidays
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<form action="delet" method="post">
								<div class="list-group">
									<c:forEach items="${events}" var="events">
										<a href="#" class="list-group-item"><input type="checkbox" id="check"
											name="eventId" value="${event.id}" /> <i
											class="fa fa-money fa-fw fa-1x"> </i> <strong><label>${events.holidayType}</label></strong>
											<span class="pull-right text-muted small"><em><fmt:formatDate
														pattern="dd-MM-yyyy" value="${events.date}" /></em> </span> </a>
									</c:forEach>

								</div>
								<div class="pull-center pull-right">
									<input type="button"
										class="btn btn-sm btn-primary"
										value="Delete holiday(s)" />
								</div>
							</form>
						</div>

						<!-- /.panel-body -->
					</div>

					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<!-- /.row -->

			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
</body>

</html>

