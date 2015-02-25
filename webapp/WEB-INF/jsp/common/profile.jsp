<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="commonheadinclude.jspf"%>
</head>

<script type="text/javascript">

	$(document).ready(function() {
		$('#profileTab a[href="#<c:out value="${param.tab}"/>"]').tab('show'); // Select tab by name
		$('.make-editable').click(function(){
			$(this).parent('td').parent('tr').find('td input').removeAttr('readonly');
		});
	});
</script>

<body>

	<div id="wrapper">
		<%@include file="navbar/navbar.jspf"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Profile</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">

				<!-- Slot for message box -->
				<%@include file="../common/messages/messagesnotifications.jsp"%>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist" id="profileTab">
								<li id="personal_details_tab"><a href="#personal_details"
									role="tab" data-toggle="tab">Personal Details</a></li>
								<li id="company_details_tab"><a href="#company_details"
									role="tab" data-toggle="tab">Company Details</a></li>
								<li id="change_password_tab"><a href="#change_password"
									role="tab" data-toggle="tab">Change Password</a></li>
								<li id="experience_tab"><a href="#experience" role="tab"
									data-toggle="tab">Experience</a></li>

							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
								<div class="tab-pane fade in active" id="personal_details">
									<div class="col-lg-12">

										<!-- /.panel-heading -->
										<div class="panel-body">
											<div class="table-responsive">
												<form action="update_profile.do?method=updateProfile"
													method="post">
													<table
														class="table table-striped table-bordered table-hover">
														<tbody>
															<tr>
																<th>First Name</th>
																<td><input class="form-control" readonly id="fname"
																	value="<c:out value="${user.firstName}"/>"
																	name="firstName"></td>
																<td><i class="fa fa-edit make-editable"
																	style="cursor: pointer" title="edit"></i></td>
															</tr>
															<tr>
																<th>Last Name</th>
																<td><input class="form-control" readonly id="lname"
																	value="<c:out value="${user.lastName}" />"
																	name="lastName"></td>
																<td><i class="fa fa-edit make-editable" 
																	style="cursor: pointer" title="edit"></i></td>
															</tr>

															<tr>
																<th>Date Of Birth</th>
																<td><input class="form-control" readonly id="dob"
																	value="<c:out value="${user.dateOfBirth}" />"
																	name="dateOfBirth"></td>
																<td><i class="fa fa-edit make-editable" 
																	style="cursor: pointer" title="edit"></i></td>
															</tr>
															<tr>
																<th>Address</th>
																<td><input class="form-control" readonly id="addr"
																	value="<c:out value="${user.address}" />"
																	name="address"></td>
																<td><i class="fa fa-edit make-editable" 
																	style="cursor: pointer" title="edit"></i></td>
															</tr>
															<tr>
																<th>Mobile Number</th>
																<td><input class="form-control" readonly id="mob"
																	value="<c:out value="${user.mobile}" />" name="mobile"></td>
																<td><i class="fa fa-edit make-editable" 
																	style="cursor: pointer" title="edit"></i></td>
															</tr>
															<tr>
																<th>&nbsp;</th>
																<td><input type="submit" name="submit" id="submit"
																	value="Update" class="btn btn-primary"
																	onclick="refresh();"></td>
																<td></td>
															</tr>
														</tbody>
													</table>
												</form>
											</div>
											<!-- /.table-responsive -->
										</div>
										<!-- /.panel-body -->


										<!-- /.panel -->
									</div>
								</div>
								<div class="tab-pane fade" id="company_details">
									<div class="panel-body">
										<div class="table-responsive">
											<table class="table table-striped table-bordered table-hover">
												<tbody>
													<tr>
														<th>Employee ID</th>
														<td><input class="form-control" readonly
															value="<c:out value="${user.employeeCode}" />"></td>

													</tr>
													<tr>
														<th>Designation</th>
														<td><input class="form-control" readonly
															value="Employee"></td>

													</tr>

													<tr>
														<th>Department</th>
														<td><input class="form-control" readonly
															value="<c:out value="${user.department}" />"></td>

													</tr>
													<tr>
														<th>Email Id</th>
														<td><input class="form-control" readonly
															value="<c:out value="${user.email}" />"></td>

													</tr>




												</tbody>
											</table>
										</div>
										<!-- /.table-responsive -->
									</div>
								</div>
								<div class="tab-pane fade" id="change_password">
									<div class="panel-body">
										<div class="table-responsive">
											<form role="form" method="post"
												action="resetPassword.do?method=reset">
												<table
													class="table table-striped table-bordered table-hover">
													<tbody>
														<tr>
															<th>Old Password</th>
															<td><input class="form-control" type="password"
																name="old_password"></td>
														</tr>
														<tr>
															<th>New Password</th>
															<td><input class="form-control" type="password"
																value="" name="password"></td>
														</tr>
														<tr>
															<th>Re-enter Password</th>
															<td><input class="form-control" type="password"
																value="" name="confirmpassword"></td>
														</tr>
														<tr>
															<td></td>
															<td><input type="submit" name="submit" id="submit"
																value="Update" class="btn btn-primary"></td>
														</tr>
													</tbody>
												</table>
											</form>
										</div>
										<!-- /.table-responsive -->
									</div>
								</div>
								<div class="tab-pane fade" id="experience">
									<div class="panel-body">
										<div class="table-responsive">
										<form action="update_profile.do?method=updateExperience"></form>
											<table class="table table-striped table-bordered table-hover">
												<tbody>
													<tr>
														<th>Company Name</th>
														<th>Start Date</th>
														<th>End Date</th>
														<th>Designation</th>
														<th>Edit</th>

													</tr>
													<c:forEach var="experience" items="${experience}" begin="0">
													<tr>
													
														<td><input class="form-control editable" readonly id="company_name"
															value="<c:out value="${experience.companyName}" />"></td>

														<td><input class="form-control editable" readonly id="year" 
															value="<c:out value="${experience.endDate}" />"></td>
															
															<td><input class="form-control editable" readonly 
															value="<c:out value="${experience.startDate}" />"></td>
															
														<td><input class="form-control editable" readonly id="designation"
															value="<c:out value="${experience.designation}" />"></td>
															
														<td><i class="fa fa-edit make-editable" style="cursor: pointer"
															title="edit"></i></td>
													</tr>
													</c:forEach>
													<!-- end of experience -->
												<tr>
																
																<th>&nbsp;</th>
																<th>&nbsp;</th>
																<td><input type="submit" name="submit" id="submit"
																	value="Update" class="btn btn-primary"
																	onclick="refresh();"></td>
																<th>&nbsp;</th>
																<th>&nbsp;</th>
															</tr>
												</tbody>
											</table>
										</div>
										<!-- /.table-responsive -->
									</div>
								</div>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- panel panel-default -->
				</div>
				<!-- col-lg-12 -->
				<!-- /.panel -->
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
</body>
</html>
