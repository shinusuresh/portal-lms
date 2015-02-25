<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="commonheadinclude.jspf"%>
</head>

<body>
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
	<div id="wrapper">
		<%@include file="navbar/navbar.jspf"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h2 class="page-header">For Approval</h2>
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

							<b>Bank Holiday Requests For Approval</b>
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
											<c:when test="${empty userBankHolidayValueList}">
												<b>No requests</b>
											</c:when>
											<c:otherwise>
												<c:forEach var="userBankHolidayValueList"
													items="${userBankHolidayValueList}" begin="0">
													<tr>
														<td><c:out
																value="${userBankHolidayValueList.requestId}" /></td>
														<td><c:out
																value="${userBankHolidayValueList.firstName} ${userBankHolidayValueList.lastName}" />
														</td>
														<td><c:out
																value="${userBankHolidayValueList.requestDate}" /></td>
														<td><c:out
																value="${userBankHolidayValueList.startDate}" /></td>
														<td><c:out
																value="${userBankHolidayValueList.endDate}" /></td>
														<td><c:out value="${userBankHolidayValueList.days}" /></td>
														<td><c:out value="${userBankHolidayValueList.status}" /></td>
														<td><a
															href="requestProcess.do?method=bankHolidayWorkingRequest&process=accept&requestId=${userBankHolidayValueList.requestId}"><button
																	type="button" class="btn btn-success">Approve</button></a></td>
														<td><button type="button" class="btn btn-danger"
																name="request-id" data-toggle="modal"
																data-target="#myModal1"
																value="${userBankHolidayValueList.requestId}"
																Onclick="myFunctionBank(${userBankHolidayValueList.requestId})">Reject</button>
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
										<form name="leaverequest"
											action="requestProcess.do?method=bankHolidayWorkingRequest&process=reject"
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

							<b>Weekend Working Requests For Approval</b>
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
											<c:when test="${empty userWeekendWorkingValueList}">
												<b>No requests</b>
											</c:when>
											<c:otherwise>
												<c:forEach var="userWeekendWorkingValueList"
													items="${userWeekendWorkingValueList}" begin="0">
													<tr>
														<td><c:out
																value="${userWeekendWorkingValueList.requestId}" /></td>
														<td><c:out
																value="${userWeekendWorkingValueList.firstName} ${userWeekendWorkingValueList.lastName}" />
														</td>
														<td><c:out
																value="${userWeekendWorkingValueList.requestDate}" /></td>
														<td><c:out
																value="${userWeekendWorkingValueList.startDate}" /></td>
														<td><c:out
																value="${userWeekendWorkingValueList.endDate}" /></td>
														<td><c:out
																value="${userWeekendWorkingValueList.days}" /></td>
														<td><c:out
																value="${userWeekendWorkingValueList.status}" /></td>
														<td><a
															href="requestProcess.do?method=weekendWorkingRequest&process=accept&requestId=${userWeekendWorkingValueList.requestId}"><button
																	type="button" class="btn btn-success">Approve</button></a></td>
														<td><button type="button" class="btn btn-danger"
																name="request-id" data-toggle="modal"
																data-target="#myModal4"
																value="${userWeekendWorkingValueList.requestId}"
																Onclick="myFunctionWeek(${userWeekendWorkingValueList.requestId})">Reject</button>
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
										<form name="weekend working"
											action="requestProcess.do?method=weekendWorkingRequest&process=reject"
											method="post">
											<div class="modal-body clearfix">
												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" /> <label
														class="col-sm-4 control-label">Comments</label>
													<textarea cols="30" rows="3" name="Comments"></textarea>
													<div class="col-md-2 col-md-offset-5">
														<button name="weekendWorkingRequest" type="submit"
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
														<td><button name="request-id" id="leave-request-id"
																type="button" class="btn btn-danger"
																value="${userLeaveValueList.requestId}"
																data-toggle="modal" data-target="#myModal2"
																Onclick="myFunctionLeave(${userLeaveValueList.requestId})">Reject</button>
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
											action="requestProcess.do?method=leaveRequest&process=reject"
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

					</div>
					<!-- /.panel-body -->

				</div>



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
										<c:when test="${empty userWorkFromHomeValueList}">
											<b>No requests</b>
										</c:when>
										<c:otherwise>
											<c:forEach var="userWorkFromHomeValueList"
												items="${userWorkFromHomeValueList}" begin="0">
												<tr>
													<td><c:out
															value="${userWorkFromHomeValueList.requestId}" /></td>
													<td><c:out
															value="${userWorkFromHomeValueList.firstName} ${userWorkFromHomeValueList.lastName}" />
													</td>
													<td><c:out
															value="${userWorkFromHomeValueList.requestDate}" /></td>
													<td><c:out
															value="${userWorkFromHomeValueList.startDate}" /></td>
													<td><c:out
															value="${userWorkFromHomeValueList.endDate}" /></td>
													<td><c:out value="${userWorkFromHomeValueList.days}" /></td>
													<td><c:out value="${userWorkFromHomeValueList.status}" /></td>
													<td><a
														href="requestProcess.do?method=workFromHomeRequest&process=accept&requestId=${userWorkFromHomeValueList.requestId}"><button
																type="button" class="btn btn-success">Approve</button></a></td>
													<td>
														<%-- <a
															href="requestProcess.do?method=workFromHomeRequest&process=reject&requestId=${userWorkFromHomeValueList.requestId}"> --%>
														<button type="button" name="request-id3"
															data-toggle="modal" data-target="#myModal3"
															class="btn btn-danger"
															value="${userWorkFromHomeValueList.requestId}"
															Onclick="myFunctionWork(${userWorkFromHomeValueList.requestId})">Reject</button>
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
										action="requestProcess.do?method=workFromHomeRequest&process=reject"
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
	</div>
</body>
</html>
