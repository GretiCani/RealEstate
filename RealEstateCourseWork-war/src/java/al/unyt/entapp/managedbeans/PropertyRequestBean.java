/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.managedbeans;

import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.RequestsForPropertyViewing;
import al.unyt.entapp.model.Sales;
import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.PropertyServiceLocal;
import al.unyt.entapp.serviceLocal.RequestPropertyServiceLocal;
import al.unyt.entapp.serviceLocal.SalesServiceLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author User
 */
@Named(value = "propertyRequestBean")
@SessionScoped
public class PropertyRequestBean implements Serializable {

    @EJB
    private RequestPropertyServiceLocal requestPropertyService;

    @EJB
    private PropertyServiceLocal propertyService;
    
    @EJB
    private SalesServiceLocal salesService;

    @Inject
    private LoginBean loginBean;
    
    
    private User currentUserInSession;
    private RequestsForPropertyViewing propertyRequest;
    private List<RequestsForPropertyViewing> propertyRequestList;
    private List<RequestsForPropertyViewing>currentBuyerPropertyRequests;
    private Property requestedProperty;
    private List<Sales> salesList;
    private Sales newSale;

    FacesMessage message = null;

    @PostConstruct
    public void doInit() {
        propertyRequestList = requestPropertyService.allRequestsForPropertyViewing();
        propertyRequest = new RequestsForPropertyViewing();
        requestedProperty = new Property();
        salesList = salesService.allSales();
        newSale = new Sales();
        currentUserInSession = loginBean.getLoggedUser();
        currentBuyerPropertyRequests = requestPropertyService.currentByerRequestForViewing(currentUserInSession);
        
    }

    public String asksForViewing(int idprop) {
        try {
            this.requestedProperty = propertyService.findProperty(idprop);
            propertyRequest.setPropertyId(requestedProperty);
            propertyRequest.setBuyerId(currentUserInSession);
            requestPropertyService.saveRequestsForPropertyViewing(propertyRequest, requestedProperty, currentUserInSession);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Request Error", "Unable to send a property viewing request at this time. Please try again later");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }
        propertyRequestList = requestPropertyService.allRequestsForPropertyViewing();
        currentBuyerPropertyRequests = requestPropertyService.currentByerRequestForViewing(currentUserInSession);
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Request Succesfully send", "One of our staff will contact you shorly to leave an appointment");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "index";

    }
    
    public String completeSale(RequestsForPropertyViewing requestedProperty){
        
       try{
        newSale.setRequestId(requestedProperty);
        newSale.setCommission(calulateCommission(requestedProperty.getPropertyId()));
        salesService.saveSales(newSale, propertyRequest);
       }catch(Exception e){
           message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Process Error", "Unable to process sale at this time. Please try again later");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "sales-list";
       }
       
       salesList = salesService.allSales();
       propertyRequestList = requestPropertyService.allRequestsForPropertyViewing();
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "Sale sucessfully completed");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "property-list";     
    }
    
     public String proccessSales(int propertyRequestId){
        try{
            this.propertyRequest = requestPropertyService.findRequestsForPropertyViewing(propertyRequestId);
        }catch(Exception e){
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Retrieving Error ", "Unable to view any property at this time");
             FacesContext.getCurrentInstance().addMessage(null, message);
             return "";
        }finally{
            return "proccess-sales";
        }
    }
    
    public double calulateCommission(Property reqProperty){
        
        return (reqProperty.getPrice() * 10)/100;
        
    }
    
    

    public PropertyRequestBean() {
    }

    public RequestsForPropertyViewing getPropertyRequest() {
        return propertyRequest;
    }

    public void setPropertyRequest(RequestsForPropertyViewing propertyRequest) {
        this.propertyRequest = propertyRequest;
    }

    public List<RequestsForPropertyViewing> getPropertyRequestList() {
        return propertyRequestList;
    }

    public void setPropertyRequestList(List<RequestsForPropertyViewing> propertyRequestList) {
        this.propertyRequestList = propertyRequestList;
    }

    public Property getRequestedProperty() {
        return requestedProperty;
    }

    public void setRequestedProperty(Property property) {
        this.requestedProperty = property;
    }

    public List<Sales> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sales> salesList) {
        this.salesList = salesList;
    }

    public List<RequestsForPropertyViewing> getCurrentBuyerPropertyRequests() {
        return currentBuyerPropertyRequests;
    }

    public void setCurrentBuyerPropertyRequests(List<RequestsForPropertyViewing> currentBuyerPropertyRequests) {
        this.currentBuyerPropertyRequests = currentBuyerPropertyRequests;
    }
    
    

}
