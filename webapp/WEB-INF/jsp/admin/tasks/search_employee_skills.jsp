<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/environment.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
 <link href="${host}/css/plugins/tags/bootstrap-tagsinput.css" rel="stylesheet">

<%-- <script type="text/javascript" src="${host}/js/plugins/typeahead/typeahead.bundle.js"></script> --%>
<script type="text/javascript" src="${host}/js/plugins/tags/bootstrap-tagsinput.js"></script> 
 <script type="text/javascript" src="${host}/js/plugins/typeahead/bloodhound.js"></script> 
<script type="text/javascript" src="${host}/js/bootstrap3-typeahead.js"></script> 


<script type="text/javascript">
	$(document).on("hidden.bs.modal", function (e) {
	    $(e.target).removeData("bs.modal").find(".modal-content").empty();
	});
	$(document).ready(function() {
		 $('[data-toggle="confirmation-singleton"]').confirmation();
		 //$('#deleteSkill').confirmation('show');
	});
</script>
</head>

<body>
     
<div id="wrapper"> 
<%@include file="../../common/navbar/navbar.jspf"%>
  
<div id="page-wrapper">
             <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Skills</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
            	<!-- Slot for message box -->
                <%@include file="../../common/messages/messagesnotifications.jsp"%>
                
                <div class="col-lg-12">
                
                	<div>
						<div class="panel panel-green">
	                        <div class="panel-heading">
	                            Search Skills
	                        </div>
	                        <div class="panel-body center-block">
	                        	<div class="col-md-12">
	                        		<form id="searchForm" class="form-horizontal" role="form">
	                        			<div class="form-group">
		                        			<label for="search" class="col-sm-2 control-label">Search by skill</label>
										    <div class="col-sm-10">
										    	<input type="text" data-provide="typeahead"  class="form-control typeahead" id="skillSearch" autocomplete="off"> 								      	
										    </div>  	
									    </div> 	
									    <div class="form-group">								   								                        	
		                        			<h3><span class="label label-success col-md-1 col-md-offset-5">OR</span></h3>
		                        		</div>	
	                        			<div class="form-group">
		                        			<label for="search" class="col-sm-2 control-label">Search by employee</label>
										    <div class="col-sm-10">
										    	<input type="text" data-provide="typeahead"  class="form-control typeahead" id="employeeSearch" autocomplete="off"> 								      	
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
                    <div class="panel panel-default" id="resultsPanel">
                        <div class="panel-heading">Search Skills</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                            <div>
                                <table class="table table-striped table-bordered table-hover" id="searchTable">
                                    <thead>
                                        <tr>
                                        	<th></th>
                                        	<th>User Name</th>
                                            <th>Skill Name</th>
                                            <th>Description</th>
                                            <th>Rating</th>                                                                                                                                   
                                        </tr>
                                    </thead>                                   
                                </table>
                                </div>
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
        //hide results
        $('#resultsPanel').hide();
        
        //Skills auto populate
		var skills = new Bloodhound({
        	  datumTokenizer: function() {
        		    return Bloodhound.tokenizers.whitespace();
        	  },
        	  queryTokenizer: Bloodhound.tokenizers.whitespace,        	  
        	  prefetch: {
        		url: '/portal-lms/skills/getallskills.ajax',
        		filter: function ( response ) {

                    return $.map(response, function (object) {
                       
                    	console.log(object.name);
                        return {value: object.id, text: object.name}
                    });
        	  	}
        	  }
       });
       skills.initialize(); 
       console.log(skills.index.datums);
              
       	$('#skillSearch').tagsinput({
       		itemValue: 'value',
       	  	itemText: 'text',
       	  typeahead: {
       		items: 4,
	       	 name: 'skills',
	         displayKey: 'text',
       	    source: skills.index.datums       	 	
       	  }
       	});
       	
       	console.log($("#typeahead").val());
       	
       	
      //Users auto populate
		populateUsers();
       	
       	//Search submit
       	$( "#searchForm" ).submit(function( event ) {       	 
       	  event.preventDefault();
       	  console.log('searching for skills with id '+$('.typeahead').val());
       	  //initiate an ajax call for skills search. Return data will populate in datatable
       	    if($('#skillSearch').val() != ''){
       	    	sendAjax("/portal-lms/skills/searchuserbyskills.ajax?search="+$('#skillSearch').val());	
       	    } else {
       	    	sendAjax("/portal-lms/skills/getskillsforuser.ajax?id="+$('#employeeSearch').val());
       	    }
       		
       	});
               
    });
    
    function populateUsers(){
    	var users = new Bloodhound({
      	  datumTokenizer: function() {
      		    return Bloodhound.tokenizers.whitespace();
      	  },
      	  queryTokenizer: Bloodhound.tokenizers.whitespace,        	  
      	  prefetch: {
      		url: '/portal-lms/users/getallusers.ajax',
      		filter: function ( response ) {

                  return $.map(response, function (object) {
                     
                  	console.log(object.name);
                      return {value: object.id, text: object.firstName}
                  });
      	  	}
      	  }
     });
     users.initialize(); 
     console.log(users.index.datums);
            
     	$('#employeeSearch').tagsinput({
     		itemValue: 'value',
     	  	itemText: 'text',
     	  typeahead: {
     		items: 4,
	       	 name: 'users',
	         displayKey: 'text',
     	    source: users.index.datums       	 	
     	  }
     	});
    }
    
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
			 	
			 	"fnCreatedRow": function( nRow, aData, iDataIndex ) {
			 		if(aData.id == "6"){
			 			console.log("SUCCESS");
			 		}
			 	},
			 	
			 	"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
			 		var index = iDisplayIndex +1;
			 		$('td:eq(0)',nRow).html(index);
			 		return nRow;
		        },
			 	
	        	"columns": [
					{ "data": null },        
	 	            { "data": "user.firstName" },
	 	            { "data": "skill.name" },
	 	            { "data": "skill.description" },
	 	            { "data": "rating" }	        	            
	 	        ]
	     });
		 loader.hidePleaseWait();
		
		}
    </script>

</body>

</html>


