package org.integration.service;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private final StudentService studentService = new StudentService();

    @Test
    void testValidXmlAgainstSchema() {
        String validXml = "<?xml version='1.0' encoding='GB2312'?><Students><Student><Id>2023001</Id><Name>张三</Name><Gender>男</Gender></Student></Students>";
        assertDoesNotThrow(() -> studentService.validateXml(validXml, "schemas/formatStudent.xsd"));
    }

    @Test
    void testInvalidXmlMissingId() {
        String invalidXml = "<?xml version='1.0' encoding='GB2312'?><Students><Student><Name>张三</Name><Gender>男</Gender></Student></Students>";
        assertThrows(SAXException.class, () -> studentService.validateXml(invalidXml, "schemas/formatStudent.xsd"));
    }
}