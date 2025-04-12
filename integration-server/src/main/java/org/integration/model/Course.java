package org.integration.model;

/**
 * 集成服务器统一的课程模型类
 */
public class Course {
    private String id;          // 课程编号（统一格式，长度9）
    private String name;        // 课程名称（最大长度40）
    private int time;           // 课时（单位：小时）
    private int credit;         // 学分（0~5）
    private String teacher;     // 授课教师（最大长度20）
    private String location;    // 授课地点（最大长度40）
    private String sharedFlag;  // 共享标记（"Y" 或 "N"）

    // Getters 和 Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTime() { return time; }
    public void setTime(int time) { this.time = time; }

    public int getCredit() { return credit; }
    public void setCredit(int credit) { this.credit = credit; }

    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSharedFlag() { return sharedFlag; }
    public void setSharedFlag(String sharedFlag) { this.sharedFlag = sharedFlag; }
}