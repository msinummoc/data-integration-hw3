package org.b.dao;

import org.b.model.Student;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    private final DataSource dataSource;

    @Autowired
    public StudentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT 学号, 姓名, 性别, 专业, 密码 FROM 学生";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("学号"));
                student.setName(rs.getString("姓名"));
                student.setGender(rs.getString("性别"));
                student.setMajor(rs.getString("专业"));
                student.setPassword(rs.getString("密码"));
                students.add(student);
            }
        }
        return students;
    }

    @Override
    @Transactional
    public int insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO 学生 (学号, 姓名, 性别, 专业, 密码) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getGender());
            stmt.setString(4, student.getMajor());
            stmt.setString(5, student.getPassword());
            return stmt.executeUpdate();
        }
    }

    public String generateStudentsXml() throws Exception {
        List<Student> students = getAllStudents();
        Document doc = XmlGenerator.generateStudentXml(students);
        return doc.asXML();
    }
}