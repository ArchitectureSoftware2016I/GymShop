/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Rol;
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
    
    public Rol persist(Rol role) {
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
    
    public Rol searchById(Integer idRoles) {
        EntityManager em = emf1.createEntityManager();
        Rol role = null;
        
        try {
            role = em.find(Rol.class
                    , idRoles);
        } catch (Exception e){
        } finally {
            em.close();
            return role;
        }
    }
    
    public Rol searchByName(String name) {
        EntityManager em = emf1.createEntityManager();
        Rol role = null;
        Query q = em.createNamedQuery("Roles.findByName");
        q.setParameter(1, name);
        try {          
            role = (Rol) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return role;
        }
    }
    
    public List<Rol> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<Rol> roles = null;
        Query q = em.createNamedQuery("Roles.findAll");
        try {          
            roles = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return roles;
        }
    }
    
    public void edit(Rol role){
        Rol roleNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            roleNew = em.merge(em.find(Rol.class, role.getIdRol())); 
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
        List<Rol> roles = null;
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
