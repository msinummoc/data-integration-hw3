package org.a.dao;

import org.a.model.Course;
import org.a.model.CourseSelection;
import org.a.model.Student;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XmlGeneratorTest {

    // 测试学生XML生成是否符合XSD规范
    @Test
    void testStudentXmlAgainstSchema() throws Exception {
        // 1. 生成测试数据
        Student student = new Student();
        student.setStudentId("2023001");
        student.setName("张三");
        student.setGender("男");
        student.setDepartment("计算机学院");
        student.setAssociatedAccount("zhangsan");
        List<Student> students = Collections.singletonList(student);

        // 2. 生成XML文档
        Document doc = XmlGenerator.generateStudentXml(students);

        // 3. 验证XML是否符合studentA.xsd
        validateXmlAgainstSchema(doc, "/schemas/studentA.xsd");
    }

    // 测试课程XML生成是否符合XSD规范
    @Test
    void testCourseXmlAgainstSchema() throws Exception {
        // 1. 生成测试数据
        Course course = new Course();
        course.setCourseId("C-101");
        course.setCourseName("数据结构");
        course.setCredit("3");
        course.setTeacher("王老师");
        course.setLocation("A栋301");
        course.setSharedFlag("1");
        List<Course> courses = Collections.singletonList(course);

        // 2. 生成XML文档
        Document doc = XmlGenerator.generateCourseXml(courses);

        // 3. 验证XML是否符合classA.xsd
        validateXmlAgainstSchema(doc, "/schemas/classA.xsd");
    }

    // 测试选课XML生成是否符合XSD规范
    @Test
    void testCourseSelectionXmlAgainstSchema() throws Exception {
        // 1. 生成测试数据
        CourseSelection selection = new CourseSelection();
        selection.setCourseId("C-101");
        selection.setStudentId("2023001");
        selection.setGrade("85");
        List<CourseSelection> selections = Collections.singletonList(selection);

        // 2. 生成XML文档
        Document doc = XmlGenerator.generateCourseSelectionXml(selections);

        // 3. 验证XML是否符合choiceA.xsd
        validateXmlAgainstSchema(doc, "/schemas/choiceA.xsd");
    }

    // 通用XML Schema验证方法
    // 修改后的 validateXmlAgainstSchema 方法
    private void validateXmlAgainstSchema(Document doc, String schemaPath) throws Exception {
        File xmlFile = File.createTempFile("test-", ".xml");

        // 1. 写入 XML 文件（强制 GB2312 编码）
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(xmlFile), "GB2312")) {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("GB2312");
            XMLWriter xmlWriter = new XMLWriter(writer, format);
            xmlWriter.write(doc);
        }

        // 2. 加载 Schema
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaSource = new StreamSource(getClass().getResourceAsStream(schemaPath));
        Schema schema = factory.newSchema(schemaSource);

        // 3. 验证时指定编码
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(xmlFile), "GB2312")) {
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(reader));
        }
    }

    // 测试XML内容是否正确（学生示例）
    @Test
    void testStudentXmlContent() {
        Student student = new Student();
        student.setStudentId("2023001");
        student.setName("张三");
        student.setGender("男");
        student.setDepartment("计算机学院");
        student.setAssociatedAccount("zhangsan");
        List<Student> students = Collections.singletonList(student);

        Document doc = XmlGenerator.generateStudentXml(students);
        String xml = doc.asXML();

        assertAll(
                () -> assertTrue(xml.contains("<学号>2023001</学号>"), "学号字段缺失"),
                () -> assertTrue(xml.contains("<姓名>张三</姓名>"), "姓名字段缺失"),
                () -> assertTrue(xml.contains("<性别>男</性别>"), "性别字段缺失"),
                () -> assertTrue(xml.contains("<院系>计算机学院</院系>"), "院系字段缺失"),
                () -> assertTrue(xml.contains("<关联账户>zhangsan</关联账户>"), "关联账户字段缺失")
        );
    }
}