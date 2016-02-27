/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Users;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author juansaab
 */
public class UsersDAO {
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TiendaJEEPU");
    
    public Users persist(Users user) {
        
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return user;  
        }
    }
    
    public boolean validateLogin(String username, String password) {
        EntityManager em = emf1.createEntityManager();
        Users user = null;
        Query q = em.createNamedQuery("Users.findByUsername");
        q.setParameter("username", username);
 
        try {
            user = (Users) q.getSingleResult();
            if (user.getPassword().equals(password)) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }
}
