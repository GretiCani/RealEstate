/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.daoI;

import al.unyt.entapp.model.Roles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface RolesI {
    
    public List<Roles> allRoles();
    public Roles findRoles(String role);
    public Roles saveRole(Roles role);
    public Roles deleteRole(Roles role);
    public Roles editRole(Roles role);
    
}
