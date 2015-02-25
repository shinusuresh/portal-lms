<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
<script>
function myFunctionLeave(requestId)
{
	$('#myModal1 #request-id').val(requestId);
}
function myFunctionWork(requestId)
{
	$('#myModal2 #request-id').val(requestId);
}
function myFunctionBank(requestId)
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
					<h3 class="page-header">Pending Requests</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<!-- /.row -->
			<div class="row">

				<h3
					style="font-family: 'Gill Sans', 'Gill Sans MT', 'Myriad Pro', 'DejaVu Sans Condensed', Helvetica, Arial, sans-serif; color: #4254BC"></h3>

			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Leave requests</h4>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table id="data-table"
									class="table table-striped table-bordered table-hover">
									<thead>
										<c:set var="count" scope="session" value="${0}" />
										<tr>
											<th>SI:NO</th>
											<th>Employee Code</th>
											<th>Year</th>
											<th>Request Date</th>
											<th>Start Date</th>
											<th>End Date</th>
											<th>Days</th>
											<th>Status</th>
											<th>Date Of Escalation</th>
											<th>Approver</th>
											<th>Marked By</th>
											<th>Half day/Full day</th>
											<th>Description</th>
											<th>cancel</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${leaves}" var="leaves">
											<tr class="odd gradeX">
												<c:set var="count" scope="session" value="${count+1}" />
												<td><c:out value="${count}" /></td>
												<td>${leaves.user.id}</td>
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
												<td>
													<button type="button" class="btn btn-danger"
														data-toggle="modal" data-target="#myModal1"
														name="request-id" value="${leaves.id}"
														Onclick="myFunctionLeave(${leaves.id})">Cancel</button>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
								<!-- table-leave -->
								<!-- Button trigger modal -->

								<div class="modal fade" id="myModal1" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog ">
										<div class="modal-content ">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span><span
														class="sr-only">Close</span>
												</button>
												<h4 class="modal-title" id="myModalLabel">
													Are you sure you want to cancel this request? <span
														id="type"></span>
												</h4>
											</div>
											<form name="leaverequest"
												action="requestProcessUser.do?method=cancelPendingLeaveRequest&process=cancel"
												method="post">
												<div class="modal-body clearfix">

													<div class="form-group clearfix">
														<input type="hidden" name="requestId" id="request-id" />
														<div class="col-xs-offset-9">
															<button name="bankHolidayWorkingRequest" type="submit"
																class=" btn btn-warning btn-sm">Yes</button>

															<a
																href="pending_request.do?method=listAllPendingRequestOfUser"><button
																	type="button" class=" btn btn-warning btn-sm ">No</button></a>
														</div>
													</div>
												</div>
											</form>

										</div>
									</div>
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



			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Work From Home Requests</h4>
							</select>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<div class="table-leave">
									<table id="data-table"
										class="table table-striped table-bordered" cellspacing="0"
										width="100%">
										<thead>
											<c:set var="count" scope="session" value="${0}" />
											<tr>
												<th>SI:NO</th>
												<th>Employee Code</th>
												<th>Year</th>
												<th>Request Date</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th>Days</th>
												<th>Status</th>
												<th>Approver</th>
												<th>Marked By</th>
												<th>Half day/Full day</th>
												<th>Is Verified</th>
												<th>Description</th>
												<th>cancel</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${workFromHomeRequests}" var="leaves">
												<tr class="odd gradeX">
													<c:set var="count" scope="session" value="${count+1}" />
													<td><c:out value="${count}" /></td>
													<td>${leaves.user.id}</td>
													<td>${leaves.year}</td>
													<td>${leaves.requestDate}</td>
													<td>${leaves.startDate}</td>
													<td>${leaves.endDate}</td>
													<td>${leaves.days}</td>
													<td>${leaves.status}</td>
													<td>${leaves.approver.firstName}</td>
													<td>${leaves.markedBy}</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.isVerified}</td>
													<td>${leaves.description}</td>
													<td>
														<button type="button" class="btn btn-danger"
															data-toggle="modal" data-target="#myModal2"
															value="${leaves.id}"
															Onclick="myFunctionWork(${leaves.id})">Cancel</button>
													</td>

												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
								<!-- table-leave -->

								<!-- Button trigger modal -->

								<div class="modal fade" id="myModal2" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog ">
										<div class="modal-content ">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span><span
														class="sr-only">Close</span>
												</button>
												<h4 class="modal-title" id="myModalLabel">
													Are you sure you want to cancel this request? <span
														id="type"></span>
												</h4>
											</div>
											<form name="leaverequest"
												action="requestProcessUser.do?method=cancelPendingWorkFromHomeRequest&process=cancel"
												method="post">
												<div class="modal-body clearfix">
													<div class="form-group clearfix">
														<input type="hidden" name="requestId" id="request-id" />
														<div class="col-xs-offset-9">
															<button name="workFromHomeRequest" type="submit"
																class=" btn btn-warning btn-sm">Yes</button>

															<a
																href="pending_request.do?method=listAllPendingRequestOfUser"><button
																	type="button" class=" btn btn-warning btn-sm ">No</button></a>
														</div>
													</div>
												</div>
											</form>

										</div>
									</div>
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

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Weekend Working Requests</h4>
							</select>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<div class="table-leave">
									<table id="data-table"
										class="table table-striped table-bordered" cellspacing="0"
										width="100%">
										<thead>
											<c:set var="count" scope="session" value="${0}" />
											<tr>
												<th>SI:NO</th>
												<th>User ID</th>
												<th>Year</th>
												<th>Request Date</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th>Days</th>
												<th>Status</th>
												<th>Approver</th>
												<th>MarkedBy</th>
												<th>half day/full day</th>
												<th>Description</th>
												<th>cancel</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${weekendWorkingRequests}" var="leaves">
												<tr class="odd gradeX">
													<c:set var="count" scope="session" value="${count+1}" />
													<td><c:out value="${count}" /></td>
													<td>${leaves.user.id}</td>
													<td>${leaves.year}</td>
													<td>${leaves.requestDate}</td>
													<td>${leaves.startDate}</td>
													<td>${leaves.endDate}</td>
													<td>${leaves.days}</td>
													<td>${leaves.status}</td>
													<td>${leaves.approver.firstName}</td>
													<td>${leaves.markedBy}</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.description}</td>
													<td>
														<button type="button" class="btn btn-danger"
															data-toggle="modal" data-target="#myModal4"
															value="${leaves.id}"
															Onclick="myFunctionWeek(${leaves.id})">Cancel</button>
													</td>

												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
								<!-- table-leave -->
								<div class="modal fade" id="myModal4" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog ">
										<div class="modal-content ">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span><span
														class="sr-only">Close</span>
												</button>
												<h4 class="modal-title" id="myModalLabel">
													Are you sure you want to cancel this request? <span
														id="type"></span>
											</div>
											<form name="leaveRequest"
												action="requestProcessUser.do?method=cancelPendingWeekendWorkingRequest&process=cancel"
												method="post">
												<div class="modal-body clearfix">

													<div class="form-group clearfix">
														<input type="hidden" name="requestId" id="request-id" />
														<div class="col-xs-offset-9">
															<button name="weekendWorkingRequest" type="submit"
																class="btn btn-warning btn-sm">Yes</button>

															<a
																href="pending_request.do?method=listAllPendingRequestOfUser"><button
																	type="button" class=" btn btn-warning btn-sm ">No</button></a>
														</div>
													</div>
												</div>
											</form>

										</div>
									</div>
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
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Bank Holiday Working Requests</h4>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<div class="table-leave">
									<table id="data-table"
										class="table table-striped table-bordered" cellspacing="0"
										width="100%">
										<thead>
											<c:set var="count" scope="session" value="${0}" />
											<tr>
												<th>SI:NO</th>
												<th>Employee Code</th>
												<th>Year</th>
												<th>Request Date</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th>Days</th>
												<th>Status</th>
												<th>Approver</th>
												<th>Half day/Full day</th>
												<th>Description</th>
												<th>Type</th>
												<th>Is Verified</th>
												<th>DESCRIPTION</th>
												<th>cancel</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${bankHolidayWorkingRequests}" var="leaves">
												<c:set var="count" scope="session" value="${count+1}" />
												<td><c:out value="${count}" /></td>
												<td>${leaves.user.id}</td>
												<td>${leaves.year}</td>
												<td>${leaves.requestDate}</td>
												<td>${leaves.startDate}</td>
												<td>${leaves.endDate}</td>
												<td>${leaves.days}</td>
												<td>${leaves.status}</td>
												<td>${leaves.approver.firstName}</td>
												<td>${leaves.hdFd}</td>
												<td>${leaves.description}</td>
												<td>${leaves.type}</td>
												<td>${leaves.isVerified}</td>
												<td>${leaves.lastUpdate}</td>
												<td>
													<button type="button" class="btn btn-danger"
														data-toggle="modal" data-target="#myModal3"
														value="${leaves.id}"
														Onclick="myFunctionBank(${leaves.id})">Cancel</button>
												</td>


											</c:forEach>

										</tbody>
									</table>
								</div>
								<!-- table-leave -->
								<!-- Button trigger modal -->

								<div class="modal fade" id="myModal3" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog ">
										<div class="modal-content ">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span><span
														class="sr-only">Close</span>
												</button>
												<h4 class="modal-title" id="myModalLabel">
													Are you sure you want to cancel this request? <span
														id="type"></span>
												</h4>
											</div>
											<form name="leaverequest"
												action="requestProcessUser.do?method=cancelPendingBankHolidayWorkingRequest&process=cancel"
												method="post">
												<div class="modal-body clearfix">

													<div class="form-group clearfix">
														<input type="hidden" name="requestId" id="request-id" />
														<div class="col-xs-offset-9">
															<button name="bankHolidayRequest" type="submit"
																class=" btn btn-warning btn-sm">Yes</button>

															<a
																href="pending_request.do?method=listAllPendingRequestOfUser"><button
																	type="button" class=" btn btn-warning btn-sm ">No</button></a>
														</div>
													</div>
												</div>
											</form>

										</div>
									</div>
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
</body>
</html>
