package BusinessLogic.Controller;

import DataAccess.DAO.ProductDAO;
import DataAccess.Entity.Products;

/**
 *  * Basarse en List of HTTP status codes
 * 200 ok
 * 400 bad request
 * 404 not found
 * 403 Prohibido = forbidden
 * @author nikic
 * @version 1.0
 * @version 1.0
 * @created 28-Feb.-2016 12:03:20 a. m.
 */
public class ManageProducts {

	public ManageProducts(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param name
	 * @param price
	 * @param stock
	 * @param img
	 */
	public int createProduct(String name, float price, int stock, String img){
		Products products = new Products();
                products.setName(name);
                products.setPrice(price);
                products.setAvailable(stock);
                products.setPicture(img);
                products.setDescription("Product called " + name + " which price is " + price);
                
                ProductDAO productsDAO = new ProductDAO();
                Products productE = productsDAO.persist(products);
                if (productE != null)
                    return productE.getIdProduct();
                else
                    return 400;
	}

	/**
	 * 
	 * @param ID
	 */
	public int deleteProduct(int ID){
            try{
		ProductDAO productDAO = new ProductDAO();
                Products productE = productDAO.searchById(ID);
                productE.setAvailable(0);
                productE.setDescription("Product deleted");
                productDAO.edit(productE);
                return 200;
            } catch(Exception e){
                return 400;
            }
	}

	/**
	 * 
	 * @param productID
         * @param name
         * @param img
         * @param description
	 * @param price
	 */
	public int editProduct(int productID, String name, String img, String description, float price){
            ProductDAO productDAO = new ProductDAO();
            Products productE = productDAO.searchById(productID);
            productE.setPrice(price);
            productE.setDescription(description);
            productE.setName(name);
            productE.setPicture(img);
            
            try{
                productDAO.edit(productE);
                return 200;
            } catch(Exception e){
                return 400;
            }
	}

	/**
	 * 
	 * @param productID
	 * @param stock
	 */
	public int editStock(int productID, int stock){
            ProductDAO productDAO = new ProductDAO();
            Products productE = productDAO.searchById(productID);
            productE.setAvailable(stock);
            try{
                productDAO.edit(productE);
                return 200;
            } catch(Exception e){
                return 400;
            }
	}

	/**
	 * 
	 * @param productID
	 */
	public Products getProduct(int productID){
            try{
                ProductDAO productDAO = new ProductDAO();
                Products productE = productDAO.searchById(productID);
                return productE;
            } catch(Exception e) {
                return new Products();
            }
	}
}//end manageProducts