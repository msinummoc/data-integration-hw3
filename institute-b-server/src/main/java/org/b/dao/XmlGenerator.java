package org.b.dao;

import org.b.model.Student;
import org.b.model.Course;
import org.b.model.CourseSelection;
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
            Element studentElem = root.addElement("student");
            studentElem.addElement("学号").addText(s.getStudentId());
            studentElem.addElement("姓名").addText(s.getName());
            studentElem.addElement("性别").addText(s.getGender());
            studentElem.addElement("专业").addText(s.getMajor());
            studentElem.addElement("密码").addText(s.getPassword());
        }
        return doc;
    }


    public static Document generateCourseXml(List<Course> courses) {
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("GB2312");
        Element root = doc.addElement("Courses");
        for (Course c : courses) {
            Element courseElem = root.addElement("course");
            courseElem.addElement("编号").addText(c.getCourseId());
            courseElem.addElement("名称").addText(c.getName());
            courseElem.addElement("学分").addText(c.getCredit());
            courseElem.addElement("老师").addText(c.getTeacher());
            courseElem.addElement("地点").addText(c.getLocation());
            courseElem.addElement("共享").addText(c.getSharedFlag());
        }
        return doc;
    }

    public static Document generateCourseSelectionXml(List<CourseSelection> selections) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("Choices");
        for (CourseSelection cs : selections) {
            Element selectionElem = root.addElement("choice");
            selectionElem.addElement("课程编号").addText(cs.getCourseId());
            selectionElem.addElement("学号").addText(cs.getStudentId());
            selectionElem.addElement("得分").addText(cs.getGrade());
        }
        return doc;
    }
}