<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
<link rel="stylesheet" type="text/css" media="screen" href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/build/css/bootstrap-datetimepicker.min.css" />
    
<script src='${host}/js/plugins/moment/moment.js'></script>
<script type="text/javascript" src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>
<script src="${host}/js/plugins/cloneya/jquery-cloneya.js"></script>
<script type="text/javascript">
	var experienceCount = 1;
	$(document).ready(function(){
		
						$('#dateOfBirth').datepicker({
							format : 'dd-mm-yyyy',
							startDate : "" + new Date()
						});

						$('#dateOfJoining').datepicker({
							format : 'dd-mm-yyyy',
							startDate : "" + new Date()
						});
						$('.startDate').datepicker({
							format : 'dd-mm-yyyy',
							startDate : "" + new Date()
						});
						$('#endDate').datepicker({
							format : 'dd-mm-yyyy',
							startDate : "" + new Date()
						});
	
						$('input[id^="datetimepicker"]').datetimepicker({
						    language: 'en',
						    pickTime: false
						});
						
						$('#clone-wrapper').cloneya()
							.on('clone_after_append', function(event, toclone, newclone) {
								var startDateId = $(newclone).find(".cloneStartId").first().attr('id');
		                        console.log('Attach calendar here to '+startDateId);
		                        attachCalendar(startDateId);
						});
						
						/* $('#columntwo').mouseover('input[id^="datetimepicker"]', function () {
							console.log($(this));
						    $(this).datetimepicker({
						    	language: 'en',
							    pickTime: false
						    });
						}); */
												
	                       
						$('#createUser').bootstrapValidator(
										{
											message : 'This value is not valid',
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {
												employeeCode : {
													validators : {
														digits : {
															message : 'This is not a valid number'
														}
													}
												},
												email : {
													validators : {
														notEmpty : {
															message : 'The email is required and cannot be empty'
														},
														emailAddress : {
															message : 'The input is not a valid email address'
														}
													}
												},
												mobile : {
													validators : {
														digits : {
															message : 'Enter valid number',
															required : true,
															max : 10
														},

														stringLength : {
															min : 10,
															max : 10,
															message : 'The number must be of 10 digits'
														}
													}
												},
												firstName : {
													validators : {
														notEmpty : {
															message : 'First name cannot be empty'
														},
														noDigits : true
													}
												},
												lastName : {
													validators : {
														noDigits : true,
														notEmpty : {
															message : 'Last name cannot be empty'
														},
														
													}
												},
												anualLeaves:{
													validators:{
								                                required: true, 
								                                digits: true,
								                                notEmpty : {
																	message : 'No of leaves name cannot be empty'
																},
													}
													
												},												
												/* 'companyName[]':{
													validators:{
						                                required: true,
						                                notEmpty: {
						                                	message: 'Company Name cannot be empty'
						                                },
													callback: {														
								                        callback: function(value, validator, $field) {
								                            if ($('input[name=experienceStatus]:checked', '#createUser').val() === 'fresher') {
								                                return true;
								                            } else {
								                            	if(value === ''){
								                            		return false;
								                            		}
									                            }
									                        }
														}//end callback													
													}																					
												}, */
												
												'designation[]':{
													validators:{
						                                required: true,
						                                notEmpty: {
						                                	message: 'Designation cannot be empty'
						                                },
													callback: {																
								                        callback: function(value, validator, $field) {
									                            if ($('input[name=experienceStatus]:checked', '#createUser').val() === 'fresher') {
									                            	 return true;							                            	
									                            } 
								                            	if(value === ''){
								                            		return {
								                            			valid: false,
								                            			message: 'Designation cannot be empty',
								                            		};								                            		
								                            	}
									                           return {
									                        	   valid: true
									                           }
															}												
														}//end callback	
													}
												},
												
												 'startDate[]':{
													validators:{
						                                required: true,
						                                notEmpty: {
						                                	message: 'Start Date cannot be empty'
						                                },
													callback: {														
								                        callback: function(value, validator, $field) {
								                            if ($('input[name=experienceStatus]:checked', '#createUser').val() === 'fresher') {
								                                return true;
								                            } 
								                            if(value === ''){
								                            		return {
								                            			valid: false,
								                            			message: 'Start Date cannot be empty',
								                            			};	
								                            	}
								                            return {
									                        	   valid: true
									                           }
									                        }
														}//end callback													
													}																					
												},
												'endDate[]':{
													validators:{
						                                required: true,
						                                notEmpty: {
						                                	message: 'End Date cannot be empty'
						                                },
													callback: {														
								                        callback: function(value, validator, $field) {
								                            if ($('input[name=experienceStatus]:checked', '#createUser').val() === 'fresher') {
								                                return true;
								                            } 
								                            if(value === ''){
							                            		return {
							                            			valid: false,
							                            			message: 'End Date cannot be empty',
							                            			};	
							                            	}
								                            return {
									                        	   valid: true
									                           }
									                        }
														}//end callback												
													}																					
												}, 
												
													
								}//fields
																						
						});
						
		//attach toggle experience for lateral fresher radio
		$('#columntwo input:radio').click(function() {
		    if ($(this).val() === 'fresher') {
		    	//hide experience details if fresher
		    	$('#clone-wrapper').hide();		      
		    } else if ($(this).val() === 'lateral') {
		    	$('#clone-wrapper').show();
		    } 
		  });
						
	});//end document.ready
	function attachCalendar(startDateId){
		$(startDateId).datepicker({
		    language: 'en',
		    pickTime: false
		});
	}
	
	function count(){
		experienceCount++;
	}
	
	function submitExperienceCount(){
		
		$('#count').val(experienceCount);
		for(var i=1; i<=experienceCount; i++){
			var company = 'companyName'+i;
			var startDate = 'startDate'+i;
			var endDate = 'endDate'+i;
			var designation = 'designation'+i;
			alert(startDate);
			  $('#'+company).attr('name', company);
			  $('#'+startDate).attr('name',startDate);
			  $('#'+endDate).attr('name',endDate);
			  $('#'+designation).attr('name',designation);
			}
	
	}
	
