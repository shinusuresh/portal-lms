<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@include file="/WEB-INF/jsp/common/environment.jspf"%> --%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#profileTab a[href="#<c:out value="${param.tab}"/>"]').tab('show'); // Select tab by name
	$('.make-editable').click(function(){
		$(this).parent('td').parent('tr').find('td input').removeAttr('readonly');
	});
});

 </script>
</head>

<body>

	<div id="wrapper">
		<%@include file="../../common/navbar/navbar.jspf"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-body">
						<div style="display: none;">
							<datalist id=userList>
							<c:forEach items="${users}" var="users">
													<option value=${users.employeeCode}>${users.firstName}</option>
											</c:forEach></datalist>
						
						</div>
						<div class="form-group has-success col-sm-4" align="left">
							<div class="modal-header">
								<h3>Search employee</h3>
							</div>
							<div class="modal-body">
								<form id="searchEmployee" class="form-horizontal"
									action="monthlyLeaves.do?method=listAllRequestOfUserBymonthAndYear" role="form" method="post">

									<div class="form-group" align="left">
										<label class="col-sm-4 control-label">Employee Code</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" rows="3"
												list="userList" placeholder="Employee ID"
												name="employeecode">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Month</label>
										<div class="col-sm-3">
											<select onchange="monthSelect()" id="selectMonth"
												class="form-control" name="month">
												<option selected="selected">month</option>
												<option value="1">January</option>
												<option value="2">February</option>
												<option value="3">March</option>
												<option value="4">April</option>
												<option value="5">May</option>
												<option value="6">June</option>
												<option value="7">July</option>
												<option value="8">August</option>
												<option value="9">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
										<label class="col-sm-2 control-label">Year</label>
										<div class="col-sm-3">
											<select onchange="monthSelect()" id="selectMonth"
												class="form-control " name="year">
												<option selected="selected">year</option>
												<c:set var="now" value="<%=new java.util.Date()%>" />
												<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
												<c:forEach var="year" begin="${year-10}" end="${year+10}">
													<option><c:out value="${year}"></c:out></option>
												</c:forEach>
											</select>

										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-offset-7 ">
											<button type="submit" 
												class="btn btn btn-primary">Search</button>
										</div>
									</div>
								</form>
							</div>

						</div>
						<div class="col-sm-offset-1 col-sm-6">
							<div class="modal-header">
								<h3>Search employee</h3>
							</div>
							<div class="modal-body">
							<table class="table table-striped table-bordered table-hover">
								<tbody>
									<tr class="info">
										<th class="col-sm-6">Name</th>
										<td><c:out value="${user.lastName}" /></td>

									</tr>
									
									<tr class="success">
										<th> Paid Bank holiday working</th>
										<td><c:out value="${bankHolidayWorkingPaid}" /></td>
									</tr>
									
									<tr class="danger">
										<th>Loss of pay</th>
										<td><c:out value="${user.email}" /></td>
									</tr>

									<tr class="success">
										<th>Paid Weekend working</th>
										<td><c:out value="${weekendWorkingPaid}" /></td>
									</tr>
								</tbody>
							</table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist" id="profileTab">
								<li id="user_holidays_tab"><a href="#userHolidays"
									role="tab" data-toggle="tab">User Holidays</a></li>
								<li id="leave_request_tab"><a href="#leaveRequests"
									role="tab" data-toggle="tab">Leave Requests</a></li>
								<li id="work_from_home_tab"><a href="#workFromHome"
									role="tab" data-toggle="tab">work from Home</a></li>
								<li id="bank_holiday_working_tab"><a href="#bankholidayworking" role="tab" data-toggle="tab">Bank
										Holiday Working</a></li>
								<li id="weekend_working_tab"><a href="#weekendWorking"
									role="tab" data-toggle="tab">Weekend Working</a></li>


							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
								<!-- user holidays -->
								<div class="tab-pane fade in active" id="userHolidays">
									<div class="col-lg-12">

										<!-- /.panel-heading -->
										<div class="panel-body">
											<div class="table-responsive">
													<table
														class="table table-striped table-bordered table-hover">
														<tbody>
															<tr>
																<th class="col-sm-6">Name</th>
																<td><c:out value="${user.lastName}" /></td>

															</tr>
															<tr>
																<th>User Name</th>
																<td><c:out value="${user.email}" /></td>
															</tr>

															<tr>
																<th>Leaves taken</th>
																<td><c:out value="${leaveRequests}" /></td>
															</tr>
															<tr>
																<th>Bank holiday working(paid)</th>
																<td><c:out value="${bankHolidayWorkingPaid}" /></td>
															</tr>
															<tr>
																<th>Bank holiday working(compoff)</th>
																<td><c:out value="${bankHolidayWorkingCompoff}" /></td>
															</tr>
															<tr>
																<th>work from home</th>
																<td><c:out value="${workFromHome}" /></td>

															</tr>
															<tr>
																<th>Weekend working(paid)</th>
																<td><c:out value="${weekendWorkingPaid}" /></td>

															</tr>
															<tr>
																<th>Weekend working(compoff)</th>
																<td><c:out value="${weekendWorkingCompoff}" /></td>

															</tr>
															<tr>
																<th>Leaves remaining</th>
																<td><c:out value="${user.lastName}" /></td>

															</tr>
															<tr>
																<th>Carry forwarded leaves</th>
																<td><c:out value="${user.lastName}" /></td>

															</tr>
														</tbody>
													</table>
											</div>
											<!-- /.table-responsive -->
										</div>
										<!-- /.panel-body -->


										<!-- /.panel -->
									</div>
								</div>
								<!-- user holidays -->
								<!-- leave request -->
								<div class="tab-pane fade" id="leaveRequests">
									<div class="col-lg-12">

										<!-- /.panel-heading -->
										<div class="panel-body">
											<div class="table-responsive">
													<table
										class="table table-striped table-bordered table-hover data-table-sortable"
										id="dataTables-example">
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
												<th>Date Of Escalation</th>
												<th>Approver</th>
												<th>Marked By</th>
												<th>Half day/Full day</th>
												<th>Description</th>
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
													<td><c:choose>
															<c:when test="${empty leaves.dateOfEscalation}">
     																   Not Applicable
   															 </c:when>
															<c:otherwise>
        														${leaves.dateOfEscalation}
    														</c:otherwise>
														</c:choose></td>
													<td>${leaves.approver.firstName}</td>
													<td>${leaves.markedBy}</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.description}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
											</div>
											<!-- /.table-responsive -->
										</div>
										<!-- /.panel-body -->


										<!-- /.panel -->
									</div>
								</div>
								<!-- leave request -->
								<!-- work from home  -->
								<div class="tab-pane fade" id="workFromHome">
									<div class="panel-body">
										<div class="table-responsive">
												<table id="data-table"
										class="table table-striped table-bordered data-table-sortable"
										cellspacing="0" width="100%">
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
													<td>${leaves.description}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
										</div>
										<!-- /.table-responsive -->
									</div>
									<!-- work from home  -->
									<!-- bank holiday working -->
								</div>
								<div class="tab-pane fade" id="bankholidayworking">
									<div class="panel-body">
										<div class="table-responsive">
											<table id="data-table"
										class="table table-striped table-bordered data-table-sortable">
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
												<th>Type</th>
												<th>Status</th>
												<th>Approver</th>
												<th>half day/full day</th>
												<th>Description</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${bankHolidayWorkingRequests}" var="leaves">
												<tr class="odd gradeX">
													<c:set var="count" scope="session" value="${count+1}" />
													<td><c:out value="${count}" /></td>
													<td>${leaves.user.id}</td>
													<td>${leaves.year}</td>
													<td>${leaves.requestDate}</td>
													<td>${leaves.startDate}</td>
													<td>${leaves.endDate}</td>
													<td>${leaves.days}</td>
													<td>${leaves.type}</td>
													<td>${leaves.status}</td>
													<td>${leaves.approver.firstName}</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.description}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
										</div>
										<!-- /.table-responsive -->
									</div>
								</div>
								<!--bank holiday working -->
								<!-- weekend working -->
								<div class="tab-pane fade" id="weekendWorking">
									<div class="panel-body">
										<div class="table-responsive">
											<table id="data-table"
										class="table table-striped table-bordered data-table-sortable"
										cellspacing="0" width="100%">
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
												<th>Type</th>
												<th>Status</th>
												<th>Approver</th>
												<th>MarkedBy</th>
												<th>half day/full day</th>
												<th>Description</th>
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
													<td>${leaves.type}</td>
													<td>${leaves.status}</td>
													<td>${leaves.approver.firstName}</td>
													<td>${leaves.markedBy}</td>
													<td>${leaves.hdFd}</td>
													<td>${leaves.description}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
										</div>
										<!-- /.table-responsive -->
									</div>
								</div>
								<!-- weekend working -->
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- panel panel-default -->
				</div>
			</div>
			<!-- row -->
			</div>
			<!-- panel -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>

</html>