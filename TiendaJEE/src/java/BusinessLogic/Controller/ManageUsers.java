package BusinessLogic.Controller;

import DataAccess.DAO.UsersDAO;
import DataAccess.Entity.Users;

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
public class ManageUsers {

	public ManageUsers(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * Si no se puede crear el usuario retornar -1
	 * 
	 * @param name
	 * @param password
	 * @param username
	 */
	public int createAccount(String name, String password, String username){
                Users user = new Users();
                user.setName(name);
                user.setPassword(password);
                user.setUsername(username);
                
                UsersDAO usersDAO = new UsersDAO();
                Users userE = usersDAO.persist(user);
                
                if(userE != null)
                    return userE.getIdUser();
                else
                    return 400;
	}

	/**
	 * 
	 * @param ID
	 */
	public int deleteAccount(int ID){
		UsersDAO usersDAO = new UsersDAO();
                try{
                    Users userE = usersDAO.searchById(ID);
                    userE.setPassword("gymshop");
                    return 200;
                } catch(Exception E){
                    return 403;
                }
                
	}

	/**
	 * 
	 * @param ID
	 * @param name
	 * @param username
	 * @param password
	 * @param newPassword
	 */
	public int editAccount(int ID, String name, String username, String password, String newPassword){
            UsersDAO usersDAO = new UsersDAO();
                
            try{
                Users userE = usersDAO.searchById(ID);
                userE.setName(name);
                userE.setUsername(username);
                userE.setPassword(newPassword);
                usersDAO.edit(userE);
                return 200;
            } catch(Exception e){
                return 404;
            }
	}

	/**
	 * 
	 * @param ID
	 * @param newRole
	 */
	public int editRole(int ID, int newRole){
            UsersDAO usersDAO = new UsersDAO();
            try{
                Users userE = usersDAO.searchById(ID);
                userE.setRol(ID);
                usersDAO.edit(userE);
                return 200;
            } catch(Exception E){
                return 404;
            }
	}

	/**
	 * 
	 * @param userID
	 */
	public Users getUser(int userID){
            UsersDAO usersDAO = new UsersDAO();
            try{
                Users userE = usersDAO.searchById(userID);
                return userE;
            } catch(Exception E){
                return new Users();  
            }   
	}
}//end manageUsers