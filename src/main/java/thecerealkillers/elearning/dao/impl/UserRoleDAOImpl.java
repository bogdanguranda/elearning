package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.utilities.Constants;
import thecerealkillers.elearning.dao.UserRoleDAO;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Lucian and Dani.
 */
@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void addRole(String username, String role) throws DAOException {
        if (role.compareTo(Constants.ADMIN) == 0)
            throw new DAOException("Can not add this role.");

        try {
            String sql = "INSERT INTO user_role VALUE (:username, :role);";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("username", username);
            namedParameters.put("role", role);
            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public void changeRole(String username, String role) throws DAOException {
        if (role.compareTo(Constants.ADMIN)  == 0)
            throw new DAOException("Can not change to this role.");

        try {
            String sqlCommand = "UPDATE user_role SET role = :role WHERE username = :username;";

            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("role", role);
            namedParameters.put("username", username);
            namedParameterJdbcTemplate.update(sqlCommand, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public String getRole(String username) throws DAOException {
        try {
            String sql = "SELECT role FROM user_role WHERE username = :username;";
            Map<String, String> namedParameters = Collections.singletonMap("username", username);

            List<String> roleList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("role");
                }
            });

            if(roleList.size() == 0) {
                throw new DAOException("Role does not exist for this username");
            }

            return roleList.get(0);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
}
