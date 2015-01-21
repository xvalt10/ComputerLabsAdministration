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
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import sessionBeans.ClassroomsFacade;
import sessionBeans.HardwareFacade;
import sessionBeans.InstalledSoftwareFacade;
import sessionBeans.SoftwareFacade;


@ManagedBean
@SessionScoped
public class ResourceManagedBean {

    // injection of EJBs used in the class
    @EJB
    ClassroomsFacade classroomsFacade;

    @EJB
    HardwareFacade hardwareFacade;

    @EJB
    SoftwareFacade softwareFacade;

    @EJB
    InstalledSoftwareFacade installedSoftwareFacade;

    //declaration of private variables
    private Classrooms classroom;
    private Hardware hardware;
    private Software software;
    private InstalledSoftware softwareByComputer;

    //getters and setters for private variables
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

    //initialisation of private variables
    @PostConstruct
    public void init() {
        classroom = new Classrooms();
        hardware = new Hardware();
        software = new Software();
        softwareByComputer = new InstalledSoftware();
        softwareByComputer.setComputerId(hardware);
        softwareByComputer.setSoftwareId(software);
    }

    /**
     * Method persists the classroom into the ods db and send an info message
     * when the operation was successful.
     */
    public void addClassroom() {
        classroomsFacade.create(classroom);
        JsfUtil.addSuccessMessage("Classroom " + classroom.getRoomNumber() + "has been successfully added.");
        classroom = new Classrooms();

    }
    
     /**
     * Method persists the hardware object into the db and redirects the user to the resourcesMgmt.xhtml page.
     * @param classroomId - id of the classroom where the db is physically located
     */
    public void addHardware(int classroomId) {
        hardware.setClassRoomId(classroomsFacade.find(classroomId));
        hardwareFacade.create(hardware);
        JsfUtil.addSuccessMessage("Hardware " + hardware.getType() + " has been successfully added.");
        hardware = new Hardware();
        try {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("resourcesMgmt.xhtml?tab=hardware");
        } catch (IOException ex) {
            Logger.getLogger(ResourceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method persists the software object into the db and redirects the user to the resourcesMgmt.xhtml page.
     */
    public void addSoftware() {
        softwareFacade.create(software);
        JsfUtil.addSuccessMessage("Software " + software.getType() + " has been successfully added.");
        software = new Software();
        try {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("resourcesMgmt.xhtml?tab=software");
        } catch (IOException ex) {
            Logger.getLogger(ResourceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      /**
     *  Method persists the InstalledSoftware object into the db and redirects the user to the resourcesMgmt.xhtml page.
     * @param hardwareId - id of computer where the software installed
     * @param softwareId - id of software
     */
    public void addSofwareToComputerStation(int hardwareId, int softwareId) {
        softwareByComputer.setComputerId(hardwareFacade.find(hardwareId));
        softwareByComputer.setSoftwareId(softwareFacade.find(softwareId));
        installedSoftwareFacade.create(softwareByComputer);

        try {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("resourcesMgmt.xhtml?tab=overview");
        } catch (IOException ex) {
            Logger.getLogger(ResourceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Based on the classroomId the classroom object is loaded from the db, this
     * object is later used in the updateResources webpage.
     *
     * @param classroomId
     * @return
     */
    public String loadClassroom(int classroomId) {
        classroom = classroomsFacade.find(classroomId);
        return "updateResources";

    }

    /**
     * Based on the hardwareId the hardware object is loaded from the db, this
     * object is later used in the updateResources webpage.
     *
     * @param hardwareId
     * @return
     */
    public String loadHardware(int hardwareId) {
        hardware = hardwareFacade.find(hardwareId);
        return "updateResources";
    }

    /**
     * Based on the softwareId the software object is loaded from the db, this
     * object is later used in the updateResources webpage.
     *
     * @param softwareId
     * @return
     */
    public String loadSoftware(int softwareId) {
        software = softwareFacade.find(softwareId);
        return "updateResources";

    }

    /**
     * The loaded harware object is updated in the db based on user input from
     * the updateResources page. If the update is successful, user is forwarded
     * to the resoourcesMgmt.xhtml page and the hardware tab is displayed.
     */
    public void editHardware() {
        hardwareFacade.edit(hardware);
        JsfUtil.addSuccessMessage("Hardware " + hardware.getType() + " has been successfully edited.");
        hardware = new Hardware();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("resourcesMgmt.xhtml?tab=hardware");
        } catch (IOException ex) {
            Logger.getLogger(ResourceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The loaded classroom object is updated in the db based on user input from
     * the updateResources page.
     *
     * @return 'resourcesMgmt' String to indicate that the request should be
     * redirected to the resourcesMgmt.xhtml page
     */
    public String editClassroom() {

        classroomsFacade.edit(classroom);
        JsfUtil.addSuccessMessage("Classroom " + classroom.getRoomNumber() + " has been successfully edited.");
        classroom = new Classrooms();

        return "resourcesMgmt";

    }

    /**
     * The loaded software object is updated in the db based on user input from
     * the updateResources page. If the update is successful, user is forwarded
     * to the resoourcesMgmt.xhtml page and the hardware tab is displayed.
     */
    public void editSoftware() {
        softwareFacade.edit(software);
        JsfUtil.addSuccessMessage("Software " + software.getType() + " has been successfully edited.");
        software = new Software();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("resourcesMgmt.xhtml?tab=software");
        } catch (IOException ex) {
            Logger.getLogger(ResourceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method deletes hardware record from the db based on hardwareId.
     *
     * @param hardwareId
     */
    public void deleteHardware(int hardwareId) {
        hardwareFacade.remove(hardwareFacade.find(hardwareId));
    }

    /**
     * Method deletes classroom record from the db based on hardwareId.
     *
     * @param classroomId
     */
    public void deleteClassroom(int classroomId) {
        classroomsFacade.remove(classroomsFacade.find(classroomId));
    }

    /**
     * Method deletes classroom record from the db based on hardwareId.
     *
     * @param softwareId
     */
    public void deleteSoftware(int softwareId) {
        softwareFacade.remove(softwareFacade.find(softwareId));
    }

   
/**
 * Method fetches all hardware records from the Hardware table. 
 * @return 
 */
    public List<Hardware> availableHardware() {
        return hardwareFacade.findAll();
    }

 /**
 * Method fetches all software records from the Hardware table. 
 * @return 
 */
    public List<Software> availableSoftware() {
        return softwareFacade.findAll();
    }

    /**
     * Method used to check which hardware components are located in the specified classroom and seat number.
     * @param classroomId
     * @param seatNo
     * @return list of hardware objects
     */
    public List<Hardware> getHardwarePerSeatAndClassroom(int classroomId, int seatNo) {
        return hardwareFacade.findHardwareByClassroomAndSeatNumber(classroomId, seatNo);
    }

    /**
     * Methods checks how many seats in the classroom are currently equipped with hardware.
     * @param classroomId
     * @return list of hardwareids of all the hardware components located at the specified classroom.
     */
    public List<Integer> getSeatCountPerClassroom(int classroomId) {
        return hardwareFacade.getSeatsPerClassroom(classroomId);
    }

    /**
     * Method fetches all software installed on a particular computer
     * @param computerId
     * @return list of InstalledSoftware objects
     */
    public List<InstalledSoftware> getInstalledSoftware(int computerId) {
        return installedSoftwareFacade.getSoftwareByComputerId(computerId);
    }

    /**
     * Creates a new instance of ResourceManagedBean
     */
    public ResourceManagedBean() {
    }

}
