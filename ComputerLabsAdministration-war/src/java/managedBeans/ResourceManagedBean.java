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
        softwareByComputer.setComputerId(hardware);
        softwareByComputer.setSoftwareId(software);
    }

    public void addClassroom() {
        classroomsFacade.create(classroom); 
        JsfUtil.addSuccessMessage("Classroom "+classroom.getRoomNumber()+"has been successfully added.");
        classroom=new Classrooms();
        
       
    }
    
    public String loadClassroom(int classroomId){
        
        classroom=classroomsFacade.find(classroomId);
        
        
        return "updateResources";
    
    }   
      public String loadHardware(int hardwareId){
        
        hardware=hardwareFacade.find(hardwareId);
        
        
        return "updateResources";
    
    }
      
       public String loadSoftware(int softwareId){
        
        software=softwareFacade.find(softwareId);
        
        
        return "updateResources";
    
    }
      
      public String editHardware(){
      hardwareFacade.edit(hardware);
      JsfUtil.addSuccessMessage("Hardware "+hardware.getType()+" has been successfully edited.");
      hardware=new Hardware();
      
       return "resourcesMgmt";
      }
    
    public String editClassroom() {
       
        classroomsFacade.edit(classroom); 
        JsfUtil.addSuccessMessage("Classroom "+classroom.getRoomNumber()+" has been successfully edited.");
        classroom=new Classrooms();
        
        return "resourcesMgmt";
          
    }
    
     public String editSoftware(){
      softwareFacade.edit(software);
      JsfUtil.addSuccessMessage("Software "+software.getType()+" has been successfully edited.");
      software=new Software();
      
       return "resourcesMgmt";
      }
    
    public void deleteHardware(int hardwareId){
    hardwareFacade.remove(hardwareFacade.find(hardwareId));
    }
    
    public void deleteClassroom(int classroomId){
    classroomsFacade.remove(classroomsFacade.find(classroomId));
    }
    
      public void deleteSoftware(int softwareId){
    softwareFacade.remove(softwareFacade.find(softwareId));
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
    
    public List<Hardware> getHardwarePerSeatAndClassroom(int classroomId,int seatNo){
    return hardwareFacade.findHardwareByClassroomAndSeatNumber(classroomId, seatNo);
    }
    
    public List<Integer> getSeatCountPerClassroom(int classroomId){
    return hardwareFacade.getSeatsPerClassroom(classroomId);
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

    public List<InstalledSoftware> getInstalledSoftware(int computerId){
    return installedSoftwareFacade.getSoftwareByComputerId(computerId);
    }
    /**
     * Creates a new instance of ResourceManagedBean
     */
    public ResourceManagedBean() {
    }

}
