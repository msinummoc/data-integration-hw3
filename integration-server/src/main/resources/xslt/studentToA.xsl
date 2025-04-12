<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" encoding="GB2312" indent="yes"/>

    <xsl:template match="/Students">
        <Students>
            <xsl:apply-templates select="Student"/>
        </Students>
    </xsl:template>

    <xsl:template match="Student">
        <student>
            <ѧ��><xsl:value-of select="Id"/></ѧ��>
            <����><xsl:value-of select="Name"/></����>
            <�Ա�><xsl:value-of select="Gender"/></�Ա�>
            <xsl:if test="Department">
                <Ժϵ><xsl:value-of select="Department"/></Ժϵ>
            </xsl:if>
            <xsl:if test="Account">
                <�����˻�><xsl:value-of select="Account"/></�����˻�>
            </xsl:if>
        </student>
    </xsl:template>
</xsl:stylesheet>