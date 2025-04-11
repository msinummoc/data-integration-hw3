package org.a.model;

public class Student {
    private String studentId;
    private String name;
    private String gender;
    private String department;
    private String associatedAccount;

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getAssociatedAccount() { return associatedAccount; }
    public void setAssociatedAccount(String associatedAccount) { this.associatedAccount = associatedAccount; }
}