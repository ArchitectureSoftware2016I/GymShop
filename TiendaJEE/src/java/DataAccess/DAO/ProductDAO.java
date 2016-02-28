/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Products;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonnathan
 */
public class ProductDAO {
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("TiendaJEEPU");
    
    public Products persist(Products product) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(product);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return product;  
        }
    }
    
    
    public Products searchById(Integer idProduct) {
        EntityManager em = emf1.createEntityManager();
        Products product = null;
        
        try {
            product = em.find(Products.class
                    , idProduct);
        } catch (Exception e){
        } finally {
            em.close();
            return product;
        }
    }
    
    public Products searchByName(String name) {
        EntityManager em = emf1.createEntityManager();
        Products product = null;
        Query q = em.createNamedQuery("Products.findByName");
        q.setParameter(1, name);
        try {          
            product = (Products) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return product;
        }
    }
    
    public List<Products> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<Products> products = null;
        Query q = em.createNamedQuery("Products.findAll");
        try {          
            products = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return products;
        }
    }
    
    public void edit(Products product){
        Products productNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            productNew = em.merge(em.find(Products.class, product.getIdProduct())); 
            productNew.setName(product.getName());
            productNew.setPrice(product.getPrice());
            productNew.setDescription(product.getDescription());
            productNew.setAvailable(product.getAvailable());
            productNew.setPicture(product.getPicture());
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean isEmpty() {
        EntityManager em = emf1.createEntityManager();
        List<Products> products = null;
        Query q = em.createNamedQuery("Products.findAll");
        try {          
            products = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            if (products == null){return true;}
            else {return false;}
        }
    }
}
