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
var attendanceObject = [];

function getContent(id){
	
	var newObject = {
			id : 1,
			punchDetails : 'a,r,u,n',
	};
	// Stopping a loop
	for ( var i = 0; i < attendanceObject.length; i++ ) {
	    if ( attendanceObject[i].id==id ) {
	    	
	    	var String = attendanceObject[i].punchDetails;
	    }
	}
	//inside loop write code for checking ids equality,then set string
// 	var String = newObject.punchDetails;
	var rows;
	var arr = String.split(',');
	if(arr.length>1)
	for (var i = 0; i <arr.length-1; ++i) {
		
		var time=arr[i];
		var splitTime=time.split(':');
		var inOut=splitTime[2];
		var splitInOut=inOut.split('(');
		if(splitInOut[1]=='FF)')
			{
			var floor='Fourth Floor';
			}
		else
			{
			floor='Third Floor';
			}
		rows += "<tr class=\"success\"><td>1</td><td>"+splitTime[0]+":"+splitTime[1]+"</td><td>"+splitInOut[0]+"</td><td>"+floor+"</td></tr>";
		}
	else
		rows = "<tr class=\"success\"><td>No records found</td></tr>";
	return rows;
}
	var id;
	$(document)
			.ready(
					function(e) {
						
						<c:forEach var="attendances" items="${attendances}" begin="0">
						var newAttendance = {
								id :<c:out value="${attendances.id}"/>,
								punchDetails :'<c:out value="${attendances.punchRecords}"/>',
						};
						//newAttendance.id : "<c:out value="${attendances.id}"/>",
						//newAttendance.punchDetails : '<c:out value="${attendances.punchRecords}"/>',
						attendanceObject.push(newAttendance);
					</c:forEach>
						$('#punchDetails').hide();
						$(".temp")
								.click(
										function() {
											
											var ids = $(this).attr('id');
											var rows = getContent(ids);
											$newRow = "<div class=\"table-responsive\" id=\"punchDetails\"><table class=\"table\" id=\"childTable1\"><thead><tr><th>Sl No</th><th>Time</th><th>IN/OUT</th><th>Floor</th></tr></thead><tbody>"+rows+"</tbody></table></div>";
											// $(this).parent().parent().after('<tr>');
											// $(this).parent().parent().after($newRow);
											if ($('.childTable').is(
															':visible')) {
												$('.childTable').remove();
												$('#childTable1').slideUp(
														'slow');
												$(this).parent().parent().next(
														'.childTable').hide(
														'slow');
											} else {
												
												$(
														'<tr class="childTable"><td colspan="15">'
																+ $newRow
																+ '</td></tr>')
														.insertAfter(
																$(this)
																		.parent()
																		.parent());
												$(this).parent().parent().next(
														'.childTable').show();

											}

											//$('#punchDetails').hide();	
											// console.log($newRow);
											//  $curRow.prepend($newRow);
											console.log('added');
										});
					});
	/* $('#button tbody').on('click', function () {
		var month = $('#selectMonth').val();
		$('#getMonth').val(month);
		//$('#getDetails').submit();
		alert($(this).closest('div').attr('id'));
		var s = $(this).parent().parent().attr('id');
		alert(s);
		$('#punchDetails').show();
		alert($(s).id);
		$('#punchDetails').appendTo('#testing');
		 var tr = $(this).closest('tr');
	     var row = table.row( tr );

	}); */
</script>
</head>
<body>

	<div id="wrapper">
		<%@include file="../../common/navbar/navbar.jspf"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h2 class="page-header">Attendance</h2>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">DataTables Advanced Tables</div>
						<!-- /.panel-heading -->
						<!-- /.panel-body -->
						<!-- /.panel-heading -->
						<div class="panel-body">
							<form action="ss.do" method="post" id="getDetails">
								<div class="form-group col-lg-2">
									<label for="selectMonth">Select Month</label> <select
										onchange="monthSelect()" id="selectMonth" class="form-control">
										<option selected="selected">select month</option>
										<option>January</option>
										<option>February</option>
										<option>March</option>
										<option>April</option>
										<option>May</option>
										<option>June</option>
										<option>July</option>
										<option>August</option>
										<option>September</option>
										<option>October</option>
										<option>November</option>
										<option>December</option>
									</select>
								</div>
								<div>
									<div class="pull-right alert alert-success"
										style="margin-left: 10px;">
										<label for="lastWeekWorkHours">Last week work hours:</label> <span
											id="lastWeekWorkHours"></span>
									</div>
									<div class="pull-right alert alert-success">
										<label for="currentMonthWorkHours">Current Month
											Average:</label> <span id="lastWeekWorkHours"></span>
									</div>
								</div>
								<input type="hidden" value="month" name="getMonth" id="getMonth" />
							</form>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div id="dataTables-example_wrapper"
									class="dataTables_wrapper form-inline" role="grid">
									<div class="row">
										<div class="col-sm-6">
											<div class="dataTables_length" id="dataTables-example_length">
												<label><select name="dataTables-example_length"
													aria-controls="dataTables-example"
													class="form-control input-sm"><option value="10">10</option>
														<option value="25">25</option>
														<option value="50">50</option>
														<option value="100">100</option></select> records per page</label>
											</div>
										</div>
										<div class="col-sm-6">
											<div id="dataTables-example_filter" class="dataTables_filter">
												<label>Search:<input type="search"
													class="form-control input-sm"
													aria-controls="dataTables-example"></label>
											</div>
										</div>
									</div>
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<c:set var="count" scope="session" value="${0}" />
											<tr>

												<th>Attendance Id</th>
												<th>Date</th>
												<th>Shift</th>
												<th>Shift In Time</th>
												<th>Shift out Time</th>
												<th>Actual In Time</th>
												<th>Actual Out Time</th>
												<th>Work Duration</th>
												<th>OT</th>
												<th>Total Duration</th>
												<th>Late By</th>
												<th>Early Going By</th>
												<th>Status</th>
												<th>User Id</th>
												<th>Punch Records</th>

											</tr>
										</thead>
									<tbody>

											<c:forEach items="${attendances}" var="attendances">

												<tr class="odd gradeX" id="inserthere">
													<c:set var="count" scope="session" value="${count+1}" />
													<td><c:out value="${count}" /></td>
													<td>${attendances.id}</td>
													<td>${attendances.shift}</td>
													<td>${attendances.shiftInTime}</td>
													<td>${attendances.shiftOutTime}</td>
													<td>${attendances.actualInTime}</td>
													<td>${attendances.actualOutTime}</td>
													<td>${attendances.workDuration}</td>
													<td>${attendances.ot}</td>
													<td>${attendances.totalDuration}</td>
													<td>${attendances.lateBy}</td>
													<td>${attendances.earlyGoingBy}</td>
													<td>${attendances.status}</td>
													<td>${attendances.user.id}</td>

													<td id="helloworld" class="text-center" width="5%"><a
													class="temp" id="${attendances.id}" href="#"><i class="fa fa-plus"></i></a></td>

													<!-- <div class="pull-right btn-group " id="hey">
															<button type="button" data-toggle="dropdown"
																class="btn btn-default dropdown-toggle"
																onclick="monthSelect()">
																<span class="caret"></span>
															</button>
															<span id="testing"></span>
														</div> -->
													<!-- </td> -->

												</tr>
											</c:forEach>
										</tbody>
									</table>
									<!-- table to be pushed inside abobe table -->
										
									<!-- //table to be pushed inside abobe table -->
								</div>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		
	</script>
</body>
</html>
