package com.ruoyi.detect.controller;


import com.ruoyi.detect.service.AlgorithmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;

    @PostMapping("/add")
    public String add(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) throws IOException {
        String result = algorithmService.runPythonAlgorithm(num1, num2);
        model.addAttribute("result", result);
        return "index";
    }
    @PostMapping("/hello")
    public Map<String, String> hello(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name);
        return response;
    }

    @GetMapping("/plot")
    public Map<String, String> getPlot() throws IOException, InterruptedException {
        // 调用Python脚本生成折线图
        Process process = Runtime.getRuntime().exec(new String[] { "python", "plot.py" });
        process.waitFor();

        // 读取Python脚本输出的Base64编码的图像字符串
        String imgStr = StreamUtils.copyToString(process.getInputStream(), StandardCharsets.UTF_8);

        // 构造响应对象
        Map<String, String> response = new HashMap<>();
        response.put("image", "data:image/png;base64," + imgStr);
        return response;
    }
}
