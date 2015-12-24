package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.PermissionDAO;
import thecerealkillers.elearning.model.Permission;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Dani.
 */
@Repository
public class PermissionDAOImpl implements PermissionDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Permission getPermission(String operationName, String roleName) throws DAOException {
        try {
            String command = "SELECT * FROM permission WHERE operationName = :operationName AND roleName = :roleName";
            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("operationName", operationName);
            namedParameters.put("roleName", roleName);

            List<Permission> permissionList = namedParameterJdbcTemplate.query(command, namedParameters, new RowMapper<Permission>() {
                @Override
                public Permission mapRow(ResultSet resultSet, int i) throws SQLException {
                    Permission permission = new Permission();

                    permission.setOperationName(resultSet.getString("operationName"));
                    permission.setRoleName(resultSet.getString("roleName"));
                    permission.setPermission(resultSet.getBoolean("permission"));

                    return permission;
                }
            });

            if (permissionList.size() == 0)
                throw new DAOException("No data.\n");

            return permissionList.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
