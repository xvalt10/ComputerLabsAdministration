/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import com.sun.xml.ws.security.impl.policy.Constants;
import entities.Hardware;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

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
    
    /**
     * Method fetches records from Hardware if the warranty of the HW expires in less than one week
     * @return list of hardware where the warranty will expire in less than one week
     */
    public List<Hardware> findHardwareWithExpiredWarranty(){

//        
       return em.createNativeQuery("select * from Hardware where expirationOfWarranty<GETDATE()",Hardware.class).getResultList();
        
    }
   //        CriteriaBuilder cb=getEntityManager().getCriteriaBuilder();
//    javax.persistence.criteria.CriteriaQuery cq = cb.createQuery(Hardware.class);
//    Root<Hardware> records=cq.from(Hardware.class);
//    Metamodel m=em.getMetamodel();
//    EntityType<Hardware> Hardware_=m.entity(Hardware.class);
//    Date currentDate=new Date();
//    cq.where(cb.gt(records.get(Hardware_.getAttribute("warrantyExpirationDate")),new Date()));
}
