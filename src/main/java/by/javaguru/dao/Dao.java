package by.javaguru.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {
    E save(E entity);
    boolean update(E entity); // // TODO: make method remove void
    boolean delete(K id); // TODO: make method remove void
    Optional<E> findById(K id);
    List<E> findAll();
}
