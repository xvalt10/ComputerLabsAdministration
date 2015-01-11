/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users getAdmin(){
    return (Users) em.createNativeQuery("select TOP 1 * from dbo.Users where roleId=4",Users.class).getSingleResult();
    }
    
    public Users getUserByUsername(String username){
    return em.createNamedQuery("Users.findByUsername",Users.class).setParameter("username", username).getSingleResult();
    }
    
    public List<Users> findInstructorsByDepartment(String department){
    return em.createNamedQuery("Users.findByDepartment", Users.class).setParameter("department", department).getResultList();
    }
    
    
    
}
