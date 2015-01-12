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
import java.time.DayOfWeek;
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
    
    public void selectTimeSlot(AjaxBehaviorEvent event,Timeslot slot){
    this.timeSlot=slot;
    }

    public String getDayOfWeek(int day) {
        String dayOfWeek = null;
        switch (day) {
            case 1:
                dayOfWeek= "Monday";break;
            case 2:
                dayOfWeek= "Tuesday";break;
            case 3:
                dayOfWeek= "Wednesday";break;
            case 4:
                dayOfWeek= "Thursday";break;
            case 5:
                dayOfWeek= "Friday";break;
            case 6:
                dayOfWeek= "Saturday";break;
            case 7:
                dayOfWeek= "Sunday";break;
            default:dayOfWeek="";

        }
        return dayOfWeek;
        
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    public void saveSchedule(int labId, int timeslotId){
        
        schedule=new Schedule();
        computerLab=new ComputerLabs();
     
    
    }
    

    public ComputerLabs getComputerLab() {
        return computerLab;
    }

    public void setComputerLab(ComputerLabs computerLab) {
        this.computerLab = computerLab;
    }

    public List<Integer> numberOfDaysSchoolIsOpened() {
        return timeslotFacade.findDaysPerWeekSchoolIsOpen();
    }

    public List<Timeslot> timeslotsPerDay(int day, int classroomId) {
        Classrooms classRoom = classroomsFacade.find(classroomId);
        return timeslotFacade.getTimeSlotsForDay(day, classRoom);
    }
    
        
    public void saveLabRequest(){
        if ( timeSlot.getIsOccupied()){
        JsfUtil.addErrorMessage("The slot you selected is occupied. Select a different one");
        
        }
        else{
   
    timeSlot.setIsOccupied(true);
    timeslotFacade.edit(timeSlot);
    computerLabsFacade.create(computerLab);
    schedule.setApprovalStatus("Pending");
    schedule.setLabId(computerLab);
    schedule.setTimeslotId(timeSlot);
    scheduleFacade.create(schedule);
    
    JsfUtil.addSuccessMessage("The request has been successfully submitted.");}
    
    }

    public List<Schedule> getPendingLabrequests(){
    return scheduleFacade.findPendingLabRequests();
    }
    
    @PostConstruct
    public void init() {
        computerLab = new ComputerLabs();
        schedule=new Schedule();
        instructor=new Users();
        computerLab.setInstructor(instructor);
    }
    
    public String formatTimeFromTimeslot(Timeslot slot){
        SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm");
    return timeFormat.format(slot.getStartTime())+"-"+timeFormat.format(slot.getEndTime());
    }

    /**
     * Creates a new instance of LabManagedBean
     */
    public LabManagedBean() {
        
        
    }

}
