package cbook.web.bean;

import cbook.domain.models.service.UserServiceModel;
import cbook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class UserBean {

    private UserService userService;
    private List<UserServiceModel> usersNotFriends;
    private ModelMapper modelMapper;
    private UserServiceModel user;
    private HttpSession session;

    public UserBean() {
    }

    @Inject
    public UserBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.session =  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.init();
    }

    private void init() {

        List<UserServiceModel> users = this.userService.getAll();

        this.user = this.userService.getByName(this.session.getAttribute("username").toString());

        this.usersNotFriends = new ArrayList<>();

            for (UserServiceModel serviceModel : users) {

                boolean hasFriend = false;

                if(this.user.getUsername().equals(serviceModel.getUsername())){
                    continue;
                }
                for (UserServiceModel userServiceModel : this.user.getFriends()) {

                    if (userServiceModel.getUsername().equals(serviceModel.getUsername())) {
                        hasFriend = true;
                    }
                }
                if(!hasFriend){
                    this.usersNotFriends.add(serviceModel);
                }
            }
        System.out.println(this.usersNotFriends.size());
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void addFriend(UserServiceModel userServiceModel) throws IOException {

        this.user = this.userService.getByName((String) this.session.getAttribute("username"));
        this.user.getFriends().add(userServiceModel);
        this.userService.update(this.user);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public List<UserServiceModel> getUsersNotFriends() {
        return this.usersNotFriends;
    }

    public void setUsersNotFriends(List<UserServiceModel> usersNotFriends) {
        this.usersNotFriends = usersNotFriends;
    }

    public UserServiceModel userWithName(String name){

       return this.userService.getByName(name);

    }
    public void unFriend(UserServiceModel user1) throws IOException {

        this.user.getFriends().remove(user1);
        this.userService.update(this.user);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/friends");
    }
}
