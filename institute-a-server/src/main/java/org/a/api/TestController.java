package org.a.api;

import org.a.dao.StudentDao;
import org.a.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.List;

@RestController
public class TestController {
    private final StudentDao studentDao;

    public TestController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() throws SQLException {
        return studentDao.getAllStudents();
    }

    @GetMapping("/insert")
    public String insertTestStudent() {
        Student student = new Student();
        student.setStudentId("199999999");
        student.setName("张三");
        student.setGender("男");
        student.setDepartment("计算机学院");
        student.setAssociatedAccount("zhangsan"); // 确保设置关联账户字段

        try {
            int rows = studentDao.insertStudent(student);
            return "插入成功，影响行数：" + rows;
        } catch (SQLException e) {
            return "插入失败：" + e.getMessage();
        }
    }
}