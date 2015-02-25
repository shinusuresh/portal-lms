<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/environment.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
</head>

<body>
     
     <div id="wrapper"> 
  <%@include file="../../common/navbar/navbar.jspf"%>
  
<div id="page-wrapper">
             <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Employee List</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
            	<!-- Slot for message box -->
                <%@include file="../../common/messages/messagesnotifications.jsp"%>
                
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Employee List
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                            <div>
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>date of birth</th>
                                            <th>mobile</th>
                                            <th>Email Id</th>
                                            <th>Department</th>  
                                            <th>Investment Declaration Form</th>                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${users}" var="user">
	                                        <tr class="odd gradeX">
	                                            <td><a href="editUserProfile.do?method=listUsersDetails&userId=${user.id}">${user.firstName}</a></td>
	                                            <td>${user.dateOfBirth}</td>
	                                            <td>${user.mobile}</td>
	                                            <td class="center">${user.email}</td>
	                                            <td class="center">${user.department}</td>	
	                                            <td class="center"><a href="declarationform.do?method=listDeclarationForm&userId=${user.id }">Declaration Form</a></td>                                           	
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

    </div>
    <!-- /#wrapper -->

    <!-- DataTables JavaScript -->
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>

</body>

</html>


</body>
</html>