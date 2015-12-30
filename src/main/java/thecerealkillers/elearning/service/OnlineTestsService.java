package thecerealkillers.elearning.service;

import org.springframework.stereotype.Service;
import thecerealkillers.elearning.exceptions.NotFoundException;
import thecerealkillers.elearning.exceptions.ServiceException;
import thecerealkillers.elearning.model.OnlineTest;

/**
 * Created with love by Lucian and @Pi on 30.12.2015.
 */
@Service
public interface OnlineTestsService {

    void createTest(OnlineTest onlineTest) throws ServiceException, NotFoundException;
}
