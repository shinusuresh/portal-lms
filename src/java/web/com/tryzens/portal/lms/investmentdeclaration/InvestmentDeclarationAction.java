package com.tryzens.portal.lms.investmentdeclaration;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tryzens.portal.investmentdeclaration.dao.InvestmentDeclarationDao;
import com.tryzens.portal.investmentdeclaration.form80.Form80;
import com.tryzens.portal.investmentdeclaration.form80c.Form80c;
import com.tryzens.portal.investmentdeclaration.hra.HomeRentAllowance;
import com.tryzens.portal.lms.common.notifications.Notification;
import com.tryzens.portal.lms.common.notifications.Notification.STATUS;
import com.tryzens.portal.login.service.PortalUserDetails;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.UserDao;

public class InvestmentDeclarationAction extends DispatchAction {
	private InvestmentDeclarationDao investmentDeclarationDao;
	private UserDao userDao;

	public ActionForward updateDeclarationForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User user = userDao.findById(Long.parseLong(sessionUser.getUserId()));
		Form80 form80 = investmentDeclarationDao.findForm80ByUserIdAndYear(
				user.getId(), Calendar.getInstance().get(Calendar.YEAR));
		Form80c form80c = investmentDeclarationDao.findForm80cByUserIdAndYear(
				user.getId(), Calendar.getInstance().get(Calendar.YEAR));
		HomeRentAllowance homeRentAllowance = investmentDeclarationDao
				.findHraByUserIdAndYear(user.getId(), Calendar.getInstance()
						.get(Calendar.YEAR));
		form80.setMediclaim_policy_premium_for_self(new BigDecimal(request
				.getParameter("mediclaimPremiumSelf")));
		form80.setMediclaim_policy_premium_for_parents(new BigDecimal(request
				.getParameter("mediclaimPremiumForParents")));
		form80.setMediclaim_treatment_of_handicaped_dependent(new BigDecimal(
				request.getParameter("medicalTreatmentOfHandicappedDependent")));
		form80.setMediclaim_treatment_of_specified_disease_for_self(new BigDecimal(
				request.getParameter("medicalTreatmentOfSpecifiedDiseaseForSelf")));
		form80.setDeduction_in_case_of_self_beign_totally_blind(new BigDecimal(
				request.getParameter("deductionInCaseOfTotallyBlind")));
		form80.setInterest_paid_on_loan_taken_for_higher_education(new BigDecimal(
				request.getParameter("interestPaidForHigherEducation")));
		BigDecimal totalform80 = form80
				.getMediclaim_policy_premium_for_self()
				.add(form80.getMediclaim_policy_premium_for_parents())
				.add(form80.getMediclaim_treatment_of_handicaped_dependent())
				.add(form80
						.getMediclaim_treatment_of_specified_disease_for_self())
				.add(form80.getDeduction_in_case_of_self_beign_totally_blind())
				.add(form80
						.getInterest_paid_on_loan_taken_for_higher_education());
		form80.setTotal(totalform80);
		form80.setDirect_investment_in_equity_under_rgess(new BigDecimal(
				request.getParameter("directRGESS")));
		form80.setMedical_benifits(new BigDecimal(request
				.getParameter("medicalBenefit")));
		form80.setInterest_on_housing_loan(new BigDecimal(request
				.getParameter("interestHousingLoan")));// setInterest_on_housing_loan(request.getParameter("interest_on_housing_loan"));
		form80c.setPayment_of_insurance_premium(new BigDecimal(request
				.getParameter("lifeInsurancePremium")));
		form80c.setEmployee_contribution_to_pf(new BigDecimal(request
				.getParameter("contributionToPF")));
		form80c.setDeposit_in_public_pf(new BigDecimal(request
				.getParameter("depositInPPF")));
		form80c.setEquity_tax_saving_mutualfund(new BigDecimal(request
				.getParameter("equityTaxSavingMutualFund")));
		form80c.setPurchase_of_national_saving_certificate(new BigDecimal(
				request.getParameter("nationalSavingCertificate")));
		form80c.setNational_savings_scheme_deposit(new BigDecimal(request
				.getParameter("nationalServiceSchema")));
		form80c.setPostoffice_saving_bonds_investment(new BigDecimal(request
				.getParameter("savingBondInvestment")));
		form80c.setNew_pension_scheme_deposit_of_80ccd(new BigDecimal(request
				.getParameter("newPensionSchemeDeposit")));
		form80c.setHouse_loan_principle_payment(new BigDecimal(request
				.getParameter("housingLoanPriciplePayment")));
		form80c.setTax_saving_fixed_deposit(new BigDecimal(request
				.getParameter("taxSavingFixedDeposit")));
		form80c.setStamp_duty_registration_charges_for_house(new BigDecimal(
				request.getParameter("stampDutyCharges")));
		form80c.setOther_eligible_investment(new BigDecimal(request
				.getParameter("otherInvestments")));
		form80c.setPension_fund(new BigDecimal(request
				.getParameter("pensionFund")));
		BigDecimal totalform80c = form80c.getPayment_of_insurance_premium()
				.add(form80c.getEmployee_contribution_to_pf())
				.add(form80c.getDeposit_in_public_pf())
				.add(form80c.getEquity_tax_saving_mutualfund())
				.add(form80c.getPurchase_of_national_saving_certificate())
				.add(form80c.getNational_savings_scheme_deposit())
				.add(form80c.getPostoffice_saving_bonds_investment())
				.add(form80c.getNew_pension_scheme_deposit_of_80ccd())
				.add(form80c.getHouse_loan_principle_payment())
				.add(form80c.getTax_saving_fixed_deposit())
				.add(form80c.getStamp_duty_registration_charges_for_house())
				.add(form80c.getPension_fund())
				.add(form80c.getOther_eligible_investment());
		form80c.setTotal(totalform80c);
		homeRentAllowance.setName_of_landlord(request
				.getParameter("nameOfLandlord"));
		homeRentAllowance.setAddress_of_landlord(request
				.getParameter("addressOfLandlord"));
		homeRentAllowance.setAddress_of_accomodation(request
				.getParameter("addressOfAccomadation"));
		homeRentAllowance.setRent_amount_per_month(new BigDecimal(request
				.getParameter("rentAmountPerMonth")));
		homeRentAllowance.setRent_amount_per_annum(new BigDecimal(request
				.getParameter("rentAmountPerAnnum")));
		form80.setCheck(5);
		form80.setCheck(10);
		investmentDeclarationDao.updateForm80(form80);
		investmentDeclarationDao.updateForm80c(form80c);
		investmentDeclarationDao.updateHra(homeRentAllowance);
		request.setAttribute("form80", form80); // try
		request.setAttribute("form80c", form80c);
		request.setAttribute("hra", homeRentAllowance);
		request.setAttribute("user", user);
		return mapping.findForward("success");
	}
	public ActionForward populateDeclarationForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		try{
		User user = userDao.findById(Long.parseLong(sessionUser.getUserId()));
		
		Form80 form80 = investmentDeclarationDao.findForm80ByUserIdAndYear(
				user.getId(), Calendar.getInstance().get(Calendar.YEAR));
		Form80c form80c = investmentDeclarationDao.findForm80cByUserIdAndYear(
				user.getId(), Calendar.getInstance().get(Calendar.YEAR));
		HomeRentAllowance homeRentAllowance = investmentDeclarationDao
				.findHraByUserIdAndYear(user.getId(), Calendar.getInstance()
						.get(Calendar.YEAR));
		if((form80 == null)&&(form80c == null)&&(homeRentAllowance == null))
		{
			form80 = new Form80();
			form80c = new Form80c();
			homeRentAllowance = new HomeRentAllowance();
			form80.setUser(user);
			form80c.setUser(user);
			homeRentAllowance.setUser(user);
			
			form80.setYear(Calendar.getInstance().get(Calendar.YEAR));
			form80c.setYear(Calendar.getInstance().get(Calendar.YEAR));
			homeRentAllowance.setYear(Calendar.getInstance().get(Calendar.YEAR));
			investmentDeclarationDao.addForm80(form80);
			investmentDeclarationDao.addForm80c(form80c);
			investmentDeclarationDao.addHra(homeRentAllowance);
			
		}
		
		request.setAttribute("form80", form80); // try
		request.setAttribute("form80c", form80c);
		request.setAttribute("hra", homeRentAllowance);
		request.setAttribute("user", user);
		}catch(Exception e)
		{
			Notification statusNotification = new Notification(
					"Error occured, Please login once again", "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");
	}
	public ActionForward listDeclarationForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		try{
			User user = userDao.findById(Long.parseLong(request.getParameter("userId")));
			Form80 form80 = investmentDeclarationDao.findForm80ByUserIdAndYear(
					user.getId(), Calendar.getInstance().get(Calendar.YEAR));
			Form80c form80c = investmentDeclarationDao.findForm80cByUserIdAndYear(
					user.getId(), Calendar.getInstance().get(Calendar.YEAR));
			HomeRentAllowance homeRentAllowance = investmentDeclarationDao
					.findHraByUserIdAndYear(user.getId(), Calendar.getInstance()
							.get(Calendar.YEAR));
			if((form80 == null)&&(form80c == null)&&(homeRentAllowance == null))
			{
				form80 = new Form80();
				form80c = new Form80c();
				homeRentAllowance = new HomeRentAllowance();
				form80.setUser(user);
				form80c.setUser(user);
				homeRentAllowance.setUser(user);
				form80.setYear(Calendar.getInstance().get(Calendar.YEAR));
				form80c.setYear(Calendar.getInstance().get(Calendar.YEAR));
				homeRentAllowance.setYear(Calendar.getInstance().get(Calendar.YEAR));
				investmentDeclarationDao.addForm80(form80);
				investmentDeclarationDao.addForm80c(form80c);
				investmentDeclarationDao.addHra(homeRentAllowance);
			}
			request.setAttribute("form80", form80); // try
			request.setAttribute("form80c", form80c);
			request.setAttribute("hra", homeRentAllowance);
			request.setAttribute("user", user);
			
		}catch(Exception e){
			Notification statusNotification = new Notification(
					"Error occured, Please login once again", "999",
					STATUS.ERROR);
			request.setAttribute("notification", statusNotification);
		}
		return mapping.findForward("success");
		
	}
	

	public void setInvestmentDeclarationDao(
			InvestmentDeclarationDao investmentDeclarationDao) {
		this.investmentDeclarationDao = investmentDeclarationDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
