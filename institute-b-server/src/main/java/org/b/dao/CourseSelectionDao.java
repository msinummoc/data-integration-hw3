package org.b.dao;

import org.b.model.CourseSelection;
import java.sql.SQLException;

public interface CourseSelectionDao {
    int insertCourseSelection(CourseSelection selection) throws SQLException;
}