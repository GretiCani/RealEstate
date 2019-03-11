/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.daoImpl;

import al.unyt.entapp.daoI.UserI;
import al.unyt.entapp.model.Roles;
import al.unyt.entapp.model.User;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao implements UserI {

    @PersistenceContext
    private EntityManager em;

    private static Logger log = Logger.getLogger(UserDao.class.getName());

    @Override
    public List<User> allUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public List<User> allSellers() {
        String query = "SELECT u from User u INNER JOIN u.roleId roles WHERE roles.roleName = :role_id";
        return (List<User>) em.createQuery(query).setParameter("role_id","seller").getResultList();
    }

    @Override
    public List<User> allBuyers() {
        String query = "SELECT u from User u INNER JOIN u.roleId roles WHERE roles.roleName = :role_id";
        return (List<User>) em.createQuery(query).setParameter("role_id","buyer").getResultList();
    }

    @Override
    public User findUser(String email) {

        return em.find(User.class, email);
    }

    @Override
    public User saveUser(User user, String roleId) {
        Roles userRole = em.find(Roles.class, roleId);
        user.setRoleId(userRole);
        em.persist(user);
        return user;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public User deleteUser(User user) {
        try {
            em.remove(em.merge(user));
            em.flush();
            return user;
        } catch (Exception e) {
            log.info("An exception happened : " + e);
            return null;
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public User editUser(User user) {
        try {
            em.merge(user);
            em.flush();
            return user;
        } catch (Exception e) {
            log.info("An exception happened : " + e);
            return null;
        }
    }

    @Override
    public User checkUser(String email, String password) {

        String query = "SELECT u from User u WHERE u.email= :email AND u.password= :password";
        return (User) em.createQuery(query).setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public User findUserByEmail(String email) {

        User user = null;

        try {
            user = em.find(User.class, email);
        } catch (Exception e) {
//if user does not exist or user credincials are wrong it with throuw an NoResultException: 
//I have captured the exception only to stop the failure of application.
//I have handled in managed bean with another try catch block
            throw new NoResultException("User not Found");
        }

        return user;

    }

}
