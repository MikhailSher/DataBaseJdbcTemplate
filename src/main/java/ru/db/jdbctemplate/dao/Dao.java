package ru.db.jdbctemplate.dao;

import java.util.List;
import java.util.Optional;

/**
 * 07.11.2020
 * Dao
 *
 * @author Sherstobitov Mikhail
 * @version v1.0
 */
public interface Dao<T> {
    Optional<T> find(Integer id);
    void save(T model);
    void update(T model);
    void delete(Integer id);

    List<T> findAll();
}
