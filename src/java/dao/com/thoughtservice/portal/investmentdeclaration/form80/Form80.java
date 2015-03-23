package com.thoughtservice.portal.investmentdeclaration.form80;

import java.math.BigDecimal;

import com.thoughtservice.portal.PersistentObject;
import com.thoughtservice.portal.user.User;

public class Form80 extends PersistentObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3952517262363370168L;
	private BigDecimal mediclaim_policy_premium_for_self=BigDecimal.ZERO; 
	private BigDecimal mediclaim_policy_premium_for_parents=BigDecimal.ZERO; 
	private BigDecimal mediclaim_treatment_of_handicaped_dependent=BigDecimal.ZERO; 
	private BigDecimal mediclaim_treatment_of_specified_disease_for_self=BigDecimal.ZERO; 
	private BigDecimal deduction_in_case_of_self_beign_totally_blind=BigDecimal.ZERO ;
	private BigDecimal interest_paid_on_loan_taken_for_higher_education=BigDecimal.ZERO; 
	private BigDecimal total=BigDecimal.ZERO; 
	private BigDecimal direct_investment_in_equity_under_rgess=BigDecimal.ZERO; 
	private BigDecimal medical_benifits=BigDecimal.ZERO; 
	private BigDecimal interest_on_housing_loan=BigDecimal.ZERO;
	private int check=0;
	private int year;
	private User user;
	public BigDecimal getMediclaim_policy_premium_for_self() {
		return mediclaim_policy_premium_for_self;
	}
	public void setMediclaim_policy_premium_for_self(
			BigDecimal mediclaim_policy_premium_for_self) {
		this.mediclaim_policy_premium_for_self = mediclaim_policy_premium_for_self;
	}
	public BigDecimal getMediclaim_policy_premium_for_parents() {
		return mediclaim_policy_premium_for_parents;
	}
	public void setMediclaim_policy_premium_for_parents(
			BigDecimal mediclaim_policy_premium_for_parents) {
		this.mediclaim_policy_premium_for_parents = mediclaim_policy_premium_for_parents;
	}
	public BigDecimal getMediclaim_treatment_of_handicaped_dependent() {
		return mediclaim_treatment_of_handicaped_dependent;
	}
	public void setMediclaim_treatment_of_handicaped_dependent(
			BigDecimal mediclaim_treatment_of_handicaped_dependent) {
		this.mediclaim_treatment_of_handicaped_dependent = mediclaim_treatment_of_handicaped_dependent;
	}
	public BigDecimal getMediclaim_treatment_of_specified_disease_for_self() {
		return mediclaim_treatment_of_specified_disease_for_self;
	}
	public void setMediclaim_treatment_of_specified_disease_for_self(
			BigDecimal mediclaim_treatment_of_specified_disease_for_self) {
		this.mediclaim_treatment_of_specified_disease_for_self = mediclaim_treatment_of_specified_disease_for_self;
	}
	public BigDecimal getDeduction_in_case_of_self_beign_totally_blind() {
		return deduction_in_case_of_self_beign_totally_blind;
	}
	public void setDeduction_in_case_of_self_beign_totally_blind(
			BigDecimal deduction_in_case_of_self_beign_totally_blind) {
		this.deduction_in_case_of_self_beign_totally_blind = deduction_in_case_of_self_beign_totally_blind;
	}
	public BigDecimal getInterest_paid_on_loan_taken_for_higher_education() {
		return interest_paid_on_loan_taken_for_higher_education;
	}
	public void setInterest_paid_on_loan_taken_for_higher_education(
			BigDecimal interest_paid_on_loan_taken_for_higher_education) {
		this.interest_paid_on_loan_taken_for_higher_education = interest_paid_on_loan_taken_for_higher_education;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getDirect_investment_in_equity_under_rgess() {
		return direct_investment_in_equity_under_rgess;
	}
	public void setDirect_investment_in_equity_under_rgess(
			BigDecimal direct_investment_in_equity_under_rgess) {
		this.direct_investment_in_equity_under_rgess = direct_investment_in_equity_under_rgess;
	}
	public BigDecimal getMedical_benifits() {
		return medical_benifits;
	}
	public void setMedical_benifits(BigDecimal medical_benifits) {
		this.medical_benifits = medical_benifits;
	}
	public BigDecimal getInterest_on_housing_loan() {
		return interest_on_housing_loan;
	}
	public void setInterest_on_housing_loan(BigDecimal interest_on_housing_loan) {
		this.interest_on_housing_loan = interest_on_housing_loan;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
