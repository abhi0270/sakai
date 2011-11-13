/**********************************************************************************
 * $URL: https://source.sakaiproject.org/contrib/signup/branches/2-6-x/api/src/java/org/sakaiproject/signup/model/SignupMeeting.java $
 * $Id: SignupMeeting.java 59241 2009-03-24 15:52:18Z guangzheng.liu@yale.edu $
***********************************************************************************
 *
 * Copyright (c) 2007, 2008, 2009 Yale University
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *   
 * See the LICENSE.txt distributed with this file.
 *
 **********************************************************************************/
package org.sakaiproject.signup.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.sakaiproject.signup.logic.Permission;

/**
 * <p>
 * This class holds the information for signup meeting/event. This object is
 * mapped directly to the DB storage by Hibernate
 * </p>
 */
/**
 * @author gl256
 *
 */
public class SignupMeeting implements MeetingTypes {

	private Long id;

	private Long recurrenceId;

	@SuppressWarnings("unused")
	private int version;

	private String title;

	private String description;

	private String location;
	
	private String category;

	/* sakai user id */
	private String creatorUserId;

	private Date startTime;

	private Date endTime;

	private Date signupBegins;

	private Date signupDeadline;

	private boolean canceled;

	private boolean locked;

	private String meetingType;
	
	/*once,daily,weekdays,weekly,biweekly */
	private String repeatType; 
	
	private boolean allowWaitList;
	
	private boolean allowComment;
	
	private boolean autoReminder;
	
	private boolean eidInputMode;

	private boolean receiveEmailByOwner;

	private List<SignupTimeslot> signupTimeSlots;

	private List<SignupSite> signupSites;
	
	private List<SignupAttachment> signupAttachments;

	private Permission permission;
	
	private boolean emailAttendeesOnly;
	
	private boolean allowAttendance;
	
	private boolean createGroups;

	/**
	 * check if the meeting/event is cancelled
	 * 
	 * @return true if the meeting/event is cancelled
	 */
	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * this is a setter.
	 * 
	 * @param cancel
	 *            a boolean value
	 */
	public void setCanceled(boolean cancel) {
		this.canceled = cancel;
	}
	
	/**
	 * check if the meeting/event can take attendance
	 * 
	 * @return true if the meeting/event can take attendance
	 */
	public boolean isAllowAttendance() {
		return allowAttendance;
	}

	/**
	 * this is a setter.
	 * 
	 * @param allowAttendance
	 *            a boolean value
	 */
	public void setAllowAttendance(boolean allowAttendance) {
		this.allowAttendance = allowAttendance;
	}

	/**
	 * get the sakai's unique internal user Id for the event/meeting creator
	 * 
	 * @return an unique internal user Id
	 */
	public String getCreatorUserId() {
		return creatorUserId;
	}

	/**
	 * this is a setter.
	 * 
	 * @param creatorId
	 *            an unique internal user Id
	 */
	public void setCreatorUserId(String creatorId) {
		this.creatorUserId = creatorId;
	}

	/**
	 * get the description of the event/meeting
	 * 
	 * @return a description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * this is a setter.
	 * 
	 * @param description
	 *            a description of an event/meeting
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get the end time of an event/meeting
	 * 
	 * @return an end time of the event/meeting
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * this is a setter.
	 * 
	 * @param endTime
	 *            the end time of the event/meeting
	 */
	public void setEndTime(Date endTime) {
		this.endTime = truncateSeconds(endTime);
	}

	/**
	 * get the unique meeting/event Id, which is generated by DB
	 * 
	 * @return an unique sequence Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * this is a setter.
	 * 
	 * @param id
	 *            an unique sequence Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * check if the event/meeting is locked
	 * 
	 * @return true if the event/meeting is locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * this is a setter.
	 * 
	 * @param locked
	 *            a boolean value
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * This method obtains the number of time slots in the event/meeting
	 * 
	 * @return the number of time slots
	 */
	public int getNoOfTimeSlots() {
		return (signupTimeSlots == null) ? 0 : signupTimeSlots.size();
	}

	/**
	 * get the recurrence Id of the event/meeting
	 * 
	 * @return the recurrence Id
	 */
	public Long getRecurrenceId() {
		return recurrenceId;
	}

	/**
	 * this is a setter.
	 * 
	 * @param recurrenceId
	 *            a unique Id
	 */
	public void setRecurrenceId(Long recurrenceId) {
		this.recurrenceId = recurrenceId;
	}

	/**
	 * get the signup starting time for the event/meeting
	 * 
	 * @return a starting time for signup
	 */
	public Date getSignupBegins() {
		return signupBegins;
	}

	/**
	 * this is a setter
	 * 
	 * @param signupBegins
	 *            a time when the signup process starts
	 */
	public void setSignupBegins(Date signupBegins) {
		this.signupBegins = truncateSeconds(signupBegins);
	}

	/**
	 * get the time when the signup process stops
	 * 
	 * @return a date of the signup deadline
	 */
	public Date getSignupDeadline() {
		return signupDeadline;
	}

