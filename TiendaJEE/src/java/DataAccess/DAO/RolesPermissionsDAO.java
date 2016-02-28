/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.RolesPermissions;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonnathan
 */
public class RolesPermissionsDAO {
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TiendaJEEPU");
    
    public RolesPermissions persist(RolesPermissions rolePermission) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(rolePermission);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return rolePermission;  
        }
    }
    
    public RolesPermissions searchById(Integer idRolePermission) {
        EntityManager em = emf1.createEntityManager();
        RolesPermissions rolePermission = null;
        
        try {
            rolePermission = em.find(RolesPermissions.class
                    , idRolePermission);
        } catch (Exception e){
        } finally {
            em.close();
            return rolePermission;
        }
    }
    
    public RolesPermissions searchByIdRole(Integer idRole) {
        EntityManager em = emf1.createEntityManager();
        RolesPermissions rolePermission = null;
        Query q = em.createNamedQuery("RolesPermissions.findByIdRole");
        q.setParameter(1, idRole);
        try {          
            rolePermission = (RolesPermissions) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return rolePermission;
        }
    }
    
    public RolesPermissions searchByIdPermission(Integer idPermission) {
        EntityManager em = emf1.createEntityManager();
        RolesPermissions rolePermission = null;
        Query q = em.createNamedQuery("RolesPermissions.findByIdPermission");
        q.setParameter(1, idPermission);
        try {          
            rolePermission = (RolesPermissions) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return rolePermission;
        }
    }
    
    public List<RolesPermissions> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<RolesPermissions> rolePermissions = null;
        Query q = em.createNamedQuery("Permissions.findAll");
        try {          
            rolePermissions = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return rolePermissions;
        }
    }
    
    public void edit(RolesPermissions rolePermission){
        RolesPermissions rolePermissionNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            rolePermissionNew = em.merge(em.find(RolesPermissions.class, rolePermission.getIdRolesPermissions())); 
            rolePermissionNew.setIdPermission(rolePermission.getIdPermission());
            rolePermissionNew.setIdRole(rolePermission.getIdRole());
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean isEmpty() {
        EntityManager em = emf1.createEntityManager();
        List<RolesPermissions> rolePermissions = null;
        Query q = em.createNamedQuery("RolesPermissions.findAll");
        try {          
            rolePermissions = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            if (rolePermissions == null){return true;}
            else {return false;}
        }
    }
}
