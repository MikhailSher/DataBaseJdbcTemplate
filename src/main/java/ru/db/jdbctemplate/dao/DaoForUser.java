package ru.db.jdbctemplate.dao;

import ru.db.jdbctemplate.models.User;

import java.util.List;

/**
 * 07.11.2020
 * DaoForUser
 *
 * @author Sherstobitov Mikhail
 * @version v1.0
 */
public interface DaoForUser extends Dao<User> {
    List<User> findAllByFirstName(String firstName);
}
