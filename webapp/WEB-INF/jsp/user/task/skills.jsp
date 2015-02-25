<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/environment.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
<link href="${host}/css/plugins/ratings/star-rating.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="js/plugins/ratings/star-rating.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".rating").on('rating.change', function(event, value, caption) {	    
	    console.log($($(this).context));
	    console.log($(this));
	    //console.log($(this).data("data-skillid"));
	    //Invoke save 
	    var skillId = $($(this).context).data("skillid");	   
	    sendAjax(caption, skillId, value, $(this));
	    
	});
	$('.rating').on('rating.clear', function(event) {
	    console.log("rating.clear");
	});
	
	function sendAjax(name, id, rating, obj) {
		loader.showPleaseWait();
		$.ajax({ 
		    url: "/portal-lms/skills/addskill.ajax", 
		    //type: 'POST', 
		    type: 'GET',
		    dataType: 'json', 
		    data: {
		    	skillId : "" + id,
		    	rating : "" + rating
		    },
		    contentType: 'application/json',
		    //mimeType: 'application/json',
		    success: function(data) { 
		    	loader.hidePleaseWait();
		        console.log(data);		      
		    },
		    done: function(data) { 	
		    	loader.hidePleaseWait();
		    	 console.log('done '+data);		        
		    },
		    error:function(data,status,er) { 
		    	loader.hidePleaseWait();
		        console.log("error: "+data+" status: "+status+" er:"+er);		       
		    }
		});
		}
});
</script>
</head>

<body>

	<div id="wrapper">
		<%@include file="../../common/navbar/navbar.jspf"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Skills List</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<!-- Slot for message box -->
				<%@include file="../../common/messages/messagesnotifications.jsp"%>

				<div class="col-lg-12">
					<c:set var="categoryKey" value="" />
					<c:forEach items="${categories}" var="entry" varStatus="catId">
						<c:set var="categoryKey" value="${catId.index}" />
						<div class="panel panel-default">							
								<div class="panel-heading">${entry.key}</div>
								<div class="panel-body">
									<c:forEach items="${entry.value}" var="item" varStatus="loop">
										<div class="row">
            								<div class="col-md-6">										
												<div class="form-group">
													<label>${item.name}</label>
													<p class="help-block">${item.description}</p>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<input type="number" name="rating-${categoryKey}-${loop.index}" id="rating-${categoryKey}-${loop.index}" value="${item.additionalFields['rating']}"
														class="rating" min=0 max=5 step=1 data-size="xs" data-showClear="false" data-skillid="${item.id}" />
													<!-- <span class="label label-warning">Warning</span> -->
												</div>	
											</div>	
										</div>
										 	
										<hr>																
									</c:forEach>	
								</div>
								<!-- /.panel-body -->							
						</div>
						<!-- /.panel panel-default -->
						
					</c:forEach>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>

</html>




