package org.b.model;

public class Course {
    private String courseId;
    private String name;
    private String credit;
    private String teacher;
    private String location;
    private String sharedFlag;

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCredit() { return credit; }
    public void setCredit(String credit) { this.credit = credit; }
    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSharedFlag() { return sharedFlag; }
    public void setSharedFlag(String sharedFlag) { this.sharedFlag = sharedFlag; }
}
