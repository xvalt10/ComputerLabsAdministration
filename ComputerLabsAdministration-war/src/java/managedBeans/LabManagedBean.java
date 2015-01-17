/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Classrooms;
import entities.ComputerLabs;
import entities.Schedule;
import entities.Timeslot;
import entities.Users;
import helperClasses.JsfUtil;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import sessionBeans.ClassroomsFacade;
import sessionBeans.ComputerLabsFacade;
import sessionBeans.ScheduleFacade;
import sessionBeans.TimeslotFacade;
import sessionBeans.UsersFacade;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class LabManagedBean {

    //injection of session beans used for CRUD operations on the DB
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private ScheduleFacade scheduleFacade;
    @EJB
    private ComputerLabsFacade computerLabsFacade;
    @EJB
    private ClassroomsFacade classroomsFacade;
    @EJB
    private TimeslotFacade timeslotFacade;

    //declaration of variables
    private ComputerLabs computerLab;
    private Schedule schedule;
    private Timeslot timeSlot;
    private Users instructor;

    public Users getInstructor() {
        return instructor;
    }

    public void setInstructor(Users instructor) {
        this.instructor = instructor;
    }

    public Timeslot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Timeslot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void selectTimeSlot(AjaxBehaviorEvent event, Timeslot slot) {
        this.timeSlot = slot;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public ComputerLabs getComputerLab() {
        return computerLab;
    }

    public void setComputerLab(ComputerLabs computerLab) {
        this.computerLab = computerLab;
    }

    /**
     * Returns String representation of day which is stored as int in the DB
     *
     * @param day
     * @return String representation of day
     */
    public String getDayOfWeek(int day) {
        String dayOfWeek = null;
        switch (day) {
            case 1:
                dayOfWeek = "Monday";
                break;
            case 2:
                dayOfWeek = "Tuesday";
                break;
            case 3:
                dayOfWeek = "Wednesday";
                break;
            case 4:
                dayOfWeek = "Thursday";
                break;
            case 5:
                dayOfWeek = "Friday";
                break;
            case 6:
                dayOfWeek = "Saturday";
                break;
            case 7:
                dayOfWeek = "Sunday";
                break;
            default:
                dayOfWeek = "";

        }
        return dayOfWeek;

    }

    public void saveSchedule(int labId, int timeslotId) {

        schedule = new Schedule();
        computerLab = new ComputerLabs();

    }

    /**
     * Used to return concrete days when computerlabs are ongoing in a
     * particular classroom
     *
     * @param classroomId
     * @return List of integer representation of days (1-Monday...7-Sunday)
     */
    public List<Integer> numberOfDaysSchoolIsOpened(int classroomId) {
        return timeslotFacade.findDaysPerWeekSchoolIsOpen(classroomId);
    }

    /**
     * Fetches all timeslots created for a particular day and classroom
     *
     * @param day
     * @param classroomId
     * @return List of timeslots
     */
    public List<Timeslot> timeslotsPerDay(int day, int classroomId) {
        Classrooms classRoom = classroomsFacade.find(classroomId);
        return timeslotFacade.getTimeSlotsForDay(day, classRoom);
    }

    /**
     * Returns list of classrooms where computer labs are held
     *
     * @return List of Clasrooms objects
     */
    public List<Classrooms> getAvailableClassrooms() {
        return classroomsFacade.findAll();
    }

    /**
     * Persists a lab request into the db. If the timeslot the user selected is
     * no longer available, it returns an error message.
     */
    public void saveLabRequest() {
        if (timeSlot.getIsOccupied()) {
            JsfUtil.addErrorMessage("The slot you selected is occupied. Select a different one");

        } else {
            timeSlot.setIsOccupied(true);
            timeslotFacade.edit(timeSlot);
            computerLabsFacade.create(computerLab);
            schedule.setApprovalStatus("Pending");
            schedule.setLabId(computerLab);
            schedule.setTimeslotId(timeSlot);
            scheduleFacade.create(schedule);

            JsfUtil.addSuccessMessage("The request has been successfully submitted.");
        }

    }

    /**
     * Fetches all pending lab requests from the db
     * @return list of schedule objects with status - 'Pending'
     */
    public List<Schedule> getPendingLabrequests() {
        return scheduleFacade.findPendingLabRequests();
    }

    /**
     * Initializes variables used in the class
     */
    @PostConstruct
    public void init() {
        computerLab = new ComputerLabs();
        schedule = new Schedule();
        instructor = new Users();
        computerLab.setInstructor(instructor);
    }

    /**
     * Formats the date of a timeslot to (HH:mm - HH:mm)
     * @param slot
     * @return 
     */
    public String formatTimeFromTimeslot(Timeslot slot) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(slot.getStartTime()) + "-" + timeFormat.format(slot.getEndTime());
    }

    /**
     * Used by admin to approve submitted lab request
     * @param scheduleId 
     */
    public void approveLabRequest(int scheduleId) {
        schedule = scheduleFacade.find(scheduleId);
        schedule.setApprovalStatus("Approved");
        schedule.getTimeslotId().setIsOccupied(true);
        scheduleFacade.edit(schedule);
        Timeslot timeslot = schedule.getTimeslotId();
        JsfUtil.addSuccessMessage("Request for the timeslot " + getDayOfWeek(timeslot.getDay()) + " " + formatTimeFromTimeslot(timeslot) + "(Room no." + timeslot.getClassRoomId().getRoomNumber() + ") has been approved for the computer lab " + schedule.getLabId().getLabName() + ".");

    }

    /**
     * Used by admin to reject submitted lab request
     * @param scheduleId 
     */
    public void rejectLabRequest(int scheduleId) {
        schedule = scheduleFacade.find(scheduleId);
        schedule.setApprovalStatus("Rejected");
        schedule.getTimeslotId().setIsOccupied(false);
        timeslotFacade.edit(schedule.getTimeslotId());
        scheduleFacade.edit(schedule);
        Timeslot timeslot = schedule.getTimeslotId();
        JsfUtil.addErrorMessage("Request for the timeslot " + getDayOfWeek(timeslot.getDay()) + " " + formatTimeFromTimeslot(timeslot) + "(Room no." + timeslot.getClassRoomId().getRoomNumber() + ") has been rejected for the computer lab " + schedule.getLabId().getLabName() + ".");

    }

    public List<ComputerLabs> getAllComputerLabs(){
    return computerLabsFacade.findAll();
    }
    /**
     * Creates a new instance of LabManagedBean
     */
    public LabManagedBean() {

    }

}
