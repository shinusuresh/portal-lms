<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>

<link href='${host}/js/plugins/calender/fullcalendar.css'
	rel='stylesheet' />
<link href='${host}/js/plugins/calender/fullcalendar.print.css'
	rel='stylesheet' media='print' />
<script src='${host}/js/plugins/calender/lib/moment.min.js'></script>
<script src='${host}/js/plugins/calender/lib/jquery-ui.custom.min.js'></script>
<script src='${host}/js/plugins/calender/fullcalendar.js'></script>
<script type="text/javascript">
var eventObjects = []; 
	$(".formData").val("valuesgoeshere");
	$(document).ready(function() {
		
		var calendar = $('#calendar').fullCalendar({
			
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay',
				
			},
			
			
			dayClick: function(date, jsEvent, view){ 
				$("#temp_123").val(date.format());
				var dateVal = $("#temp_123").val();
				$('#date-display').text(dateVal);
				$('[data-toggle="popover"]').popover('hide');
				
				
			},

			defaultDate: moment(),
			selectable: true,
			
			selectHelper: true,
			firstDay:1,
			select: function(start, end, allDay) {
				document.getElementById('submit_text').innerHTML = 'Save Holiday';
				document.getElementById('spanTitle').innerHTML = 'Create Holiday';
				document.getElementById('spanLabel').innerHTML = 'Do you want to set holiday on';
				$('#createEventModal #unique_id').val('');
				$('#createEventModal #holiday_type').val('');
        		$('#createEventModal #description').val('');
				$('#createEventModal #date').val(start);							
				$('#createEventModal').modal('show');
				
			},
		
			
			editable: false,
			
			events: [
			         
						<c:forEach var="holiday" items="${events}" begin="0">					
						{
							id: '<c:out value="${holiday.id}"/>',
							title: '<c:out value="${holiday.holidayType}"/>',
							description: '<c:out value="${holiday.description}"/>',
							start: $.fullCalendar.moment('<fmt:formatDate pattern="yyyy-MM-dd" value="${holiday.date}" />'),
							end: $.fullCalendar.moment('<fmt:formatDate pattern="yyyy-MM-dd" value="${holiday.date}" />'),
							backgroundColor: '#ED1317',
							//color: '#ED1317',
							allDay: true,
							
						},
						</c:forEach>						
				],	
							
				eventRender: function(event, element, calEvent) {
					eventObjects.push(event);
					console.log($(calEvent));
					console.log(event.id);
					 element.find(".fc-event-inner").css('height', '65px');
			         element.find(".fc-event-title").after($("<span class=\"fc-event-icons\"></span>")
			        	.html("<span rel=\"popover\"  data-html=\"true\" data-toggle=\"popover\" data-placement=\"bottom\" style=\"float: right;\" "+ 
			        			"data-container=\"body\" data-content=\"<span style=\'width: 200px;\'>"+
			        			"<a id=\'deleteHoliday\' href='#' onClick=\'deleteHoliday("+event.id+")\' class=\'btn btn-danger btn-sm\'><i class='fa fa-trash-o'></i> Delete Holiday</a> "+
			        			"<a id=\'updateHoliday\' href='#' onClick=\'updateHoliday("+event.id+")\' class=\'btn btn-info btn-sm\'><i class='fa fa-edit'></i> Update Holiday</a><span>\"> "+
			        			"<i class=\"fa fa-cog fa-2x\"></i></span></a>"));
			        
			         
			         
			    }
			
			
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
						holiday_type : {
							validators : {
								notEmpty : {
									message : 'Holiday Type cannot be empty'
								},
							}
						},
					}
		});
		
		$('[data-toggle="popover"]').popover();		
		
		//To tackle refreshes
		window.history.pushState("","", location.href); 
		
	});
	
	function deleteHoliday(id){	
		$('[data-toggle="popover"]').popover('hide');
		console.log("id is"+id);
		/* $('[data-toggle="popover"]').popover('destroy');
		$('#confirm').modal({ backdrop: 'static', keyboard: false })
	        .one('click', '#delete', function (e) {
	           // $form.trigger('submit');
	        });
 		*/
 		location.href = 'inputHoliday.do?method=deleteHoliday&selection=addholidays&id='+id;
	}
	function updateHoliday(id){	
		$('[data-toggle="popover"]').popover('hide');	
		console.log("id is"+id);
		var obj;
		$('#createEventModal #unique_id').val('');
		for ( var i=0; i<eventObjects.length; i++){ 
		obj = eventObjects[i];
		alert(obj.id);
        	if(obj.id == id)
        		{
        		alert(id);
        		$('#createEventModal #unique_id').val(obj.id);
        		$('#createEventModal #holiday_type').val(obj.title);
        		$('#createEventModal #description').val(obj.description);
        		$('#date-display').text((obj.start).format());
        		break;
        		}
		}
		$('#createEventModal').modal('show');
		document.getElementById('submit_text').innerHTML = 'Update Holiday';
		document.getElementById('spanTitle').innerHTML = 'Update Holiday';
		document.getElementById('spanLabel').innerHTML = 'Do you want to update holiday on ';
		
		/* $('[data-toggle="popover"]').popover('destroy');
		$('#confirm').modal({ backdrop: 'static', keyboard: false })
	        .one('click', '#delete', function (e) {
	           // $form.trigger('submit');
	        });
 		*/
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
					<h1 class="page-header">Add Holidays</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<c:if test="${not empty events}">
				<%@include file="../../common/holidays/listBankHolidays.jspf"%>
			</c:if>	
			<div class="row">				
				<div class="col-lg-12">
					<div class="panel panel-default">
						
						<div class="panel-body">
							<c:set var="divNeeded" value="false"></c:set>
							<%@include file="../../common/messages/messagesnotifications.jsp"%>
							<div id='calendar'></div>
							<div class="modal fade" id="createEventModal" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel"><span id="spanTitle">Create Holiday</span></h4>
										</div>
										<div class="modal-body">
											<form id="createAppointmentForm" role="form" method="post" action="inputHoliday.do?method=addHoliday&selection=addholidays">
												<div class="alert alert-info">
					                                 <span id="spanLabel"> Do you want to set holiday On </span> <span id="date-display"> </span>
					                            </div>												
												<hr>
												<input type="hidden" name="leaveDate" id="temp_123" />	
												<input type="hidden" name="unique_id" id="unique_id" />											
												<div class="form-group">
													<label>Holiday Type</label>
													<input type="text" name="holiday_type" id="holiday_type" autocomplete="off" class="form-control placeholder-no-fix">
												</div>
												<div class="form-group">
													<label>Description</label>
													<textarea cols="22" rows="3" name="description" id="description" class="form-control"></textarea>
												</div>

												<div class="modal-footer">
													<div class="col-sm-offset-4 col-sm-8">														
														<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
														<button type="submit" class="btn btn-primary" ><span id="submit_text">Save Holiday</span></button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
								
								<!-- Confirm dialog -->
								<div id="confirm" class="modal hide fade">
									<div class="modal-body">Are you sure?</div>
									<div class="modal-footer">
										<button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Delete</button>
										<button type="button" data-dismiss="modal" class="btn">Cancel</button>
									</div>
								</div>

							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
				
				</div>
			</div> <!-- /#row -->	
			</div><!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->

	</body>

</html>
