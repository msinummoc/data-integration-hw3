package org.a.dao;

import org.a.model.Student;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
        String sql = "SELECT 学号, 姓名, 性别, 院系, 关联账户 FROM 学生表";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("学号"));
                student.setName(rs.getString("姓名"));
                student.setGender(rs.getString("性别"));
                student.setDepartment(rs.getString("院系"));
                student.setAssociatedAccount(rs.getString("关联账户"));
                students.add(student);
            }
        }
        return students;
    }

    public String generateStudentsXml() throws Exception {
        List<Student> students = getAllStudents();
        Document doc = XmlGenerator.generateStudentXml(students);
        return doc.asXML();
    }
}