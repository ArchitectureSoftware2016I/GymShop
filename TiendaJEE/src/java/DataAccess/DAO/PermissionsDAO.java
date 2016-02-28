/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Permissions;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonnathan
 */
public class PermissionsDAO {
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TiendaJEEPU");
    
    public Permissions persist(Permissions permission) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(permission);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return permission;  
        }
    }
    
    public Permissions searchById(Integer idPermission) {
        EntityManager em = emf1.createEntityManager();
        Permissions permission = null;
        
        try {
            permission = em.find(Permissions.class
                    , idPermission);
        } catch (Exception e){
        } finally {
            em.close();
            return permission;
        }
    }
    
    public Permissions searchByName(String name) {
        EntityManager em = emf1.createEntityManager();
        Permissions permission = null;
        Query q = em.createNamedQuery("Permissions.findByName");
        q.setParameter(1, name);
        try {          
            permission = (Permissions) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return permission;
        }
    }
    
    public List<Permissions> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<Permissions> permissions = null;
        Query q = em.createNamedQuery("Permissions.findAll");
        try {          
            permissions = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return permissions;
        }
    }
    
    public void edit(Permissions permission){
        Permissions permissionNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            permissionNew = em.merge(em.find(Permissions.class, permission.getIdPermission())); 
            permissionNew.setName(permission.getName());
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean isEmpty() {
        EntityManager em = emf1.createEntityManager();
        List<Permissions> permissions = null;
        Query q = em.createNamedQuery("Permissions.findAll");
        try {          
            permissions = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            if (permissions == null){return true;}
            else {return false;}
        }
    }
}
