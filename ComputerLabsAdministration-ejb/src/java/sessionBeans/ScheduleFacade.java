/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Schedule;
import entities.Timeslot;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class ScheduleFacade extends AbstractFacade<Schedule> {
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScheduleFacade() {
        super(Schedule.class);
    }
    
    public Integer findLabByTimeslot(Timeslot timeslot){
        Schedule schedule;
       try{
        schedule = (Schedule) em.createNativeQuery("select * from Schedule where approvalStatus in ('Pending','Approved') and timeslotId=?", Schedule.class).setParameter(1, timeslot.getTimeslotId()).getSingleResult();
       }catch(NoResultException e){
       return null;
       }
        return schedule.getLabId().getLabId();
    }
    
    public List<Schedule> findPendingLabRequests(){
    return em.createNamedQuery("Schedule.findByApprovalStatus",Schedule.class).setParameter("approvalStatus", "Pending").getResultList();
    
    }
    
    public String findLabnameByTimeslot(Timeslot slot){
        try{
        return (String) em.createNativeQuery("select labName from ComputerLabs c join Schedule s on c.labId=s.labId where s.timeslotId=?").setParameter(1, slot.getTimeslotId()).getSingleResult();
        }catch(NoResultException e){
        return "";
        }
    }
    
      public String findLabApprovalStatusByTimeslot(Timeslot slot){
        try{
        return (String) em.createNativeQuery("select approvalStatus from ComputerLabs c join Schedule s on c.labId=s.labId where s.timeslotId=?").setParameter(1, slot.getTimeslotId()).getSingleResult();
        }catch(NoResultException e){
        return "";
        }
    }
    
  
}
