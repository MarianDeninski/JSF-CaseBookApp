package cbook.repository;


import cbook.domain.entities.User;

public interface UserRepository extends GeneralRepository<User,String>{

    void update(User user);
}
