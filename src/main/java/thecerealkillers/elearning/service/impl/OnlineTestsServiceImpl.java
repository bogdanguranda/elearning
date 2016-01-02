package thecerealkillers.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thecerealkillers.elearning.dao.OnlineTestsDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.OnlineTest;
import thecerealkillers.elearning.service.OnlineTestsService;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
@Service
public class OnlineTestsServiceImpl implements OnlineTestsService {

    @Autowired
    private OnlineTestsDAO onlineTestsDAO;

    @Override
    public void createTest(OnlineTest onlineTest) throws ServiceException {
        try {
            if (onlineTestsDAO.isTestExistent(onlineTest)) {
                throw new ServiceException(ServiceException.FAILED_TEST_ALREADY_EXISTS);
            }
            onlineTestsDAO.addTest(onlineTest);
        } catch (DAOException daoExeption) {
            throw new ServiceException(daoExeption.getMessage());
        }
    }

    @Override
    public void deleteTest(String title, String course) throws ServiceException {
        try {
            OnlineTest onlineTest = new OnlineTest(title, course);
            if (onlineTestsDAO.isTestExistent(onlineTest)) {
                onlineTestsDAO.deleteTest(onlineTest);
            }
            else {
                throw new ServiceException(ServiceException.FAILED_TEST_NOT_EXISTS);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
