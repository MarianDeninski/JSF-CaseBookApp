package cbook.repository;

import java.util.List;

public interface GeneralRepository <T,E> {

    T add(T entity);

    List<T> getAll();

    T getById(E id);

    T getByName(E name);

}
