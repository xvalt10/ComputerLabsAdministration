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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    public void init(){
        classroom=new Classrooms();
        hardware=new Hardware();
        software=new Software();
        softwareByComputer=new InstalledSoftware();
    }
    
    public void addClassroom(){
    classroomsFacade.create(classroom);
    }
    
    public void addHardware(){
    hardware.setClassRoomId(classroomsFacade.find(classroom.getClassRoomId()));
    hardwareFacade.create(hardware);
    }
    
    public void addSoftware(){
    softwareFacade.create(software);
    }
    
    /**
     * Creates a new instance of ResourceManagedBean
     */
    public ResourceManagedBean() {
    }


    
    
}
