<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
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
					<h2 class="page-header">For Approval</h2>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<!-- Slot for message box -->
								<%@include file="../../common/messages/messagesnotifications.jsp"%>

			<div class="row">

				<div class="col-lg-12">
					
							
						<div class="panel panel-default">
							<div class="panel-heading">
								<b>Leave Requests For Approval</b>
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>Sl.no</th>
												<th>Employee Name</th>
												<th>Request Date</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th>No. of days</th>
												<th>Half day/ Full day</th>
												<th>Date of escalation</th>
												<th>Request Status</th>
												<th>Accept</th>
												<th>Reject</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>										
											<c:when test="${empty userLeaveValueList}">
												<b>No requests</b>
												</c:when>
												<c:otherwise>
											<c:forEach var="userLeaveValueList"
												items="${userLeaveValueList}" begin="0">
												<tr>
													<td><c:out value="${userLeaveValueList.requestId}" /></td>
													<td><c:out
															value="${userLeaveValueList.firstName} ${userLeaveValueList.lastName}" />
													</td>
													<td><c:out value="${userLeaveValueList.requestDate}" /></td>
													<td><c:out value="${userLeaveValueList.startDate}" /></td>
													<td><c:out value="${userLeaveValueList.endDate}" /></td>
													<td><c:out value="${userLeaveValueList.days}" /></td>
													<td><c:out value="${userLeaveValueList.hdFd}" /></td>
													<td><c:out value="${userLeaveValueList.days}" /></td>
													<td><c:out value="${userLeaveValueList.status}" /></td>
													<td><a
														href="requestProcess.do?method=leaveRequest&process=accept&requestId=${userLeaveValueList.requestId}"><button
																type="button" class="btn btn-success">Approve</button></a></td>
													<td><a
														href="requestProcess.do?method=leaveRequest&process=reject&requestId=${userLeaveValueList.requestId}"><button
																type="button" class="btn btn-danger">Reject</button></a></td>


												</tr>
											</c:forEach>
											</c:otherwise>
											</c:choose>
										</tbody>
									</table>
								</div>
								<!-- /.table-responsive -->

							</div>
							<!-- /.panel-body -->

						</div>
					

					
				</div>



			</div>
			<!-- /.row -->
			
		

			
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>
</html>
