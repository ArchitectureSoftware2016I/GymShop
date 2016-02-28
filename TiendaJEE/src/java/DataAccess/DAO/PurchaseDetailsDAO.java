/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.PurchaseDetails;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonnathan
 */
public class PurchaseDetailsDAO {
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TiendaJEEPU");
    
    public PurchaseDetails persist(PurchaseDetails purchaseDetails) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(purchaseDetails);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return purchaseDetails;  
        }
    }
    
    
    public PurchaseDetails searchById(Integer idPurchaseDetails) {
        EntityManager em = emf1.createEntityManager();
        PurchaseDetails purchaseDetails = null;
        
        try {
            purchaseDetails = em.find(PurchaseDetails.class
                    , idPurchaseDetails);
        } catch (Exception e){
        } finally {
            em.close();
            return purchaseDetails;
        }
    }
    
    public PurchaseDetails searchByPurchase(Integer idPurchase) {
        EntityManager em = emf1.createEntityManager();
        PurchaseDetails purchaseDetails = null;
        Query q = em.createNamedQuery("PurchaseDetails.findByIdPurchase");
        q.setParameter(1, idPurchase);
        try {          
            purchaseDetails = (PurchaseDetails) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return purchaseDetails;
        }
    }
    
    public List<PurchaseDetails> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<PurchaseDetails> purchaseDetails = null;
        Query q = em.createNamedQuery("PurchaseDetails.findAll");
        try {          
            purchaseDetails = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return purchaseDetails;
        }
    }
    
    public void edit(PurchaseDetails purchaseDetails){
        PurchaseDetails purchaseDetailsNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            purchaseDetailsNew = em.merge(em.find(PurchaseDetails.class, purchaseDetails.getIdPurchaseDetails())); 
            purchaseDetailsNew.setIdProduct(purchaseDetails.getIdProduct());
            purchaseDetailsNew.setIdPurchase(purchaseDetails.getIdPurchase());
            purchaseDetailsNew.setQuantity(purchaseDetails.getQuantity());
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean isEmpty() {
        EntityManager em = emf1.createEntityManager();
        List<PurchaseDetails> purchasesDetails = null;
        Query q = em.createNamedQuery("PurchaseDetails.findAll");
        try {          
            purchasesDetails = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            if (purchasesDetails == null){return true;}
            else {return false;}
        }
    }
}
