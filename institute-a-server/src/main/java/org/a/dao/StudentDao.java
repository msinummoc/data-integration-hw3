// StudentDao.java
package org.a.dao;

import org.a.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents() throws SQLException;
    int insertStudent(Student student) throws SQLException;
}