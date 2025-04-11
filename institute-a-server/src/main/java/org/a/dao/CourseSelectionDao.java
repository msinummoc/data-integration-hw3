package org.a.dao;

import org.a.model.CourseSelection;
import java.sql.SQLException;

public interface CourseSelectionDao {
    int insertCourseSelection(CourseSelection selection) throws SQLException;
}