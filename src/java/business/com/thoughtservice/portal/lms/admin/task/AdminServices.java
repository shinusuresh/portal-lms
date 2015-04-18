package com.thoughtservice.portal.lms.admin.task;

import java.util.List;
import java.util.Set;

import com.thoughtservice.portal.lms.admin.task.exceptions.SkillAlreadyExistsException;
import com.thoughtservice.portal.lms.admin.task.exceptions.UserAlreadyExists;
import com.thoughtservice.portal.role.Role;
import com.thoughtservice.portal.user.User;
import com.thoughtservice.portal.user.experience.Experience;
import com.thoughtservice.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.thoughtservice.portal.user.request.leaverequest.LeaveRequest;
import com.thoughtservice.portal.user.request.weekendworking.WeekendWorking;
import com.thoughtservice.portal.user.request.workfromhome.WorkFromHome;
import com.thoughtservice.portal.user.skills.Skill;

public interface AdminServices {

	/**
	 * Performs all business operations needed for creating a user. Once user is
	 * created successfully and email will be send to the user for activating
	 * his portal account
	 * 
	 * @param user
	 * @return userId
	 */
	Long createUser(User user, Role role, Set<Experience> experiences)
			throws UserAlreadyExists;
	
	void saveExperience(Set<Experience> experiences, Long userId);

	List<LeaveRequest> findAllLeaveRequestByYear(int year);

	List<WorkFromHome> findAllWorkFromHomeRequestByYear(int year);
	
	List<WeekendWorking> findAllWeekendWorkingRequestByYear(int year);
	
	List<BankHolidayWorking> findAllBankHolidayWorkingRequestByYear(int year);

	String createSkill(Skill skill) throws SkillAlreadyExistsException;

	void updateUserHolidays(LeaveRequest leaveRequest) throws Exception;


	void updateUserHolidays(BankHolidayWorking bankHolidayWorking)
			throws Exception;

	void updateUserHolidays(WorkFromHome workFromHome) throws Exception;

	void updateUserHolidays(WeekendWorking weekendWorking)
			throws Exception;
	
	List<LeaveRequest> listPendingLeaveRequestForCancellation();

	List<WorkFromHome> listPendingWorkFromHomeRequestForCancellation();

	List<BankHolidayWorking> listPendingBankHolidayWorkingRequestForCancellation();

	List<WeekendWorking> listPendingWeekendWorkingRequestForCancellation();
	
	/**
	 * Sends email to all users who have admin role set.
	 * Uses the Email template passed in as parameter
	 * @param emailTemplate
	 */
	void sendEmailToAdmin(String content);
}
