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
			
			defaultDate: moment(),
			selectable: true,
			selectHelper: true,
			firstDay:1,
			select: function(start, end, allDay) {				
				$('#createEventModal #date').val(start, end);							
				$('#createEventModal').modal('show');
				$("#temp_123").val(start.format()+'to'+end.format()); $(this).css('background-color','#F4BA4D');
		         var dateVal = $("#temp_123").val();
					$('#date-display').text(dateVal);
				
			},
			
			editable: true,
			events: [
			     	<c:forEach var="holiday" items="${events}" begin="0">					
					{
					title: '<c:out value="${holiday.holidayType}"/>',
					start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${holiday.date}" />',								
					},
				</c:forEach>
					<c:forEach var="leaves" items="${leaves}" begin="0">					//shows leaves 
					{
					title: '<c:out value="${leaves.description}"/>',
					start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${leaves.startDate}" />',
					end: '<fmt:formatDate pattern="yyyy-MM-dd" value="${leaves.endDate}" />',
					backgroundColor: '#378006'
					},
				</c:forEach>
					<c:forEach var="workFromHomeRequests" items="${workFromHomeRequests}" begin="0">					//shows work from home
					{
					title: '<c:out value="${workFromHomeRequests.startDate}"/>',
					start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${workFromHomeRequests.startDate}" />',
					end: '<fmt:formatDate pattern="yyyy-MM-dd" value="${workFromHomeRequests.endDate}" />',
					backgroundColor: '#378006'
					},
				</c:forEach>
					<c:forEach var="bankHolidayRequests" items="${bankHolidayWorkingRequests}" begin="0">					//shows bank holiday working
					{
					title: '<c:out value="${bankHolidayRequests.startDate}"/>',
					start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${bankHolidayRequests.startDate}" />',
					end: '<fmt:formatDate pattern="yyyy-MM-dd" value="${bankHolidayRequests.endDate}" />',
					backgroundColor: '#378006'
					},
				</c:forEach>
			]
		});
		
		
		
		
		
	});

	
	
	function fn()
	{
	 document.getElementById('hide').hidden=false;
	}
	
	
	function fn3()
	{
		
		document.getElementById('hide').hidden=true;    
	}
	window.onload = function() 
	{            
	 document.getElementById('hide').hidden=true; 
	 document.getElementById('hide_leave_type').hidden=false; 
	}
	
</script>
<style>
#calendar {
	width: 90%;
	margin: 40px auto;
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
					<h1 class="page-header">Bank Holiday Working Request</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							<!-- Slot for message box -->
							<%@include file="../../common/messages/messagesnotifications.jsp"%>
							<div id='calendar'></div>
							<!-- Modal -->
							<div class="modal fade" id="createEventModal" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												Create Request on<span id="date-display"></span>
											</h4>
										</div>
										<div class="modal-body">
											<form id="createAppointmentForm" class="form-horizontal"
												role="form"
												action="newBankHolidayRequest.do?method=addNewRequest"
												method="post">
												<input type="hidden" name="leaveDate" id="temp_123" /> <input
													type="hidden" name="requestType" value="BankHolidayWorking"
													id="temp_123" />
												<div class="form-group form-horizontal">
													<div class="form-group">
														<label class="col-sm-4 control-label"> Description</label>
														<div class="col-sm-8 form-group">
															<textarea class="form-control " rows="3"
																placeholder="Description" name="description"></textarea>
														</div>
													</div>
													<label class="col-sm-4 control-label"
														for="inputHolidayName">Select Approver</label>
													<div class="col-sm-8 form-group">
														<select required="required" class="form-control"
															name="approver">

															<c:forEach var="approvers" items="${approvers}" begin="0">

																<option value="${approvers.id}"><c:out
																		value="${approvers.firstName}" /></option>>
													</c:forEach>


														</select>
													</div>
													<label class="col-sm-4 control-label"
														for="inputHolidayName">Select bank holiday working
														type</label>
													<div class="col-sm-8 form-group">
														<select required="required" class="form-control"
															name="bankHolidayWorkingType">

															<option value="paid">Paid</option>
															<option value="compoff">compoff</option>


														</select>
													</div>
													<div id="hide_leave_type">

														<div class="col-sm-offset-4 col-sm-8">

															<label class="radio-inline"> <input type="radio"
																name="RadioGroup1" value="FullDay" id="RadioGroup1_0"
																onclick="fn3();"> Full day
															</label>  <label class="radio-inline"> <input
																type="radio" name="RadioGroup1" value="HalfDay"
																id="RadioGroup1_1" onclick="fn();"> Half day
															</label> <br>
														</div>

													</div>

													<div id="hide">

														<div class="col-sm-offset-4 col-sm-8">

															<label class="radio-inline"> <input type="radio"
																name="RadioGroup2" value="fornoon" id="RadioGroup1_0">

																Forenoon
															</label>  <label class="radio-inline"> <input
																type="radio" name="RadioGroup2" value="Afternoon"
																id="RadioGroup1_1"> Afternoon
															</label> <br>
														</div>
													</div>
												</div>


												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-8">
														<button type="submit" class="btn btn-default">Save</button>

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