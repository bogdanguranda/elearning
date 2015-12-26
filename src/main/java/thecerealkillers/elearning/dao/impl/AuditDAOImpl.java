package thecerealkillers.elearning.dao.impl;


import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.AuditItem;
import thecerealkillers.elearning.dao.AuditDAO;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Dani.
 */
@Repository
public class AuditDAOImpl implements AuditDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void addEvent(AuditItem auditItem) throws DAOException {
        try {
            String command = "INSERT INTO audit (username, operationName, dataReceived, response, success, timestamp) " +
                    "VALUES (:username, :operationName, :dataReceived, :response, :success, DEFAULT);";

            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("username", auditItem.getUsername());
            namedParameters.put("operationName", auditItem.getOperationName());
            namedParameters.put("dataReceived", auditItem.getDataReceived());
            namedParameters.put("response", auditItem.getResponse());

            if (auditItem.getSuccess())
                namedParameters.put("success", "1");
            else
                namedParameters.put("success", "0");

            namedParameterJdbcTemplate.update(command, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
