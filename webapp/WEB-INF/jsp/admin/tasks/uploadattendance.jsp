<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../../common/commontaglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../../common/commonheadinclude.jspf"%>
<script src="js/plugins/jquery-upload/jquery.ui.widget.js"></script>
<script src="js/plugins/jquery-upload/jquery.iframe-transport.js"></script>
<script src="js/plugins/jquery-upload/jquery.fileupload.js"></script>
<style type="text/css">
	.fileinput-button {
	  position: relative;
	  overflow: hidden;
	}
	.fileinput-button input {
	  position: absolute;
	  top: 0;
	  right: 0;
	  margin: 0;
	  opacity: 0;
	  -ms-filter: 'alpha(opacity=0)';
	  font-size: 200px;
	  direction: ltr;
	  cursor: pointer;
	}
	
	/* Fixes for IE < 8 */
	@media screen\9 {
	  .fileinput-button input {
	    filter: alpha(opacity=0);
	    font-size: 100%;
	    height: 100%;
	  }
	}
</style>
<script type="text/javascript">

$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:
    var url = 'fileUpload.do?method=upload';
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        done: function (e, data) {
            $.each(data.result.files, function (index, file) {
                $('<p/>').text(file.name).appendTo('#files');
            });
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            );
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});

</script>
</head>

<body>

	<div id="wrapper">
		<%@include file="../../common/navbar/navbar.jspf"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Upload Attendance</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

				<div class="row">				
					<div class="col-lg-12">
						<div class="panel panel-default">
							
							<div class="panel-body">
								<c:set var="divNeeded" value="false"></c:set>
								<%@include file="../../common/messages/messagesnotifications.jsp"%>
								<div> 
								      <span class="btn btn-success fileinput-button">
									        <i class="glyphicon glyphicon-plus"></i>
									        <span>Select file and upload</span>
									        <!-- The file input field used as target for the file upload widget -->
									        <input id="fileupload" type="file" name="uploadedFile" accept="application/vnd.ms-excel">
									    </span>
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
