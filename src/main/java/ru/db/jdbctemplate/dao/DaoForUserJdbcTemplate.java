package ru.db.jdbctemplate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.db.jdbctemplate.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;

/**
 * 07.11.2020
 * DaoForUserJdbcTemplate
 *
 * @author Sherstobitov Mikhail
 * @version v1.0
 */
public class DaoForUserJdbcTemplate implements DaoForUser {

    private JdbcTemplate template;

    private final String SQL_SELECT_ALL =
            "SELECT * FROM users";

    private Map<Integer, User> usersMap = new HashMap<>();


    private final String SQL_SELECT_ALL_BY_FIRST_NAME =
            "SELECT * FROM users WHERE first_name = ?";

    public DaoForUserJdbcTemplate(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper
            = (ResultSet resultSet, int i) -> {
       Integer id = resultSet.getInt("id");
       String firstName = resultSet.getString("first_name");
       String lastName = resultSet.getString("last_name");

        return new User(id, firstName, lastName, new ArrayList<>());
    };

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_ALL_BY_FIRST_NAME, userRowMapper, firstName);
    }

    @Override
    public Optional<User> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT_ALL, userRowMapper);
    }
}
