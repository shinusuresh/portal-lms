<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/jsp/common/commontaglibs.jspf"%>
<%@include file="/WEB-INF/jsp/common/environment.jspf"%>
<%-- <%@page import="com.tryzens.portal.investmentdeclaration.form80.Form80"%> --%>
<%-- <%@page
	import="com.tryzens.portal.investmentdeclaration.form80c.Form80c"%> --%>
<%-- <%@page
	import="com.tryzens.portal.investmentdeclaration.hra.HomeRentAllowance"%> --%>
<%-- <%@page import="com.tryzens.portal.user.User"%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Investment Declaration</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<%@include file="/WEB-INF/jsp/common/commonheadinclude.jspf"%>

<script type="text/javascript">
	$(document).ready(function() {
		$(".help-declaration input,textarea").tooltip({
			placement : 'top'
		});

		//Form validations here
		$('#declarationForm').bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				mediclaimPremiumSelf : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				directRGESS : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				mediclaimPremiumForParents : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				medicalTreatmentOfHandicappedDependent : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				medicalTreatmentOfSpecifiedDiseaseForSelf : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				deductionInCaseOfTotallyBlind : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				interestPaidForHigherEducation : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				medicalBenefit : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				interestHousingLoan : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				lifeInsurancePremium : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				contributionToPF : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				depositInPPF : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				equityTaxSavingMutualFund : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				nationalSavingCertificate : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				nationalServiceSchema : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				savingBondInvestment : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				newPensionSchemeDeposit : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				housingLoanPriciplePayment : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				taxSavingFixedDeposit : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				stampDutyCharges : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				otherInvestments : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				pensionFund : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				rentAmountPerMonth : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
				rentAmountPerAnnum : {
					validators : {
						digits : {
							message : 'The input is not a valid number'
						}
					}
				},
			}
		});
	});
	function submitClick()
	{
		document.getElementById("btnclick").val('submit');
	}
</script>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	<script type="text/javascript">
		$(window).load(function() {
			// ..
			var a = '<c:out value="${user.email}"/>';
			$('#submitDeclarationForm').hide();
			$('#userName').text(a);

			// ..
		});
	</script>
</sec:authorize>
</head>

