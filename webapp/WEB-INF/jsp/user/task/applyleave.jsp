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
var eventObjects = []; 
	$(document).ready(function() {
		
		$('#reset').click(function() {
	});
		var calendar = $('#calendar').fullCalendar({
			
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month'
			},
			defaultDate: moment(),
			selectable: true,
			selectHelper: true,
			firstDay:1,
			select: function(start, end, allDay) {
				var startDate =new Date(start);
				var endDate = new Date(end);
				var newenddate = new Date(endDate);
				newenddate.setDate(newenddate.getDate() - 1);
				end = new Date(newenddate);
				if(startDate.getMonth() != newenddate.getMonth()){
					end = new Date(startDate.getFullYear(), startDate.getMonth() + 1, 0);
				}
				$('#createEventModal #date').val(start, end);
				$('#createEventModal').modal('show');
				$("#temp_123").val(start.format()+'\tto\t'+end.getDate()+'-'+(end.getMonth()+1)+'-'+end.getFullYear()); $(this).css('background-color','#F4BA4D');
				var dateVal = $("#temp_123").val();
					$('#date-display').text(dateVal);
					//getting start date and end date
			},
			
			
			events: [
     	       	<c:forEach var="holiday" items="${events}" begin="0">					//shows calendar holidays 
					{
						//var dsplit = dateVar.split("-");
						//var d=new Date(dsplit[0],dsplit[1]-1,dsplit[2]);
     	       		//var date = new Date('<fmt:formatDate pattern="dd-MM-yyyy" value="${holiday.date}" />');
     	       		//alert(date);
					
					uniqueId: '<c:out value="${holiday.id}"/>',
					start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${holiday.date}" />',
					end: '<fmt:formatDate pattern="yyyy-MM-dd" value="${holiday.date}" />',
					type: 'Tryzens Holiday',
					title: '<c:out value="${holiday.description}"/>',
					editable: false,
					backgroundColor: '#F5A9A9'
					},
				</c:forEach>
		     	<c:forEach var="leaves" items="${leaves}" begin="0">					//shows leaves 
				{
		     		
				title: '<c:out value="${leaves.status}"/>',
				uniqueId: '<c:out value="${leaves.id}"/>',
				<c:set var="newEndDate"  value="${leaves.endDate}" />
				<c:set target="${newEndDate}" property="time" value="${newEndDate.time + 86400000}"/>
				start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${leaves.startDate}" />',
				end: '<fmt:formatDate pattern="yyyy-MM-dd" value="${newEndDate}" />',
				type: 'LeaveRequest',
				status: '<c:out value="${leaves.status}"/>',
				uniqueId: '<c:out value="${leaves.id}"/>',
				description: '<c:out value="${leaves.description}"/>',
				approver: '<c:out value="${leaves.approver.firstName}"/>',
				editable: false,
				backgroundColor: '#E2A9F3'
				
				},
			</c:forEach>
				<c:forEach var="workFromHomeRequests" items="${workFromHomeRequests}" begin="0">					//shows work from home
				{
					<c:set var="newEndDate"  value="${workFromHomeRequests.endDate}" />
					<c:set target="${newEndDate}" property="time" value="${newEndDate.time + 86400000}"/>
				uniqueId: '<c:out value="${workFromHomeRequests.id}"/>',
				title: '<c:out value="${workFromHomeRequests.status}"/>',
				start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${workFromHomeRequests.startDate}" />',
				end: '<fmt:formatDate pattern="yyyy-MM-dd" value="${newEndDate}" />',
				type: 'WorkFromHomeRequest',
				status: '<c:out value="${workFromHomeRequests.status}"/>',
				uniqueId: '<c:out value="${workFromHomeRequests.id}"/>',
				description: '<c:out value="${workFromHomeRequests.description}"/>',
				approver: '<c:out value="${workFromHomeRequests.approver.firstName}"/>',
				editable: false,
				backgroundColor: '#378006'
				},
			</c:forEach>
				
				<c:forEach var="weekendWorkingRequests" items="${weekendWorkingRequests}" begin="0">					//shows weekendWorking  
				{
				title: '<c:out value="${weekendWorkingRequests.status}"/>',
				<c:set var="newEndDate"  value="${weekendWorkingRequests.endDate}" />
				<c:set target="${newEndDate}" property="time" value="${newEndDate.time + 86400000}"/>
				start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${weekendWorkingRequests.startDate}" />',
				end: '<fmt:formatDate pattern="yyyy-MM-dd" value="${newEndDate}" />',
				type: 'WeekendWorkingRequest',
				status: '<c:out value="${weekendWorkingRequests.status}"/>',
				uniqueId: '<c:out value="${weekendWorkingRequests.id}"/>',
				description: '<c:out value="${weekendWorkingRequests.description}"/>',
				approver: '<c:out value="${weekendWorkingRequests.approver.firstName}"/>',
				backgroundColor: '#819FF7'
				},
			</c:forEach>
				
				<c:forEach var="bankHolidayRequests" items="${bankHolidayWorkingRequests}" begin="0">					//shows bank holiday working
				{
					<c:set var="newEndDate"  value="${bankHolidayRequests.endDate}" />
					<c:set target="${newEndDate}" property="time" value="${newEndDate.time + 86400000}"/>
				title: '<c:out value="${bankHolidayRequests.status}"/>',
				start: '<fmt:formatDate pattern="yyyy-MM-dd" value="${bankHolidayRequests.startDate}" />',
				end: '<fmt:formatDate pattern="yyyy-MM-dd" value="${newEndDate}" />',
				type: 'BankHolidayWorkingRequest',
				status: '<c:out value="${bankHolidayRequests.status}"/>',
				uniqueId: '<c:out value="${bankHolidayRequests.id}"/>',
				description: '<c:out value="${bankHolidayRequests.description}"/>',
				approver: '<c:out value="${bankHolidayRequests.approver.firstName}"/>',
				editable: false,
				backgroundColor: '#A9F5D0'
				},
			</c:forEach>
		],
		 eventRender: function(event, element,calEvent) {
			 eventObjects.push(event);
			 //Make status first letter uppercase
			 element.find(".fc-event-title").css('text-transform', 'capitalize');
			 element.find(".fc-event-inner").css('height', '65px');
			 element.find(".fc-event-title").after($("<span class=\"fc-event-icons\"></span>")
					 .html("<a id=\'cancelRequest\' href='#' onClick=\'cancelHoliday("+event.uniqueId+")\' style=\'float: right;\'> <i class=\"fa fa-cog fa-2x\"></i></a> "+
			        			"</span>"));
			        
			         
			         
					 },
		
		/* eventClick: function(event) {
			$('#createEventModalEvent #type_input').val(event.type);
			var type= $('#type_input').val();
			switch(type){
			case "Tryzens Holiday":
				$('#createEventModalHoliday').modal('show');
				break;
			default: 
				$('#createEventModalEvent').modal('show');
			}	
			$('#createEventModalEvent #requestId').val(event.uniqueId);
			$('#createEventModalEvent #start_date_input').val(event.start.format());
			$('#createEventModalEvent #end_date_input').val(event.end.format());
			$('#createEventModalEvent #status_input').val(event.status);
			$('#createEventModalEvent #approver_input').val(event.approver);
			var approver= $('#approver_input').val();
			$('#type').text(type);
			$('#approver').text(approver);
			
			if(event.status =='CancellationRejected')
			{
				
			$('#cancel-button').prop("disabled",true);
			}
			else
				{
				$('#cancel-button').prop("disabled",false);
				} 
			
	    },*/ eventMouseover: function(event, jsEvent) {
	    	$('#createEventModalEvent #type_input').val(event.type);
			var type= $('#type_input').val();
			$('#createEventModalEvent #requestId').val(event.uniqueId);
			$('#createEventModalEvent #start_date_input').val(event.start.format());
			$('#createEventModalEvent #end_date_input').val(event.end.format());
			$('#createEventModalEvent #status_input').val(event.status);
			$('#createEventModalEvent #title_input').val(event.description);
			$('#createEventModalEvent #approver_input').val(event.approver);
			var approver= $('#approver_input').val();
			var status;
			switch(type){
			case "Tryzens Holiday":
				status='Tryzens Holiday'
				break;
			default: 
				status = 'Request status: '+event.status;
			}	
			$('#status').text(status);
			$('#type').text(type);
			//hoverdiv(event, 'divtoshow');
			
			if(event.status =='cancelled'||event.status=='waitingCancellation'||event.status=='Rejected')
			{
				
				$('#createEventModalEvent #cancel-button').prop("disabled",true);
			}
			else
				{
				$('#createEventModalEvent #cancel-button').prop("disabled",false);
				} ;
			
        },
        eventAfterRender : function(event, element) {
        	console.log($(event));
        	var eventType = event.type;
        	var statusText = "";
        	if(eventType != 'Tryzens Holiday'){
        		statusText = event.type + ' ' + event.status + ' with ' + event.approver;
        	} else {
        		statusText = 'Tryzens Holiday';
        	}
        	
        	element.tooltip({	            	            
	            html:true,
	            title: statusText
            });
        }
		});
		
	});
	
	$('[data-toggle="popover"]').popover();		

	function hoverdiv(e,divid){
	    var div = document.getElementById(divid);

	   $(div).css('visibility','visible');
	   setTimeout(function(){
		   $(div).css('visibility','hidden');
		},3000);
	   return true;
	}
	function cancelHoliday(event){	
		$('#createEventModalEvent').modal('show');
		//console.log("id is"+event);
	}
	
	
	 function cancelHoliday(id) {
		 for ( var i=0; i<eventObjects.length; i++){ 
				obj = eventObjects[i];
		        	if(obj.uniqueId == id)
		        		{
		    			var type= $('#type_input').val();
		    			switch(type){
		    			case "Tryzens Holiday":
		    				$('#createEventModalHoliday').modal('show');
		    				break;
		    			default: 
		    				$('#createEventModalEvent').modal('show');
		    			}	
		    			var approver= $('#approver_input').val();
		        		break; 
		        		}
				}
			
			
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
		document.getElementById('hide_leave_type').hidden=false; 
		document.getElementById('hide').hidden=true;    
	}
	function fn3()
	{
		document.getElementById('hide_leave_type').hidden=true; 
		document.getElementById('hide').hidden=true;    
	}
	function fn4()
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
					<h1 class="page-header">New Request</h1>
					<div>
						<div class="panel panel-green">
	                        <div class="panel-heading">
	                            Color Legend
	                        </div>
	                        <div class="panel-body center-block">
	                        	<div class="col-md-2">
	                        		<span class="foo"
										style="background-color: #F5A9A9; display: inline-block"></span><label>
											Annual Holiday </label>
	                        	</div>
	                        	
	                        	<div class="col-md-2">
	                        		<span class="foo"
										style="background-color: #E2A9F3; display: inline-block"></span>
										<label>Leave</label>
								</div>
								<div class="col-md-2">
									<span class="foo"
										style="background-color: #378006; display: inline-block"></span><label>Work
											from home</label>
								</div>		
								<div class="col-md-3">
									<span class="foo"
										style="background-color: #A9F5D0; display: inline-block"></span><label>Bank
											Holiday Working</label>
								</div>
								<div class="col-md-3">
									<span class="foo"
										style="background-color: #819FF7; display: inline-block"></span><label>Weekend
											Working</label>
	                        	</div>
							</div>
	                        
	                    </div>						
					</div>
				</div>

				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="alert alert-info" id="divtoshow"
								style="visibility: hidden;">
								<a href="#" class="close" data-dismiss="alert">&times;</a><span
									id="status"> </span>.
							</div>
							<!-- Slot for message box -->
							<c:set var="divNeeded" value="false"></c:set>
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
												Create Request on <span id="date-display"></span>
											</h4>
										</div>
										<div class="modal-body">
											<form id="createAppointmentForm" class="form-horizontal"
												role="form" action="newrequest.do?method=addLeaveRequest"
												method="post">
												<input type="hidden" name="leaveDate" id="temp_123" />


												<div class="form-group">
													<label class="col-sm-4 control-label">Holiday
														Description</label>
													<div class="col-sm-8">
														<textarea class="form-control" rows="3"
															placeholder="Holiday Description"
															name="holidayDescription"></textarea>
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-4 control-label">Holiday Type</label>
													<div class="col-sm-8">
														<label class="radio-inline"> <input type="radio"
															name="RequestType" id="Leave" value="leave"
															onclick="fn3();">Leave
														</label> <label class="radio-inline"> <input type="radio"
															name="RequestType" id="WorkfromHome" value="WorkFromHome"
															onclick="fn3();">Work From Home
														</label><label class="radio-inline"> <input type="radio"
															name="RequestType" id="weekendwork" value="weekendwork"
															onclick="fn1();">Weekend work
														</label>
													</div>
													<br>
													<div id="hide_leave_type">
														<div class="form-group form-horizontal">
															<br> <label class="col-sm-4 control-label">Select
																Type</label>


															<div class="col-sm-6">
																<select required="required" class="form-control"
																	name="weekendWorkingType">

																	<option value="paid">Paid</option>
																	<option value="compoff">compoff</option>

																</select>
															</div>
														</div>
													</div>

													<div id="hide_leave_type">

														<div class="col-sm-offset-4 col-sm-8">
																														  
																<label class="radio-inline"> <input type="radio" 
																	name="RadioGroup1" value="FullDay" id="RadioGroup1_0"
																	onclick="fn4();"> Full day
																</label> <label class="radio-inline"> <input type="radio"
																	name="RadioGroup1" value="halfDay" id="RadioGroup1_1"
																	onclick="fn();"> Half day
																</label>	    																  
															
														</div>


													</div>

													<div id="hide">

														<div class="col-sm-offset-4 col-sm-8">

															<label class="radio-inline"> <input type="radio"
																name="RadioGroup2" value="ForNoon" id="RadioGroup1_0">

																Forenoon
															</label> <label class="radio-inline"> <input type="radio"
																name="RadioGroup2" value="Afternoon" id="RadioGroup1_1">
																Afternoon
															</label> <br>
														</div>

													</div>



												</div>

												<div class="form-group form-horizontal">
													<label class="col-sm-4 control-label"
														for="inputHolidayName">Select Approver</label>
													<div class="col-sm-8">
														<select required="required" class="form-control"
															name="approver">
															<c:forEach var="approvers" items="${approvers}" begin="0">

																<option name="approver" value="${approvers.id}"><c:out
																		value="${approvers.firstName}" /></option>>
													</c:forEach>

														</select>
													</div>
												</div>

												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-8">
														<button type="submit" class="btn btn-primary btn-sm">Save</button>
														<button type="reset" id="reset" class="btn btn-danger btn-sm" data-dismiss="modal">Cancel</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>

							</div>
							<!-- /.modal hide -->
						</div>
						<!--  new modal hide event -->

						<div class="modal fade" id="createEventModalEvent" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">
											Holiday Type <span id="type"></span>
										</h4>
										<span id="datew"></span> <span id="date1"></span>
									</div>
									<div class="modal-body">
										<form class="form-horizontal" role="form"
											action="cancelRequest.do?method=cancellationRequest"
											method="post">
											<div class="form-group form-horizontal">
												<label class="col-sm-4 control-label">Start Date</label>
												<div class="col-sm-8">
													<input class="form-control" type="text"
														name="start_date_input" disabled="disabled"
														id="start_date_input" />
												</div>
											</div>
											<div class="form-group form-horizontal">
												<label class="col-sm-4 control-label">End Date</label>
												<div class="col-sm-8">
													<input class="form-control" type="text" disabled="disabled"
														name="end_date_input" id="end_date_input" />
												</div>
											</div>
											<div class="form-group form-horizontal">
												<label class="col-sm-4 control-label">Status</label>
												<div class="col-sm-8">
													<input class="form-control" disabled="disabled" type="text"
														name="status_input" id="status_input" />
												</div>
											</div>
											<div class="form-group form-horizontal">
												<label class="col-sm-4 control-label">Description</label>
												<div class="col-sm-8">
													<input class="form-control" disabled="disabled" type="text"
														name="title_input" id="title_input" />
												</div>
											</div>

											<input type="hidden" name="requestId" id="requestId" /> <input
												type="hidden" name="type_input" id="type_input" /> <input
												type="hidden" name="from" value="calendar">
											<div class="form-group form-horizontal">
												<label class="col-sm-4 control-label "> Approver</label>
												<div class="col-sm-8">
													<input type="text" class=" form-control"
														disabled="disabled" name="approver_input"
														id="approver_input" />
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-offset-4 col-sm-8">
													<button class="btn btn-warning btn-sm" type="submit"
														id="cancel-button" class="btn btn-default">Cancel
														Request</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>

						</div>
						<!--  new modal hide holiday -->

						<div class="modal fade" id="createEventModalHoliday" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">
											Holiday Type <span id="type"></span>
										</h4>
										<span id="datew"></span> <span id="date1"></span>
									</div>
									<div class="modal-body">
										<form class="form-horizontal" role="form" action="#"
											method="post">
											<div class="form-group form-horizontal">
												<label class="col-sm-4 control-label">Date</label>
												<div class="col-sm-8">
													<input class="form-control" type="text"
														name="start_date_input" disabled="disabled"
														id="start_date_input" />
												</div>
											</div>
											<div class="form-group form-horizontal">
												<label class="col-sm-4 control-label">Holiday
													Description</label>
												<div class="col-sm-8">
													<input class="form-control" disabled="disabled" type="text"
														name="title_input" id="title_input" />
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-offset-4 col-sm-8">
													<button class="btn-warning btn-sm" type="submit"
														class="btn btn-default" id="cancelButton">Create
														bank holiday working</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>

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
<script type="text/javascript">
	
$('#createAppointmentForm').bootstrapValidator(
		{
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				holidayDescription : {
					validators : {
						notEmpty : {
							message : 'Description cannot be empty'
						},
					}
				},
				RequestType: {
	                validators: {
	                    notEmpty: {
	                        message: 'Holiday Type is required'
	                    }
	                }
	            },
			}
		});

</script>
</body>