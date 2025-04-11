package org.a.dao;

import org.a.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {
    private final DataSource dataSource;

    @Autowired
    public CourseDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT 课程编号, 课程名称, 学分, 授课老师, 授课地点, 共享 FROM 课程表";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getString("课程编号"));
                course.setCourseName(rs.getString("课程名称"));
                course.setCredit(rs.getString("学分"));
                course.setTeacher(rs.getString("授课老师"));
                course.setLocation(rs.getString("授课地点"));
                course.setSharedFlag(rs.getString("共享"));
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public int insertCourse(Course course) throws SQLException {
        String sql = "INSERT INTO 课程表 (课程编号, 课程名称, 学分, 授课老师, 授课地点, 共享) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getCredit());
            stmt.setString(4, course.getTeacher());
            stmt.setString(5, course.getLocation());
            stmt.setString(6, course.getSharedFlag());

            return stmt.executeUpdate();
        }
    }
}