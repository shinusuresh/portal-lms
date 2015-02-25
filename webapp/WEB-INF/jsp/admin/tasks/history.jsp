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
		<c:set var="count" scope="session" value="${0}" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">User Holidays</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<!-- Slot for message box -->
				<%@include file="../../common/messages/messagesnotifications.jsp"%>

				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<div>
									<table
										class="table table-striped table-bordered table-hover data-table-sortable"
										id="dataTables-example">
										<thead>
											<tr>
												<th>Name</th>
												<th>UserName</th>
												<th>Leaves Taken</th>
												<th>Bank Holiday Working(Paid)</th>
												<th>Bank Holiday Working(Compoff)</th>
												<th>Work From Home</th>
												<th>Leaves Remaining</th>
												<th>Carry Forwarded Leaves</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach items="${userHolidays}" var="userHolidays">
												<tr class="odd gradeX">
													<td>${userHolidays.getUser().getFirstName()}</td>
													<td>${userHolidays.getUser().getEmail()}</td>
													<td>${userHolidays.leavesTaken}</td>
													<td>${userHolidays.bankHolidayWorkingPaid}</td>
													<td>${userHolidays.bankHolidayWorkingCompoff}</td>
													<td>${userHolidays.workFromHome}</td>
													<td>${userHolidays.leavesRemaining}</td>
													<td>${userHolidays.carryForwardedLeaves}</td>
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

		<!-- DataTables JavaScript -->
		<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
		<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

		<!-- DataTables JavaScript -->
		<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
		<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
</body>

</html>