	/**
	 * this is a setter
	 * 
	 * @param signupDeadLine
	 *            the time when signup process stops
	 */
	public void setSignupDeadline(Date signupDeadLine) {
		this.signupDeadline = truncateSeconds(signupDeadLine);
	}

	/**
	 * get the starting time of the event/meeting
	 * 
	 * @return the starting date of the event/meeting
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * this is a setter
	 * 
	 * @param startTime
	 *            the time when the event/meeting starts
	 */
	public void setStartTime(Date startTime) {
		this.startTime = truncateSeconds(startTime);
	}

	/**
	 * get the name of the event/meeting
	 * 
	 * @return the name of the event/meeting
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * this is a setter
	 * 
	 * @param title
	 *            a name of the event/meeting
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * get a list of SignupSite objects, which indicate the scope of the
	 * event/meeting
	 * 
	 * @return a list of SignupSite objects,
	 */
	public List<SignupSite> getSignupSites() {
		return signupSites;
	}

	/**
	 * this is a setter
	 * 
	 * @param signupSites
	 *            a SignupSite object
	 */
	public void setSignupSites(List<SignupSite> signupSites) {
		this.signupSites = signupSites;
	}

	/**
	 * get a list of SignupTimeslot objects, which contains the event/meeting
	 * segments information
	 * 
	 * @return a list of SignupTimeslot objects
	 */
	public List<SignupTimeslot> getSignupTimeSlots() {
		return signupTimeSlots;
	}

	/**
	 * this is a setter.
	 * 
	 * @param signupTimeSlots
	 *            a list of SignupTimeslot objects
	 */
	public void setSignupTimeSlots(List<SignupTimeslot> signupTimeSlots) {
		this.signupTimeSlots = signupTimeSlots;
	}

	/**
	 * get the location, where the event/meeting occurs
	 * 
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * this is a setter.
	 * 
	 * @param location
	 *            a location, where the event/meeting occurs
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * get the category of the event/meeting
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * this is a setter.
	 * 
	 * @param category
	 *            a category, the type of event/meeting
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	/**
	 * get the Permission object, which contains the user's operation
	 * permissions on this event/meeting
	 * 
	 * @return a Permission object
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * this is a setter
	 * 
	 * @param permission
	 *            a permission object
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * get the event/meeting type (individual,group and announcement)
	 * 
	 * @return one of the type (individual,group and announcement)
	 */
	public String getMeetingType() {
		return meetingType;
	}

	/**
	 * this is a setter
	 * 
	 * @param meetingType
	 *            a defined meeting type (individual,group and announcement)
	 */
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	/**
	 * check if the organizer of the event/meeting should receive email
	 * notification
	 * 
	 * @return true if the organizer of the event/meeting should receive email
	 *         notification
	 */
	public boolean isReceiveEmailByOwner() {
		return receiveEmailByOwner;
	}

	/**
	 * this is a setter.
	 * 
	 * @param receiveEmailByOwner
	 *            a boolean value
	 */
	public void setReceiveEmailByOwner(boolean receiveEmailByOwner) {
		this.receiveEmailByOwner = receiveEmailByOwner;
	}

	/**
	 * get the maximum nubmer of the attendees, which is allowed in one time
	 * slot
	 * 
	 * @return the maximum nubmer of the attendees
	 */
	public int getMaxNumberOfAttendees() {
		if (signupTimeSlots == null || signupTimeSlots.isEmpty())
			return 0;

		return signupTimeSlots.get(0).getMaxNoOfAttendees();
	}

	/**
	 * test if two event/meeting objects are equal
	 */
	public boolean equals(Object object) {
		if (object == null || !(object instanceof SignupMeeting))
			return false;
		SignupMeeting other = (SignupMeeting) object;

		if (id == null)
			return false;

		return id.equals(other.getId());
	}

	public int hashCode() {
		return title.hashCode() + 2 * description.hashCode();
	}

	/**
	 * This method will obtain the SignupTimeslot object according to the
	 * timeslot Id
	 * 
	 * @param timeslotId
	 *            a timeslot Id
	 * @return a SignupTimeslot object
	 */
	public SignupTimeslot getTimeslot(Long timeslotId) {
		if (signupTimeSlots == null)
			return null;

		for (SignupTimeslot timeslot : signupTimeSlots) {
			if (timeslot.getId().equals(timeslotId))
				return timeslot;
		}

		return null;
	}

	/**
	 * This method will check if the event/meeting is already expired
	 * 
	 * @return true if the event/meeting is expired
	 */
	public boolean isMeetingExpired() {
		Date today = new Date();
		// pastMeeting => today>endDate => value>0
		int value = today.compareTo(endTime);
		return value > 0;
	}

	/**
	 * This method will check if the current time has already passed the signup
	 * deadline
	 * 
	 * @return true if the current time has already passed the signup deadline
	 */
	public boolean isPassedDeadline() {
		Date today = new Date();
		int value = today.compareTo(signupDeadline);
		return value > 0;
	}

