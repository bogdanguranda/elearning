package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.dao.ModuleDAO;
import thecerealkillers.elearning.model.Module;

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
 * Created by cuvidk on 12/22/2015.
 * .
 */
@Repository
public class ModuleDAOImpl implements ModuleDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void storeModule(Module module) throws DAOException {
        try {
            String sql = "INSERT INTO module VALUES (:title, :course, :description);";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", module.getTitle());
            namedParameters.put("course", module.getCourse());
            namedParameters.put("description", module.getDescription());

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void deleteModule(Module module) throws DAOException {
        try {
            String sql = "DELETE FROM module WHERE title= :title AND course= :course";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", module.getTitle());
            namedParameters.put("course", module.getCourse());

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public List<Module> getAll(String course) throws DAOException {
        try {
            List<Module> modules;
            String sqlCommand = "SELECT * FROM Module WHERE course = :course";
            Map<String, String> namedParameters = Collections.singletonMap("course", course);

            modules = namedParameterJdbcTemplate.query(sqlCommand, namedParameters, new RowMapper<Module>() {
                @Override
                public Module mapRow(ResultSet resultSet, int i) throws SQLException {
                    Module module = new Module();

                    module.setCourse(resultSet.getString("course"));
                    module.setDescription(resultSet.getString("description"));
                    module.setTitle(resultSet.getString("title"));

                    return module;
                }
            });
            return modules;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public Module get(String title, String course) throws DAOException {
        try {
            List<Module> modules;
            String sqlCommand = "SELECT * FROM Module WHERE title = :title AND course = :course";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", title);
            namedParameters.put("course", course);

            modules = namedParameterJdbcTemplate.query(sqlCommand, namedParameters, new RowMapper<Module>() {
                @Override
                public Module mapRow(ResultSet resultSet, int i) throws SQLException {
                    Module module = new Module();

                    module.setDescription(resultSet.getString("description"));
                    module.setTitle(resultSet.getString("title"));
                    module.setCourse(resultSet.getString("course"));

                    return module;
                }
            });

            return modules.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void update(Module module, String newTitle) throws DAOException {
        try {
            String sql = "UPDATE module SET title='" + newTitle + "' WHERE title = :title AND course = :course";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", module.getTitle());
            namedParameters.put("course", module.getCourse());

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public boolean isModuleExistent(Module module) throws DAOException {
        try {
            List<Module> modules;
            String sqlCommand = "SELECT * FROM Module WHERE title = :title AND course = :course";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", module.getTitle());
            namedParameters.put("course", module.getCourse());

            modules = namedParameterJdbcTemplate.query(sqlCommand, namedParameters, new RowMapper<Module>() {
                @Override
                public Module mapRow(ResultSet resultSet, int i) throws SQLException {
                    Module module = new Module();

                    module.setTitle(resultSet.getString("title"));
                    module.setCourse(resultSet.getString("course"));
                    module.setDescription(resultSet.getString("description"));

                    return module;
                }
            });

            if (modules.size() > 0) {
                return true;
            }
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
        return false;
    }
}