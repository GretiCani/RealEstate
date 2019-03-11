/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.daoImpl;

import al.unyt.entapp.daoI.RequestForPropertyViewingI;
import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.RequestsForPropertyViewing;
import al.unyt.entapp.model.User;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RequestForPropertyViewingDao implements RequestForPropertyViewingI {
    
    @PersistenceContext
    private EntityManager em;

    private static Logger log = Logger.getLogger(UserDao.class.getName());

    @Override
    public List<RequestsForPropertyViewing> allRequestsForPropertyViewing() {
      
        return em.createNamedQuery("RequestsForPropertyViewing.findAll").getResultList();
    }
    
    @Override
    public List<RequestsForPropertyViewing> currentByerRequestForViewing(User currentUser) {

    String query = "SELECT r from RequestsForPropertyViewing r  INNER JOIN r.buyerId user WHERE user.email = :email ";  
    return (List<RequestsForPropertyViewing>) em.createQuery(query)
            .setParameter("email",currentUser.getEmail()).getResultList();
                
    }

    @Override
    public RequestsForPropertyViewing findRequestsForPropertyViewing(int id) {
           return em.find(RequestsForPropertyViewing.class, id);
    }

    @Override
    public RequestsForPropertyViewing saveRequestsForPropertyViewing(RequestsForPropertyViewing request, Property requestedProperty,User userFromSession) {
          
        request.setPropertyId(requestedProperty);
        request.setBuyerId(userFromSession);
        em.persist(request);
        em.flush();
        return request;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public RequestsForPropertyViewing deleteRequestsForPropertyViewing(RequestsForPropertyViewing request) {
          
        try{
            em.remove(em.merge(request));
            em.flush();
            return request;
            }catch(Exception e){
                log.info("An exception happened : " + e);
            return null;
            }
    }

    @Override
    public RequestsForPropertyViewing editRequestsForPropertyViewing(RequestsForPropertyViewing request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}
