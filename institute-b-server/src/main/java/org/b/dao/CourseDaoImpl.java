package org.b.dao;

import org.b.model.Course;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        String sql = "SELECT 编号, 名称, 学分, 老师, 地点, 共享 FROM 课程";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getString("编号"));
                course.setName(rs.getString("名称"));
                course.setCredit(rs.getString("学分"));
                course.setTeacher(rs.getString("老师"));
                course.setLocation(rs.getString("地点"));
                course.setSharedFlag(rs.getString("共享"));
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    @Transactional
    public int insertCourse(Course course) throws SQLException {
        String sql = "INSERT INTO 课程 (编号, 名称, 学分, 老师, 地点, 共享) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseId());
            stmt.setString(2, course.getName());
            stmt.setString(3, course.getCredit());
            stmt.setString(4, course.getTeacher());
            stmt.setString(5, course.getLocation());
            stmt.setString(6, course.getSharedFlag());
            return stmt.executeUpdate();
        }
    }

    public String generateCoursesXml() throws Exception {
        List<Course> courses = getAllCourses();
        Document doc = XmlGenerator.generateCourseXml(courses);
        return doc.asXML();
    }
}