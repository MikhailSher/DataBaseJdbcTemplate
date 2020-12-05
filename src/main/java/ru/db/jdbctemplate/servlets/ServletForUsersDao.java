package ru.db.jdbctemplate.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.db.jdbctemplate.dao.DaoForUser;
import ru.db.jdbctemplate.dao.DaoForUserJdbcTemplate;
import ru.db.jdbctemplate.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * 07.11.2020
 * ServletForUsersDao
 *
 * @author Sherstobitov Mikhail
 * @version v1.0
 */
@WebServlet("/users")
public class ServletForUsersDao extends HttpServlet {
    private DaoForUser daoForUser;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            daoForUser = new DaoForUserJdbcTemplate(dataSource);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = null;
        if (req.getParameter("firstName") != null) {
            String firstName = req.getParameter("firstName");
            users = daoForUser.findAllByFirstName(firstName);
        } else {
            users = daoForUser.findAll();
        }
        req.setAttribute("usersFromServer", users);
        req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
    }


}
