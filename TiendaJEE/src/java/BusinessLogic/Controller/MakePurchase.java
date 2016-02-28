package BusinessLogic.Controller;

import DataAccess.DAO.PurchaseDAO;
import DataAccess.DAO.PurchaseDetailsDAO;
import DataAccess.Entity.PurchaseDetails;
import DataAccess.Entity.Purchases;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @created 28-Feb.-2016 12:03:19 a. m.
 */
public class MakePurchase {

	public MakePurchase(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param clientID
	 * @param productsID
         * @param quantity
	 * @param value
	 */
	public int buy(int clientID, int[] productsID, int[] quantity, float value){
            boolean failed = false;
            Purchases purchase = new Purchases();
            Date now = new Date();
            purchase.setBuyerId(clientID);
            purchase.setDate(now);
            purchase.setTotal(value);
            
            PurchaseDAO purchasesDAO = new PurchaseDAO();
            Purchases purchasesE = purchasesDAO.persist(purchase);
            
            for(int i=0; i<productsID.length; i++){
                PurchaseDetails purchaseDetails = new PurchaseDetails();
                purchaseDetails.setIdPurchase(purchasesE.getIdPurchase());
                purchaseDetails.setIdProduct(productsID[i]);
                purchaseDetails.setQuantity(quantity[i]);
                
                PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO();
                PurchaseDetails purchaseDetailsE = purchaseDetailsDAO.persist(purchaseDetails);
                if (purchaseDetailsE != null)
                    continue;
                else
                    failed = true;
                    break;
            }
            
            if (failed == true)
                return 400;
            else
                return 200;
	}
        
        /**
	 * @param purchaseID
	 * @param clientID
         * @param newDate
	 * @param productsID
         * @param quantity
	 * @param value
	 */
	public int editPurchase(int purchaseID, int clientID, Date newDate, int[] productsID, int[] quantity, float value){
            boolean failed = false;
            
            PurchaseDAO purchasesDAO = new PurchaseDAO();
            Purchases purchasesE = purchasesDAO.searchById(purchaseID);
            purchasesE.setBuyerId(clientID);
            purchasesE.setDate(newDate);
            purchasesE.setTotal(value);
            try{
                purchasesDAO.edit(purchasesE);
            } catch(Exception e){
                return 400;
            }
            
            PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO();
            List<PurchaseDetails> purchaseDetails = purchaseDetailsDAO.searchAll();
            
            for(int i=0; i<purchaseDetails.size(); i++){
                if (purchaseDetails.get(i).getIdPurchase() == purchaseID){
                    PurchaseDetails purchaseDetailsE = purchaseDetails.get(i);
                    for(int j=0; j< productsID.length; j++){
                        if(purchaseDetailsE.getIdProduct() == productsID[i]){
                            purchaseDetailsE.setQuantity(quantity[i]);
                            try{
                                purchaseDetailsDAO.edit(purchaseDetailsE);
                            } catch(Exception e){
                                return 400;
                            }
                        }
                    }
                }
            }
            
            return 200;
	}

	/**
	 * 
	 * @param purchaseID
	 */
	public Purchases getPurchaseByID(int purchaseID){
		PurchaseDAO purchaseDAO = new PurchaseDAO();
                Purchases purchasesE = purchaseDAO.searchById(purchaseID);
                return purchasesE;
	}
        
        /**
	 * 
	 * @param userID
	 */
	public Purchases getPurchaseByUser(int userID){
		PurchaseDAO purchaseDAO = new PurchaseDAO();
                Purchases purchasesE = purchaseDAO.searchByBuyer(userID);
                return purchasesE;
	}
}//end makePurchase