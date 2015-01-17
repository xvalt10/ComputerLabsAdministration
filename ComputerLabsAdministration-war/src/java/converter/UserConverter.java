/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entities.Users;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionBeans.UserRolesFacade;
import sessionBeans.UsersFacade;

/**
 *
 * @author Tomas
 */
@FacesConverter("userConverter")
public class UserConverter implements Converter {
    
    @EJB
    private UsersFacade userFacade;
    

    /**
     * Creates a new instance of userCategoryConverter
     */
    

    public UserConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Users user=(Users)userFacade.find(Integer.parseInt(value)); 
        return user;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
