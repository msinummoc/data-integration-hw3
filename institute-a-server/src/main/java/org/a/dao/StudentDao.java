package org.a.dao;


import org.a.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 查询所有学生
    public List<Student> getAllStudents() {
        String sql = "SELECT 学号, 姓名, 性别 FROM 学生表";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student();
            student.setStudentId(rs.getString("学号"));
            student.setName(rs.getString("姓名"));
            student.setGender(rs.getString("性别"));
            return student;
        });
    }

    // 插入学生记录（示例）
    public int insertStudent(Student student) {
        String sql = "INSERT INTO 学生表 (学号, 姓名, 性别) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                student.getStudentId(),
                student.getName(),
                student.getGender()
        );
    }
}
