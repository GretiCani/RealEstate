/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.managedbeans;

import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.UserServiceLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean {

    User user;
    
    @EJB
    UserServiceLocal userService;
    
    private String userRole;
    
    @PostConstruct
    public void doInit(){
        user = new User();
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String register() {

        FacesMessage message = null;

        if (userService.findUserByEmail(user.getEmail()) != null) {

            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Register Error", "User with this e-mail address already exist, try different e-mail address");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";

        } else if (userRole == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Register Error", "A user should be an seller or buyer. Please make your choice");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        } else {

            userService.saveUser(user, userRole);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Register Done", "You were registered successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login";

        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RegisterBean() {
    }

}
