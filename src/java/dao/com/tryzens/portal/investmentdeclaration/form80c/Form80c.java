package com.tryzens.portal.investmentdeclaration.form80c;

import java.math.BigDecimal;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.user.User;

public class Form80c extends PersistentObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 259891007821803546L;
	private BigDecimal payment_of_insurance_premium=BigDecimal.ZERO ;
	private BigDecimal employee_contribution_to_pf=BigDecimal.ZERO; 
	private BigDecimal deposit_in_public_pf=BigDecimal.ZERO; 
	private BigDecimal equity_tax_saving_mutualfund=BigDecimal.ZERO; 
	private BigDecimal purchase_of_national_saving_certificate=BigDecimal.ZERO; 
	private BigDecimal national_savings_scheme_deposit=BigDecimal.ZERO; 
	private BigDecimal postoffice_saving_bonds_investment=BigDecimal.ZERO; 
	private BigDecimal new_pension_scheme_deposit_of_80ccd =BigDecimal.ZERO;
	private BigDecimal house_loan_principle_payment=BigDecimal.ZERO; 
	private BigDecimal tax_saving_fixed_deposit=BigDecimal.ZERO; 
	private BigDecimal stamp_duty_registration_charges_for_house=BigDecimal.ZERO; 
	private BigDecimal other_eligible_investment=BigDecimal.ZERO; 
	private BigDecimal pension_fund=BigDecimal.ZERO; 
	private BigDecimal total=BigDecimal.ZERO;
	private User user;
	private int year;
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
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
