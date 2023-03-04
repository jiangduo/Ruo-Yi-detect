package com.ruoyi.detect.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detect.mapper.DetectFileMapper;
import com.ruoyi.detect.domain.DetectFile;
import com.ruoyi.detect.service.IDetectFileService;

/**
 * 测试文件Service业务层处理
 * 
 * @author totoro
 * @date 2023-03-03
 */
@Service
public class DetectFileServiceImpl implements IDetectFileService 
{
    @Autowired
    private DetectFileMapper detectFileMapper;

    /**
     * 查询测试文件
     * 
     * @param fileId 测试文件主键
     * @return 测试文件
     */
    @Override
    public DetectFile selectDetectFileByFileId(Long fileId)
    {
        return detectFileMapper.selectDetectFileByFileId(fileId);
    }

    /**
     * 查询测试文件列表
     * 
     * @param detectFile 测试文件
     * @return 测试文件
     */
    @Override
    public List<DetectFile> selectDetectFileList(DetectFile detectFile)
    {
        return detectFileMapper.selectDetectFileList(detectFile);
    }

    /**
     * 新增测试文件
     * 
     * @param detectFile 测试文件
     * @return 结果
     */
    @Override
    public int insertDetectFile(DetectFile detectFile)
    {
        return detectFileMapper.insertDetectFile(detectFile);
    }

    /**
     * 修改测试文件
     * 
     * @param detectFile 测试文件
     * @return 结果
     */
    @Override
    public int updateDetectFile(DetectFile detectFile)
    {
        return detectFileMapper.updateDetectFile(detectFile);
    }

    /**
     * 批量删除测试文件
     * 
     * @param fileIds 需要删除的测试文件主键
     * @return 结果
     */
    @Override
    public int deleteDetectFileByFileIds(Long[] fileIds)
    {
        return detectFileMapper.deleteDetectFileByFileIds(fileIds);
    }

    /**
     * 删除测试文件信息
     * 
     * @param fileId 测试文件主键
     * @return 结果
     */
    @Override
    public int deleteDetectFileByFileId(Long fileId)
    {
        return detectFileMapper.deleteDetectFileByFileId(fileId);
    }
}
