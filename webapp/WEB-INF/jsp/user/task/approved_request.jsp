<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
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
		<%@include file="../../common/navbar/navbar.jspf"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h2 class="page-header">Approved Requests</h2>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<!-- /.panel-body -->
							<b>Approved Bank Holiday Working Request</b>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>Sl.no</th>
											<th>Request Date</th>
											<th>Start Date</th>
											<th>End Date</th>
											<th>No. of days</th>
											<th>Request Status</th>
											<th>Cancel Request</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="bankHolidayWorkingRequests"
											items="${bankHolidayWorkingRequests}" begin="0">
											<tr>
												<td><c:out value="${bankHolidayWorkingRequests.id}" /></td>
												<td><c:out
														value="${bankHolidayWorkingRequests.requestDate }" /></td>
												<td><c:out
														value="${bankHolidayWorkingRequests.startDate}" /></td>
												<td><c:out
														value="${bankHolidayWorkingRequests.endDate}" /></td>

												<td><c:out value="${bankHolidayWorkingRequests.days}" /></td>
												<td><c:out value="${bankHolidayWorkingRequests.status}" /></td>
												<td><c:choose>
														<c:when
															test="${ bankHolidayWorkingRequests.status == 'CancellationRejected'}">
															<button type="button" class="btn btn-danger"
																disabled="disabled">Cancel</button>
														</c:when>
														<c:otherwise>
															<button type="button" class="btn btn-danger"
																data-toggle="modal" data-target="#myModal1"
																id="cancelApproved"
																value="${bankHolidayWorkingRequests.id}"
																Onclick="myFunctionBank(${bankHolidayWorkingRequests.id})">Cancel</button>
														</c:otherwise>
													</c:choose></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
							<!-- Button trigger modal -->

							<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Are you sure you want to cancel this request? <span
													id="type"></span>
											</h4>
										</div>
										<form name="BankHolidaWorking"
											action="cancelRequest.do?method=bankHolidayWorkingCancellationRequest"
											method="post">
											<div class="modal-body clearfix">

												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" />
													<div class="col-xs-offset-9">
														<button name="bankHolidayWorkingRequest" type="submit"
															class=" btn btn-warning btn-sm">Yes</button>

														<a
															href="approved_request.do?method=listAllApprovedRequestOfUser"><button
																type="button" class=" btn btn-warning btn-sm ">No</button></a>
													</div>
												</div>
											</div>
										</form>

									</div>
								</div>
							</div>

						</div>
						<!-- /.panel-body -->
						<div class="panel-heading">
							<!-- /.panel-body -->
							<b>Approved Leave Requests</b>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>Sl.no</th>
											<th>Request Date</th>
											<th>Start Date</th>
											<th>End Date</th>
											<th>No. of days</th>
											<th>Request Status</th>
											<th>Cancel Request</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="leaves" items="${leaves}" begin="0">
											<tr>
												<td><c:out value="${leaves.id}" /></td>
												<td><c:out value="${leaves.requestDate }" /></td>
												<td><c:out value="${leaves.startDate}" /></td>
												<td><c:out value="${leaves.endDate}" /></td>
												<td><c:out value="${leaves.days}" /></td>
												<td><c:out value="${leaves.status}" /></td>
												<td><c:choose>
														<c:when test="${ leaves.status == 'CancellationRejected'}">
															<button type="button" class="btn btn-danger"
																disabled="disabled">Cancel</button>
														</c:when>
														<c:otherwise>
															<button type="button" class="btn btn-danger"
																data-toggle="modal" data-target="#myModal2"
																value="${leaves.id}" name="request-id"
																Onclick="myFunctionLeave(${leaves.id})">Cancel</button>
														</c:otherwise>
													</c:choose></td>


											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
							<!-- Button trigger modal -->

							<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Are you sure you want to cancel this request? <span
													id="type"></span>
											</h4>
										</div>
										<form name="leaverequest"
											action="cancelRequest.do?method=leaveCancellationRequest"
											method="post">
											<div class="modal-body clearfix">

												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" />
													<div class="col-xs-offset-9">
														<button name="leaveRequest" type="submit"
															class=" btn btn-warning btn-sm">Yes</button>

														<a
															href="approved_request.do?method=listAllApprovedRequestOfUser"><button
																type="button" class=" btn btn-warning btn-sm ">No</button></a>
													</div>
												</div>
											</div>
										</form>

									</div>
								</div>
							</div>
						</div>
						<div class="panel-heading">
							<!-- /.panel-body -->
							<b>Approved Work From Home Requests</b>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>Sl.no</th>
											<th>Request Date</th>
											<th>Start Date</th>
											<th>End Date</th>
											<th>No. of days</th>
											<th>Request Status</th>
											<th>Cancel Request</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="workFromHomeRequests"
											items="${workFromHomeRequests}" begin="0">
											<tr>
												<td><c:out value="${workFromHomeRequests.id}" /></td>
												<td><c:out value="${workFromHomeRequests.requestDate }" /></td>
												<td><c:out value="${workFromHomeRequests.startDate}" /></td>
												<td><c:out value="${workFromHomeRequests.endDate}" /></td>
												<td><c:out value="${workFromHomeRequests.days}" /></td>
												<td><c:out value="${workFromHomeRequests.status}" /></td>
												<td><c:choose>
														<c:when
															test="${ workFromHomeRequests.status == 'CancellationRejected'}">
															<button type="button" class="btn btn-danger"
																disabled="disabled">Cancel</button>
														</c:when>
														<c:otherwise>
															<button type="button" class="btn btn-danger"
																data-toggle="modal" data-target="#myModal3"
																id="cancelApproved" value="${workFromHomeRequests.id}"
																Onclick="myFunctionWork(${workFromHomeRequests.id})">Cancel</button>
														</c:otherwise>
													</c:choose></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
							<!-- Button trigger modal -->

							<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Are you sure you want to cancel this request? <span
													id="type"></span>
											</h4>
										</div>
										<form name="workfromHome"
											action="cancelRequest.do?method=workFromHomeCancellationRequest"
											method="post">
											<div class="modal-body clearfix">

												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" />
													<div class="col-xs-offset-9">
														<button name="workFromHome" type="submit"
															class=" btn btn-warning btn-sm">Yes</button>

														<a
															href="approved_request.do?method=listAllApprovedRequestOfUser"><button
																type="button" class=" btn btn-warning btn-sm ">No</button></a>
													</div>
												</div>
											</div>
										</form>

									</div>
								</div>
							</div>

						</div>





						<div class="panel-heading">
							<!-- /.panel-body -->
							<b>Approved Weekend Working Requests</b>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>Sl.no</th>
											<th>Request Date</th>
											<th>Start Date</th>
											<th>End Date</th>
											<th>No. of days</th>
											<th>Request Status</th>
											<th>Cancel Request</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="weekendWorkingRequests"
											items="${weekendWorkingRequests}" begin="0">
											<tr>
												<td><c:out value="${weekendWorkingRequests.id}" /></td>
												<td><c:out
														value="${weekendWorkingRequests.requestDate }" /></td>
												<td><c:out value="${weekendWorkingRequests.startDate}" /></td>
												<td><c:out value="${weekendWorkingRequests.endDate}" /></td>
												<td><c:out value="${weekendWorkingRequests.days}" /></td>
												<td><c:out value="${weekendWorkingRequests.status}" /></td>
												<td><c:choose>
														<c:when
															test="${ weekendWorkingRequests.status == 'CancellationRejected'}">
															<button type="button" class="btn btn-danger"
																disabled="disabled">Cancel</button>
														</c:when>
														<c:otherwise>
															<button type="button" class="btn btn-danger"
																data-toggle="modal" data-target="#myModal4"
																id="cancelApproved" value="${weekendWorkingRequests.id}"
																Onclick="myFunctionWeek(${weekendWorkingRequests.id})">Cancel</button>
														</c:otherwise>
													</c:choose></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
							<!-- Button trigger modal -->

							<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Are you sure you want to cancel this request? <span
													id="type"></span>
											</h4>
										</div>
										<form name="weekendWorking"
											action="cancelRequest.do?method=weekendWorkingCancellationRequest"
											method="post">
											<div class="modal-body clearfix">

												<div class="form-group clearfix">
													<input type="hidden" name="requestId" id="request-id" />
													<div class="col-xs-offset-9">
														<button name="weekendWorking" type="submit"
															class=" btn btn-warning btn-sm">Yes</button>

														<a
															href="approved_request.do?method=listAllApprovedRequestOfUser"><button
																type="button" class=" btn btn-warning btn-sm ">No</button></a>
													</div>
												</div>
											</div>
										</form>

									</div>
								</div>
							</div>

						</div>



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
