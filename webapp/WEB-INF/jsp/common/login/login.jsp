<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="../commontaglibs.jspf"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../commonheadinclude.jspf"%>

</head>

<body>


    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">                    	
                    	<c:if test="${not empty param.error and not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}">  
	                    	<div class="alert alert-danger" role="alert">
							  <a href="#" class="alert-link">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </a>

							</div>
						</c:if>
						<!-- Slot for message box -->
						<c:set var="divNeeded" value="false"></c:set>

                        <%@include file="../../common/messages/messagesnotifications.jsp"%>
                        
                        <form role="form" action="<c:url value='j_spring_security_check' />" method="post" id="loginForm">
                            <fieldset>
                                <div class="form-group">                                	
                                    <input class="form-control" placeholder="E-mail" name="j_username" type="email" autofocus>
                                </div>
                                <div class="form-group">                                
                                    <input class="form-control" placeholder="Password" name="j_password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="_spring_security_remember_me" type="checkbox">Remember Me
                                    </label>
                                    <span class="pull-right">
					                    <a data-toggle="modal" href="#forgotPasswordModal"> Forgot Password?</a>					
					                </span>
                                </div>                                                               
                                <button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Forgot Password panel -->
		<div class="modal fade" id="forgotPasswordModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				
					<div class="modal-content forgotpassword-modal">
						<form role="form" action="userDashboardActions.do?method=forgotPassword" id="forgotPasswordForm" method="post">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Forgot Password ?</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<p>Enter your e-mail address below to reset your password.</p>
									<input type="text" name="email" placeholder="Email"
										autocomplete="off" class="form-control placeholder-no-fix">
								</div>		
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Recover Password</button>
							</div>
						</form>
					</div>
				
			</div>
		</div>


	</div>

<script type="text/javascript">

$(document).ready(function(){
	$('#forgotPasswordForm').bootstrapValidator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					email : {
						validators : {
							notEmpty : {
								message : 'The email is required and cannot be empty'
							},
							emailAddress : {
								message : 'The email is not a valid email address'
							}
						}
					}
				}
		});
	$('#loginForm').bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			j_username : {
				validators : {
					notEmpty : {
						message : 'The login id is required and cannot be empty'
					},
					emailAddress : {
						message : 'The login id is not a valid email address'
					}
				}
			},
			j_password : {
				validators : {
					notEmpty : {
						message : 'The password is required and cannot be empty'
					}					
				}
			}
		}
});
});

</script>

</body>

</html>
