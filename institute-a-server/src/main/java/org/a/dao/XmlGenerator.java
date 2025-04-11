
package org.a.dao;

import org.a.model.Student;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.List;

public class XmlGenerator {
    public static Document generateStudentXml(List<Student> students) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("Students");

        for (Student s : students) {
            Element studentElem = root.addElement("student");
            studentElem.addElement("学号").addText(s.getStudentId());
            studentElem.addElement("姓名").addText(s.getName());
            studentElem.addElement("性别").addText(s.getGender());
            studentElem.addElement("院系").addText(s.getDepartment());
            studentElem.addElement("关联账户").addText(s.getAssociatedAccount());
        }
        return doc;
    }
}