<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/environment.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
</head>

<body>

	<div id="wrapper">
		<%@include file="../../common/navbar/navbar.jspf"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Detailed Log</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<!-- Slot for message box -->
				<%@include file="../../common/messages/messagesnotifications.jsp"%>

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Detailed Log</div>
						<!-- /.panel-heading -->

						<div class="panel-body">
							<div class="table-responsive">
								<div>
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<c:set var="count" scope="session" value="${0}" />
											<tr>
												<th>SI:NO</th>
												<th>Year</th>
												<th>Request Date</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th>Days</th>
												<th>Status</th>
												<th>Date Of Escalation</th>
												<th>Approver id</th>
												<th>Marked By</th>
												<th>Half day/Full day</th>
												<th>Description</th>
												<th>Type</th>
												<th>Type Of Request</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${leaves}" var="leaves">
												<tr class="odd gradeX">
													<c:set var="count" scope="session" value="${count+1}" />
													<td><c:out value="${count}" /></td>
													<td>${leaves.year}</td>
													<td>${leaves.requestDate}</td>
													<td>${leaves.startDate}</td>
													<td>${leaves.endDate}</td>
													<td>${leaves.days}</td>
													<td>${leaves.status}</td>
													<td>${leaves.dateOfEscalation}</td>
													<td>${leaves.approver.firstName}</td>
													<td>${leaves.markedBy}</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.description}</td>
													<td>not applicable</td>
													<td>${leaves.typeOfRequest}</td>
											</c:forEach>

											<c:forEach items="${workFromHomeRequests}" var="leaves">
												<tr class="odd gradeX">
													<c:set var="count" scope="session" value="${count+1}" />
													<td><c:out value="${count}" /></td>

													<td>${leaves.year}</td>
													<td>${leaves.requestDate}</td>
													<td>${leaves.startDate}</td>
													<td>${leaves.endDate}</td>
													<td>${leaves.days}</td>
													<td>${leaves.status}</td>
													<td>not applicable</td>
													<td>${leaves.approver.firstName}</td>
													<td>${leaves.markedBy}</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.description}</td>
													<td>not applicable</td>
													<td>${leaves.typeOfRequest}</td>
											</c:forEach>

											<c:forEach items="${bankHolidayWorkingRequests}" var="leaves">
												<tr class="odd gradeX">
													<c:set var="count" scope="session" value="${count+1}" />
													<td><c:out value="${count}" /></td>


													<td>${leaves.year}</td>
													<td>${leaves.requestDate}</td>
													<td>${leaves.startDate}</td>
													<td>${leaves.endDate}</td>
													<td>${leaves.days}</td>
													<td>${leaves.status}</td>
													<td>not applicable</td>
													<td>${leaves.approver.firstName}</td>
													<td>not applicable</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.description}</td>
													<td>${leaves.type}</td>
													<td>${leaves.typeOfRequest}</td>
												</tr>
											</c:forEach>

											<c:forEach items="${weekendWorkingRequests}" var="leaves">
												<tr class="odd gradeX">
													<c:set var="count" scope="session" value="${count+1}" />
													<td><c:out value="${count}" /></td>


													<td>${leaves.year}</td>
													<td>${leaves.requestDate}</td>
													<td>${leaves.startDate}</td>
													<td>${leaves.endDate}</td>
													<td>${leaves.days}</td>
													<td>${leaves.status}</td>
													<td>not applicable</td>
													<td>${leaves.approver.firstName}</td>
													<td>not applicable</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.description}</td>
													<td>${leaves.type}</td>
													<td>${leaves.typeOfRequest}</td>
												</tr>
											</c:forEach>


										</tbody>

									</table>
								</div>
							</div>
							<!-- /.table-responsive -->

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

	<!-- DataTables JavaScript -->
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-example').dataTable();
		});
	</script>

</body>

</html>


