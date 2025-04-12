package org.integration.model;

/**
 * 集成服务器的统一学生模型
 * 整合各院系学生表字段差异
 */
public class Student {
    private String id;          // 学号（统一格式，如最大长度12）
    private String name;        // 姓名（最大长度40）
    private String gender;      // 性别（枚举：男/女）
    private String major;       // 专业（兼容院系B的“专业”）
    private String account;     // 关联账户（可选字段）

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
}