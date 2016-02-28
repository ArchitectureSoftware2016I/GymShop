/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Roles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonnathan
 */
public class RolesDAO {
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TiendaJEEPU");
    
    public Roles persist(Roles role) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(role);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return role;  
        }
    }
    
    public Roles searchById(Integer idRoles) {
        EntityManager em = emf1.createEntityManager();
        Roles role = null;
        
        try {
            role = em.find(Roles.class
                    , idRoles);
        } catch (Exception e){
        } finally {
            em.close();
            return role;
        }
    }
    
    public Roles searchByName(String name) {
        EntityManager em = emf1.createEntityManager();
        Roles role = null;
        Query q = em.createNamedQuery("Roles.findByName");
        q.setParameter(1, name);
        try {          
            role = (Roles) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return role;
        }
    }
    
    public List<Roles> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<Roles> roles = null;
        Query q = em.createNamedQuery("Roles.findAll");
        try {          
            roles = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return roles;
        }
    }
    
    public void edit(Roles role){
        Roles roleNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            roleNew = em.merge(em.find(Roles.class, role.getIdRol())); 
            roleNew.setName(role.getName());
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean isEmpty() {
        EntityManager em = emf1.createEntityManager();
        List<Roles> roles = null;
        Query q = em.createNamedQuery("Roles.findAll");
        try {          
            roles = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            if (roles == null){return true;}
            else {return false;}
        }
    }
    
}
