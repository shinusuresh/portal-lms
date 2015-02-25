<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
<link rel="stylesheet" href="${host}/css/plugins/morris-0.4.1.min.css">
<link href="${host}/css/plugins/ratings/star-rating.css" rel="stylesheet" type="text/css">

<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="${host}/js/plugins/morris/morris.js"></script>
<script type="text/javascript" src="js/plugins/ratings/star-rating.js"></script>

</head>

<body>

	<div id="wrapper">

		<%@include file="../../common/navbar/navbar.jspf"%>


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h2 class="page-header">Welcome</h2>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<script type="text/javascript">
		/* 	$(document).ready(function(){
			$('#uploadattendance').click(function(){
				$( "#file_input" ).click();
			});
			});
 */			</script>
				<!-- /.row -->
				<div class="row">
					<a href="useractions.do?method=load"><div
							class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-user fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div>Add New User</div>
										</div>
									</div>
								</div>

							</div>

						</div> </a> <a
						href="employeelist.do?method=listUsers&selection=employeelist"><div
							class="col-lg-3 col-md-6">

							<div class="panel panel-green">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-list fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div>Employee List</div>
										</div>
									</div>
								</div>

							</div>

						</div> </a> <a
						href="applyleave.do?method=listDetailsToMarkLeave&selection=markleave"><div
							class="col-lg-3 col-md-6">
							<div class="panel panel-yellow">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-edit fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div>Mark Leave</div>

										</div>
									</div>
								</div>

							</div>
						</div> </a> <a href="history_admin.do"><div class="col-lg-3 col-md-6">
							<div class="panel panel-red">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-history fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">

											<div>History</div>
										</div>
									</div>
								</div>

							</div>
						</div> </a>
						
				</div>

				<!-- Portlets divs -->
				<div class="row">
					<%@include file="../../common/portlets/publicholidays.jspf"%>
				</div>
			</sec:authorize>

			<!-- Manager dashboard -->
			<sec:authorize ifAllGranted="ROLE_MANAGER">
				<!-- /.row -->
				<div class="row">
					<a href="applyleave_user.do?method=listAllRequestsOfLastTwoYearsByUserId">
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-envelope fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div>New Request!</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</a> <a href="pending_request.do?method=listAllPendingRequestOfUser"><div
							class="col-lg-3 col-md-6">
							<div class="panel panel-green">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-tasks fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div>Pending Request!</div>
										</div>
									</div>
								</div>
							</div>

						</div> </a> <a href="for_approval.do?method=listRequestsForApproval"><div
							class="col-lg-3 col-md-6">

							<div class="panel panel-yellow">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-inbox fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div>For Approval!</div>
										</div>
									</div>
								</div>
							</div>
						</div> </a> <a
						href="approved_request.do?method=listAllApprovedRequestOfUser"><div
							class="col-lg-3 col-md-6">
							<div class="panel panel-red">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-gift fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div>Approved Request!</div>
										</div>
									</div>
								</div>
							</div>
						</div> </a>
				</div>

				<!-- /.row -->
				<div class="row">

					<%@include file="../../common/portlets/publicholidays.jspf"%>
					<%@include file="../../common/portlets/skillmatrix.jspf"%>

					<div class="col-lg-6">

						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-bar-chart-o fa-fw"></i> Leave Status
							</div>
							<div class="panel-body">
								<div id="morris-donut-chart"></div>
							</div>

						</div>

					</div>

				</div>
				<!-- /.row -->

			</sec:authorize>


			<!-- User dashboard -->
			<sec:authorize ifAllGranted="ROLE_USER">
				<!-- /.row -->
				<div class="row">
					<a href="applyleave_user.do?method=listAllRequestsOfLastTwoYearsByUserId">
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-envelope fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div>New Request!</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</a> <a href="pending_request.do?method=listAllPendingRequestOfUser"><div
							class="col-lg-3 col-md-6">
							<div class="panel panel-green">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-tasks fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">${pendingLeavesRequestCount}</div>
											<div>Pending Request!</div>
										</div>
									</div>
								</div>
							</div>
						</div> </a> <a
						href="approved_request.do?method=listAllApprovedRequestOfUser"><div
							class="col-lg-3 col-md-6">
							<div class="panel panel-red">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-gift fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">${approvedLeavesCount}</div>
											<div>Approved Request!</div>
										</div>
									</div>
								</div>
							</div>


						</div> </a><a href="skillset.do?method=loadUserSkills&selection=listskills"><div
							class="col-lg-3 col-md-6">
							<div class="panel panel-yellow">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-star fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">${skillsCount}</div>
											<div>Skill matrix</div>
										</div>
									</div>
								</div>
							</div>


						</div> </a>
				</div>

				<!-- /.row -->
				<div class="row">

					<%@include file="../../common/portlets/publicholidays.jspf"%>

					<%@include file="../../common/portlets/skillmatrix.jspf"%>

					<div class="col-lg-6">

						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-bar-chart-o fa-fw"></i> Leave Status
							</div>
							<div class="panel-body">
								<div id="morris-donut-chart"></div>
							</div>

						</div>

					</div>

				</div>
				<!-- /.row -->

			</sec:authorize>

			<!-- /.row -->
			<div class="row">

				<!-- /.col-lg-8 -->

				<!-- /.col-lg-4 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<!-- provide values to the chart -->
	<script type="text/javascript">
	$(document).ready(function(){
		if($('#morris-donut-chart').length > 0){
			Morris.Donut({
				element : 'morris-donut-chart',
				data : [ {
					label : "Leaves Taken",
					value : <c:out value="${leavesTaken}" />
				}, {
					label : "Leaves Remaining",
					value : <c:out value="${leavesRemaining}" />
				}, {
					label : "Total work from home",
					value : <c:out value="${totalWorkFromHome}" />
				}, {
					label : "Bank holiday working",
					value : <c:out value="${bankHolidayWorkingPaid}" />
				} ],
				colors : [ '#D9534F', '#5CB85C', '#f0ad4e', '#428bca' ],
				resize : true
			});
		}
	});
	</script>

</body>

</html>
