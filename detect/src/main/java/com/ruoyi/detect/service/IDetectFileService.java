package com.ruoyi.detect.service;

import java.util.List;
import com.ruoyi.detect.domain.DetectFile;

/**
 * 测试文件Service接口
 * 
 * @author totoro
 * @date 2023-03-03
 */
public interface IDetectFileService 
{
    /**
     * 查询测试文件
     * 
     * @param fileId 测试文件主键
     * @return 测试文件
     */
    public DetectFile selectDetectFileByFileId(Long fileId);

    /**
     * 查询测试文件列表
     * 
     * @param detectFile 测试文件
     * @return 测试文件集合
     */
    public List<DetectFile> selectDetectFileList(DetectFile detectFile);

    /**
     * 新增测试文件
     * 
     * @param detectFile 测试文件
     * @return 结果
     */
    public int insertDetectFile(DetectFile detectFile);

    /**
     * 修改测试文件
     * 
     * @param detectFile 测试文件
     * @return 结果
     */
    public int updateDetectFile(DetectFile detectFile);

    /**
     * 批量删除测试文件
     * 
     * @param fileIds 需要删除的测试文件主键集合
     * @return 结果
     */
    public int deleteDetectFileByFileIds(Long[] fileIds);

    /**
     * 删除测试文件信息
     * 
     * @param fileId 测试文件主键
     * @return 结果
     */
    public int deleteDetectFileByFileId(Long fileId);
}
