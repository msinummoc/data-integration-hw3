package org.integration.util;

import org.springframework.core.io.ClassPathResource;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class XsltTransformer {
    /**
     * 将XML按目标院系的XSLT转换
     * @param xmlData 输入XML
     * @param xsltPath XSLT文件路径（如 studentToB.xsl）
     */
    public static String transform(String xmlData, String xsltPath) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslt = new StreamSource(new ClassPathResource(xsltPath).getInputStream());
        Transformer transformer = factory.newTransformer(xslt);

        StringWriter writer = new StringWriter();
        transformer.transform(
                new StreamSource(new StringReader(xmlData)),
                new StreamResult(writer)
        );
        return writer.toString();
    }
}