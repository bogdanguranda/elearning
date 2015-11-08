package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import thecerealkillers.elearning.dao.UserAdminDAO;
import thecerealkillers.elearning.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cuvidk on 11/8/2015.
 */
public class UserAdminDAOImpl implements UserAdminDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        String sqlCommand = "select * from user;";

        users = namedParameterJdbcTemplate.query(sqlCommand, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setStudentId(resultSet.getString("studentId"));
                user.setHash(resultSet.getString("hash"));
                user.setSalt(resultSet.getString("salt"));

                return user;
            }
        });
        return null;
    }
}
