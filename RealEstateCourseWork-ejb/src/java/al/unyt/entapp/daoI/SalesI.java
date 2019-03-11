/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.daoI;

import al.unyt.entapp.model.RequestsForPropertyViewing;
import al.unyt.entapp.model.Sales;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface SalesI {
    
    public List<Sales> allSales();
    public Sales findSale(int id);
    public Sales saveSales(Sales sale,RequestsForPropertyViewing request);
    public Sales deleteSales(Sales sale);
    public Sales editSales(Sales sale);
    
}
