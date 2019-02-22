package cbook.web.bean;
import cbook.domain.models.binding.UserRegisterBindingModel;
import cbook.domain.models.service.UserServiceModel;
import cbook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterBean {

    private UserService userService;
    private ModelMapper modelMapper;
    private UserRegisterBindingModel userRegisterBindingModel;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterBindingModel = new UserRegisterBindingModel();

    }
    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {

        if(!this.userRegisterBindingModel.getPassword()
                .equals(this.userRegisterBindingModel.getConfirmPassword())){

            FacesContext.getCurrentInstance().getExternalContext().redirect("/register");
        }
        this.userService.register(this.modelMapper.map(this.userRegisterBindingModel,UserServiceModel.class));

        FacesContext.getCurrentInstance().getExternalContext().redirect("/login");

    }
}
