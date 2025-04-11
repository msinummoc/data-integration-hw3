package org.a.api;

// src/main/java/com/edu/a/api/TestController.java

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

    // 测试接口：查询所有学生
    @GetMapping("/students")
    public List<Student> getAllStudents() throws Exception {
        return studentDao.getAllStudents();
    }

    // 测试接口：插入学生
    @GetMapping("/insert")
    public String insertTestStudent() {
        Student student = new Student();
        student.setStudentId("A-1001");
        student.setName("张三");
        student.setGender("男");
        int rows = studentDao.insertStudent(student);
        return "插入成功，影响行数：" + rows;
    }
}
