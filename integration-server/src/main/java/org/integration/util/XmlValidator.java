package org.integration.util;

import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;

public class XmlValidator {
    /**
     * 验证 XML 是否符合指定的 XSD
     * @param xmlData XML 字符串
     * @param xsdPath XSD 文件路径（类路径下）
     */
    public static void validate(String xmlData, String xsdPath) throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaSource = new StreamSource(XmlValidator.class.getResourceAsStream(xsdPath));
        Schema schema = factory.newSchema(schemaSource);
        Validator validator = schema.newValidator();

        try (StringReader reader = new StringReader(xmlData)) {
            validator.validate(new StreamSource(reader));
        }
    }
}