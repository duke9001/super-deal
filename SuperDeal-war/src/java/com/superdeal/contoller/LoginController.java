/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superdeal.contoller;

import com.superdeal.entity.WebClient;
import com.superdeal.model.WebClientFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * login authentication control 
 * @author udith dissanayake
 * @version 1.0 (20/8/2015)
 */
@ManagedBean(name="loginController")
@SessionScoped
public class LoginController implements Serializable{

    @EJB
    private WebClientFacade webClientFacade;
    private String userName;
    private String password;
    public boolean isLogged = true;
    List<WebClient> webClientList = new ArrayList<WebClient>();
    private WebClient webClient = new WebClient();
    public LoginController() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /*
     * check correct username and password entered
     * @return if correct user return to homepage
     * @return else show error page
     */    
    public String checkLogin(){
         webClient = webClientFacade.find(this.userName);
        if ((webClient.getUsername().equals(userName) && (webClient.getPassword().equals(password)))) {
           return "index.xhtml?faces-redirect=true";
        }
        return "error";
                
        
    }
    
}