<body>
	<div id="wrapper">
		<%-- 	<%
		User user = (User) request.getAttribute("user");
	%>
	<%
		String userName = "Nill";
	%> --%>

		<%-- <%
		userName = user.getFirstName;
	%> --%>

		<!-- Include Static top navbar -->
		<%-- <%
		Form80 form80 = (Form80) request.getAttribute("form80");
	%> --%>
		<%-- <%
		Form80c form80c = (Form80c) request.getAttribute("form80c");
	%> --%>
		<%-- <%
		HomeRentAllowance hra = (HomeRentAllowance) request
				.getAttribute("hra"); --%>

		<%@include file="/WEB-INF/jsp/common/navbar/navbar.jspf"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		<jsp:useBean id="date" class="java.util.Date" />
		<div id="page-wrapper">
			<div class="row clearfix">
				<form class="form-horizontal" role="form" method="post"
					id="declarationForm"
					action="declarationform.do?method=updateDeclarationForm&selection=declarationform">
					<div class="col-md-12 column">
						<h3 class="text-center text-primary">
							Investment Declaration for year -
							<fmt:formatDate value="${date}" pattern="yyyy" />
							<span id="userName" class="text-primary pull-right small"></span>
						</h3>
						<div class="row">
							<!-- Erorr/Success box -->

							<c:if test="${form80.check} == 10)">
								<div class="alert alert-success" role="alert">
									You have successfully created declaration for year -
									<fmt:formatDate value="${date}" pattern="yyyy" />
								</div>


							</c:if>

							<c:if test="${form80.check} == 5)">

								<div class="alert alert-danger" role="alert">Sorry. An
									error occured while submitting the declaration.</div>

							</c:if>



							<div class="col-md-6">
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="row">
											<div class="col-md-1">
												<span class="badge badge-info badge-ultra">A</span>
											</div>
											<div class="col-md-11">
												<h3 class="text-center">Deduction to be claimed U/S 80</h3>
											</div>
										</div>
										<!-- END <div class="row"> -->
										<!-- 80/c form -->

										<!-- Deduction to be claimed U/S 80 -->
										<div class="form-group">

											<label for="exampleInputEmail1"
												class="col-sm-8 control-label">Mediclaim Policy
												Premium for self</label>
											<div class="col-sm-4">
												<input type="hidden" name="firstName"
													value="${user.firstName}"> <input type="text"
													class="form-control" id="mediclaimPremiumSelf"
													name="mediclaimPremiumSelf" data-toggle="tooltip"
													title="Mediclaim Policy Premium for self [u/s 80D-upto Rs.15,000/-]"
													value="${form80.mediclaim_policy_premium_for_self}">

											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1"
												class="col-sm-8 control-label">Mediclaim Policy
												Premium for parents</label>
											<div class="col-sm-4">
												<input type="text" class="form-control"
													id="mediclaimPremiumForParents"
													name="mediclaimPremiumForParents" data-toggle="tooltip"
													title="Mediclaim Policy Premium [u/s 80D-upto Rs.15,000/-if parents are less than 60 years else Rs.20,000/-] "
													value="${form80.mediclaim_policy_premium_for_parents}">
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1"
												class="col-sm-8 control-label">Medical treatment of
												handicapped dependent</label>
											<div class="col-sm-4">
												<input type="text" class="form-control"
													id="medicalTreatmentOfHandicappedDependent"
													name="medicalTreatmentOfHandicappedDependent"
													data-toggle="tooltip"
													title="Medical treatment of handicapped dependent [u/s80DD-upto Rs.50,000/- & Rs.100,000/- for severe disability]"
													value="${form80.mediclaim_treatment_of_handicaped_dependent}">
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1"
												class="col-sm-8 control-label">Medical treatment of
												specified diseases for self</label>
											<div class="col-sm-4">
												<input type="text" class="form-control"
													id="medicalTreatmentOfSpecifiedDiseaseForSelf"
													data-toggle="tooltip"
													name="medicalTreatmentOfSpecifiedDiseaseForSelf"
													title="Medical treatment of specified diseases for self/dependent [u/s80DDB-upto Rs.40,000/- & Rs.60,000/- for senior citizen]"
													value="${form80.mediclaim_treatment_of_specified_disease_for_self}">
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1"
												class="col-sm-8 control-label">Deduction in case of
												self being totally blind or physically handicapped </label>
											<div class="col-sm-4">
												<input type="text" class="form-control"
													id="deductionInCaseOfTotallyBlind" data-toggle="tooltip"
													name="deductionInCaseOfTotallyBlind"
													title="Deduction in case of self being totally blind or physically handicapped [u/s80 U]- upto Rs 50,000/- "
													value="${form80.deduction_in_case_of_self_beign_totally_blind}">
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1"
												class="col-sm-8 control-label">INTEREST paid on loan
												taken for higher education</label>
											<div class="col-sm-4">
												<input type="text" class="form-control"
													id="interestPaidForHigherEducation"
													name="interestPaidForHigherEducation" data-toggle="tooltip"
													title="INTEREST paid on loan taken for higher education [U/s 80 E - No limit]"
													value="${form80.interest_paid_on_loan_taken_for_higher_education}">
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1"
												class="col-sm-8 control-label">Total</label>
											<div class="col-sm-4">
												<input class="form-control" type="text" placeholder="0"
													readonly value="${form80.total}" name="total">
											</div>
										</div>
									</div>
									<!-- END <div class="panel-body"> -->
								</div>
								<!-- END <div class="panel panel-default"> -->
							</div>
							<!-- END <div class="col-md-6"> -->

							<div class="row">
								<div class="col-md-6">
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="row">
												<div class="col-md-1">
													<span class="badge badge-info badge-ultra">B</span>
												</div>
												<div class="col-md-11">
													<h3 class="text-center">Direct Investment</h3>
												</div>
											</div>
											<!-- END <div class="row"> -->
											<div class="form-group">
												<label for="exampleInputEmail1"
													class="col-sm-8 control-label">Direct Investment in
													Equity under RGESS</label>
												<div class="col-sm-4">
													<input type="text" class="form-control" id="directRGESS"
														name="directRGESS" data-toggle="tooltip"
														title="Direct Investment in Equity under RGESS "
														value="${form80.direct_investment_in_equity_under_rgess}">
												</div>
											</div>
										</div>
										<!-- END <div class="panel-body"> -->
									</div>
									<!-- END <div class="panel panel-default"> -->
								</div>
								<!-- END <div class="col-md-6"> -->

								<div class="col-md-6">
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="row">
												<div class="col-md-1">
													<span class="badge badge-info badge-ultra">C</span>
												</div>
												<div class="col-md-11">
													<h3 class="text-center">Medical Benefit</h3>
												</div>
											</div>
											<!-- END <div class="row"> -->
											<div class="form-group">
												<label for="exampleInputEmail1"
													class="col-sm-8 control-label">Medical Benefit
													(Rs.15000) Required</label>
												<div class="col-sm-4">
													<input type="text" class="form-control" id="medicalBenefit"
														name="medicalBenefit" data-toggle="tooltip"
														title="Tooltip on top"
														value="${form80.medical_benifits}">
												</div>
											</div>

										</div>
										<!-- END <div class="panel-body"> -->
									</div>
									<!-- END <div class="panel panel-default"> -->
								</div>
								<!-- END <div class="col-md-6"> -->

								<div class="col-md-6">
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="row">
												<div class="col-md-1">
													<span class="badge badge-info badge-ultra">D</span>
												</div>
												<div class="col-md-11">
													<h3 class="text-center">Interest on Housing Loan</h3>
												</div>
											</div>
											<!-- END <div class="row"> -->

											<div class="form-group">
												<label for="exampleInputEmail1"
													class="col-sm-8 control-label">Interest on Housing
													Loan [u/s 24(2)] -- Maximum up to Rs.1,50,000(2,50,000 for
													loan upto 25lakh taken in FY 2013-14)</label>
												<div class="col-sm-4">
													<input type="text" class="form-control"
														id="interestHousingLoan" name="interestHousingLoan"
														data-toggle="tooltip" title="Tooltip on top"
														value="${form80.interest_on_housing_loan}">
												</div>
											</div>

										</div>
										<!-- END <div class="panel-body"> -->
									</div>
									<!-- END <div class="panel panel-default"> -->
								</div>
								<!-- END <div class="col-md-6"> -->

							</div>
							<!-- END <div class="row">  -->

						</div>
						<!-- END <div class="row"> -->

						<div class="row">
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-1">
											<span class="badge badge-info badge-ultra">E</span>
										</div>
										<div class="col-md-11">
											<h3 class="text-center">Rebate U/S 80C</h3>
										</div>
									</div>
									<!-- END <div class="row"> -->

									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">Payment
											of Life Insurance Premium</label>
										<div class="col-sm-2">
											<input type="text" class="form-control"
												id="lifeInsurancePremium" name="lifeInsurancePremium"
												data-toggle="tooltip" title="Tooltip on top"
												value="${form80c.payment_of_insurance_premium}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">Employee's
											contribution to PF</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="contributionToPF"
												data-toggle="tooltip" name="contributionToPF"
												title="Tooltip on top"
												value="${form80c.employee_contribution_to_pf}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">Deposit
											in Public Provident Fund(PPF){Maximum of Rs.70,000/-}</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="depositInPPF"
												data-toggle="tooltip" name="depositInPPF"
												title="Tooltip on top"
												value="${form80c.deposit_in_public_pf}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">
											Equity Tax saver Mutual Funds - ELSS </label>
										<div class="col-sm-2">
											<input type="text" class="form-control"
												id="equityTaxSavingMutualFund" data-toggle="tooltip"
												name="equityTaxSavingMutualFund" title="Tooltip on top"
												value="${form80c.equity_tax_saving_mutualfund}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">Purchase
											of National Saving Certificates (NSC)</label>
										<div class="col-sm-2">
											<input type="text" class="form-control"
												id="nationalSavingCertificate" data-toggle="tooltip"
												name="nationalSavingCertificate" title="Tooltip on top"
												value="${form80c.purchase_of_national_saving_certificate}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">National
											Service Scheme (NSS) deposit</label>
										<div class="col-sm-2">
											<input type="text" class="form-control"
												id="nationalServiceSchema" name="nationalServiceSchema"
												data-toggle="tooltip" title="Tooltip on top"
												value="${form80c.national_savings_scheme_deposit}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">Post
											Office/Tax saving Bonds investments</label>
										<div class="col-sm-2">
											<input type="text" class="form-control"
												id="savingBondInvestment" data-toggle="tooltip"
												name="savingBondInvestment" title="Tooltip on top"
												value="${form80c.postoffice_saving_bonds_investment}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">New
											pension scheme (NPS) Deposit 80CCD</label>
										<div class="col-sm-2">
											<input type="text" class="form-control"
												id="newPensionSchemeDeposit" name="newPensionSchemeDeposit"
												data-toggle="tooltip" title="Tooltip on top"
												value="${form80c.new_pension_scheme_deposit_of_80ccd}">
										</div>
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">Housing
											Loan Principal repayment</label>
										<div class="col-sm-2">
											<input type="text" class="form-control"
												id="housingLoanPriciplePayment" data-toggle="tooltip"
												name="housingLoanPriciplePayment" title="Tooltip on top"
												value="${form80c.house_loan_principle_payment}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">Tax
											saving Fixed Deposit for 5 yrs. or more</label>
										<div class="col-sm-2">
											<input type="text" class="form-control"
												id="taxSavingFixedDeposit" name="taxSavingFixedDeposit"
												data-toggle="tooltip" title="Tooltip on top"
												value="${form80c.tax_saving_fixed_deposit}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">Stamp
											Duty/Registration charges for house</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="stampDutyCharges"
												data-toggle="tooltip" name="stampDutyCharges"
												title="Tooltip on top"
												value="${form80c.stamp_duty_registration_charges_for_house}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">
											Other Eligible Investments (Please provide details)</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="otherInvestments"
												name="otherInvestments" data-toggle="tooltip"
												title="Tooltip on top"
												value="${form80c.other_eligible_investment}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" class="col-sm-5 control-label">
											Pension Fund (80 CCC) </label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="pensionFund"
												name="pensionFund" data-toggle="tooltip"
												title="Tooltip on top" value="${form80c.pension_fund}">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1"
											class="col-sm-5 control-label">Total Amount</label>
										<div class="col-sm-2">
											<input class="form-control" name="totalForm80c" type="text"
												placeholder="0" readonly data-toggle="tooltip"
												title="Total Amount from 1 to 13 (Restricted to Rs. 1,00,000/-)"
												value="${form80c.total}">
										</div>
									</div>
								</div>
								<!-- END <div class="panel-body"> -->
							</div>
							<!-- END <div class="panel panel-default"> -->
						</div>
						<!-- END <div class="row"> -->

						<!-- HRA -->
						<div class="row">
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-1">
											<span class="badge badge-info badge-ultra">F</span>
										</div>
										<div class="col-md-11">
											<h3 class="text-center">House Rent Allowance (H.R.A.)</h3>
										</div>
									</div>
									<!-- END <div class="row"> -->

									<div class="row">
										<div class="form-group">
											<label for="exampleInputEmail1"
												class="col-sm-5 control-label">Name of the landlord</label>
											<div class="col-sm-2">
												<input type="text" name="nameOfLandlord"
													class="form-control" id="Name of the landlord"
													data-toggle="tooltip" title="Name of the landlord"
													value="${hra.name_of_landlord}">
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputEmail1"
												class="col-sm-5 control-label">Address of the
												landlord</label>
											<div class="col-sm-5">
												<textarea class="form-control" id="addressOfLandlord"
													rows="4" data-toggle="tooltip"
													title="Address of the landlord" name="addressOfLandlord">${hra.address_of_landlord}</textarea>
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputEmail1"
												class="col-sm-5 control-label">Address of
												accomodation</label>
											<div class="col-sm-5">
												<textarea class="form-control" id="addressOfAccomadation"
													rows="4" data-toggle="tooltip"
													title="Address of the landlord"
													name="addressOfAccomadation">${hra.address_of_accomodation}</textarea>
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputEmail1"
												class="col-sm-5 control-label">Rent amount (Per
												Month)</label>
											<div class="col-sm-2">
												<input type="text" class="form-control"
													name="rentAmountPerMonth" id="rentAmountPerMonth"
													data-toggle="tooltip" title="Rent amount (Per month)"
													value="${hra.rent_amount_per_month}">
											</div>
										</div>
										<div class="form-group">
											<label for="exampleInputEmail1"
												class="col-sm-5 control-label">Rent amount (Per
												Annum)</label>
											<div class="col-sm-2">
												<input type="text" class="form-control"
													name="rentAmountPerAnnum" id="rentAmountPerAnnum"
													data-toggle="tooltip" title="Rent amount (Per Annum)"
													value="${hra.rent_amount_per_annum}">
											</div>
										</div>
									</div>
									<!-- END <div class="row"> -->
								</div>
								<!-- END <div class="panel-body"> -->
							</div>
							<!-- END <div class="panel panel-default"> -->
						</div>
						<!-- END <div class="row"> -->

						<div class="form-group text-center">
                        <input type="hidden" name="btnclick" value="pdf">
							<button type="reset" class="btn btn-danger">Reset Button</button>
							<button id="submitDeclarationForm" type="submit"
								name="submitDeclarationForm" class="btn btn-primary" Onclick="submitClick();">Submit
								Declaration</button>

							<a class="btn btn-primary" href="ConvertToPDF.do?method=doGet"
								role="button" ><span class="glyphicon glyphicon-print"></span>
								Save to PDF</a>


							<%-- <button name="saveToPdf" value="<%form80.getU_id(); %>" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-print"></span>Save to PDF</button> --%>
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
	<!-- /#wrapper -->
</body>
</html>
