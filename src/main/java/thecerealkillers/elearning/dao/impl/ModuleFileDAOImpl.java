package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import thecerealkillers.elearning.dao.ModuleFileDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Module;
import thecerealkillers.elearning.model.ModuleFile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cuvidk on 12/29/2015.
 */
public class ModuleFileDAOImpl implements ModuleFileDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void storeFile(ModuleFile file) throws DAOException {
        try {
            String sql = "INSERT INTO module_file VALUES (:name, :course, :module, " + file.getSize() + ", :type," +
                    file.getContent() + ");";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("name", file.getName());
            namedParameters.put("course", file.getAssociatedCourse());
            namedParameters.put("module", file.getAssociatedModule());
            namedParameters.put("type", file.getType());

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public boolean isModuleFileExistent(ModuleFile file) throws DAOException {
        try {
            List<ModuleFile> files;
            String sqlCommand = "SELECT * FROM module_file WHERE title = :name AND course = :course AND module = :module";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", file.getName());
            namedParameters.put("course", file.getAssociatedCourse());
            namedParameters.put("module", file.getAssociatedModule());

            files = namedParameterJdbcTemplate.query(sqlCommand, namedParameters, new RowMapper<ModuleFile>() {
                @Override
                public ModuleFile mapRow(ResultSet resultSet, int i) throws SQLException {
                    ModuleFile file = new ModuleFile();

                    file.setName(resultSet.getString("name"));
                    file.setAssociatedCourse(resultSet.getString("course"));
                    file.setAssociatedModule(resultSet.getString("module"));
                    file.setContent(resultSet.getBytes("content"));
                    file.setSize(resultSet.getInt("size"));
                    file.setType(resultSet.getString("type"));

                    return file;
                }
            });

            if (files.size() > 0) {
                return true;
            }
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
        return false;
    }
}
