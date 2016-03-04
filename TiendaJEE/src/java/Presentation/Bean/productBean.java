/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.ProductDAO;
import DataAccess.Entity.Product;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JuanSaab
 */
@ManagedBean
@ViewScoped
public class productBean {
    
    private List<Product> products;

    public productBean() {
        ProductDAO productDAO = new ProductDAO();
        products = productDAO.searchAll();
    }
    
    public List<Product> getProducts() {
        return products;
    }
    
}
