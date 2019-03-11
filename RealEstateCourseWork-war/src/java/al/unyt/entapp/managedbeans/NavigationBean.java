/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author User
 */
@Named(value = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

    public String ridirectToIndex() {
        return "/index.xhtml?faces-redirect=true";
    }

    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String toLogin() {
        return "/login.xhtml";
    }

    public String redirectToAdminIndex() {
        return "/admin/index.xhtml?faces-redirect=true";
    }

    public String toAdminIndex() {
        return "/admin/index.xhtml";
    }

    public String redirectToSellerIndex() {
        return "/seller/index.xhtml?faces-redirect=true";
    }

    public String toSellerIndex() {
        return "/admin/index.xhtml";
    }

    public String redirectToBuyerIndex() {
        return "/buyer/index.xhtml?faces-redirect=true";
    }

    public String toBuyerIndex() {
        return "/buyer/index.xhtml";
    }

    public NavigationBean() {

    }
    
    
}
