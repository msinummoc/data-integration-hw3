package org.a.model;

public class CourseSelection {
    private String courseId;    // 课程编号
    private String studentId;   // 学生编号
    private String grade;       // 成绩

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}