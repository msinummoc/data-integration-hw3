package org.a.dao;

import org.a.model.Student;
import org.dom4j.Document;
import org.testng.annotations.Test;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.*;
import static org.junit.jupiter.api.Assertions.*;

class XmlGeneratorTest {
    // 测试数据准备
    private List<Student> createTestStudents() {
        Student s1 = new Student();
        s1.setStudentId("2023001");
        s1.setName("张三");
        s1.setGender("男");
        s1.setDepartment("计算机学院");
        s1.setAssociatedAccount("zhangsan");
        return Arrays.asList(s1);
    }

    // 测试XML生成是否符合XSD规范
    @Test
    void testGeneratedXmlAgainstSchema() throws Exception {
        // 1. 生成XML文档
        List<Student> students = createTestStudents();
        Document doc = XmlGenerator.generateStudentXml(students);

        // 2. 将XML写入临时文件（或直接使用内存内容）
        File xmlFile = new File("target/test-student.xml");
        doc.write(new FileWriter(xmlFile, UTF_16));  //不知道可不可以

        // 3. 加载XSD Schema
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaSource = new StreamSource(getClass().getResourceAsStream("/schemas/studentA.xsd"));
        Schema schema = factory.newSchema(schemaSource);

        // 4. 创建Validator并验证XML
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xmlFile)); // 若无异常则验证通过
    }

    // 测试XML内容是否正确
    @Test
    void testXmlContent() {
        List<Student> students = createTestStudents();
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