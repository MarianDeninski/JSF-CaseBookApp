package cbook.service;

import cbook.domain.entities.User;
import cbook.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel entity);

    List<UserServiceModel> getAll();

    UserServiceModel getById(String id);

    UserServiceModel getByName(String name);

    void update(UserServiceModel user);

}
