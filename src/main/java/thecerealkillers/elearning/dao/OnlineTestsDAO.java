package thecerealkillers.elearning.dao;

import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.OnlineTest;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
@Repository
public interface OnlineTestsDAO {

    boolean isTestExistent(OnlineTest onlineTest) throws DAOException;

    void addTest(OnlineTest onlineTest) throws DAOException;
}
