/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import java.util.List;
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
    
    public User persist(User user) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(user);
            em.getTransaction().commit();
            em.flush();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return user;  
        }
    }
    
    public int validateLogin(String username, String password) {
        EntityManager em = emf1.createEntityManager();
        User user = null;
        Query q = em.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);
 
        try {
            user = (User) q.getSingleResult();
            if (user.getPassword().equals(password)) {
                return user.getRol();
            }
            else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        } finally {
            em.close();
        }
    }
    
    public User searchById(Integer idUser) {
        EntityManager em = emf1.createEntityManager();
        User user = null;
        
        try {
            user = em.find(User.class
                    , idUser);
        } catch (Exception e){
        } finally {
            em.close();
            return user;
        }
    }
    
    public User searchByUsername(String username) {
        EntityManager em = emf1.createEntityManager();
        User user = null;
        Query q = em.createNamedQuery("Users.findByUsername");
        q.setParameter(1, username);
        try {          
            user = (User) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return user;
        }
    }
    
    public List<User> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<User> users = null;
        Query q = em.createNamedQuery("Users.findAll");
        try {          
            users = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return users;
        }
    }
    
    public void edit(User user){
        User userNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            userNew = em.merge(em.find(User.class, user.getIdUser())); 
            userNew.setName(user.getName());
            userNew.setPassword(user.getPassword());
            userNew.setRol(user.getRol());
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean isEmpty() {
        EntityManager em = emf1.createEntityManager();
        List<User> users = null;
        Query q = em.createNamedQuery("Users.findAll");
        try {          
            users = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            if (users == null){return true;}
            else {return false;}
        }
    }
    
    
}
