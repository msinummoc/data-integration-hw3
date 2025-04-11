package org.a.dao;

import org.a.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentDaoTest {
    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private StudentDaoImpl studentDao;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        studentDao = new StudentDaoImpl(dataSource);
    }

    @Test
    void testGetAllStudents() throws SQLException {
        // Mock数据库返回数据
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("学号")).thenReturn("2023001");
        when(resultSet.getString("姓名")).thenReturn("张三");
        when(resultSet.getString("性别")).thenReturn("男");
        when(resultSet.getString("院系")).thenReturn("计算机学院");
        when(resultSet.getString("关联账户")).thenReturn("zhangsan");

        List<Student> students = studentDao.getAllStudents();
        assertEquals(1, students.size());
        Student s = students.get(0);
        assertEquals("2023001", s.getStudentId());
        assertEquals("张三", s.getName());
    }

    @Test
    void testInsertStudent() throws SQLException {
        Student student = new Student();
        student.setStudentId("A-1001");
        student.setName("测试学生");
        student.setGender("男");
        student.setDepartment("测试学院");
        student.setAssociatedAccount("test");

        when(preparedStatement.executeUpdate()).thenReturn(1);
        int rows = studentDao.insertStudent(student);
        assertEquals(1, rows);
    }

    @Test
    void testEmptyResult() throws SQLException {
        when(resultSet.next()).thenReturn(false);
        List<Student> students = studentDao.getAllStudents();
        assertTrue(students.isEmpty());
    }
}