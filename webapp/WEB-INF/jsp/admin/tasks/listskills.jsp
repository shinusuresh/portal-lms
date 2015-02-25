<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/environment.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
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
                    <h1 class="page-header">Skills List</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
            	<!-- Slot for message box -->
                <%@include file="../../common/messages/messagesnotifications.jsp"%>
                
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <!-- <a data-toggle="modal" href="#addUpdateSkillModal"><i class="fa fa-plus-square fa-2x"></i> Add Skills</a> -->
                            <a data-toggle="modal" data-target="#addUpdateSkillModal" href="skillset.do?method=loadSkills&selection=listskills"><i class="fa fa-plus-square fa-2x"></i> Add Skills</a>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                            <div>
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Skill Name</th>
                                            <th>Description</th>   
                                            <th>Action</th>                                                                                     
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${skills}" var="skill">
	                                        <tr class="odd gradeX">
	                                            <td><a data-toggle="modal" data-target="#addUpdateSkillModal" 
	                                            	href="skillset.do?method=updateSkillLoad&id=${skill.id}&selection=listskills">${skill.name}</a></td>
	                                            <td>${skill.description}</td>
	                                            <td><a data-toggle="confirmation-singleton" data-placement="left" 
	                                            	data-href="skillset.do?method=deleteSkill&id=${skill.id}&selection=addskills" 
	                                            	data-original-title="Are you sure?" class="btn btn-danger btn-sm">Delete</a></td>	                                            	                                           	
	                                        </tr>
                                       </c:forEach>
                                    </tbody>
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
        <!-- Modal window for add new skills -->
        <div class="modal fade" id="addUpdateSkillModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                 <h4 class="modal-title">Modal title</h4>		
		            </div>
		            <div class="modal-body"></div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		                <button type="button" class="btn btn-primary">Save changes</button>
		            </div>
		        </div>
		        <!-- /.modal-content -->
		    </div>
		    <!-- /.modal-dialog -->
		</div>	       
        
        

    </div>
    <!-- /#wrapper -->

    <!-- DataTables JavaScript -->
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable({
        	'aoColumnDefs': [{
                'bSortable': false,
                'aTargets': [-1] /* 1st one, start by the right */
            }],
            "aaSorting": []
        });
    });
    </script>

</body>

</html>


</body>
</html>