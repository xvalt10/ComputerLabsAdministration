/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.UserRoles;
import entities.Users;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @EJB
    private UserRolesFacade userRolesFacade;
    
    
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
    
    public List<Users> findAllInstructors(UserRoles role){
    return em.createNamedQuery("Users.findByRoleId", Users.class).setParameter("roleId", role).getResultList();
    }
    
    public List<Users> findUsersWithRoleTechnicalStaff(){
        UserRoles role=userRolesFacade.find(3);
    return em.createNamedQuery("Users.findByRoleId", Users.class).setParameter("roleId", role).getResultList();
    }
  
}
