package com.thoughtservice.portal.lms.investmentdeclaration.pdf.user;

import java.math.BigDecimal;

public class UserDataBuilder {

	private UserData userData = new UserData();
	
	public UserDataBuilder setFirstName(String firstName) {
		userData.setFirstName(firstName);
		return this;
	}
	
	public UserDataBuilder setLastName(String lastName) {
		userData.setLastName(lastName);
		return this;
	}
	
	public UserDataBuilder setGender(String gender) {
		userData.setGender(gender);
		return this;
	}
	
	public UserDataBuilder setEmployeeCode1(Long empolyeeCode1) {
		userData.setEmployeecode1(empolyeeCode1);
		return this;
	}
	public UserDataBuilder setEmail(String email) {
		userData.setEmail(email);
		return this;
	}
	
	
	
	public UserDataBuilder setPassword(String password) {
		userData.setPassword(password);
		return this;
	}
	
	
	
	public UserDataBuilder setPan(String pan) {
		userData.setPan(pan);
		return this;
	}
	
	
	public UserDataBuilder setMediclaimForSelf(BigDecimal mediclaimForSelf) {
		userData.setMediclaimForSelf(mediclaimForSelf);
		return this;
	}
	
	public UserDataBuilder setMediclaimForParents(BigDecimal mediclaimForParents) {
		userData.setMediclaimForParents(mediclaimForParents);
		return this;
	}
	
   public UserDataBuilder setMedicaltreatmentOfHandicappedDependent(BigDecimal medicaltreatmentOfHandicappedDependent )
   {
	 userData.setMedicaltreatmentOfHandicappedDependent(medicaltreatmentOfHandicappedDependent);  
	 return this;
   }
   
   
	public UserDataBuilder setMedicaltreatmentDiseasesforself(BigDecimal medicaltreatmentDiseasesforself ) {
		userData.setMedicaltreatmentDiseasesforself(medicaltreatmentDiseasesforself);
		return this;
	}
   
   
	public UserDataBuilder setDeductionincaseofSelfBeingTotallyBlind(BigDecimal deductionincaseofSelfBeingTotallyBlind ) {
		userData.setDeductionincaseofSelfBeingTotallyBlind(deductionincaseofSelfBeingTotallyBlind);
		return this;
	
	}
	
	public UserDataBuilder setInerestPaidonLoanTakenforHigherEducation(BigDecimal inerestPaidonLoanTakenforHigherEducation ) {
		userData.setInerestPaidonLoanTakenforHigherEducation(inerestPaidonLoanTakenforHigherEducation);
		return this;
	}
	
	
	
	public UserDataBuilder setTotal(BigDecimal total ) {
		userData.setTotal(total);
		return this;
	}
	
	
	public UserDataBuilder setDirectInvestmentinEquityUnderrgess (BigDecimal  directInvestmentinEquityUnderrgess ) {
		userData.setDirectInvestmentinEquityUnderrgess(directInvestmentinEquityUnderrgess);
		return this;
	}
	
	
	
	public UserDataBuilder setMedicalBenefit (BigDecimal  medicalBenefit) {
		userData.setMedicalBenefit(medicalBenefit);
		return this;
	}
	
	
	public UserDataBuilder setPayment_of_insurance_premium  (BigDecimal  payment_of_insurance_premium ) {
		userData.setPayment_of_insurance_premium  ( payment_of_insurance_premium  );
		return this;
	}
	
	
	
	
	

	public UserDataBuilder setInterestonHousingLoan (BigDecimal interestonHousingLoan ) {
		userData.setInterestonHousingLoan(interestonHousingLoan);
		return this;
	}
	
	public UserDataBuilder setEmployee_contribution_to_pf  (BigDecimal employee_contribution_to_pf  ) {
		userData.setEmployee_contribution_to_pf (employee_contribution_to_pf);
		return this;
	}
	
	

	public UserDataBuilder setDeposit_in_public_pf (BigDecimal deposit_in_public_pf ) {
		userData.setDeposit_in_public_pf ( deposit_in_public_pf );
		return this;
	}
	
	
	
	public UserDataBuilder setEquity_tax_saving_mutualfund  (BigDecimal equity_tax_saving_mutualfund ) {
		userData.setEquity_tax_saving_mutualfund(equity_tax_saving_mutualfund);
		return this;
	}
	


	
	public UserDataBuilder setPurchase_of_national_saving_certificate (BigDecimal  purchase_of_national_saving_certificate ) {
		userData.setPurchase_of_national_saving_certificate(purchase_of_national_saving_certificate);
		return this;
	}
	

	
	public UserDataBuilder setNational_savings_scheme_deposit (BigDecimal national_savings_scheme_deposit ) {
		userData.setNational_savings_scheme_deposit(national_savings_scheme_deposit );
		return this;
	}
	

	
	public UserDataBuilder setPostoffice_saving_bonds_investment (BigDecimal postoffice_saving_bonds_investment ) {
		userData.setPostoffice_saving_bonds_investment(postoffice_saving_bonds_investment);
		return this;
	}
	
	
	
	
	

	public UserDataBuilder setNew_pension_scheme_deposit_of_80ccd (BigDecimal new_pension_scheme_deposit_of_80ccd ) {
		userData.setNew_pension_scheme_deposit_of_80ccd(new_pension_scheme_deposit_of_80ccd);
		return this;
	}
	
	
	

	public UserDataBuilder setHouse_loan_principle_payment (BigDecimal house_loan_principle_payment ) {
		userData.setHouse_loan_principle_payment(house_loan_principle_payment );
		return this;
	}
	
	
	
	
	public UserDataBuilder setTax_saving_fixed_deposit  (BigDecimal tax_saving_fixed_deposit) {
		userData.setTax_saving_fixed_deposit ( tax_saving_fixed_deposit );
		return this;
	}
	
	
	public UserDataBuilder setStamp_duty_registration_charges_for_house (BigDecimal stamp_duty_registration_charges_for_house) {
		userData.setStamp_duty_registration_charges_for_house  ( stamp_duty_registration_charges_for_house );
		return this;
	}
	
	
	
	
	
	public UserDataBuilder setOther_eligible_investment (BigDecimal  other_eligible_investment ) {
		userData.setOther_eligible_investment(other_eligible_investment  );
		return this;
	}
	
	
	public UserDataBuilder setPension_fund (BigDecimal pension_fund ) {
		userData.setPension_fund ( pension_fund );
		return this;
	}
	
	
	
	
	
	public UserDataBuilder setNameOfLandLord (String nameOfLandLord ) {
		userData.setNameOfLandLord(nameOfLandLord);
		return this;
	}
	
	
	
	public UserDataBuilder setLifeInsurancePremium  (String lifeInsurancePremium  ) {
		userData.setLifeInsurancePremium (lifeInsurancePremium );
		return this;
	}
	
	
	
	public UserDataBuilder setaddress_oflandlord(String  address_oflandlord ) {
		userData.setAddress_oflandlord(address_oflandlord );
		return this;
	}
	
	
	public UserDataBuilder setAddressofaccomodation(String addressofaccomodation) {
		userData.setAddressofaccomodation(addressofaccomodation );
		return this;
	}
	
	
	public UserDataBuilder setRentamountpermonth (BigDecimal rentamountpermonth) {
		userData.setRentamountpermonth(rentamountpermonth );
		return this;
	}
	
	
	
	public UserDataBuilder setRentamountperannum (BigDecimal rentamountperannum ) {
		userData.setRentamountperannum( rentamountperannum );
		return this;
	}
	
	
	
	/**
	 * Build the userdata object
	 * @return
	 */
	public UserData build(){
		return userData;
	}
}
