/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.daoImpl;

import al.unyt.entapp.daoI.SalesI;
import al.unyt.entapp.model.RequestsForPropertyViewing;
import al.unyt.entapp.model.Sales;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SalesDao implements SalesI {
    
    @PersistenceContext
    private EntityManager em;

    private static Logger log = Logger.getLogger(UserDao.class.getName());

    @Override
    public List<Sales> allSales() {
      return em.createNamedQuery("Sales.findAll").getResultList();
    }

    @Override
    public Sales findSale(int id) {
        return em.find(Sales.class, id);
           }

    @Override
    public Sales saveSales(Sales sale,RequestsForPropertyViewing request) {
          sale.setRequestId(request);
          em.persist(sale);
          return sale;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Sales deleteSales(Sales sale) {
        try{
            em.remove(em.merge(sale));
            em.flush();
            return sale;
        }catch(Exception e){
            log.info("An exception happened : " + e);
            return null;
        }
    }

    @Override
    public Sales editSales(Sales sale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
