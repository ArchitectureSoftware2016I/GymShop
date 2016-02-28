package BusinessLogic.Controller;

import DataAccess.DAO.PermissionsDAO;
import DataAccess.DAO.RolesPermissionsDAO;
import DataAccess.Entity.Permissions;
import DataAccess.Entity.RolesPermissions;
import java.util.List;

/**
 * Basarse en List of HTTP status codes
 * 200 ok
 * 400 bad request
 * 404 not found
 * 403 Prohibido = forbidden
 * @author nikic
 * @version 1.0
 * @created 28-Feb.-2016 12:03:20 a. m.
 */
public class ManagePermissions {

	public ManagePermissions(){

	}

	public void finalize() throws Throwable {

	}
        
	/**
	 * 
	 * @param name
	 */
	public int addPermission(String name){
            Permissions permission = new Permissions();
            permission.setName(name);
            
            PermissionsDAO permissionsDAO = new PermissionsDAO();
            Permissions permissionsE = permissionsDAO.persist(permission);
            
            if (permissionsE != null) {
                return permissionsE.getIdPermission();
            } else {
                return -1;
            }
	}

	/**
	 * 
	 * @param permissions
	 * @param role
	 */
	public int addPrivilege(int permissions, int role){
            RolesPermissions rolesPermission = new RolesPermissions();
            rolesPermission.setIdRole(role);
            rolesPermission.setIdPermission(permissions);
            
            RolesPermissionsDAO rolesPermissionDAO = new RolesPermissionsDAO();
            RolesPermissions rolesPermissionsE = rolesPermissionDAO.persist(rolesPermission);
            
            if (rolesPermissionsE != null) {
                return rolesPermissionsE.getIdPermission();
            } else {
                return 400;
            }
	}

	/**
	 * 
	 * @param permissionID
	 * @param name
	 */
	public int editPermission(int permissionID, String name){
            PermissionsDAO permissionsDAO = new PermissionsDAO();
            Permissions permissionsE = permissionsDAO.searchById(permissionID);
            permissionsE.setName(name);
            
            try{
                permissionsDAO.edit(permissionsE);
                return 200;
            } catch( Exception E) {
                return 400;
            }
            
	}

	/**
	 * 
	 * @param permissionID
	 */
	public Permissions getPermission(int permissionID){
            try{
                PermissionsDAO permissionsDAO = new PermissionsDAO();
                Permissions permissionsE = permissionsDAO.searchById(permissionID);
                return permissionsE;
            } catch(Exception e){
                return new Permissions();
            }
	}

	/**
	 * 
	 * @param role
	 * @param permissionID
	 */
	public int isPermitted(int role, int permissionID){
            RolesPermissionsDAO rolesPermissionDAO = new RolesPermissionsDAO();
            List<RolesPermissions> rolesPermissionsE = rolesPermissionDAO.searchAll();
            for(int i= 0; i<rolesPermissionsE.size(); i++){
                if(rolesPermissionsE.get(i).getIdPermission()  == permissionID && rolesPermissionsE.get(i).getIdRole() == role)
                    return 200;
            }
            return 403;
	}

	/**
	 * 
	 * @param permissionID
	 * @param role
	 */
	public int removePrivilege(int permissionID, int role){
            RolesPermissionsDAO rolesPermissionDAO = new RolesPermissionsDAO();
            RolesPermissions rolesPermissionsE = rolesPermissionDAO.searchByIdPermission(permissionID);
            
            if (rolesPermissionsE != null) {
                return rolesPermissionsE.getIdPermission();
            } else {
                return 400;
            }
	}
}//end managePermissions