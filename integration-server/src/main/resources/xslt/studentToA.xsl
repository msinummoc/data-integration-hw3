<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="xml" encoding="GB2312" indent="yes"/>

    <!-- 根模板 -->
    <xsl:template match="/Students">
        <Students>
            <xsl:apply-templates select="Student"/>
        </Students>
    </xsl:template>

    <!-- 学生记录转换 -->
    <xsl:template match="Student">
        <student>
            <!-- 必填字段映射 -->
            <学号><xsl:value-of select="Id"/></学号>
            <姓名><xsl:value-of select="Name"/></姓名>
            <性别><xsl:value-of select="Gender"/></性别>

            <!-- 可选字段映射（处理空值） -->
            <xsl:if test="Department">
                <院系><xsl:value-of select="Department"/></院系>
            </xsl:if>
            <xsl:if test="Account">
                <关联账户><xsl:value-of select="Account"/></关联账户>
            </xsl:if>
        </student>
    </xsl:template>

</xsl:stylesheet>