/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Schedule;
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
    
}
