package cbook.web.bean;

import cbook.domain.models.binding.UserLoginBindingModel;
import cbook.domain.models.service.UserServiceModel;
import cbook.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class LoginBean {

    private UserService userService;
    private UserLoginBindingModel userLoginBindingModel;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel = null;
        try {
             userServiceModel =  this.userService.getByName(this.userLoginBindingModel.getUsername());
        }catch (Exception e){

            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("/login");
            return;
        }

        if(userServiceModel==null){

            FacesContext.getCurrentInstance().getExternalContext().redirect("/login");
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("username",this.userLoginBindingModel.getUsername());

        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("/home");
    }
}
