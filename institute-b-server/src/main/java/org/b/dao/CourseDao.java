package org.b.dao;

import org.b.model.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    List<Course> getAllCourses() throws SQLException;
    int insertCourse(Course course) throws SQLException;
}