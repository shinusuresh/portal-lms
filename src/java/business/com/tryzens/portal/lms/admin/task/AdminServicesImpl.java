package com.tryzens.portal.lms.admin.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.tryzens.portal.jobs.calculateleaves.CalculateLeaves;
import com.tryzens.portal.lms.admin.task.exceptions.SkillAlreadyExistsException;
import com.tryzens.portal.lms.admin.task.exceptions.UserAlreadyExists;
import com.tryzens.portal.lms.admin.utils.AdminUtils;
import com.tryzens.portal.lms.mail.AccountActivationEmail;
import com.tryzens.portal.lms.mail.AdminGenericEmail;
import com.tryzens.portal.role.Role;
import com.tryzens.portal.user.User;
import com.tryzens.portal.user.dao.RoleDao;
import com.tryzens.portal.user.dao.UserDao;
import com.tryzens.portal.user.experience.Experience;
import com.tryzens.portal.user.experience.dao.ExperienceDao;
import com.tryzens.portal.user.request.bankholidayworking.BankHolidayWorking;
import com.tryzens.portal.user.request.bankholidayworking.dao.BankHolidayWorkingDao;
import com.tryzens.portal.user.request.leaverequest.LeaveRequest;
import com.tryzens.portal.user.request.leaverequest.dao.LeaveRequestDao;
import com.tryzens.portal.user.request.weekendworking.WeekendWorking;
import com.tryzens.portal.user.request.weekendworking.dao.WeekendWorkingDao;
import com.tryzens.portal.user.request.workfromhome.WorkFromHome;
import com.tryzens.portal.user.skills.Skill;
import com.tryzens.portal.user.skills.dao.SkillsDao;
import com.tryzens.portal.userholidays.UserHolidays;
import com.tryzens.portal.userholidays.dao.UserHolidaysDao;
import com.tryzens.portal.workfromhome.dao.WorkFromHomeDao;

@Transactional
public class AdminServicesImpl implements AdminServices {
	
	private static final Log LOGGER = LogFactory.getLog(AdminServicesImpl.class);

	private UserDao userDao;
	private RoleDao roleDao;
	private AccountActivationEmail email;
	private PasswordEncoder passwordEncoder;
    private LeaveRequestDao leaveRequestDao;
    private WorkFromHomeDao workFromHomeDao;
    private BankHolidayWorkingDao bankHolidayWorkingDao;
	private SkillsDao skillsDao;
	private UserHolidaysDao userHolidaysDao;
	private CalculateLeaves calculateLeaves;
    private WeekendWorkingDao weekendWorkingDao;
    private ExperienceDao experienceDao;
    private AdminGenericEmail adminGenericEmail;

	public Long createUser(User user, Role role, Set<Experience> experiences)
			throws UserAlreadyExists {
		// Check if user is already registered. Check for the email id is
		// available in DB already
		User existingUser = userDao.findByEmail(user.getEmail());
		if (existingUser != null) {
			throw new UserAlreadyExists("Email " + user.getEmail()
					+ " already exists.");
		} else {

			// Set selected role to user
			user.setRoles(constructUserRole(role.getId()));
			
			//Generate a random password and encode it
			String plainPassword = AdminUtils.generateRandomPassword();
			String password = passwordEncoder.encodePassword(plainPassword, null);		
			user.setPassword(password);
			user.setPlainPassword(plainPassword);
			
			//Activate the user
			user.setStatus(1);
			//set the experience
			user.setExperience(experiences);
			
			Long savedUser = userDao.addCustomer(user);
			user.setId(savedUser);
			User lmsUser= userDao.findByEmail(user.getEmail());
			UserHolidays userHolidays= new UserHolidays();
			Calendar now = Calendar.getInstance();
			userHolidays.setYear(now.get(Calendar.YEAR));
			userHolidays.setLeavesTaken((long) 0);
			userHolidays.setBankHolidayWorkingCompoff((long)0);
			userHolidays.setBankHolidayWorkingPaid((long)0);
			userHolidays.setWorkFromHome((long)0);
			userHolidays.setLeavesRemaining((long) user.getAnnualLeaves());
			userHolidays.setCarryForwardedLeaves((long)0);
			userHolidays.setUser(lmsUser);
			userHolidaysDao.addUserHolidays(userHolidays);
			if (savedUser != null) {
				//Send email to user
				try {
					email.setData(user);
					email.sendEmail();
				} catch (MessagingException e) {					
					LOGGER.error(e);					
				} catch (MailSendException me){
					LOGGER.error(me);
				}
				return savedUser;
			}
		}
		return null;
	}
	
	public void saveExperience(Set<Experience> experiences, Long userId) {
		User user = new User();
		user.setId(userId);
		//for each experience set user id
		for(Experience experience : experiences) {
			experience.setUser(user);
		}		
		experienceDao.saveRequest(experiences);
	}
	
	public String createSkill(Skill skill) throws SkillAlreadyExistsException {
		//Check if skill already exist
		List<Skill> sk = skillsDao.getSkillByName(skill.getName());
		if(sk != null && sk.size() > 0){
			throw new SkillAlreadyExistsException("Skill already exists");
		} else {
			Long savedSkillId = skillsDao.addSkills(skill);
			return savedSkillId.toString();
		}		
	}
		

