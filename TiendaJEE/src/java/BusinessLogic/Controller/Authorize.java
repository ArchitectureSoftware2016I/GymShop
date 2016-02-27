/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UsersDAO;

/**
 *
 * @author juansaab
 */
public class Authorize {
    
    public boolean loginAuthorize(String username, String password) {
        UsersDAO userDAO = new UsersDAO();
        boolean valid = userDAO.validateLogin(username, password);
        
        if (valid) {
            return true;
        }
        else {
            return false;
        }
    }
    
    
}
