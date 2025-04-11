package org.a.model;

public class Course {
    private String courseId;       // 课程编号
    private String courseName;     // 课程名称
    private String credit;         // 学分
    private String teacher;        // 授课老师
    private String location;       // 授课地点
    private String sharedFlag;     // 共享标记

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCredit() { return credit; }
    public void setCredit(String credit) { this.credit = credit; }

    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSharedFlag() { return sharedFlag; }
    public void setSharedFlag(String sharedFlag) { this.sharedFlag = sharedFlag; }
}