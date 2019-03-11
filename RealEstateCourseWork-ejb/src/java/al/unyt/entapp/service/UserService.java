/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.service;

import al.unyt.entapp.daoI.UserI;
import al.unyt.entapp.model.User;
import al.unyt.entapp.serviceLocal.UserServiceLocal;
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
@WebService(serviceName = "UserService")
@Stateless
public class UserService implements UserServiceLocal {

    @EJB
    private UserI ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "allUsers")
    @Override
    public List<User> allUsers() {
        return ejbRef.allUsers();
    }

    @WebMethod(operationName = "findUser")
    @Override
    public User findUser(@WebParam(name = "email") String email) {
        return ejbRef.findUser(email);
    }

    @WebMethod(operationName = "saveUser")
    @Override
    public User saveUser(@WebParam(name = "user") User user, @WebParam(name = "roleId") String roleId) {
        return ejbRef.saveUser(user, roleId);
    }

    @WebMethod(operationName = "deleteUser")
    @Override
    public User deleteUser(@WebParam(name = "user") User user) {
        return ejbRef.deleteUser(user);
    }

    @WebMethod(operationName = "editUser")
    @Override
    public User editUser(@WebParam(name = "user") User user) {
        return ejbRef.editUser(user);
    }

    @WebMethod(operationName = "checkUser")
    @Override
    public User checkUser(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        return ejbRef.checkUser(email, password);
    }

    @WebMethod(operationName = "findUserByEmail")
    @Override
    public User findUserByEmail(@WebParam(name = "email") String email) {
        return ejbRef.findUserByEmail(email);
    }

    @WebMethod(operationName = "allSellers")
    @Override
    public List<User> allSellers() {
        return ejbRef.allSellers();
    }

    @WebMethod(operationName = "allBuyers")
    @Override
    public List<User> allBuyers() {
        return ejbRef.allBuyers();
    }

}
