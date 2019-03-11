/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.managedbeans;



import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.PropertyServiceLocal;
import al.unyt.entapp.serviceLocal.UserServiceLocal;
import java.io.Serializable;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author User
 */
@Named(value = "startUpBean")
@SessionScoped
public class StartUpBean implements Serializable {

    private List<Property> enabledProperty;
    private List<Property>searchedProperty;
    private Property property;
    private String searchedString;
    
    FacesMessage message = null;
    
    @EJB
    private PropertyServiceLocal propertyService;
    
    @PostConstruct
    public void doInit(){
        enabledProperty = propertyService.getEnabledProperty();
    }
    
    
    public StartUpBean() {
    }

    public String listPropertySearched(){
        searchedProperty = propertyService.searchedProperties(searchedString);
        searchedString="";
    return "searched-list.xhtml";

    }
    
    public String viewProperty(int propertyId){
        try{
            this.property = propertyService.findProperty(propertyId);
        }catch(Exception e){
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Retrieving Error ", "Unable to view any property at this time");
             FacesContext.getCurrentInstance().addMessage(null, message);
             return "";
        }finally{
            return "property-view";
        }
    }
    public List<Property> getEnabledProperty() {
        return enabledProperty;
    }

    public void setEnabledProperty(List<Property> enabledProperty) {
        this.enabledProperty = enabledProperty;
    }

    public List<Property> getSearchedProperty() {
        return searchedProperty;
    }

    public void setSearchedProperty(List<Property> searchedProperty) {
        this.searchedProperty = searchedProperty;
    }

    public String getSearchedString() {
        return searchedString;
    }

    public void setSearchedString(String searchedString) {
        this.searchedString = searchedString;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    
    
    
   
}
