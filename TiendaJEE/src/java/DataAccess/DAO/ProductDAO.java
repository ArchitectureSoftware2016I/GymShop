/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Product;
import Presentation.Bean.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juansaab
 */
@Stateless
public class ProductDAO extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "TiendaJEEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductDAO() {
        super(Product.class);
    }
    
}
