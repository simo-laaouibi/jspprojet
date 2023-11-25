/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Login;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import service.LoginaService;

/**
 *
 * @author SIMO LAAOUIBI
 */
@ManagedBean
public class LoginBean {
    private Login login;
    private LoginaService loginaService;
    private Object externalContext;

    public LoginBean() {
        login =new Login();
        loginaService=new LoginaService();
    }
    public void test() throws IOException{
        
       boolean etat =loginaService.login(login.getUser(), login.getPassword());
        if (etat) {
            System.out.println("bien");
                
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bien d'authentification", "Nom d'utilisateur ou mot de passe incorrect."));
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            externalContext.redirect(request.getContextPath() + "/faces/employeForm.xhtml");
            ///Tar/faces/serviceForm.xhtml
        }
        else{
            System.out.println("!!! bien");
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'authentification", "Nom d'utilisateur ou mot de passe incorrect."));
           // return "index.xhtml";
        }
       
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    
    
}