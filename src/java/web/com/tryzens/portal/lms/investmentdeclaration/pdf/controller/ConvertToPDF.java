package com.tryzens.portal.lms.investmentdeclaration.pdf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
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
import com.tryzens.portal.lms.investmentdeclaration.InvestmentDeclarationAction;
import com.tryzens.portal.lms.investmentdeclaration.pdf.DeclarationPdfDocument;
import com.tryzens.portal.lms.investmentdeclaration.pdf.user.UserData;
import com.tryzens.portal.lms.investmentdeclaration.pdf.user.UserDataBuilder;

public class ConvertToPDF extends DispatchAction {
	private InvestmentDeclarationDao investmentDeclarationDao;
	private UserDao userDao;


	public ConvertToPDF() {
		super();
	}

	public ActionForward doGet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			PortalUserDetails sessionUser = (PortalUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			Long id = Long.parseLong(sessionUser.getUserId());
			String filePath = System.getProperty("java.io.tmpdir");
			User user = new User();
			Form80 form80 = new Form80();
			Form80c form80c = new Form80c();
			HomeRentAllowance homeRentAllowance = new HomeRentAllowance();
			try {

				user = userDao
						.findById(Long.parseLong(sessionUser.getUserId()));
				form80 = investmentDeclarationDao
						.findForm80ByUserIdAndYear(user.getId(), Calendar
								.getInstance().get(Calendar.YEAR));
				form80c = investmentDeclarationDao
						.findForm80cByUserIdAndYear(user.getId(), Calendar
								.getInstance().get(Calendar.YEAR));
				homeRentAllowance = investmentDeclarationDao
						.findHraByUserIdAndYear(user.getId(), Calendar
								.getInstance().get(Calendar.YEAR));

				if ((form80 == null) && (form80c == null)
						&& (homeRentAllowance == null)) {
					form80 = new Form80();
					form80c = new Form80c();
					homeRentAllowance = new HomeRentAllowance();
					form80.setUser(user);
					form80c.setUser(user);
					homeRentAllowance.setUser(user);

					form80.setYear(Calendar.getInstance().get(Calendar.YEAR));
					form80c.setYear(Calendar.getInstance().get(Calendar.YEAR));
					homeRentAllowance.setYear(Calendar.getInstance().get(
							Calendar.YEAR));
					investmentDeclarationDao.addForm80(form80);
					investmentDeclarationDao.addForm80c(form80c);
					investmentDeclarationDao.addHra(homeRentAllowance);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			DeclarationPdfDocument declarationPdfDocument = new DeclarationPdfDocument();

			// Build user data from DB
			UserData userData = new UserDataBuilder()
					.setFirstName(user.getFirstName())
					.setLastName(user.getLastName())
					.setEmployeeCode1(user.getEmployeeCode())
					.setEmail(user.getEmail())
					.setPassword(user.getPassword())
					.setGender(user.getGender())
					.setPan(user.getPan())
					.setMediclaimForSelf(
							form80.getMediclaim_policy_premium_for_self())
					.setMediclaimForParents(
							form80.getMediclaim_policy_premium_for_parents())
					.setMedicaltreatmentOfHandicappedDependent(
							form80.getMediclaim_treatment_of_handicaped_dependent())
					.setMedicaltreatmentDiseasesforself(
							form80.getMediclaim_policy_premium_for_self())
					.setDeductionincaseofSelfBeingTotallyBlind(
							form80.getDeduction_in_case_of_self_beign_totally_blind())
					.setInerestPaidonLoanTakenforHigherEducation(
							form80.getInterest_paid_on_loan_taken_for_higher_education())
					.setTotal(form80.getTotal())
					.setDirectInvestmentinEquityUnderrgess(
							form80.getDirect_investment_in_equity_under_rgess())
					.setMedicalBenefit(form80.getMedical_benifits())
					.setInterestonHousingLoan(
							form80.getInterest_on_housing_loan())
					.setPayment_of_insurance_premium(
							form80c.getPayment_of_insurance_premium())
					.setEmployee_contribution_to_pf(
							form80c.getEmployee_contribution_to_pf())
					.setDeposit_in_public_pf(form80c.getDeposit_in_public_pf())
					.setEquity_tax_saving_mutualfund(
							form80c.getEquity_tax_saving_mutualfund())
					.setPurchase_of_national_saving_certificate(
							form80c.getPurchase_of_national_saving_certificate())
					.setNational_savings_scheme_deposit(
							form80c.getNational_savings_scheme_deposit())
					.setPostoffice_saving_bonds_investment(
							form80c.getPostoffice_saving_bonds_investment())
					.setNew_pension_scheme_deposit_of_80ccd(
							form80c.getNew_pension_scheme_deposit_of_80ccd())
					.setHouse_loan_principle_payment(
							form80c.getHouse_loan_principle_payment())
					.setTax_saving_fixed_deposit(
							form80c.getTax_saving_fixed_deposit())
					.setStamp_duty_registration_charges_for_house(
							form80c.getStamp_duty_registration_charges_for_house())
					.setOther_eligible_investment(
							form80c.getOther_eligible_investment())
					.setPension_fund(form80c.getPension_fund())
					.setNameOfLandLord(homeRentAllowance.getName_of_landlord())
					.setaddress_oflandlord(
							homeRentAllowance.getAddress_of_landlord())
					.setAddressofaccomodation(
							homeRentAllowance.getAddress_of_accomodation())
					.setRentamountpermonth(
							homeRentAllowance.getRent_amount_per_month())
					.setRentamountperannum(
							homeRentAllowance.getRent_amount_per_annum())
					.build();

			String pdf = declarationPdfDocument.createDeclarationDocument(
					filePath, userData);

			File downloadFile = new File(pdf);
			FileInputStream inStream = new FileInputStream(downloadFile);
			response.setContentLength((int) downloadFile.length());

			// forces download
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// obtains response's output stream
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void setInvestmentDeclarationDao(
			InvestmentDeclarationDao investmentDeclarationDao) {
		this.investmentDeclarationDao = investmentDeclarationDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}
