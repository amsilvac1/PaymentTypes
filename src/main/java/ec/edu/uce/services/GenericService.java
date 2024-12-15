package ec.edu.uce.services;

import java.util.List;

public interface GenericService<T> {
    void save(T entity);
    T findById(int id);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}