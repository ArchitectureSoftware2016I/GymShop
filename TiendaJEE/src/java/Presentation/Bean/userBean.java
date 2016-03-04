/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.Authorize;
import BusinessLogic.Controller.ManageUsers;
import Presentation.Bean.SessionBean;
import java.io.IOException;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author juansaab
 */
@ManagedBean
@ViewScoped
@Dependent
public class userBean implements Serializable {

    private String password;
    private String username;
    private String msg;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String validateLogin() {
        Authorize auth = new Authorize();
        Integer valid = auth.loginAuthorize(username, password);
        if (valid > 0) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", username);
            session.setAttribute("rol", valid.toString());
            return "logged";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() throws IOException {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        String root = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(root+"/faces/index.xhtml");
        return "login";
    }
    
    public void signup() {
        ManageUsers userController = new ManageUsers();
        int answer;
        answer = userController.createAccount(name, password, username);
        if (answer != 400) {
            msg = "¡Tu cuenta de usuario ha sido creada exitosamente!";
        }
        else {
            msg = "Ocurrió un error y no pudimos crear tu cuenta";
        }
    }


}
