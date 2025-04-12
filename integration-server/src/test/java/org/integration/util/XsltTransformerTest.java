package org.integration.util;

import org.junit.jupiter.api.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

import static org.junit.jupiter.api.Assertions.*;

class XsltTransformerTest {
    //incorrect test
    @Test
    void testTransformToInstituteA1() throws Exception {
        // 输入XML（集成服务器统一格式）
        String inputXml = "<?xml version='1.0' encoding='GB2312'?>" +
                "<Students>" +
                "   <Student>" +
                "       <Id>2023001</Id>" +
                "       <Name>张三</Name>" +
                "       <Gender>男</Gender>" +
                "       <Department>计算机学院</Department>" +
                "       <Account>zhangsan</Account>" +
                "   </Student>" +
                "</Students>";

        // 预期输出的XML片段（院系A格式）
        String expectedOutput = "<学号>2023001</学号>" +
                "<姓名>张三</姓名>" +
                "<性别>男</性别>" +
                "<院系>计算机学院</院系>" +
                "<关联账户>zhangsan</关联账户>";

        // 调用转换方法，使用 studentToA.xsl
        String transformedXml = XsltTransformer.transform(inputXml, "xslt/studentToA.xsl");

        // 验证转换结果
        assertTrue(
                transformedXml.contains(expectedOutput),
                "转换结果不符合院系A的格式要求。实际输出：\n" + transformedXml
        );
    }

    @Test
    void testTransformToInstituteA() throws Exception {
        // 输入 XML（集成服务器统一格式）
        String inputXml = "<?xml version='1.0' encoding='GB2312'?>" +
                "<Students>" +
                "   <Student>" +
                "       <Id>2023001</Id>" +
                "       <Name>张三</Name>" +
                "       <Gender>男</Gender>" +
                "       <Department>计算机学院</Department>" +
                "       <Account>zhangsan</Account>" +
                "   </Student>" +
                "</Students>";

        // 预期输出的 XML（严格匹配修正后的格式）
        String expectedOutput = "<?xml version=\"1.0\" encoding=\"GB2312\"?>" +
                "<Students>" +
                "<student>" +
                "<学号>2023001</学号>" +
                "<姓名>张三</姓名>" +
                "<性别>男</性别>" +
                "<院系>计算机学院</院系>" +
                "<关联账户>zhangsan</关联账户>" +
                "</student>" +
                "</Students>";

        // 调用转换方法，使用 studentToA.xsl
        String transformedXml = XsltTransformer.transform(inputXml, "xslt/studentToA.xsl");

        // 使用 XMLUnit 进行精确对比（忽略空格和换行）
        Diff diff = DiffBuilder.compare(expectedOutput)
                .withTest(transformedXml)
                .ignoreWhitespace()
                .checkForSimilar()
                .build();

        assertFalse(diff.hasDifferences(), "转换结果不符合院系A的格式要求。差异：\n" + diff.toString());
    }

    @Test
    void testTransformToInstituteB() throws Exception {
        String inputXml = "<?xml version='1.0' encoding='GB2312'?><Students><Student><Id>2023001</Id><Name>张三</Name><Gender>男</Gender></Student></Students>";
        String expectedOutput = "<学号>2023001</学号><名臣>张三</名臣><性别>男</性别>";

        String transformedXml = XsltTransformer.transform(inputXml, "xslt/studentToB.xsl");
        assertTrue(transformedXml.contains(expectedOutput), "转换结果不符合预期");
    }
}