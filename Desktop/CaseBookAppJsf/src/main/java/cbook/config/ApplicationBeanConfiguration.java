package cbook.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

public class ApplicationBeanConfiguration {

    @Produces
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory("book").createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