	private Calendar cal = Calendar.getInstance();

	/**
	 * This will test if the event/meeting is cross days
	 * 
	 * @return true if the event/meeting is cross days
	 */
	public boolean isMeetingCrossDays() {
		cal.setTime(getStartTime());
		int startingDay = cal.get(Calendar.DAY_OF_YEAR);
		cal.setTime(getEndTime());
		int endingDay = cal.get(Calendar.DAY_OF_YEAR);
		return (startingDay != endingDay);
	}

	/**
	 * This will test if the event/meeting is started to sign up
	 * 
	 * @return true if the sign-up begin time is before current time.
	 */
	public boolean isStartToSignUp() {
		return signupBegins.before(new Date());
	}

	/**
	 * Set the second value to zero. it only need to accurate to minutes level.
	 * Otherwise it may cause one minute shorter display confusion
	 * 
	 * @param time
	 *            a Date object
	 * @return a Date object
	 */
	private Date truncateSeconds(Date time) {
		/* set second to zero */
		if (time == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public boolean isRecurredMeeting() {
		if (recurrenceId != null || DAILY.equals(getRepeatType()) || WEEKLY.equals(getRepeatType())
				|| BIWEEKLY.equals(getRepeatType()) || WEEKDAYS.equals(getRepeatType()))
			return true;
		else
			return false;
	}

	private Date repeatUntil;

	/**
	 * It's a getter.
	 * 
	 * @return a Date object
	 */
	public Date getRepeatUntil() {
		return repeatUntil;
	}

	/**
	 * It's a setter.
	 * 
	 * @param r
	 *            a Date object
	 */
	public void setRepeatUntil(Date r) {
		repeatUntil = r;
	}
	
	//numbers of occurrences
	private int repeatNum;	
	public int getRepeatNum() {
		return repeatNum;
	}

	public void setRepeatNum(int repeatNum) {
		this.repeatNum = repeatNum;
	}

	/**
	 * It's a getter for UI or email part.
	 * 
	 * @return a String type like daily, weekly etc.
	 */
	public String getRepeatType() {
		return repeatType;
	}

	/**
	 * It's a setter.
	 * 
	 * @param r
	 *            a String object such as daily, weekly etc.
	 */
	public void setRepeatType(String r) {
		repeatType = r;
	}

	private boolean applyToAllRecurMeetings;

	/**
	 * see if recurring meetings are identical for assigned participants. It is
	 * used for email part.
	 * 
	 * @return
	 */
	public boolean isApplyToAllRecurMeetings() {
		return applyToAllRecurMeetings;
	}

	/**
	 * It's a setter.
	 * 
	 * @param applyToAllRecurMeetings
	 *            a boolean value
	 */
	public void setApplyToAllRecurMeetings(boolean applyToAllRecurMeetings) {
		this.applyToAllRecurMeetings = applyToAllRecurMeetings;
	}
	
	/* For RESTful case to pass siteId for email*/
	private String currentSiteId;

	public String getCurrentSiteId() {
		return currentSiteId;
	}

	public void setCurrentSiteId(String currentSiteId) {
		this.currentSiteId = currentSiteId;
	}

	public boolean isAllowWaitList() {
		return allowWaitList;
	}

	public void setAllowWaitList(boolean allowWaitList) {
		this.allowWaitList = allowWaitList;
	}

	public boolean isAllowComment() {
		return allowComment;
	}

	public void setAllowComment(boolean allowComment) {
		this.allowComment = allowComment;
	}

	public boolean isAutoReminder() {
		return autoReminder;
	}

	public void setAutoReminder(boolean autoReminder) {
		this.autoReminder = autoReminder;
	}	

	public boolean isEidInputMode() {
		return eidInputMode;
	}

	public void setEidInputMode(boolean eidInputMode) {
		this.eidInputMode = eidInputMode;
	}

	public List<SignupAttachment> getSignupAttachments() {
		return signupAttachments;
	}

	public void setSignupAttachments(List<SignupAttachment> signupAttachments) {
		this.signupAttachments = signupAttachments;
	}
	
	public boolean hasSignupAttachments(){
		if(this.signupAttachments ==null || this.signupAttachments.isEmpty())
			return false;
		else
			return true;
	}
	
	private boolean inMultipleCalendarBlocks = false;

	public boolean isInMultipleCalendarBlocks() {
		return inMultipleCalendarBlocks;
	}

	public void setInMultipleCalendarBlocks(boolean inMultipleCalendarBlocks) {
		this.inMultipleCalendarBlocks = inMultipleCalendarBlocks;
	}

	public boolean isEmailAttendeesOnly() {
		return emailAttendeesOnly;
	}

	public void setEmailAttendeesOnly(boolean emailAttendeesOnly) {
		this.emailAttendeesOnly = emailAttendeesOnly;
	}
	
	public boolean isCreateGroups() {
		return createGroups;
	}

	
	public void setCreateGroups(boolean createGroups) {
		this.createGroups = createGroups;
	}
	
}
