/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.service;

import al.unyt.entapp.daoI.SalesI;
import al.unyt.entapp.model.RequestsForPropertyViewing;
import al.unyt.entapp.model.Sales;
import al.unyt.entapp.serviceLocal.SalesServiceLocal;
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
@WebService(serviceName = "SalesService")
@Stateless()
public class SalesService implements SalesServiceLocal {

    @EJB
    private SalesI ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "allSales")
    @Override
    public List<Sales> allSales() {
        return ejbRef.allSales();
    }

    @WebMethod(operationName = "findSale")
    @Override
    public Sales findSale(@WebParam(name = "id") int id) {
        return ejbRef.findSale(id);
    }

    @WebMethod(operationName = "saveSales")
    @Override
    public Sales saveSales(@WebParam(name = "sale") Sales sale, @WebParam(name = "request") RequestsForPropertyViewing request) {
        return ejbRef.saveSales(sale, request);
    }

    @WebMethod(operationName = "deleteSales")
    @Override
    public Sales deleteSales(@WebParam(name = "sale") Sales sale) {
        return ejbRef.deleteSales(sale);
    }

    @WebMethod(operationName = "editSales")
    @Override
    public Sales editSales(@WebParam(name = "sale") Sales sale) {
        return ejbRef.editSales(sale);
    }
    
}
