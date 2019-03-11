/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.managedbeans;

import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.UserServiceLocal;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author User
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    UserServiceLocal userService;
    
     @Inject
     LoginBean loginBean;

    List<User> sellerList;
    List<User> buyerList;
    List<Property>currentUserProperties;

    User selectedUser;

    FacesMessage msg = null;

    Iterator userListIterator;

    @PostConstruct
    public void doInt() {
        sellerList = userService.allSellers();
        buyerList = userService.allBuyers();

    }

    public void deleteSelectedUser(User thisUser) {

        selectedUser = thisUser;

        try {
            userService.deleteUser(selectedUser);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Delete Error ", "Unable to delete user at this time");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } finally {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Done", " User  no longer exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            try {
                //repopulate Lists for updating datatable after delete an user
                buyerList = userService.allBuyers();
                sellerList = userService.allSellers();
            } catch (Exception e) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reload Error", " Unable to reload data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

    }

    public String viewSelectedUser(String email) {

        try {
            selectedUser = userService.findUser(email);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Retrieving Error ", "Unable to retrieve user at this time, try again later");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "";
        } finally {

            return "edit-user";

        }
    }

    public String updateUser() {

        try {
            selectedUser = userService.editUser(selectedUser);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Error ", "Unable to update user at this time, try again later");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "";
        } finally {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Done", " User sucessfully updated");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            try {
                //repopulate Lists for updating datatable after delete an user
                buyerList = userService.allBuyers();
                sellerList = userService.allSellers();
            } catch (Exception e) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reload Error", " Unable to reload data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            return "index";
        }

    }


    public List<User> getSellerList() {
        return sellerList;
    }

    public void setSellerList(List<User> sellerList) {
        this.sellerList = sellerList;
    }

    public List<User> getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List<User> buyerList) {
        this.buyerList = buyerList;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

}
