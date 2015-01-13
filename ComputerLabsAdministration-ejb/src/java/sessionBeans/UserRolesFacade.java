/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.UserRoles;
import entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomas
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {
    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    

    public UserRolesFacade() {
        super(UserRoles.class);
    }
    
    public String getUserRoleByUsername(String username){
        Users user= (Users) em.createNamedQuery("Users.findByUsername",Users.class).setParameter("username", username).getSingleResult();
        return user.getRoleId().getRoleName();
    }
    
}
