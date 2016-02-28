/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Purchases;
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
    
    public Purchases persist(Purchases purchase) {
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
    
    
    public Purchases searchById(Integer idPurchase) {
        EntityManager em = emf1.createEntityManager();
        Purchases purchase = null;
        
        try {
            purchase = em.find(Purchases.class
                    , idPurchase);
        } catch (Exception e){
        } finally {
            em.close();
            return purchase;
        }
    }
    
    public Purchases searchByBuyer(Integer idBuyer) {
        EntityManager em = emf1.createEntityManager();
        Purchases purchase = null;
        Query q = em.createNamedQuery("Purchases.findByBuyerId");
        q.setParameter(1, idBuyer);
        try {          
            purchase = (Purchases) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return purchase;
        }
    }
    
    public List<Purchases> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<Purchases> purchases = null;
        Query q = em.createNamedQuery("Purchases.findAll");
        try {          
            purchases = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return purchases;
        }
    }
    
    public void edit(Purchases purchase){
        Purchases purchaseNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            purchaseNew = em.merge(em.find(Purchases.class, purchase.getIdPurchase())); 
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
        List<Purchases> purchases = null;
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
