package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import thecerealkillers.elearning.dao.ModuleDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Message;
import thecerealkillers.elearning.model.Module;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuvidk on 12/22/2015.
 */
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
}
