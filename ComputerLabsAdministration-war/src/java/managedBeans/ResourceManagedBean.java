/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Classrooms;
import entities.Hardware;
import entities.InstalledSoftware;
import entities.Software;
import helperClasses.JsfUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import sessionBeans.ClassroomsFacade;
import sessionBeans.HardwareFacade;
import sessionBeans.InstalledSoftwareFacade;
import sessionBeans.SoftwareFacade;

/**
 *
 * @author t.valkovic
 */
@ManagedBean
@SessionScoped
public class ResourceManagedBean {

    @EJB
    ClassroomsFacade classroomsFacade;

    @EJB
    HardwareFacade hardwareFacade;

    @EJB
    SoftwareFacade softwareFacade;

    @EJB
    InstalledSoftwareFacade installedSoftwareFacade;

    private Classrooms classroom;
    private Hardware hardware;
    private Software software;
    private InstalledSoftware softwareByComputer;

    public Classrooms getClassroom() {
        return classroom;
    }

    public void setClassroom(Classrooms classroom) {
        this.classroom = classroom;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public InstalledSoftware getSoftwareByComputer() {
        return softwareByComputer;
    }

    public void setSoftwareByComputer(InstalledSoftware softwareByComputer) {
        this.softwareByComputer = softwareByComputer;
    }

    @PostConstruct
    public void init() {
        classroom = new Classrooms();
        hardware = new Hardware();
        software = new Software();
        softwareByComputer = new InstalledSoftware();
    }

    public void addClassroom() {
        classroomsFacade.create(classroom); 
        JsfUtil.addSuccessMessage("Classroom "+classroom.getRoomNumber()+"has been successfully added.");
        classroom=new Classrooms();
        
       
    }

    public void addHardware(int classroomId) {
        hardware.setClassRoomId(classroomsFacade.find(classroomId));
        hardwareFacade.create(hardware);
        JsfUtil.addSuccessMessage("Hardware "+hardware.getType()+" has been successfully added.");
        hardware=new Hardware();
    }
    
    public List<Hardware> availableHardware(){
    return hardwareFacade.findAll();
    }
   
    public List<Software> availableSoftware(){
    return softwareFacade.findAll();
    }
    

    public void addSoftware() {
        softwareFacade.create(software);
        JsfUtil.addSuccessMessage("Software "+software.getType()+" has been successfully added.");
        software=new Software();
    }

    public void addSofwareToComputerStation(int hardwareId, int softwareId) {
        softwareByComputer.setComputerId(hardwareFacade.find(hardwareId));
        softwareByComputer.setSoftwareId(softwareFacade.find(softwareId));
        installedSoftwareFacade.create(softwareByComputer);
    }

    /**
     * Creates a new instance of ResourceManagedBean
     */
    public ResourceManagedBean() {
    }

}
