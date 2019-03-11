/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.daoI;

import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.RequestsForPropertyViewing;
import al.unyt.entapp.model.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface RequestForPropertyViewingI {
    
    public List<RequestsForPropertyViewing> allRequestsForPropertyViewing();
    public List<RequestsForPropertyViewing>currentByerRequestForViewing(User currentUser);
    public RequestsForPropertyViewing findRequestsForPropertyViewing(int id);
    public RequestsForPropertyViewing saveRequestsForPropertyViewing(RequestsForPropertyViewing request, Property requestedProperty, User userFromSession);
    public RequestsForPropertyViewing deleteRequestsForPropertyViewing(RequestsForPropertyViewing request );
    public RequestsForPropertyViewing editRequestsForPropertyViewing(RequestsForPropertyViewing request);
    
}
