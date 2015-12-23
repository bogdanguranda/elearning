package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.exceptions.DAOException;
import thecerealkillers.elearning.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CoursesDAOImpl implements CoursesDAO {

    private static final String GROUP = "GROUP_";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void add(Course course) throws DAOException {
        try {
            String sql = "insert into course values (:title, :about, :details, :owner, :associatedGroup);";
            Map<String, String> namedParameters = new HashMap<>();
            namedParameters.put("title", course.getTitle());
            namedParameters.put("about", course.getAbout());
            namedParameters.put("details", course.getDetails());
            namedParameters.put("owner", course.getOwner());
            namedParameters.put("associatedGroup", GROUP + course.getTitle());

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void remove(String title) throws DAOException {
        try {
            String sql = "delete from course where title = :title;";
            Map<String, String> namedParameters = Collections.singletonMap("title", title);

            namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void update(String title, Course updatedCourse) throws DAOException {
        throw new DAOException("NOT YET IMPLEMENTED");
    }

    @Override
    public Course get(String title) throws DAOException {
        try {
            String sql = "select * from course where title = :title;";
            Map<String, String> namedParameters = Collections.singletonMap("title", title);

            List<Course> courseList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Course>() {
                @Override
                public Course mapRow(ResultSet resultSet, int i) throws SQLException {
                    Course course = new Course();
                    course.setTitle(resultSet.getString("title"));
                    course.setAbout(resultSet.getString("about"));
                    course.setDetails(resultSet.getString("details"));
                    course.setOwner(resultSet.getString("owner"));

                    return course;
                }
            });
            if (courseList.size() == 0)
                throw new DAOException("Course " + title + " is not available.");
            return courseList.get(0);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public List<Course> getAll() throws DAOException {
        try {
            List<Course> courseList;
            String sql = "select * from course;";

            courseList = namedParameterJdbcTemplate.query(sql, new RowMapper<Course>() {
                @Override
                public Course mapRow(ResultSet resultSet, int i) throws SQLException {
                    Course course = new Course();
                    course.setTitle(resultSet.getString("title"));
                    course.setAbout(resultSet.getString("about"));
                    course.setDetails(resultSet.getString("details"));
                    course.setOwner(resultSet.getString("owner"));
                    course.setAssociatedGroup(resultSet.getString("associatedGroup"));
                    return course;
                }
            });
            return courseList;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public boolean isCourseExistent(Course course) throws DAOException {
        try {
            String sql = "select * from course where title = :title;";
            Map<String, String> namedParameters = Collections.singletonMap("title", course.getTitle());

            List<Course> courseList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Course>() {
                @Override
                public Course mapRow(ResultSet resultSet, int i) throws SQLException {
                    Course course = new Course();
                    course.setTitle(resultSet.getString("title"));
                    course.setAbout(resultSet.getString("about"));
                    course.setDetails(resultSet.getString("details"));
                    course.setOwner(resultSet.getString("owner"));

                    return course;
                }
            });
            if (courseList.size() > 0) {
                return true;
            }
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
        return false;
    }

    @Override
    public void enrollUser(String courseTitle, String username) throws DAOException {
        try {
            String groupName = GROUP + courseTitle;

            String sqlINSERT = "INSERT INTO `elearning_db`.`group_user` VALUE (:group, :username);";

            Map<String, String> namedParametersINSERT = new HashMap<>();

            namedParametersINSERT.put("group", groupName);
            namedParametersINSERT.put("username", username);

            namedParameterJdbcTemplate.update(sqlINSERT, namedParametersINSERT);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public boolean userIsEnrolled(String courseTitle, String username) throws DAOException {
        try {
            String group = GROUP + courseTitle;
            String sql = "SELECT * FROM elearning_db.group_user WHERE group_user.group = :group AND username = :username;";

            Map<String, String> namedParameters = new HashMap<>();

            namedParameters.put("group", group);
            namedParameters.put("username", username);

            List<String> groupList = namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("group");
                }
            });

            return groupList.size() != 0;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public void unEnrollUser(String title, String username) throws DAOException {
        try {
            String groupName = GROUP + title;

            String sqlDELETE = "DELETE FROM `elearning_db`.`group_user` WHERE group_user.group = :group AND username = :username;";

            Map<String, String> namedParametersINSERT = new HashMap<>();

            namedParametersINSERT.put("group", groupName);
            namedParametersINSERT.put("username", username);

            namedParameterJdbcTemplate.update(sqlDELETE, namedParametersINSERT);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public List<String> getEnrolled(String title) throws DAOException {
        try {
            String groupName = GROUP + title;

            List<String> users;
            String sqlSELECT = "SELECT username FROM group_user WHERE group_user.group = :group;";
            Map<String, String> namedParameterss = Collections.singletonMap("group", groupName);

            users = namedParameterJdbcTemplate.query(sqlSELECT, namedParameterss, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("username");
                }
            });

            return users;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
