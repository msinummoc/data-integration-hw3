package org.integration.service;

import org.integration.util.XsltTransformer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;

import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 验证XML是否符合指定的XSD
     *
     * @param xmlData XML字符串
     * @param xsdPath XSD文件路径（类路径下）
     */
    public void validateXml(String xmlData, String xsdPath) throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaSource = new StreamSource(new ClassPathResource(xsdPath).getInputStream());
        Schema schema = factory.newSchema(schemaSource);
        Validator validator = schema.newValidator();

        try (StringReader reader = new StringReader(xmlData)) {
            validator.validate(new StreamSource(reader));
        }
    }

    /**
     * 处理学生数据（如转发给其他院系）
     *
     * @param xmlData 已验证的XML数据
     */
    public void processStudentData(String xmlData) {
        // 示例：转发给院系B
        String targetInstitute = "B";
        String transformedXml = transformXmlForInstitute(xmlData, targetInstitute);
        sendToInstituteServer(targetInstitute, transformedXml);
    }

    /**
     * 将XML转换为目标院系格式并发送
     * @param xmlData 统一格式的XML
     * @param targetInstitute 目标院系（如B）
     */
    private String transformXmlForInstitute(String xmlData, String targetInstitute) {
        try {
            String xsltPath = "xslt/studentTo" + targetInstitute + ".xsl";
            return XsltTransformer.transform(xmlData, xsltPath);
        } catch (Exception e) {
            throw new RuntimeException("XSLT转换失败: " + e.getMessage());
        }
    }

    /**
     * 发送数据到目标院系服务器
     * @param institute 院系标识（如B）
     * @param xmlData 转换后的XML
     */
    private void sendToInstituteServer(String institute, String xmlData) {
        String targetUrl = "http://institute-" + institute.toLowerCase() + "-server/api/students";
        restTemplate.postForEntity(targetUrl, xmlData, String.class);
    }
}