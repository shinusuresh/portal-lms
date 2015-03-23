package com.thoughtservice.portal.lms.investmentdeclaration.pdf.user;

import java.math.BigDecimal;

public class UserData {

	 private String firstName;
     private String lastName;
	 public int user_id ;
	 public Long employeecode1; 
	 public String email; 
	 public String password;
	 public String gender;
	 public String pan;
		
	 
	 
	 //80
	private BigDecimal mediclaimForSelf;
	private BigDecimal mediclaimForParents;
	private BigDecimal medicaltreatmentOfHandicappedDependent;
	private BigDecimal medicaltreatmentDiseasesforself;
	private BigDecimal deductionincaseofSelfBeingTotallyBlind;
	private BigDecimal inerestPaidonLoanTakenforHigherEducation;
	private BigDecimal total;
	private BigDecimal directInvestmentinEquityUnderrgess;
	private BigDecimal medicalBenefit;
	private BigDecimal interestonHousingLoan;
	
	//80c

	private  BigDecimal payment_of_insurance_premium ;
	private  BigDecimal employee_contribution_to_pf; 
	private  BigDecimal deposit_in_public_pf; 
	private  BigDecimal equity_tax_saving_mutualfund; 
	private  BigDecimal purchase_of_national_saving_certificate; 
	private  BigDecimal national_savings_scheme_deposit; 
	private  BigDecimal postoffice_saving_bonds_investment; 
	private  BigDecimal new_pension_scheme_deposit_of_80ccd ;
	private  BigDecimal house_loan_principle_payment; 
	private  BigDecimal tax_saving_fixed_deposit; 
	private  BigDecimal stamp_duty_registration_charges_for_house; 
	private  BigDecimal other_eligible_investment; 
	private  BigDecimal pension_fund; 

	
	
	
	//hra
	private String nameOfLandLord;
	private String lifeInsurancePremium;
	private String address_oflandlord; 
	private String addressofaccomodation; 
	private BigDecimal rentamountpermonth; 
	private BigDecimal rentamountperannum ;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Long getEmployeecode1() {
		return employeecode1;
	}
	public void setEmployeecode1(Long empolyeeCode1) {
		this.employeecode1 = empolyeeCode1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public BigDecimal getMediclaimForSelf() {
		return mediclaimForSelf;
	}
	public void setMediclaimForSelf(BigDecimal mediclaimForSelf) {
		this.mediclaimForSelf = mediclaimForSelf;
	}
	public BigDecimal getMediclaimForParents() {
		return mediclaimForParents;
	}
	public void setMediclaimForParents(BigDecimal mediclaimForParents) {
		this.mediclaimForParents = mediclaimForParents;
	}
	public BigDecimal getMedicaltreatmentOfHandicappedDependent() {
		return medicaltreatmentOfHandicappedDependent;
	}
	public void setMedicaltreatmentOfHandicappedDependent(
			BigDecimal medicaltreatmentOfHandicappedDependent) {
		this.medicaltreatmentOfHandicappedDependent = medicaltreatmentOfHandicappedDependent;
	}
	public BigDecimal getMedicaltreatmentDiseasesforself() {
		return medicaltreatmentDiseasesforself;
	}
	public void setMedicaltreatmentDiseasesforself(
			BigDecimal medicaltreatmentDiseasesforself) {
		this.medicaltreatmentDiseasesforself = medicaltreatmentDiseasesforself;
	}
	public BigDecimal getDeductionincaseofSelfBeingTotallyBlind() {
		return deductionincaseofSelfBeingTotallyBlind;
	}
	public void setDeductionincaseofSelfBeingTotallyBlind(
			BigDecimal deductionincaseofSelfBeingTotallyBlind) {
		this.deductionincaseofSelfBeingTotallyBlind = deductionincaseofSelfBeingTotallyBlind;
	}
	public BigDecimal getInerestPaidonLoanTakenforHigherEducation() {
		return inerestPaidonLoanTakenforHigherEducation;
	}
	public void setInerestPaidonLoanTakenforHigherEducation(
			BigDecimal inerestPaidonLoanTakenforHigherEducation) {
		this.inerestPaidonLoanTakenforHigherEducation = inerestPaidonLoanTakenforHigherEducation;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getDirectInvestmentinEquityUnderrgess() {
		return directInvestmentinEquityUnderrgess;
	}
	public void setDirectInvestmentinEquityUnderrgess(
			BigDecimal directInvestmentinEquityUnderrgess) {
		this.directInvestmentinEquityUnderrgess = directInvestmentinEquityUnderrgess;
	}
	public BigDecimal getMedicalBenefit() {
		return medicalBenefit;
	}
	public void setMedicalBenefit(BigDecimal medicalBenefit) {
		this.medicalBenefit = medicalBenefit;
	}
	public BigDecimal getInterestonHousingLoan() {
		return interestonHousingLoan;
	}
	public void setInterestonHousingLoan(BigDecimal interestonHousingLoan) {
		this.interestonHousingLoan = interestonHousingLoan;
	}
	public BigDecimal getPayment_of_insurance_premium() {
		return payment_of_insurance_premium;
	}
	public void setPayment_of_insurance_premium(
			BigDecimal payment_of_insurance_premium) {
		this.payment_of_insurance_premium = payment_of_insurance_premium;
	}
	public BigDecimal getEmployee_contribution_to_pf() {
		return employee_contribution_to_pf;
	}
	public void setEmployee_contribution_to_pf(
			BigDecimal employee_contribution_to_pf) {
		this.employee_contribution_to_pf = employee_contribution_to_pf;
	}
	public BigDecimal getDeposit_in_public_pf() {
		return deposit_in_public_pf;
	}
	public void setDeposit_in_public_pf(BigDecimal deposit_in_public_pf) {
		this.deposit_in_public_pf = deposit_in_public_pf;
	}
	public BigDecimal getEquity_tax_saving_mutualfund() {
		return equity_tax_saving_mutualfund;
	}
	public void setEquity_tax_saving_mutualfund(
			BigDecimal equity_tax_saving_mutualfund) {
		this.equity_tax_saving_mutualfund = equity_tax_saving_mutualfund;
	}
	public BigDecimal getPurchase_of_national_saving_certificate() {
		return purchase_of_national_saving_certificate;
	}
	public void setPurchase_of_national_saving_certificate(
			BigDecimal purchase_of_national_saving_certificate) {
		this.purchase_of_national_saving_certificate = purchase_of_national_saving_certificate;
	}
	public BigDecimal getNational_savings_scheme_deposit() {
		return national_savings_scheme_deposit;
	}
	public void setNational_savings_scheme_deposit(
			BigDecimal national_savings_scheme_deposit) {
		this.national_savings_scheme_deposit = national_savings_scheme_deposit;
	}
	public BigDecimal getPostoffice_saving_bonds_investment() {
		return postoffice_saving_bonds_investment;
	}
	public void setPostoffice_saving_bonds_investment(
			BigDecimal postoffice_saving_bonds_investment) {
		this.postoffice_saving_bonds_investment = postoffice_saving_bonds_investment;
	}
	public BigDecimal getNew_pension_scheme_deposit_of_80ccd() {
		return new_pension_scheme_deposit_of_80ccd;
	}
	public void setNew_pension_scheme_deposit_of_80ccd(
			BigDecimal new_pension_scheme_deposit_of_80ccd) {
		this.new_pension_scheme_deposit_of_80ccd = new_pension_scheme_deposit_of_80ccd;
	}
	public BigDecimal getHouse_loan_principle_payment() {
		return house_loan_principle_payment;
	}
	public void setHouse_loan_principle_payment(
			BigDecimal house_loan_principle_payment) {
		this.house_loan_principle_payment = house_loan_principle_payment;
	}
	public BigDecimal getTax_saving_fixed_deposit() {
		return tax_saving_fixed_deposit;
	}
	public void setTax_saving_fixed_deposit(BigDecimal tax_saving_fixed_deposit) {
		this.tax_saving_fixed_deposit = tax_saving_fixed_deposit;
	}
	public BigDecimal getStamp_duty_registration_charges_for_house() {
		return stamp_duty_registration_charges_for_house;
	}
	public void setStamp_duty_registration_charges_for_house(
			BigDecimal stamp_duty_registration_charges_for_house) {
		this.stamp_duty_registration_charges_for_house = stamp_duty_registration_charges_for_house;
	}
	public BigDecimal getOther_eligible_investment() {
		return other_eligible_investment;
	}
	public void setOther_eligible_investment(BigDecimal other_eligible_investment) {
		this.other_eligible_investment = other_eligible_investment;
	}
	public BigDecimal getPension_fund() {
		return pension_fund;
	}
	public void setPension_fund(BigDecimal pension_fund) {
		this.pension_fund = pension_fund;
	}
	public String getNameOfLandLord() {
		return nameOfLandLord;
	}
	public void setNameOfLandLord(String nameOfLandLord) {
		this.nameOfLandLord = nameOfLandLord;
	}
	public String getLifeInsurancePremium() {
		return lifeInsurancePremium;
	}
	public void setLifeInsurancePremium(String lifeInsurancePremium) {
		this.lifeInsurancePremium = lifeInsurancePremium;
	}
	public String getAddress_oflandlord() {
		return address_oflandlord;
	}
	public void setAddress_oflandlord(String address_oflandlord) {
		this.address_oflandlord = address_oflandlord;
	}
	public String getAddressofaccomodation() {
		return addressofaccomodation;
	}
	public void setAddressofaccomodation(String addressofaccomodation) {
		this.addressofaccomodation = addressofaccomodation;
	}
	public BigDecimal getRentamountpermonth() {
		return rentamountpermonth;
	}
	public void setRentamountpermonth(BigDecimal rentamountpermonth) {
		this.rentamountpermonth = rentamountpermonth;
	}
	public BigDecimal getRentamountperannum() {
		return rentamountperannum;
	}
	public void setRentamountperannum(BigDecimal rentamountperannum) {
		this.rentamountperannum = rentamountperannum;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	
	
}
	
	