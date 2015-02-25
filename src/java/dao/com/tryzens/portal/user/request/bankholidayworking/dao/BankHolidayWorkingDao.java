package com.tryzens.portal.user.request.bankholidayworking.dao;

import java.util.List;

import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;

public interface BankHolidayWorkingDao {
	void saveRequest(BankHolidayWorking bankHolidayWorking);

	List<BankHolidayWorking> findPendingRequest(Long approverId);

	List<BankHolidayWorking> findAllBankHolidayWorkingRequestByUserId(
			long userId);

	BankHolidayWorking findRequestByRequestId(Long requestId);

	void updateStatus(BankHolidayWorking bankHolidayWorking);

	List<BankHolidayWorking> findAllApprovedBankHolidayWorkingRequestByUserId(
			Long userId);
	List<BankHolidayWorking> findAllBankHolidayWorkingRequestByYear(int year);

	List<BankHolidayWorking> findAllPendingBankHolidayWorkingRequestByUserId(
			Long userId);

	List<BankHolidayWorking> listPendingBankHolidayWorkingRequestForCancellationToAdmin();
	
	List<BankHolidayWorking> listPendingBankHolidayWorkingRequestForCancellation(Long approverId);

	List<BankHolidayWorking> findAllBankHolidayWorkingRequestsOfLastTwoYearsByUserId(
			long userId, int year);

	List<BankHolidayWorking> findAllBankHolidayWorkingRequestsOfUserByMonthAndYear(
			Long id, int month,int year);
}
