/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import Presentation.Bean.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juansaab
 */
@Stateless
public class UserDAO extends AbstractFacade<User> {

    @PersistenceContext(unitName = "TiendaJEEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserDAO() {
        super(User.class);
    }
    
    public User getByUsername(String username){
        
        System.out.println(em.createNamedQuery("User.findByUsername").setParameter("username", username).getSingleResult());
        return (User) em.createNamedQuery("User.findByUsername").setParameter("username", username).getSingleResult();
    }
    
}
