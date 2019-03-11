/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.serviceLocal;

import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface PropertyServiceLocal {
    
    public List<Property> allProperty();
    public List<Property> currentUserProperties(User currentUserInSession);
    public List<Property> getEnabledProperty();
    public List<Property> searchedProperties(String searchedString);
    public Property findProperty(int id);
    public Property saveProperty(Property property,User userFromSession);
    public Property deleteProperty(Property property);
    public Property editProperty(Property property);
            
    
}
