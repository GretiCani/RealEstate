/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.daoI;

import al.unyt.entapp.model.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface UserI {

    public List<User> allUsers();

    public List<User> allSellers();

    public List<User> allBuyers();

    public User findUser(String email);

    public User saveUser(User user, String roleId);

    public User deleteUser(User user);

    public User editUser(User user);

    public User checkUser(String email, String password);

    public User findUserByEmail(String email);

}
