/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionBeans.UserRolesFacade;

/**
 *
 * @author Tomas
 */
@FacesConverter("userCategoryConverter")
public class userCategoryConverter implements Converter {
    
    @EJB
    private UserRolesFacade userRolesFacade;
    

    /**
     * Creates a new instance of userCategoryConverter
     */
    public userCategoryConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return userRolesFacade.find(Integer.parseInt(value)); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
