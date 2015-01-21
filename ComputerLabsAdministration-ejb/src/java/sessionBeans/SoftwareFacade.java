/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Hardware;
import entities.Software;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class SoftwareFacade extends AbstractFacade<Software> {
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SoftwareFacade() {
        super(Software.class);
    }
    
     public List<Software> findSoftwareWithExpiredLicence(){ 
       return em.createNativeQuery("select * from Software where licenceExpirationDate< GETDATE()-7").getResultList();
    }
}
