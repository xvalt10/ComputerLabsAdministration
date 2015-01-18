/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.SitePost;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class SitePostFacade extends AbstractFacade<SitePost> {
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SitePostFacade() {
        super(SitePost.class);
    }
    
    public List<Integer> countPostsByLabId(){
        return em.createNativeQuery("select distinct labId from SitePost").getResultList();  
        
    
    }
    
    public List<SitePost> getPostsByLabId(int labId){
    return em.createNativeQuery("select * from SitePost where labId=? order by SubmissionDate desc",SitePost.class).setParameter(1, labId).getResultList();
    }
    
}
