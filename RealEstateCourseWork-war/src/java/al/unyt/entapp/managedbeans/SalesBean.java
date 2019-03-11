/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.managedbeans;

import al.unyt.entapp.model.Sales;
import al.unyt.entapp.serviceLocal.SalesServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author User
 */
@Named(value = "salesBean")
@SessionScoped
public class SalesBean implements Serializable {

    List<Sales> salesList;
    
    @EJB
    SalesServiceLocal salesService;
    
    @PostConstruct
    public void doInit(){
        salesList = salesService.allSales();
    }
    
    public SalesBean() {
    }

    public List<Sales> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sales> salesList) {
        this.salesList = salesList;
    }
    
    
   
    
}
