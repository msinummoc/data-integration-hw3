package org.integration.model;

/**
 * 集成服务器统一的选课模型类
 */
public class CourseSelection {
    private String sid;     // 学生编号（统一格式，最大长度12）
    private String cid;     // 课程编号（统一格式，长度9）
    private int score;      // 成绩（0~100）

    // Getters 和 Setters
    public String getSid() { return sid; }
    public void setSid(String sid) { this.sid = sid; }

    public String getCid() { return cid; }
    public void setCid(String cid) { this.cid = cid; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}