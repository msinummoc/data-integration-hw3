// StudentDao.java
package org.a.dao;

import org.a.model.Student;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents() throws Exception;
}