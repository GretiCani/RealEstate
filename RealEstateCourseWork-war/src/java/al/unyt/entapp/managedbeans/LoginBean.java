/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.managedbeans;

import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.UserServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String email;
    private String password;

    private User loggedUser;

    @EJB
    private UserServiceLocal userService;

    @Inject
    private NavigationBean navigationBean;

    public String doLogin() {

        FacesMessage msg;

        try {
            loggedUser = userService.checkUser(email, password);
        } catch (Exception e) {

            msg = new FacesMessage("Login error!", "Invalid credincials or user does not exist");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return navigationBean.toLogin();

        }
        
        this.setUserInSession(loggedUser.getRoleId().getRoleName());

        if (loggedUser != null && loggedUser.getRoleId().getRoleName().equals("admin")) {
            return navigationBean.redirectToAdminIndex();
        } else if (loggedUser != null && loggedUser.getRoleId().getRoleName().equals("seller")) {
            return navigationBean.redirectToSellerIndex();
        } else if (loggedUser != null && loggedUser.getRoleId().getRoleName().equals("buyer")) {
            return navigationBean.redirectToBuyerIndex();
        }

        msg = new FacesMessage("Login error!", "Invalid credincials or user does not exist");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigationBean.toLogin();
    }

    public String doLogout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();

        return navigationBean.ridirectToIndex();
    }

    public void setUserInSession(String Role) {
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("userRole", Role);
                
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User user) {
        this.loggedUser = user;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

    public LoginBean() {
    }

}
