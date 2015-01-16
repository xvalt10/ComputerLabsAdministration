/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Hardware;
import entities.InstalledSoftware;
import entities.Software;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tomas
 */
@Stateless
public class InstalledSoftwareFacade extends AbstractFacade<InstalledSoftware> {
    @EJB
    private HardwareFacade hardwareFacade;
    
    
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstalledSoftwareFacade() {
        super(InstalledSoftware.class);
    }
    
    public List<InstalledSoftware> getSoftwareByComputerId(int computerId){
        Hardware computer=hardwareFacade.find(computerId);
        List resultList = em.createNamedQuery("InstalledSoftware.findByComputerId",InstalledSoftware.class).setParameter("computerId", computer).getResultList();
        return resultList;
    }
    
}
