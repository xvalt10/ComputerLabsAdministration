/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Hardware;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class HardwareFacade extends AbstractFacade<Hardware> {
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HardwareFacade() {
        super(Hardware.class);
    }
    
    public List<Integer> getSeatsPerClassroom(int classroomId){
    List<Integer> listOfSeats=  em.createNativeQuery("select distinct(seatNo) from Hardware where classroomId=? and  seatNo is not null")
             .setParameter(1, classroomId).getResultList();
    return listOfSeats;
    }
    
    public List<Hardware> findHardwareByClassroomAndSeatNumber(int classroomId, int seatNo){
        List resultList = em.createNativeQuery("select * from Hardware where classroomId =? and seatNo=?",Hardware.class)
                .setParameter(1, classroomId)
                .setParameter(2, seatNo)
                .getResultList();
        
        return resultList;
    }
    
}
