package ru.vyazankin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.RuntimeMBeanException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Inject
    HttpSession httpSession;

    public void logout(){
        httpSession.invalidate();
        logger.info("Logout done!");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(httpSession.getServletContext().getContextPath() + "/product.xhtml");
        } catch (IOException e) {
            throw new RuntimeException("Unable to redirect!", e);
        }
    }
}
