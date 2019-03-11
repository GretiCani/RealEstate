/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.service;

import al.unyt.entapp.daoI.PropertyI;
import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.PropertyServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author User
 */
@WebService(serviceName = "PropertyService")
@Stateless()
public class PropertyService implements PropertyServiceLocal {

    @EJB
    private PropertyI ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "allProperty")
    @Override
    public List<Property> allProperty() {
        return ejbRef.allProperty();
    }

    @WebMethod(operationName = "findProperty")
    @Override
    public Property findProperty(@WebParam(name = "id") int id) {
        return ejbRef.findProperty(id);
    }

    @WebMethod(operationName = "saveProperty")
    @Override
    public Property saveProperty(@WebParam(name = "property") Property property, @WebParam(name = "userFromSession") User userFromSession) {
        return ejbRef.saveProperty(property, userFromSession);
    }

    @WebMethod(operationName = "deleteProperty")
    @Override
    public Property deleteProperty(@WebParam(name = "property") Property property) {
        return ejbRef.deleteProperty(property);
    }

    @WebMethod(operationName = "editProperty")
    @Override
    public Property editProperty(@WebParam(name = "property") Property property) {
        return ejbRef.editProperty(property);
    }

    @WebMethod(operationName = "currentUserProperties")
    @Override
    public List<Property> currentUserProperties(User currentUserInSession) {
        return ejbRef.currentUserProperties(currentUserInSession);
    }

    @WebMethod(operationName = "getEnabledProperty")
    @Override
    public List<Property> getEnabledProperty() {
        return ejbRef.getEnabledProperty();
    }
    
    @WebMethod(operationName = "searchedProperties")
    @Override
    public List<Property> searchedProperties(String searchedString) {
          
        return ejbRef.searchedProperties(searchedString);
    }

}