</script>
</head>

<body>

	<div id="wrapper">
		<%@include file="../../common/navbar/navbar.jspf"%>
		<div id="page-wrapper" style="min-height: 325px;">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Create New Employee</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">

						<div class="panel-body">
							<div class="row">

								<!-- Slot for message box -->
								<%@include
									file="../../common/messages/messagesnotifications.jsp"%>

								<form role="form" action="useractions.do?method=save" id="createUser" method="post">
									<div class="col-lg-6">
										<h3>Personal Details</h3>
										<hr>

										<div class="form-group">
											<label>Employee Code</label> <input class="form-control"
												name="employeeCode" placeholder="Enter employee code"
												id="employeeCode">
											<!-- <p class="help-block">Example block-level help text here.</p> -->
										</div>
										<div class="form-group">
											<label>First Name</label> <input class="form-control"
												name="firstName" id="firstName">
										</div>
										<div class="form-group">
											<label>Last Name</label> <input class="form-control"
												name="lastName" >
										</div>
										<div class="form-group">
											<label>Email</label> <input class="form-control" name="email">
										</div>
										<div class="form-group">
											<label>Department</label> <select class="form-control"
												name="department">
												<option value="support">Support</option>
												<option value="Development">Development</option>
												<option value="testing">Testing</option>
											</select>
										</div>
										<div class="form-group">
											<label>No of annual leaves</label> <input
												class="form-control" name="anualLeaves">
										</div>
										<div class="form-group">
											<label>PAN</label> <input class="form-control" name="pan">
										</div>
										<div class="form-group">
											<label>Gender</label> <select class="form-control"
												name="gender">
												<option>Male</option>
												<option>Female</option>
											</select>
										</div>
										<div class="form-group">
											<label>Date of birth</label>
											<div class="input-group">
												<div class="date" id="dateOfBirth" data-date=""
													data-date-format="dd-mm-yyyy">
													<input class="form-control" size="16" type="text" value="" readonly="" name="dateOfBirth"> 
														<span class="input-group-addon">
															<a href="#" class="add-on"><i class="glyphicon glyphicon-calendar"></i></a>
														</span>	
												</div>
											</div>	
										</div>

										<div class="form-group">
											<label>Date of joining</label>
											<div class="input-group">
												<div class="date" id="dateOfJoining"
													data-date="" data-date-format="dd-mm-yyyy">
													<input class="form-control" size="16" type="text" value="" readonly="" name="dateOfJoining">													
													<span class="input-group-addon">
														<a href="#" class="add-on"><i class="glyphicon glyphicon-calendar"></i></a>
													</span>													
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Role</label> <select class="form-control"
												name="roleId">
												<c:forEach items="${roles}" var="role">
													<option value="<c:out value="${role.id}"/>"><c:out
															value="${role.description}" /></option>
												</c:forEach>

											</select>
										</div>
									</div>
									<!-- /.col-lg-6 (nested) -->
									<div class="col-lg-6 column2" id="columntwo">
										
											<h3>Work Experience</h3>
											<hr>
											<div class="col-xs-6">
												<div class="radio">
												  <label>
												    <input type="radio" name="experienceStatus" id="optionsRadios1" value="lateral" checked>Lateral</label>
												</div>
											</div>
											
											<div class="col-xs-6">
												<div class="radio">
												  <label>
												    <input type="radio" name="experienceStatus" id="optionsRadios1" value="fresher">Fresher</label>
												</div>
											</div>
											<div id="clone-wrapper">
												<div class="toclone">
													<div class="form-group">
														<label>Company Name</label> <input class="form-control" name="companyName[]">
													</div>
													<div class="form-group">
														<label>Designation</label> <input class="form-control" name="designation[]">
													</div>
													<!-- <div class="form-group">
														<div class='input-group date' id='datetimepicker'>
															<label>Date Time</label>
										                    <input type='text' class="form-control cloneStartId" name="dateTimePick[]" id="datetimepicker"/>
										                    	<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
										                    </span>
										                </div>
													</div> -->
																										
													<div class="col-xs-6">
														<div class="form-group">
															<label for="disabledSelect">Start Date</label>
															<div class="input-group">
																<div class="date startDate" id="startDate"
																	data-date="" data-date-format="dd-mm-yyyy">
																	<input  class="form-control" size="16" type="text" value=""  name="startDate[]">													
																	<span class="input-group-addon">
																		<a href="#" class="add-on"><i class="glyphicon glyphicon-calendar"></i></a>
																	</span>													
																</div>
															</div>
														</div>	
													</div>													
													<div class="col-xs-6">
														<div class="form-group">
															<label for="disabledSelect">End Date</label>														
															<div class="input-group date" id="endDate"
																	data-date="" data-date-format="dd-mm-yyyy">															
																	<span class="input-group-addon">
																		<a href="#" class="add-on"><i class="glyphicon glyphicon-calendar"></i></a>
																	</span>
																													
																	<input class="form-control" size="16" type="text" value="" name="endDate[]">														
															</div>
														</div>	
													</div>												
													<div class="col-xs-12">
														<div class="form-group pull-right">
															<a href="#" class="clone btn btn-sm btn-primary" Onclick="count();"><i
																class="glyphicon glyphicon-plus" ></i> Add Experience </a>
															<a href="#" class="delete btn btn-sm btn-primary" Onclick="count();"><i
																class="glyphicon glyphicon-trash" ></i> Delete Experience </a>
														</div>
													</div>	
													</div><!-- /.toclone -->
												</div>											
										<h3>Contact Details</h3>
										<hr>
										<div class="form-group">
											<label for="mobile">Mobile</label> <input
												class="form-control" id="mobile" name="mobile" type="text">
										</div>
										<div class="form-group">
											<label>Address</label>
											<textarea class="form-control" rows="3" name="address"></textarea>
											<input type="hidden" id="count" name="experienceCount"></input>
										</div>
									<!-- /.col-lg-6 (nested) -->
								
										</div>
											<div class="col-lg-12">
										<hr>
										<button type="submit" class="btn btn-primary" onclick="submitExperienceCount()">
										<i class="glyphicon glyphicon-save"></i> Save User
										</button>
										<button type="reset" class="btn btn-danger">Reset</button>

									</div>
								</form>
							</div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	
</body>
</html>
