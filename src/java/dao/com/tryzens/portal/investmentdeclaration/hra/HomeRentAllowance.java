package com.tryzens.portal.investmentdeclaration.hra;

import java.math.BigDecimal;

import com.tryzens.portal.PersistentObject;
import com.tryzens.portal.user.User;

public class HomeRentAllowance extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5338137001028989599L;
	private String name_of_landlord=""; 
	 private String address_of_landlord=""; 
	 private String address_of_accomodation=""; 
	 private BigDecimal rent_amount_per_month=BigDecimal.ZERO; 
	 private BigDecimal rent_amount_per_annum=BigDecimal.ZERO ;
	 private User user;
	 private int year;
	public String getName_of_landlord() {
		return name_of_landlord;
	}
	public void setName_of_landlord(String name_of_landlord) {
		this.name_of_landlord = name_of_landlord;
	}
	public String getAddress_of_landlord() {
		return address_of_landlord;
	}
	public void setAddress_of_landlord(String address_of_landlord) {
		this.address_of_landlord = address_of_landlord;
	}
	public String getAddress_of_accomodation() {
		return address_of_accomodation;
	}
	public void setAddress_of_accomodation(String address_of_accomodation) {
		this.address_of_accomodation = address_of_accomodation;
	}
	public BigDecimal getRent_amount_per_month() {
		return rent_amount_per_month;
	}
	public void setRent_amount_per_month(BigDecimal rent_amount_per_month) {
		this.rent_amount_per_month = rent_amount_per_month;
	}
	public BigDecimal getRent_amount_per_annum() {
		return rent_amount_per_annum;
	}
	public void setRent_amount_per_annum(BigDecimal rent_amount_per_annum) {
		this.rent_amount_per_annum = rent_amount_per_annum;
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
