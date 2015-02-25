package com.tryzens.portal.investmentdeclaration.dao;

import com.tryzens.portal.investmentdeclaration.form80.Form80;
import com.tryzens.portal.investmentdeclaration.form80c.Form80c;
import com.tryzens.portal.investmentdeclaration.hra.HomeRentAllowance;

public interface InvestmentDeclarationDao {
	void addForm80(Form80 form80);

	void addForm80c(Form80c form80c);

	void addHra(HomeRentAllowance homeRentAllowance);

	void updateForm80(Form80 form80);

	void updateForm80c(Form80c form80c);

	void updateHra(HomeRentAllowance homeRentAllowance);

	Form80 findForm80ByUserIdAndYear(Long userId, int year);

	Form80c findForm80cByUserIdAndYear(Long userId, int year);

	HomeRentAllowance findHraByUserIdAndYear(Long userId, int year);

}
