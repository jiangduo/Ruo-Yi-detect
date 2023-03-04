package com.ruoyi.detect.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.detect.domain.DetectFile;
import com.ruoyi.detect.service.IDetectFileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试文件Controller
 * 
 * @author totoro
 * @date 2023-03-03
 */
@RestController
@RequestMapping("/detect/file")
public class DetectFileController extends BaseController
{
    @Autowired
    private IDetectFileService detectFileService;

    /**
     * 查询测试文件列表
     */
    @PreAuthorize("@ss.hasPermi('detect:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(DetectFile detectFile)
    {
        startPage();
        List<DetectFile> list = detectFileService.selectDetectFileList(detectFile);
        return getDataTable(list);
    }

    /**
     * 导出测试文件列表
     */
    @PreAuthorize("@ss.hasPermi('detect:file:export')")
    @Log(title = "测试文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DetectFile detectFile)
    {
        List<DetectFile> list = detectFileService.selectDetectFileList(detectFile);
        ExcelUtil<DetectFile> util = new ExcelUtil<DetectFile>(DetectFile.class);
        util.exportExcel(response, list, "测试文件数据");
    }

    /**
     * 获取测试文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('detect:file:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(detectFileService.selectDetectFileByFileId(fileId));
    }

    /**
     * 新增测试文件
     */
    @PreAuthorize("@ss.hasPermi('detect:file:add')")
    @Log(title = "测试文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DetectFile detectFile)
    {
        return toAjax(detectFileService.insertDetectFile(detectFile));
    }

    /**
     * 修改测试文件
     */
    @PreAuthorize("@ss.hasPermi('detect:file:edit')")
    @Log(title = "测试文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DetectFile detectFile)
    {
        return toAjax(detectFileService.updateDetectFile(detectFile));
    }

    /**
     * 删除测试文件
     */
    @PreAuthorize("@ss.hasPermi('detect:file:remove')")
    @Log(title = "测试文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(detectFileService.deleteDetectFileByFileIds(fileIds));
    }
}
