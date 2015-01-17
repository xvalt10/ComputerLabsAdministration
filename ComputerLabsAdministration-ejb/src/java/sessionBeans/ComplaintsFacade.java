/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Complaints;
import entities.Users;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class ComplaintsFacade extends AbstractFacade<Complaints> {
    
    @EJB
    private UsersFacade usersFacade;
    
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComplaintsFacade() {
        super(Complaints.class);
    }
    
    public List<Complaints> findSubmittedComplaintsByUser(){
        String usernameOfUserSignedIn=FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        Users user=usersFacade.getUserByUsername(usernameOfUserSignedIn);
        return em.createNamedQuery("Complaints.findBySubmittedBy",Complaints.class).setParameter("submittedBy", user).getResultList();
    }
    
    public List<Complaints> findComplaintsAssignedToUser(){
        String usernameOfUserSignedIn=FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        Users user=usersFacade.getUserByUsername(usernameOfUserSignedIn);
        return em.createNamedQuery("Complaints.findByAssignedTo",Complaints.class).setParameter("assignedTo", user).getResultList();
    }
    
}
