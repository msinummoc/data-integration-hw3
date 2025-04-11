package org.a.dao;

import org.a.model.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    List<Course> getAllCourses() throws SQLException;
    int insertCourse(Course course) throws SQLException;
}