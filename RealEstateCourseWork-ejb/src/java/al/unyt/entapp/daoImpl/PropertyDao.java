/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.daoImpl;

import al.unyt.entapp.daoI.PropertyI;
import al.unyt.entapp.model.Property;
import al.unyt.entapp.model.User;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PropertyDao implements PropertyI {
    
    @PersistenceContext
    private EntityManager em;

    private static Logger log = Logger.getLogger(UserDao.class.getName());

    @Override
    public List<Property> allProperty() {
         return em.createNamedQuery("Property.findAll").getResultList();
    }
    
    @Override
    public List<Property> searchedProperties(String searchedString) {
        
        String query = "SELECT p from Property p  WHERE p.description LIKE :description";
        return (List<Property>) em.createQuery(query).setParameter("description","%"+searchedString+"%").getResultList();
 }

    @Override
    public List<Property> currentUserProperties(User currentUserInSession) {
         
        String query = "SELECT p from Property p INNER JOIN p.sellerId user WHERE user.email = :email AND p.status = :status";
        return (List<Property>) em.createQuery(query).setParameter("email",currentUserInSession.getEmail())
                .setParameter("status","enable").getResultList();
    }

    @Override
    public List<Property> getEnabledProperty() {
        String query = "SELECT p from Property p  WHERE p.status = :status";
        return (List<Property>) em.createQuery(query).setParameter("status","enable").getResultList();
    }
    @Override
    public Property findProperty(int id) {
         
        return em.find(Property.class, id);
    }

    @Override
    public Property saveProperty(Property property,User userFromSession) {
       
       try{ 
        property.setSellerId(userFromSession);
        em.persist(property);
        return property;
       }catch(Exception e){
           log.info("An exception happened : " + e);
           return null;
       }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Property deleteProperty(Property property) {
      try{
      
          em.remove(em.merge(property));
          em.flush();
          return property;
      }catch(Exception e){
          log.info("An exception happened : " + e);
           return null;
      }
    }

    @Override
    public Property editProperty(Property property) {
       
        try{
            em.merge(property);
            em.flush();
            return property;
        }catch(Exception e){
           log.info("An exception happened : " + e);
           return null; 
        }
    }

    

    
   
    
    
}
