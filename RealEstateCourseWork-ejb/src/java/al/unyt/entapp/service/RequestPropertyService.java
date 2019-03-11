/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.service;

import al.unyt.entapp.daoI.RequestForPropertyViewingI;
import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.RequestsForPropertyViewing;
import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.RequestPropertyServiceLocal;
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
@WebService(serviceName = "RequestPropertyService")
@Stateless()
public class RequestPropertyService implements RequestPropertyServiceLocal {

    @EJB
    private RequestForPropertyViewingI ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "allRequestsForPropertyViewing")
    @Override
    public List<RequestsForPropertyViewing> allRequestsForPropertyViewing() {
        return ejbRef.allRequestsForPropertyViewing();
    }
    
    @WebMethod(operationName = "currentByerRequestForViewing")
    @Override
    public List<RequestsForPropertyViewing> currentByerRequestForViewing(User currentUser) {
         return ejbRef.currentByerRequestForViewing(currentUser);
    }

    @WebMethod(operationName = "findRequestsForPropertyViewing")
    @Override
    public RequestsForPropertyViewing findRequestsForPropertyViewing(@WebParam(name = "id") int id) {
        return ejbRef.findRequestsForPropertyViewing(id);
    }

    @WebMethod(operationName = "saveRequestsForPropertyViewing")
    @Override
    public RequestsForPropertyViewing saveRequestsForPropertyViewing(@WebParam(name = "request") RequestsForPropertyViewing request, @WebParam(name = "requestedProperty") Property requestedProperty, @WebParam(name = "userFromSession") User userFromSession) {
        return ejbRef.saveRequestsForPropertyViewing(request, requestedProperty, userFromSession);
    }

    @WebMethod(operationName = "deleteRequestsForPropertyViewing")
    @Override
    public RequestsForPropertyViewing deleteRequestsForPropertyViewing(@WebParam(name = "request") RequestsForPropertyViewing request) {
        return ejbRef.deleteRequestsForPropertyViewing(request);
    }

    @WebMethod(operationName = "editRequestsForPropertyViewing")
    @Override
    public RequestsForPropertyViewing editRequestsForPropertyViewing(@WebParam(name = "request") RequestsForPropertyViewing request) {
        return ejbRef.editRequestsForPropertyViewing(request);
    }

    
    
}
