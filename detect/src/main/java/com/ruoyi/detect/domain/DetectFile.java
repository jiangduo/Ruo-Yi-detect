package com.ruoyi.detect.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试文件对象 detect_file
 * 
 * @author totoro
 * @date 2023-03-03
 */
public class DetectFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long fileId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fileName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fileContext;

    /** $column.columnComment */
    private Date fileUpdate;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileContext(String fileContext) 
    {
        this.fileContext = fileContext;
    }

    public String getFileContext() 
    {
        return fileContext;
    }
    public void setFileUpdate(Date fileUpdate) 
    {
        this.fileUpdate = fileUpdate;
    }

    public Date getFileUpdate() 
    {
        return fileUpdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("fileName", getFileName())
            .append("fileContext", getFileContext())
            .append("fileUpdate", getFileUpdate())
            .toString();
    }
}
