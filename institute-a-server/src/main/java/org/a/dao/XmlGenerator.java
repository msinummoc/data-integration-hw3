
package org.a.dao;

import org.a.model.Student;
import org.a.model.Course;
import org.a.model.CourseSelection;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.List;

public class XmlGenerator {
    public static Document generateStudentXml(List<Student> students) {
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("GB2312");
        Element root = doc.addElement("Students");

        for (Student s : students) {
            Element studentElem = root.addElement("Student");
            studentElem.addElement("Id").addText(s.getStudentId());
            studentElem.addElement("Name").addText(s.getName());
            studentElem.addElement("Gender").addText(s.getGender());
            studentElem.addElement("Department").addText(s.getDepartment());
            studentElem.addElement("Account").addText(s.getAssociatedAccount());
        }
        return doc;
    }

    // 生成课程XML
    public static Document generateCourseXml(List<Course> courses) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("Courses");

        for (Course c : courses) {
            Element courseElem = root.addElement("course");
            courseElem.addElement("课程编号").addText(c.getCourseId());
            courseElem.addElement("课程名称").addText(c.getCourseName());
            courseElem.addElement("学分").addText(c.getCredit());
            courseElem.addElement("授课老师").addText(c.getTeacher());
            courseElem.addElement("授课地点").addText(c.getLocation());
            courseElem.addElement("共享").addText(c.getSharedFlag());
        }
        return doc;
    }

    // 生成选课XML
    public static Document generateCourseSelectionXml(List<CourseSelection> selections) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("Choices");

        for (CourseSelection cs : selections) {
            Element selectionElem = root.addElement("choice");
            selectionElem.addElement("课程编号").addText(cs.getCourseId());
            selectionElem.addElement("学生编号").addText(cs.getStudentId());
            selectionElem.addElement("成绩").addText(cs.getGrade());
        }
        return doc;
    }


}