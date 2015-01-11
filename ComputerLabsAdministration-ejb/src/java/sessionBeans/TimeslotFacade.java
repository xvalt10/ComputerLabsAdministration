/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Classrooms;
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
public class TimeslotFacade extends AbstractFacade<Timeslot> {
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TimeslotFacade() {
        super(Timeslot.class);
    }
    
    public List<Integer> findDaysPerWeekSchoolIsOpen(){
        return em.createNativeQuery("select distinct [day] from dbo.Timeslot").getResultList();
    }
    
    public List<Timeslot> getTimeSlotsForDay(int day, Classrooms classroom){
    return em.createNativeQuery("select * from dbo.Timeslot where [day]=? and classRoomId=?",Timeslot.class)
            .setParameter(1, day)
            .setParameter(2, classroom.getClassRoomId())
            .getResultList();
    }
    
}
