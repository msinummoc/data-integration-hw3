package org.b.dao;

import org.b.model.CourseSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.*;

@Repository
public class CourseSelectionDaoImpl implements CourseSelectionDao {
    private final DataSource dataSource;

    @Autowired
    public CourseSelectionDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public int insertCourseSelection(CourseSelection selection) throws SQLException {
        String sql = "INSERT INTO 选课 (课程编号, 学号, 得分) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, selection.getCourseId());
            stmt.setString(2, selection.getStudentId());
            stmt.setString(3, selection.getGrade());
            return stmt.executeUpdate();
        }
    }
}