	private Set<Role> constructUserRole(Long roleId) {
		// set a role
		Set<Role> roles = new HashSet<Role>();
		Role role = roleDao.findRoleById(roleId);
		roles.add(role);

		return roles;
	}
	//list all leave requests by year
	public List<LeaveRequest> findAllLeaveRequestByYear(int year) {
		
		List<LeaveRequest> leaveRequests = leaveRequestDao
				.findAllLeaveRequestByYear(year);
		return  leaveRequests;
	}
	public List<BankHolidayWorking> findAllBankHolidayWorkingRequestByYear(
			int year) {
		List<BankHolidayWorking> bankHolidayWorking=bankHolidayWorkingDao.findAllBankHolidayWorkingRequestByYear(year);
		return bankHolidayWorking;
		
	}
	public List<WorkFromHome> findAllWorkFromHomeRequestByYear(int year) {
		
		List<WorkFromHome> workFromHome=workFromHomeDao.
				findAllWorkFromHomeRequestByYear(year);
		return workFromHome;
	}
	
	public List<WeekendWorking> findAllWeekendWorkingRequestByYear(
		int year) {
			List<WeekendWorking> weekendWorking= weekendWorkingDao.	
					findAllWeekendWorkingRequestByYear(year);
		return weekendWorking;
	}
	
	public void updateUserHolidays(LeaveRequest leaveRequest) throws Exception
	{
		calculateLeaves.addLeave(leaveRequest);
	}
	public void updateUserHolidays(BankHolidayWorking bankHolidayWorking) throws Exception
	{
		calculateLeaves.addLeave(bankHolidayWorking);
	}
	public void updateUserHolidays(WorkFromHome workFromHome) throws Exception
	{
		calculateLeaves.addLeave(workFromHome);
	}
	
	public void updateUserHolidays(WeekendWorking weekendWorking) throws Exception
	{
		calculateLeaves.addLeave( weekendWorking);
	}
	
	public List<LeaveRequest> listPendingLeaveRequestForCancellation()
	{
		List<LeaveRequest> pendingList= leaveRequestDao.listPendingLeaveRequestForCancellationToAdmin();
		return pendingList;
		
	}
	public List<WorkFromHome> listPendingWorkFromHomeRequestForCancellation()
	{
		List<WorkFromHome> pendingList= workFromHomeDao.listPendingWorkFromHomeRequestForCancellationToAdmin();
		return pendingList;
		
	}
	
	
	public List<WeekendWorking> listPendingWeekendWorkingRequestForCancellation()
	{
		List<WeekendWorking> pendingList= weekendWorkingDao.listPendingWeekendWorkingRequestForCancellationToAdmin();
		return pendingList;
		
	}
	
	public List<BankHolidayWorking> listPendingBankHolidayWorkingRequestForCancellation()
	{
		List<BankHolidayWorking> pendingList= bankHolidayWorkingDao.listPendingBankHolidayWorkingRequestForCancellationToAdmin();
		return pendingList;
		
	}

	/**
	 * Send email to all users belongs to admin group
	 */
	@Override
	public void sendEmailToAdmin(String content) {
		List<User> adminUsers = userDao.findAllUserByRole("ROLE_ADMIN");
		List<String> adminEmail = new ArrayList<String>();
		//Get email address for all users and set in list
		for(User user : adminUsers) {
			adminEmail.add(user.getEmail());
		}
		
		adminGenericEmail.sendAdminEmail(adminEmail, content);		
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public AccountActivationEmail getAccountActivationEmail() {
		return email;
	}

	public void setAccountActivationEmail(AccountActivationEmail email) {
		this.email = email;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	public void setLeaveRequestDao(LeaveRequestDao leaveRequestDao) {
		this.leaveRequestDao = leaveRequestDao;
	}


	public void setWorkFromHomeDao(WorkFromHomeDao workFromHomeDao) {
		this.workFromHomeDao = workFromHomeDao;
	}


	public void setBankHolidayWorkingDao(BankHolidayWorkingDao bankHolidayWorkingDao) {
		this.bankHolidayWorkingDao = bankHolidayWorkingDao;
	}

	
	public void setSkillsDao(SkillsDao skillsDao) {
		this.skillsDao = skillsDao;
	}

	public void setUserHolidaysDao(UserHolidaysDao userHolidaysDao) {
		this.userHolidaysDao = userHolidaysDao;
	}

	public void setCalculateLeaves(CalculateLeaves calculateLeaves) {
		this.calculateLeaves = calculateLeaves;
	}


	public void setWeekendWorkingDao(WeekendWorkingDao weekendWorkingDao) {
		this.weekendWorkingDao = weekendWorkingDao;
	}

	public void setExperienceDao(ExperienceDao experienceDao) {
		this.experienceDao = experienceDao;
	}

	public void setAdminGenericEmail(AdminGenericEmail adminGenericEmail) {
		this.adminGenericEmail = adminGenericEmail;
	}

	

	

	
}
