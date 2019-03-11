/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.managedbeans;

import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.PropertyServiceLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author User
 */
@Named(value = "propertyBean")
@SessionScoped
public class PropertyBean implements Serializable {

    @EJB
    private PropertyServiceLocal propertyService;

    @Inject
    private LoginBean loginBean;

    private List<Property> propertyList;
    private List<Property> currentUserProperties;
    private List<Property> enabledProperties;
    private Property property;
    private User currentUserInsession;

    private UploadedFile file;

    FacesMessage message = null;
    //change this directory path before run the app
    private static final String  destinationPath = "C:/Users/User/Documents/NetBeansProjects/RealEstateCourseWork/RealEstateCourseWork-war/web/resources/images";
    

    public PropertyBean() {
    }

    @PostConstruct
    public void doInit() {
        propertyList = propertyService.allProperty();
        property = new Property();
        currentUserInsession = loginBean.getLoggedUser();
        currentUserProperties = propertyService.currentUserProperties(currentUserInsession);
        enabledProperties = propertyService.getEnabledProperty();

    }

    public String addProperty() {

        try {
            property.setId(null);
            property.setStatus("enable");
            property.setPictureUrl(this.setPicture());
            propertyService.saveProperty(property, currentUserInsession);

        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Register Error", "Unable to add property at this time. Please try again later");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }
        currentUserProperties = propertyService.currentUserProperties(currentUserInsession);
        return "property-list";
    }

    public String deleteProperty(Property selectedProperty) {
        this.property = selectedProperty;

        try {
            this.property.setStatus("disable");
            propertyService.editProperty(property);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Delete Error ", "Unable to property user at this time");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        } finally {
            currentUserProperties = propertyService.currentUserProperties(currentUserInsession);
            return "property-list";
            
        }
    }

    public String generateRandomNameForPicture() {
        byte[] byteArray = new byte[10];
        new Random().nextBytes(byteArray);
        return new String(byteArray, Charset.forName("UTF-8"));
    }

    public String setPicture() {

        String pictureName = this.generateRandomNameForPicture();
        
        String pictureId = file.getFileName();
        if (file.getSize() != 0) {
            if (new File(destinationPath, pictureId).exists()) {
                pictureId = this.generateRandomNameForPicture()+pictureId;
            }

            File destination = new File(destinationPath, pictureId);
            
            try {
                InputStream in = file.getInputstream();
                OutputStream out = new FileOutputStream(destination);
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                in.close();
                out.flush();
                out.close();
            } catch (IOException e) {
               
              
            }

        }
       return pictureId;
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
    
    
 
    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getCurrentUserInsession() {
        return currentUserInsession;
    }

    public void setCurrentUserInsession(User user) {
        this.currentUserInsession = user;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Property> getCurrentUserProperties() {
        return currentUserProperties;
    }

    public void setCurrentUserProperties(List<Property> currentUserProperties) {
        this.currentUserProperties = currentUserProperties;
    }

    public List<Property> getEnabledProperties() {
        return enabledProperties;
    }

    public void setEnabledProperties(List<Property> enabledProperties) {
        this.enabledProperties = enabledProperties;
    }
    
    

}
