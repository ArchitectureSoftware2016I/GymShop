/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Purchase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonnathan
 */
public class PurchaseDAO {
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TiendaJEEPU");
    
    public Purchase persist(Purchase purchase) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(purchase);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return purchase;  
        }
    }
    
    
    public Purchase searchById(Integer idPurchase) {
        EntityManager em = emf1.createEntityManager();
        Purchase purchase = null;
        
        try {
            purchase = em.find(Purchase.class
                    , idPurchase);
        } catch (Exception e){
        } finally {
            em.close();
            return purchase;
        }
    }
    
    public Purchase searchByBuyer(Integer idBuyer) {
        EntityManager em = emf1.createEntityManager();
        Purchase purchase = null;
        Query q = em.createNamedQuery("Purchases.findByBuyerId");
        q.setParameter(1, idBuyer);
        try {          
            purchase = (Purchase) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return purchase;
        }
    }
    
    public List<Purchase> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<Purchase> purchases = null;
        Query q = em.createNamedQuery("Purchases.findAll");
        try {          
            purchases = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return purchases;
        }
    }
    
    public void edit(Purchase purchase){
        Purchase purchaseNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            purchaseNew = em.merge(em.find(Purchase.class, purchase.getIdPurchase())); 
            purchaseNew.setBuyerId(purchase.getBuyerId());
            purchaseNew.setDate(purchase.getDate());
            purchaseNew.setTotal(purchase.getTotal());
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean isEmpty() {
        EntityManager em = emf1.createEntityManager();
        List<Purchase> purchases = null;
        Query q = em.createNamedQuery("Purchases.findAll");
        try {          
            purchases = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            if (purchases == null){return true;}
            else {return false;}
        }
    }
    
}
