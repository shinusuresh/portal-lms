<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>

<link href='js/plugins/calender/fullcalendar.css' rel='stylesheet' />
<link href='js/plugins/calender/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<script src='js/plugins/calender/lib/moment.min.js'></script>
<script src='js/plugins/calender/lib/jquery-ui.custom.min.js'></script>
<script src='js/plugins/calender/fullcalendar.js'></script>
<script>

	$(document).ready(function() {
		

		var calendar = $('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
									
			
			dayClick: function(date, jsEvent, view){ 
				$("#temp_123").val(date.format()); $(this).css('background-color','#F4BA4D');
				var dateVal = $("#temp_123").val();
				$('#date-display').text(dateVal);
			},
			defaultDate: moment(),
			selectable: true,
			selectHelper: true,
			firstDay:1,
			select: function(start, end, allDay) {				
				$('#createEventModal #date').val(start);							
				$('#createEventModal').modal('show');
				$("#temp_123").val(start.format()+'\tto\t'+end.format()); $(this).css('background-color','#F4BA4D');
		         var dateVal = $("#temp_123").val();
					$('#date-display').text(dateVal);
			},
			
			editable: true,
			events: [
					<c:forEach var="holiday" items="${events}" begin="0">					
						{
						title: '<c:out value="${holiday.holidayType}"/>',
						start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${holiday.date}" />'								
						},
					</c:forEach>
	
					]
		});	
		
		
	      
		$('#createAppointmentForm').bootstrapValidator(
						{
							message : 'This value is not valid',
							feedbackIcons : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							fields : {
								employeecode:{
									validators:{
				                                required: true, 
				                                digits: true,
				                                notEmpty : {
													message : 'Employee code cannot be empty'
												},
									}
									
								},	
								description : {
									validators : {
										notEmpty : {
											message : 'The description is required and cannot be empty'
										},
									}
								},
								HolidayType : {
									validators : {
										notEmpty : {
											message : 'select Holiday type'
										},
									}
								},
								RadioGroup1 : {
									validators : {
										notEmpty : {
											message : 'select Holiday type'
										},
									}
								},
								RadioGroup2 : {
									validators : {
										notEmpty : {
											message : 'select Holiday type'
										},
									}
								},
							}
						});
		
	});
	
	
	function fn()
	{
	 document.getElementById('hide').hidden=false;
	}
	function fn1()
	{
		document.getElementById('hide').hidden=true;       
	}
	window.onload = function() 
	{            
	 document.getElementById('hide').hidden=true;       
	}
	function fn()
	{
	 document.getElementById('hide').hidden=false;
	}
	function fn2()
	{
	 document.getElementById('hide_leave_type').hidden=false;
	}
	function fn1()
	{
		document.getElementById('hide_leave_type').hidden=true; 
		document.getElementById('hide').hidden=true;    
	}
	function fn3()
	{
		
		document.getElementById('hide').hidden=true;    
	}
	window.onload = function() 
	{            
	 document.getElementById('hide').hidden=true; 
	 document.getElementById('hide_leave_type').hidden=true; 
	}
</script>
<style>
#calendar {
	width: 90%;
	margin: 20px auto;
}

.fc-sat {
	color: #F70E12 !important;
}

.fc-sun {
	color: #F70E12 !important;
}
</style>
</head>

<body>

	<div id="wrapper">

		<%@include file="../../common/navbar/navbar.jspf"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Mark Leaves</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
						<%@include file="../../common/messages/messagesnotifications.jsp"%>
							<div id='calendar'></div>
							<!-- Modal -->
							<div style="display: none;">
							<datalist id=userList>
							<c:forEach items="${users}" var="users">
													<option value=${users.employeeCode}>${users.firstName}</option>
											</c:forEach></datalist>
						
						</div>
							<div class="modal fade" id="createEventModal" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Mark Leave on<span id="date-display"></span>
											</h4>
										</div>
										<div class="modal-body">
											<form id="createAppointmentForm" class="form-horizontal" action="addleave.do?method=markUserLeave"
												role="form" method="post">
												<input type="hidden" name="leaveDate" id="temp_123" />

												<div class="form-group">
													<label class="col-sm-4 control-label">Employee Code</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" rows="3" list="userList"
															placeholder="Employee ID" name="employeecode">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">Description</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" rows="3" name="description"
															placeholder="description">
													</div>
												</div>



												<div class="form-group">
													<label class="col-sm-4 control-label">Holiday Type</label>
													<div class="col-sm-8">
														<label class="radio-inline"> <input type="radio"
															name="HolidayType" id="Leave" value="leave"
															onclick="fn2();">Leave
														</label> <label class="radio-inline"> <input type="radio"
															name="HolidayType" id="WorkfromHome" value="workfromhome"
															onclick="fn2();">Work From Home
														</label>
													</div>


													<div id="hide_leave_type">

														<div class="col-sm-offset-4 col-sm-8">

															<label class="radio-inline"> <input type="radio"
																name="RadioGroup1" value="fullday" id="RadioGroup1_0"
																onclick="fn3();"> Full day
															</label> <label class="radio-inline"> <input type="radio"
																name="RadioGroup1" value="halfday" id="RadioGroup1_1"
																onclick="fn();"> Half day
															</label> <br>

														</div>

													</div>


													<div id="hide">

														<div class="col-sm-offset-4 col-sm-8">

															<label class="radio-inline"> <input type="radio"
																name="RadioGroup2" value="radio" id="RadioGroup1_0">
																Forenoon
															</label> <label class="radio-inline"> <input type="radio"
																name="RadioGroup2" value="radio" id="RadioGroup1_1">
																Afternoon
															</label> <br>
														</div>

													</div>


												</div>



												<!--  
										        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="inputHolidayName">Select Approver</label>
                                            <div class="col-sm-8">
                                            <select class="form-control">
                                                <option>Shinu</option>
                                                <option>Arun</option>
                                                <option>Shankar</option>
                                                <option>Shekhar</option>
                                                <option>Bonny</option>
                                                
                                            </select>
                                            </div>
                                        </div>
                                        
                                        -->


												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-8">
														<button type="submit" class="btn btn-default">Save</button>
														<button type="reset" class="btn btn-default">Cancel</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<!-- /.modal hide -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>

		</div>
		<!-- /#page-wrapper -->

	</div>
</body>