package cbook.domain.models.service;

import cbook.domain.entities.User;

import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {

    private String id;
    private String username;
    private String password;
    private String gender;
    private User user;
    private List<UserServiceModel> friends;


    public UserServiceModel() {


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserServiceModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserServiceModel> friends) {
        this.friends = friends;
    }


}
