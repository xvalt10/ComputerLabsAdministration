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
    
    public int findLabByTimeslot(Timeslot timeslot){
        Schedule schedule = em.createNamedQuery("Schedule.findByTimeslotId", Schedule.class).setParameter("timeslotId", timeslot).getSingleResult();
        return schedule.getLabId().getLabId();
    }
    
    public List<Schedule> findPendingLabRequests(){
    return em.createNamedQuery("Schedule.findByApprovalStatus",Schedule.class).setParameter("approvalStatus", "Pending").getResultList();
    
    }
    
}
