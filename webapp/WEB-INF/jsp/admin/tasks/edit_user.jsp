<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
</head>

<script type="text/javascript">
	function edit(e) {
		//alert(e);
		document.getElementById(e).readOnly = false;
	}

	$(document).ready(function() {
		$('#profileTab a[href="#<c:out value="${param.tab}"/>"]').tab('show'); // Select tab by name
	});
	function alertAdmin()
	{
		if (confirm('Editing email will change the login credentials of employee!')) {
			edit('email'); 
		} else {
			document.getElementById("email").readOnly = true;
		}
	}
</script>

<body>

	<div id="wrapper">
		  <%@include file="../../common/navbar/navbar.jspf"%>
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
				<%@include file="../../common/messages/messagesnotifications.jsp"%>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<!-- Nav tabs -->



								<ul class="nav nav-tabs" role="tablist" id="profileTab">
									<li id="personal_details_tab"><a href="#personal_details"
										role="tab" data-toggle="tab">Personal Details</a></li>
									<li id="company_details_tab"><a href="#company_details"
										role="tab" data-toggle="tab">Company Details</a></li>

								</ul>
								<form action="edit_user.do?method=editUser"
														method="post">
														<input type="hidden" name="userId" value="${user.id}">
								<!-- Tab panes -->
								<div class="tab-content">
									<div class="tab-pane fade in active" id="personal_details">
										<div class="col-lg-12">

											<!-- /.panel-heading -->
											<div class="panel-body">
												<div class="table-responsive">
													
														<table
															class="table table-striped table-bordered table-hover">
															<tbody>
																<tr>
																	<th>First Name</th>
																	<td><input class="form-control" readonly
																		id="fname"
																		value="<c:out value="${user.firstName}"/>"
																		name="firstName"></td>
																	<td><i class="fa fa-edit" onClick="edit('fname');"
																		style="cursor: pointer" title="edit"></i></td>
																</tr>
																<tr>
																	<th>Last Name</th>
																	<td><input class="form-control" readonly
																		id="lname" value="<c:out value="${user.lastName}" />"
																		name="lastName"></td>
																	<td><i class="fa fa-edit" onClick="edit('lname');"
																		style="cursor: pointer" title="edit"></i></td>
																</tr>

																<tr>
																	<th>Date Of Birth</th>
																	<td><input class="form-control" readonly id="dob"
																		value="<c:out value="${dob}" />"
																		name="dateOfBirth"></td>
																	<td><i class="fa fa-edit" onClick="edit('dob');"
																		style="cursor: pointer" title="edit"></i></td>
																</tr>
																<tr>
																	<th>Address</th>
																	<td><input class="form-control" readonly id="addr"
																		value="<c:out value="${user.address}" />"
																		name="address"></td>
																	<td><i class="fa fa-edit" onClick="edit('addr');"
																		style="cursor: pointer" title="edit"></i></td>
																</tr>
																<tr>
																	<th>Mobile Number</th>
																	<td><input class="form-control" readonly id="mob"
																		value="<c:out value="${user.mobile}" />" name="mobile"></td>
																	<td><i class="fa fa-edit" onClick="edit('mob');"
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
													<table
														class="table table-striped table-bordered table-hover">
														<tbody>
															<tr>
																<th>Employee ID</th>
																<td><input class="form-control" readonly id="eId" name="empId"
																	value="<c:out value="${user.employeeCode}" />"></td>
																<td><i class="fa fa-edit" onClick="edit('eId');"
																	style="cursor: pointer" title="edit"></i></td>

															</tr>
															<tr>
																<th>Email</th>
																<td><input class="form-control" readonly id="email" name="email"
																	value="${user.email}"></td>
																<td><i class="fa fa-edit" onClick="alertAdmin();"
																	style="cursor: pointer" title="edit"></i></td>

															</tr>

															<tr>
																<th>Department</th>
																<td><select class="form-control" name="department" >
																<option value="<c:out value="${user.department}"/>"><c:out value="${user.department}"/></option>
																<option value="testing">Testing</option>
																<option value="support">Support</option>
																<option value="development">Development</option>
																</select></td>
																<td></td>

															</tr>
															<tr>
																<th>&nbsp;</th>
																<td><input type="submit" name="submit" id="submit"
																	value="Update" class="btn btn-primary"
																	onclick="refresh();">
																	<a onclick="return confirm('Are you sure, you want to deactivate ${user.firstName}')" href="edit_user.do?method=deactivate&userId=${user.id}"><input type="button" name="deactivate" id="deactivate"
																	value="DeactivateUser" class="btn btn-primary"
																	onclick="refresh();"></a></td>
																<td></td>
															</tr>
														</tbody>
													</table>
											</div>
											<!-- /.table-responsive -->
										</div>
									</div>
									</form>
							
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
