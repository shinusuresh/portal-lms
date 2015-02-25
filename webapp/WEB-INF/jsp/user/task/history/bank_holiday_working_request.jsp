<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/jsp/common/commontaglibs.jspf"%>
<%@include file="/WEB-INF/jsp/common/environment.jspf"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="/WEB-INF/jsp/common/commonheadinclude.jspf"%>
<script type="text/javascript" src="${host}/js/plugins/tags/bootstrap-tagsinput.js"></script> 
 <script type="text/javascript" src="${host}/js/plugins/typeahead/bloodhound.js"></script> 
<script type="text/javascript" src="${host}/js/bootstrap3-typeahead.js"></script> 
</head>
<body>

	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/navbar/navbar.jspf"%>
		<%--   <c:set var="count" scope="session" value="${0}"/> --%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Bank Holiday Working</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<!-- Slot for message box -->
				<%@include
					file="/WEB-INF/jsp/common/messages/messagesnotifications.jsp"%>

				<div class="col-lg-12">
				<!-- for filtering leaves -->
					<div>
						<div class="panel panel-green">
	                        <div class="panel-heading">
	                            Search Skills
	                        </div>
	                        <div class="panel-body center-block">
	                        	<div class="col-md-12">
	                        		<form id="searchForm" class="form-horizontal" role="form">
	                        			<div class="form-group">
		                        			<label for="search" class="col-sm-2 control-label">Month</label>
										    <div class="col-sm-2">
										    	<select  data-provide="typeahead"
												class="form-control typeahead" id="month" autocomplete="off">
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
										    <!-- 	<input type="text" data-provide="typeahead"  class="form-control typeahead" id="month" autocomplete="off">  -->								      	
										    </div>  	
									    </div> 	
									    <div class="form-group">								   								                        	
		                        			<h3><span class="label label-success col-md-1 col-md-offset-5">AND</span></h3>
		                        		</div>	
	                        			<div class="form-group">
		                        			<label for="search" class="col-sm-2 control-label">Year</label>
										    <div class="col-sm-2">
										    <select  id="year" class="form-control typeahead" autocomplete="off">
												<option selected="selected">year</option>
												<c:set var="now" value="<%=new java.util.Date()%>" />
												<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
												<c:forEach var="year" begin="${year-10}" end="${year+10}">
													<option><c:out value="${year}"></c:out></option>
												</c:forEach>
											</select>
										    	<!-- <input type="text" data-provide="typeahead"  class="form-control typeahead" id="year" autocomplete="off"> --> 								      	
										    </div>  	
									    </div>
									    <div class="form-group">
										    <div class="col-sm-offset-2 col-sm-10">
										      <button type="submit" class="btn btn-primary pull-right">Search</button>
										    </div>
										 </div>									
	                        		</form>		
	                        	</div>	
	                        	                    	
	                        	
							</div>
	                        
	                    </div>						
					</div>
					<div class="panel panel-default">
						<h4>Bank holiday working request</h4>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<div class="table-leave">
									<table id="searchTable"
										class="table table-striped table-bordered table-hover data-table-sortable">
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
									<%-- 	<tbody>
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

										</tbody> --%>
									</table>
								</div>
								<!-- table BankHoliday -->
							</div>
							<!-- /.table-responsive -->

						</div>
						<!-- /.panel-body -->

					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<!-- /.row -->

			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- DataTables JavaScript -->
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('.data-table-sortable').dataTable({
				"aaSorting" : [ [ 1, 'desc' ] ]
			});
			
			//Search submit
	       	$( "#searchForm" ).submit(function( event ) {       	 
	       	  event.preventDefault();
	       	  console.log('searching for skills with id '+$('.typeahead').val());
	       	  //initiate an ajax call for skills search. Return data will populate in datatable
	       	    if($('#month').val() != 'month'&&$('#year').val() != 'year'){
	       	    	sendAjax("/portal-lms/requests/getbankholidayworkingrequestsforuser.ajax?month="+$('#month').val()+"&year="+$('#year').val());	
	       	    } else {
	       	    	//sendAjax("/portal-lms/skills/getskillsforuser.ajax?id="+$('#employeeSearch').val());
	       	    }
	       		
	       	});

		});
		   function sendAjax(url) {
				loader.showPleaseWait();
				$('#resultsPanel').show();
				$('#searchTable').dataTable().fnDestroy();
				 $('#searchTable').dataTable({			 	
					 	"ajax": {
					 		"url" : url,
					 		"dataSrc": function (json) {
				                var arr = Object.keys(json).map(function(k) { return json[k] });
				                return arr;
				            }
					 	},
						"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					 		var index = iDisplayIndex +1;
					 		$('td:eq(0)',nRow).html(index);
					 		return nRow;
				        },
	
			        	"columns": [
							{ "data": null },
			 	            { "data": "user.id" },
			 	            { "data": "year" },
			 	            { "data": "requestDate" },
			 	            { "data": "startDate" },
			 	            { "data": "endDate" },
			 	            { "data": "days" },
			 	            { "data": "type" },
			 	            { "data": "status" },
			 	            { "data": "approver.firstName" },
			 	            { "data": "hdFd" },
			 	            { "data": "description" } 
			 	        ]
			     });
				 loader.hidePleaseWait();
				
				}
	</script>

</body>


</html>


