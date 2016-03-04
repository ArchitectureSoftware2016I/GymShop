/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Product;
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
    
    public Product persist(Product product) {
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
    
    
    public Product searchById(Integer idProduct) {
        EntityManager em = emf1.createEntityManager();
        Product product = null;
        
        try {
            product = em.find(Product.class
                    , idProduct);
        } catch (Exception e){
        } finally {
            em.close();
            return product;
        }
    }
    
    public Product searchByName(String name) {
        EntityManager em = emf1.createEntityManager();
        Product product = null;
        Query q = em.createNamedQuery("Product.findByName");
        q.setParameter(1, name);
        try {          
            product = (Product) q.getSingleResult();
        } catch (Exception e){
        } finally {
            em.close();
            return product;
        }
    }
    
    public List<Product> searchAll() {
        EntityManager em = emf1.createEntityManager();
        List<Product> products = null;
        Query q = em.createNamedQuery("Product.findAll");
        try {          
            products = q.getResultList();
        } catch (Exception e){
        } finally {
            em.close();
            return products;
        }
    }
    
    public void edit(Product product){
        Product productNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
            productNew = em.merge(em.find(Product.class, product.getIdProduct())); 
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
        List<Product> products = null;
        Query q = em.createNamedQuery("Product.findAll");
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
