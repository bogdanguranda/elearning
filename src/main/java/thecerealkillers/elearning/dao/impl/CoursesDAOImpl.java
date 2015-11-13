package thecerealkillers.elearning.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import thecerealkillers.elearning.dao.CoursesDAO;
import thecerealkillers.elearning.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CoursesDAOImpl implements CoursesDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void add(Course course) {
        String sql = "insert into course values (:title, :about, :details, :owner);";
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("title", course.getTitle());
        namedParameters.put("about", course.getAbout());
        namedParameters.put("details", course.getDetails());
        namedParameters.put("owner", course.getOwner());

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public void remove(String title) {
        String sql = "delete from course where title = :title;";
        Map<String, String> namedParameters = Collections.singletonMap("title", title);

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public void update(String title, Course updatedCourse) {

    }

    @Override
    public Course get(String title) {
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

        return courseList.get(0);
    }

    @Override
    public List<Course> getAll() {
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

                return course;
            }
        });

        return courseList;
    }
}
