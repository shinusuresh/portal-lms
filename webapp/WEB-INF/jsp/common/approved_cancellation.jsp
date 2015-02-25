<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="commonheadinclude.jspf"%>
<script type="text/javascript">
function myFunctionLeave(requestId)
  {
	$('#myModal2 #request-id').val(requestId);
  }
function myFunctionBank(requestId)
{
	$('#myModal1 #request-id').val(requestId);
}
function myFunctionWork(requestId)
{
	$('#myModal3 #request-id').val(requestId);
}
function myFunctionWeek(requestId)
{
	$('#myModal4 #request-id').val(requestId);
}


</script>
</head>

<body>

	<div id="wrapper">
		<%@include file="navbar/navbar.jspf"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h2 class="page-header">For approval of cancellation of
						approved request</h2>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<!-- Slot for message box -->
			<%@include file="messages/messagesnotifications.jsp"%>
			<div class="row">

				<div class="col-lg-12">

					<div class="panel panel-default">
						<div class="panel-heading">

							<b>Bank Holiday Requests</b>
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
											<th>Request Status</th>
											<th>Accept</th>
											<th>Reject</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty userBankHolidayCancellationValueList}">
												<b>No requests</b>
											</c:when>
											<c:otherwise>
												<c:forEach var="userBankHolidayCancellationValueList"
													items="${userBankHolidayCancellationValueList}" begin="0">
													<tr>
														<td><c:out
																value="${userBankHolidayCancellationValueList.requestId}" /></td>
														<td><c:out
																value="${userBankHolidayCancellationValueList.firstName} ${userBankHolidayCancellationValueList.lastName}" />
														</td>
														<td><c:out
																value="${userBankHolidayCancellationValueList.requestDate}" /></td>
														<td><c:out
																value="${userBankHolidayCancellationValueList.startDate}" /></td>
														<td><c:out
																value="${userBankHolidayCancellationValueList.endDate}" /></td>
														<td><c:out
																value="${userBankHolidayCancellationValueList.days}" /></td>
														<td><c:out
																value="${userBankHolidayCancellationValueList.status}" /></td>
														<td><a
															href="cancelRequestProcess.do?method=cancelBankHolidayWorkingRequest&process=CancelApprovedRequest&requestId=${userBankHolidayCancellationValueList.requestId}"><button
																	type="button" class="btn btn-success">Approve</button></a></td>
														<td>
															<%-- <a
															href="cancelRequestProcess.do?method=cancelBankHolidayWorkingRequest&process=abort&requestId=${userBankHolidayCancellationValueList.requestId}"> --%>
															<button type="button" data-toggle="modal"
																data-target="#myModal1" class="btn btn-danger"
																value="${userBankHolidayCancellationValueList.requestId}"
																Onclick="myFunctionBank(${userBankHolidayCancellationValueList.requestId})">Reject</button>

														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
							<!-- Button trigger modal -->

							<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Bank Holiday Working Request <span id="type"></span>
											</h4>
										</div>
										<form name="bankHolidayWorking"
											action="cancelRequestProcess.do?method=cancelBankHolidayWorkingRequest&process=abort"
											method="post">
											<div class="modal-body clearfix">
												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" /> <label
														class="col-sm-4 control-label">Comments</label>
													<textarea cols="30" rows="3" name="Comments"></textarea>
													<div class="col-md-2 col-md-offset-5">
														<button name="bankHolidayWorkingRequest" type="submit"
															class="btn btn-danger btn-sm">Reject</button>
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->


					<div class="panel panel-default">
						<div class="panel-heading">
							<b>Leave Requests </b>
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
											<c:when test="${empty userLeaveCancellationValueList}">
												<b>No requests</b>
											</c:when>
											<c:otherwise>
												<c:forEach var="userLeaveCancellationValueList"
													items="${userLeaveCancellationValueList}" begin="0">
													<tr>
														<td><c:out
																value="${userLeaveCancellationValueList.requestId}" /></td>
														<td><c:out
																value="${userLeaveCancellationValueList.firstName} ${userLeaveCancellationValueList.lastName}" />
														</td>
														<td><c:out
																value="${userLeaveCancellationValueList.requestDate}" /></td>
														<td><c:out
																value="${userLeaveCancellationValueList.startDate}" /></td>
														<td><c:out
																value="${userLeaveCancellationValueList.endDate}" /></td>
														<td><c:out
																value="${userLeaveCancellationValueList.days}" /></td>
														<td><c:out
																value="${userLeaveCancellationValueList.hdFd}" /></td>
														<td><c:out
																value="${userLeaveCancellationValueList.days}" /></td>
														<td><c:out
																value="${userLeaveCancellationValueList.status}" /></td>
														<td><a
															href="cancelRequestProcess.do?method=cancelLeaveRequest&process=CancelApprovedRequest&requestId=${userLeaveCancellationValueList.requestId}"><button
																	type="button" class="btn btn-success">Approve</button></a></td>
														<td>
															<%-- <a
															href="cancelRequestProcess.do?method=cancelLeaveRequest&process=abort&requestId=${userLeaveCancellationValueList.requestId}"> --%>
															<button type="button" data-target="#myModal2"
																data-toggle="modal" class="btn btn-danger"
																value="${userLeaveCancellationValueList.requestId}"
																Onclick="myFunctionLeave(${userLeaveCancellationValueList.requestId})">Reject</button>
															<!-- </a> -->
														</td>

													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
							<!-- Button trigger modal -->

							<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Leave Request <span id="type"></span>
											</h4>
										</div>
										<form name="leaverequest"
											action="cancelRequestProcess.do?method=cancelLeaveRequest&process=abort"
											method="post">
											<div class="modal-body clearfix">
												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" /> <label
														class="col-sm-4 control-label">Comments</label>
													<textarea cols="30" rows="3" name="Comments"></textarea>
													<div class="col-md-2 col-md-offset-5">
														<button name="leaveRequest" type="submit"
															class="btn btn-danger btn-sm">Reject</button>
													</div>
												</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- /.panel-body -->

					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<b>Weekend Working Request</b>
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
											<th>Request Status</th>
											<th>Accept</th>
											<th>Reject</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when
												test="${empty userWeekendWorkingCancellationValueList}">
												<b>No requests</b>
											</c:when>
											<c:otherwise>
												<c:forEach var="userWeekendWorkingCancellationValueList"
													items="${userWeekendWorkingCancellationValueList}"
													begin="0">
													<tr>
														<td><c:out
																value="${userWeekendWorkingCancellationValueList.requestId}" /></td>
														<td><c:out
																value="${userWeekendWorkingCancellationValueList.firstName} ${userWeekendWorkingCancellationValueList.lastName}" />
														</td>
														<td><c:out
																value="${userWeekendWorkingCancellationValueList.requestDate}" /></td>
														<td><c:out
																value="${userWeekendWorkingCancellationValueList.startDate}" /></td>
														<td><c:out
																value="${userWeekendWorkingCancellationValueList.endDate}" /></td>
														<td><c:out
																value="${userWeekendWorkingCancellationValueList.days}" /></td>
														<td><c:out
																value="${userWeekendWorkingCancellationValueList.status}" /></td>
														<td><a
															href="cancelRequestProcess.do?method=cancelWeekendWorkingRequest&process=CancelApprovedRequest&requestId=${userWeekendWorkingCancellationValueList.requestId}"><button
																	type="button" class="btn btn-success">Approve</button></a></td>
														<td>
															<button type="button" data-toggle="modal"
																data-target="#myModal4" class="btn btn-danger"
																value="${userWeekendWorkingCancellationValueList.requestId}"
																Onclick="myFunctionWeek(${userWeekendWorkingCancellationValueList.requestId})">Reject</button>
															<!-- </a> -->
														</td>


													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->

							<!-- Button trigger modal -->
							<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Weekend Working Request <span id="type"></span>
											</h4>
										</div>
										<form name="weekendWorking"
											action="cancelRequestProcess.do?method=cancelWeekendWorkingRequest&process=abort"
											method="post">
											<div class="modal-body clearfix">

												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" /> <label
														class="col-sm-4 control-label">Comments</label>
													<textarea cols="30" rows="3" name="Comments"></textarea>
													<div class="col-md-2 col-md-offset-5">
														<button name="weekendWorking" type="submit"
															class="btn btn-danger btn-sm">Reject</button>
													</div>
												</div>

											</div>
										</form>
									</div>
								</div>
							</div>

						</div>
						<!-- /.panel-body -->

					</div>
					<!-- /.panel -->



					<div class="panel panel-default">
						<div class="panel-heading">
							<b>Work From Home Request</b>
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
											<th>Request Status</th>
											<th>Accept</th>
											<th>Reject</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty userWorkFromHomeCancellationValueList}">
												<b>No requests</b>
											</c:when>
											<c:otherwise>
												<c:forEach var="userWorkFromHomeCancellationValueList"
													items="${userWorkFromHomeCancellationValueList}" begin="0">
													<tr>
														<td><c:out
																value="${userWorkFromHomeCancellationValueList.requestId}" /></td>
														<td><c:out
																value="${userWorkFromHomeCancellationValueList.firstName} ${userWorkFromHomeCancellationValueList.lastName}" />
														</td>
														<td><c:out
																value="${userWorkFromHomeCancellationValueList.requestDate}" /></td>
														<td><c:out
																value="${userWorkFromHomeCancellationValueList.startDate}" /></td>
														<td><c:out
																value="${userWorkFromHomeCancellationValueList.endDate}" /></td>
														<td><c:out
																value="${userWorkFromHomeCancellationValueList.days}" /></td>
														<td><c:out
																value="${userWorkFromHomeCancellationValueList.status}" /></td>
														<td><a
															href="cancelRequestProcess.do?method=cancelWorkFromHomeRequest&process=CancelApprovedRequest&requestId=${userWorkFromHomeCancellationValueList.requestId}"><button
																	type="button" class="btn btn-success">Approve</button></a></td>
														<td>
															<%-- <a
															href="cancelRequestProcess.do?method=cancelWorkFromHomeRequest&process=abort&requestId=${userWorkFromHomeCancellationValueList.requestId}"> --%>
															<button type="button" data-toggle="modal"
																data-target="#myModal3" class="btn btn-danger"
																value="${userWorkFromHomeCancellationValueList.requestId}"
																Onclick="myFunctionWork(${userWorkFromHomeCancellationValueList.requestId})">Reject</button>
															<!-- </a> -->
														</td>


													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->

							<!-- Button trigger modal -->
							<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Work From Home Request <span id="type"></span>
											</h4>
										</div>
										<form name="workFromHome"
											action="cancelRequestProcess.do?method=cancelWorkFromHomeRequest&process=abort"
											method="post">
											<div class="modal-body clearfix">

												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" /> <label
														class="col-sm-4 control-label">Comments</label>
													<textarea cols="30" rows="3" name="Comments"></textarea>
													<div class="col-md-2 col-md-offset-5">
														<button name="workFromHome" type="submit"
															class="btn btn-danger btn-sm">Reject</button>
													</div>
												</div>

											</div>
										</form>
									</div>
								</div>
							</div>

						</div>
						<!-- /.panel-body -->

					</div>
					<!-- /.panel -->



				</div>



			</div>
			<!-- /.row -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>
</html>

